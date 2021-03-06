package com.wyb.spatialquery.service.impl;

import com.wyb.spatialquery.domain.PointOfInterest;
import com.wyb.spatialquery.mapper.PointOfInterestMapper;
import com.wyb.spatialquery.service.PointOfInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * POI相关
 * @author wangyongbing
 * @date 2019/9/8 19:59
 * @version 1.0.0
 */
@Service
public class PointOfInterestServiceImpl implements PointOfInterestService {

    private final PointOfInterestMapper pointOfInterestMapper;

    @Autowired
    public PointOfInterestServiceImpl(PointOfInterestMapper pointOfInterestMapper) {
        this.pointOfInterestMapper = pointOfInterestMapper;
    }

    @Override
    public List<PointOfInterest> queryByExtent(String extent) {
        return this.pointOfInterestMapper.findByExtent(extent);
    }

}
