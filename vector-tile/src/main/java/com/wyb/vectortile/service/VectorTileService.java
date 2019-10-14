package com.wyb.vectortile.service;

/**
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019/10/10 22:41
 */
public interface VectorTileService {

    /**
     *
     * 根据编号获取mvt
     *
     * @author wangyongbing
     * @date 2019/10/10 23:01
     *
     * @param x 行号
     * @param y 列号
     * @param z 级别
     * @param type 类型
     *
     * @return pbf
     *
     */
    byte[] findByTileCode(Integer x, Integer y, Integer z, Integer type);

}
