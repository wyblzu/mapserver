/**
 * Created by wyb on 2017/9/6.
 *
 */
$(function () {
    var bounds = [73.4510046356223, 18.1632471876417, 134.976797646506, 53.5319431522236];
    var map = new ol.Map({
        controls: ol.control.defaults({
            attribution: false
        }),
        target: 'map',
        layers: [new ol.layer.Tile({
            source: new ol.source.XYZ({
                url: 'http://t0.tianditu.gov.cn/DataServer?T=vec_w&x={x}&y={y}&l={z}&tk=9fb37f7d888b84de41af517302a0b55c'
            })
        }), new ol.layer.Tile({
            source: new ol.source.XYZ({
                url: 'http://t0.tianditu.gov.cn/DataServer?T=cva_w&x={x}&y={y}&l={z}&tk=9fb37f7d888b84de41af517302a0b55c'
            })
        })],
        view: new ol.View({
            projection: 'EPSG:3857',
            center: new ol.proj.fromLonLat([116.3, 39.9]),
            minZoom: 2,
            zoom: 15,
            maxZoom: 18
        })
    });
    map.getView().fit(new ol.proj.transformExtent(bounds, 'EPSG:4326', 'EPSG:3857'), map.getSize());
    showMousePosition(map);
    spatialQuery(map);
});



