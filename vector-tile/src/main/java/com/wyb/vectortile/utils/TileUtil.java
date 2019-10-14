package com.wyb.vectortile.utils;

/**
 * 切片算法
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-14 17:19
 **/
public class TileUtil {

    private final static double WORLDMERCATORMAX = 20037508.3427892;

    private final static double WORLDMERCATORMIN = -1 * WORLDMERCATORMAX;

    private final static double WORLDMERCATORSIZE =
            WORLDMERCATORMAX - WORLDMERCATORMIN;

    private final static int DENSIFY_FACTOR = 4;

    public static boolean tileIsValid(Integer x, Integer y, Integer z) {
        double size = Math.pow(2, z);

        if (x >= size || y >= size) {
            return false;
        }
        if (x < 0 || y < 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 切片转box
     *
     * @author wangyongbing
     * @date 2019/10/14 21:43
     *
     * @param x 行
     * @param y 列
     * @param z 层级
     *
     * @return 参考 {@link }
     *
     */
    public static double[] tileToEnvelope(Integer x, Integer y, Integer z) {
        double worldTileSize = Math.pow(2, z);
        double tileMercatorSize = WORLDMERCATORSIZE / worldTileSize;
        double xmin = WORLDMERCATORMIN + tileMercatorSize * x;
        double xmax = WORLDMERCATORMIN + tileMercatorSize * (x + 1);
        double ymin = WORLDMERCATORMAX - tileMercatorSize * (y + 1);
        double ymax = WORLDMERCATORMAX - tileMercatorSize * y;
        double segsize = (xmax - xmin) / DENSIFY_FACTOR;
        return new double[]{xmin, ymin, xmax, ymax, segsize};
    }
}
