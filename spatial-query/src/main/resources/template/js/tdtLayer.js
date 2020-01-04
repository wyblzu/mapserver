/**
 * Created by 汪永兵 on 2017/4/10.
 *
 */
//获取天地图图层
function getTdtLayer(lyr) {
    var url = "http://t4.tianditu.com/DataServer?T="+lyr+"&x={x}&Y={y}&L={z}&tk=2ce94f67e58faa24beb7cb8a09780552";
    var projection = ol.proj.get("EPSG:4326");
    var projectionExtent = [ -180, -90, 180, 90 ];
    var maxResolution = (ol.extent.getWidth(projectionExtent) / (256 * 2));
    var resolutions = new Array(19);
    for (var z = 0; z < 19; ++z) {
        resolutions[z] = maxResolution / Math.pow(2, z);
    }
    var tileOrigin = ol.extent.getTopLeft(projectionExtent);
    return new ol.layer.Tile({
        extent: [ -180, -90, 180, 90],
        source: new ol.source.TileImage({
            tileUrlFunction: function(tileCoord) {
                var z = tileCoord[0]+1;
                var x = tileCoord[1];
                var y = -tileCoord[2]-1;
                var n = Math.pow(2, z + 1);
                x = x % n;
                if (x * n < 0) {
                    x = x + n;
                }
                return url.replace('{z}', z.toString())
                    .replace('{y}', y.toString())
                    .replace('{x}', x.toString());
            },
            projection: projection,
            tileGrid: new ol.tilegrid.TileGrid({
                origin: tileOrigin,
                resolutions: resolutions,
                tileSize: 256
            })
        })
    });
}
//显示鼠标位置
function showMousePosition(map) {
    var element = document.getElementById('mousePosition');
    map.on("pointermove", function (event) {
        var coord = ol.proj.toLonLat(event.coordinate);
        element.innerHTML = ol.coordinate.format(coord, '{x},{y}', 6);
    })
}
function tdtLayer(url) {
    var projection4326 = ol.proj.get("EPSG:4326"),
        projectionExtent = [-180, -90, 180, 90],
        maxResolution = (ol.extent.getWidth(projectionExtent) / (256 * 2)),
        resolutions = new Array(19),
        tileOrigin = ol.extent.getTopLeft(projectionExtent);
    for (var z = 0; z < 19; ++z) {
        resolutions[z] = maxResolution / Math.pow(2, z);
    }
    return new ol.layer.Tile({
        extent: [-180, -90, 180, 90],
        source: new ol.source.XYZ({
            tileUrlFunction: function (tileCoord) {
                var z = tileCoord[0] + 1;
                var x = tileCoord[1];
                var y = -tileCoord[2] - 1;
                var n = Math.pow(2, z + 1);
                x = x % n;
                if (x * n < 0) {
                    x = x + n;
                }
                return url.replace('{z}', z.toString())
                    .replace('{y}', y.toString())
                    .replace('{x}', x.toString());
            },
            projection: projection4326,
            tileGrid: new ol.tilegrid.TileGrid({
                origin: tileOrigin,
                resolutions: resolutions,
                tileSize: 256
            })
        })
    })
}



