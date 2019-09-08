package com.wyb.spatialquery.mapper;

import com.wyb.spatialquery.domain.PointOfInterest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PointOfInterestMapper {

    List<PointOfInterest> findByExtent(String extent);
}
