package com.wyb.spatialquery.service;


import com.google.gson.JsonObject;

/**
 * cluster query
 *
 * @author wangyongbing
 * @version 1.0.0
 * @since 2020-01-13 下午9:19
 **/
public interface PointOfClusterService {

    /**
     *
     * 聚合查询
     *
     * @author wangyongbing
     * @since 2020/1/13 下午9:17
     *
     * @param cellSize 网格大小
     *
     * @return com.fasterxml.jackson.databind.util.JSONPObject
     *
     */
     String queryByCluster(Double cellSize);
}
