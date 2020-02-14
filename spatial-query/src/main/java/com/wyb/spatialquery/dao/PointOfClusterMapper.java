package com.wyb.spatialquery.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 聚合查询
 *
 * @author wangyongbing
 * @version 1.0.0
 * @since 2020-01-13 下午8:39
 **/
@Repository
@Mapper
public interface PointOfClusterMapper {

    String findByCluster(@Param("cellSize") Double cellSize);
}
