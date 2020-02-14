package com.wyb.spatialquery.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wyb.spatialquery.dao.PointOfClusterMapper;
import com.wyb.spatialquery.service.PointOfClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

/**
 * cluster  query
 *
 * @author wangyongbing
 * @version 1.0.0
 * @since 2020-01-13 下午9:23
 **/
@Service
public class PointOfClusterServiceImpl implements PointOfClusterService {



    private final PointOfClusterMapper pointOfClusterMapper;

    @Autowired
    public PointOfClusterServiceImpl(PointOfClusterMapper pointOfClusterMapper) {
        this.pointOfClusterMapper = pointOfClusterMapper;
    }

    @Override
    public String queryByCluster(Double cellSize) {
        return this.pointOfClusterMapper.findByCluster(cellSize);
    }
}
