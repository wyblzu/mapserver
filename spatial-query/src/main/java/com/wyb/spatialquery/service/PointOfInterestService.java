package com.wyb.spatialquery.service;

import com.wyb.spatialquery.domain.PointOfInterest;

import java.util.List;

public interface PointOfInterestService {

    List<PointOfInterest> queryByExtent(String extent);
}
