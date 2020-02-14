/**
 * Created by wyb on 2016/11/30.
 *
 */
function spatialQuery(map) {
    //定义坐标存储变量
    var drawGeometry;
    //绘制图形添加到map中
    var drawLayerSource = new ol.source.Vector();
    var drawLayer = new ol.layer.Vector({
        source: drawLayerSource,
        name: '绘制图形',
        style: new ol.style.Style({
            fill: new ol.style.Style({
                color: 'rgba(255, 255, 255, 0.3)'
            }),
            stroke: new ol.style.Stroke({
                color: '#CC0F13',
                width: 2
            }),
            image: new ol.style.Circle({
                radius: 7,
                fill: new ol.style.Fill({
                    color: '#ffcc33'
                })
            })
        })
    });
    map.addLayer(drawLayer);
    var button = $('#pan').button('toggle');
    var interaction;
    $('div.btn-group button').on('click', function (event) {
        var id = event.target.id;
        button.button('toggle');
        button = $('#' + id).button('toggle');
        //清除上一个动作
        map.removeInteraction(interaction);
        //清除前面interaction
        drawLayerSource.clear();
        switch (event.target.id) {
            case 'rectangle':
                interaction = new ol.interaction.Draw({
                    source: drawLayerSource,
                    type: 'LineString',
                    maxPoints: 2,
                    geometryFunction: function (coordinates, geometry) {
                        if (!geometry) {
                            geometry = new ol.geom.Polygon(null);
                        }
                        var start = coordinates[0];
                        var end = coordinates[1];
                        geometry.setCoordinates([
                            [start, [start[0], end[1]], end, [end[0], start[1]], start]
                        ]);
                        return geometry;
                    }
                });
                interaction.on('drawstart', function () {
                    var beforeQueryLayer = getLayerByName(map, '兴趣点');
                    beforeQueryLayer.getSource().clear();
                });
                map.addInteraction(interaction);
                interaction.on('drawend', function (evt) {
                    drawGeometry = evt.feature.getGeometry();
                    query(map, 0, drawGeometry, drawLayer);
                });
                break;
            case 'polygon':
                interaction = new ol.interaction.Draw({
                    source: drawLayerSource,
                    type: 'Polygon',
                    minPoints: 3,
                    geometryFunction: function (coordinates, geometry) {
                        if (!geometry) {
                            geometry = new ol.geom.Polygon(null);
                        }
                        geometry.setCoordinates(coordinates);
                        return geometry;
                    }
                });
                interaction.on('drawstart', function () {
                    var beforeQueryLayer = getLayerByName(map, '兴趣点');
                    beforeQueryLayer.getSource().clear();

                });
                map.addInteraction(interaction);
                interaction.on('drawend', function (evt) {
                    drawGeometry = evt.feature.getGeometry();
                    query(map, 1, drawGeometry, drawLayer);
                });
                break;
            case 'circle':
                interaction = new ol.interaction.Draw({
                    source: drawLayerSource,
                    type: 'Circle'
                });
                interaction.on('drawstart', function () {
                    var beforeQueryLayer = getLayerByName(map, '兴趣点');
                    beforeQueryLayer.getSource().clear();
                });
                map.addInteraction(interaction);
                interaction.on('drawend', function (evt) {
                    drawGeometry = evt.feature.getGeometry();
                    query(map, 2, drawGeometry, drawLayer);
                });
                break;
            case 'clear':
                var beforeQueryLayer = getLayerByName(map, '兴趣点');
                beforeQueryLayer.getSource().clear();
                break;
            case 'pan':
                break;


            case 'heatmap':
                var source = new ol.source.Vector({
                    weight: 'weight'
                });
                map.addLayer(new ol.layer.Heatmap({
                    source: source
                }));
                // map.on('pointerdrag', function (e) {
                //     console.log(e);
                // });
                map.on('moveend', function () {
                    fetch("http://192.168.29.136:9003/api-spatialquery/api/cluster").then(response => response.json()).then(data =>
                    {
                        source.clear();
                        var points = data.data;
                        source.addFeatures((new ol.format.GeoJSON()).readFeatures(points, {
                            dataProjection: 'EPSG:4326',
                            featureProjection: 'EPSG:3857'
                        }))
                    })
                });

                // heatMapLayer(map);
                break;

        }
    });
}

