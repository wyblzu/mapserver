package com.wyb.spatialquery.mapper;

import com.wyb.spatialquery.domain.PointOfInterest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * POI相关
 * @author wangyongbing
 * @date 2019/9/8 19:59
 * @version 1.0.0
 */
@Repository
@Mapper
public interface PointOfInterestMapper{

    /**
     *
     * 范围查询方法
     *
     * @author wangyongbing
     * @date 2019/9/8 20:02
     *
     * @param extent 查询范围
     *
     * @return java.util.List<com.wyb.spatialquery.domain.PointOfInterest>
     *
     */
    List<PointOfInterest> findByExtent(String extent);
}
