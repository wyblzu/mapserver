package com.wyb.spatialquery.service;

import com.wyb.spatialquery.domain.PointOfInterest;

import java.util.List;

/**
 * POI相关
 * @author wangyongbing
 * @date 2019/9/8 19:59
 * @version 1.0.0
 */
public interface PointOfInterestService {

    /**
     *
     * 根据范围查询POI
     *
     * @author wangyongbing
     * @date 2019/9/8 19:59
     *
     * @param extent 查询范围
     *
     * @return java.util.List<com.wyb.spatialquery.domain.PointOfInterest>
     *
     */
    List<PointOfInterest> queryByExtent(String extent);
}