function query(map, typeId, drawGeometry, drawLayer) {
    var queryLayerSource = new ol.source.Vector();
    var queryLayer = new ol.layer.Vector({
        source: queryLayerSource,
        projection: 'EPSG:3857',
        name: '兴趣点',
        style: new ol.style.Style({
            image: new ol.style.Circle({
                radius: 5,
                fill: new ol.style.Fill({
                    color: 'green'
                }),
                stroke: new ol.style.Stroke({
                    color: 'red',
                    width: 2
                })
            })
        })
    });
    map.addLayer(queryLayer);
    if (typeId === 2) {
        var wgs84Sphere = new ol.Sphere(6378137);
        var radius = wgs84Sphere.haversineDistance(drawGeometry.getFirstCoordinate(), drawGeometry.getLastCoordinate());
        var center = drawGeometry.transform('EPSG:3857', 'EPSG:4326').getCenter();
        $.ajax({
            type: 'POST',
            url: 'query',
            data: {radius: radius, center: center, type: typeId},
            dataType: 'json',
            traditional: true,
            success: function (data) {
                queryLayerSource.clear();
                queryLayerSource.addFeatures()


                showQueryData(map, data, drawLayer)
            }
        })
    } else {
        var wkt = new ol.format.WKT();
        if (typeId == 1) {
            var tempCoords = (drawGeometry.getCoordinates()[0]);
            tempCoords.push(drawGeometry.getFirstCoordinate());
            drawGeometry.setCoordinates(new Array(tempCoords));
        }
        var wktString = wkt.writeGeometry(drawGeometry.transform('EPSG:3857', 'EPSG:4326'));
        $.ajax({
            type: 'GET',
            url: 'query',
            data: {wkt: wktString, type: typeId},
            dataType: 'json',
            success: function (data) {
                queryLayerSource.clear();
                map.getView().fit(queryLayerSource.getExtent(), map.getSize());
                showQueryData(map, data, drawLayer)
            }
        });
    }

}

//获取所画图形的坐标信息
function queryPointCaptial(map, elementId, drawGeometry, drawLayer) {
    var wkt = new ol.format.WKT();

    alert(wkt.writeGeometry(drawGeometry));

    // var queryCoor = "LINESTRING(";
    // //矩形查询
    // if (elementId == 'rectangle') {
    // 	queryCoor += drawCoordinate[0][0] + ' ' + drawCoordinate[0][1] + ',' +drawCoordinate[1][0] + ' ' + drawCoordinate[0][1] + ',' + drawCoordinate[1][0] + ' ' + drawCoordinate[1][1] + ',' + drawCoordinate[0][0] + ' '+ drawCoordinate[1][1] + ',' + drawCoordinate[0][0] + ' ' + drawCoordinate[0][1];
    // }
    // //多边形
    // if (elementId == 'polygon') {
    //     var drawCoorLength = drawCoordinate[0].length;
    //     drawCoordinate = drawCoordinate[0];
    //     for (var i = 0; i < drawCoorLength - 1; i++) {
    //         queryCoor += drawCoordinate[i][0] + ' ' + drawCoordinate[i][1] + ',';
    //     }
    //     queryCoor += drawCoordinate[drawCoorLength - 1][0] + ' ' + drawCoordinate[drawCoorLength - 1][1];
    // }
    // queryCoor += ")";
    // $.ajax({
    //     type: 'POST',
    //     url: 'query.do',
    //     data: {queryCoordinates: queryCoor},
    //     dataType: 'json',
    //     success: function (data) {
    //     	showQueryData(map, data, drawLayer)
    //     }
    // });
}

//查询结果添加到图层
function showQueryData(map, data, drawLayer) {
    //查询数据样式
    var styleQuery = new ol.style.Style({
        image: new ol.style.Circle({
            radius: 5,
            fill: new ol.style.Fill({
                color: 'green'
            }),
            stroke: new ol.style.Stroke({color: 'red', width: 2})
        })
    });
    if (data.features != null) {
        var source = new ol.source.Vector({
            features: (new ol.format.GeoJSON).readFeatures(data)
        });
        //聚类
//    var clusterSource = new ol.source.Cluster({
//        distance: 20,
//        source: source
//    });
        var queryLayerName = '查询结果';
        var queryLayer = new ol.layer.Vector({
            source: source,
            projection: 'EPSG:3857',
            name: queryLayerName,
            style: styleQuery
        });
        var extent = source.getExtent();
        map.addLayer(queryLayer);
        map.removeLayer(drawLayer);
        //缩放至查询范围
        map.getView().fit(extent, map.getSize());
        //显示查询点的属性信息
        // showPopup(map, queryLayerName);
    }

}

//根据名称获取图层
function getLayerByName(map, layerName) {
    var foundLayer = null;
    var layersOnMap = map.getLayers();
    for (var i = 0; i < layersOnMap.getLength(); i++) {
        var layer = layersOnMap.item(i);
        if (layer.get('name') === layerName) {
            foundLayer = layer;
        }
    }
    return foundLayer;
}