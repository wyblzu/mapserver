package com.wyb.spatialquery.service.impl;

import com.wyb.spatialquery.domain.PointOfInterest;
import com.wyb.spatialquery.mapper.PointOfInterestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointOfInterestServiceImpl {

    private final PointOfInterestMapper pointOfInterestMapper;

    @Autowired
    public PointOfInterestServiceImpl(PointOfInterestMapper pointOfInterestMapper) {
        this.pointOfInterestMapper = pointOfInterestMapper;
    }

    public List<PointOfInterest> queryByExtent(String extent) {
        return this.pointOfInterestMapper.findByExtent(extent);
    }

}
