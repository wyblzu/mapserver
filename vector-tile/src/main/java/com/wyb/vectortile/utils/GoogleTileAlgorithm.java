package com.wyb.vectortile.utils;

/**
 * 谷歌地图瓦片算法
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 22:44
 **/
public class GoogleTileAlgorithm {

    /**
     *
     * 编码转坐标
     *
     * @author wangyongbing
     * @date 2019/10/10 22:57
     * @return minLongitude, minLatitude, maxLongitude, maxLatitude
     *
     */
    public static Double[] code2Coordinate(Integer x, Integer y, Integer zoom) {
        return new Double[]{tileToLongitude(x, zoom), tileToLatitude(y + 1, zoom), tileToLongitude(x+1, zoom), tileToLatitude(y, zoom)};
    }


    private static Double tileToLongitude(Integer x, Integer zoom) {
        return x / Math.pow(2.0, zoom) * 360.0 - 180;
    }

    private static Double tileToLatitude(Integer y, Integer zoom) {
        double n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2.0, zoom);
        return Math.toDegrees(Math.atan(Math.sinh(n)));
    }
}

