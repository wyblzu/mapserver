package com.wyb.vectortile.service.impl;

import com.wyb.vectortile.dao.VectorTileMapper;
import com.wyb.vectortile.pojo.query.TileEnvelopeQuery;
import com.wyb.vectortile.service.VectorTileService;
import com.wyb.vectortile.utils.GoogleTileAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 矢量瓦片
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 23:02
 **/
@Service
@CacheConfig(cacheNames = "VectorTile")
public class VectorTileServiceImpl implements VectorTileService {

    private final VectorTileMapper vectorTileMapper;

    @Autowired
    public VectorTileServiceImpl(VectorTileMapper vectorTileMapper) {
        this.vectorTileMapper = vectorTileMapper;
    }


    @Override
    @Cacheable(key = "#z + '-' + #x + '-' + #y")
    public String findByTileCode(Integer x, Integer y, Integer z) {
        System.out.println("数据库查询瓦片");
        Double[] tileCoordinates = GoogleTileAlgorithm.code2Coordinate(x, y, z);
        TileEnvelopeQuery tileEnvelope = new TileEnvelopeQuery();
        tileEnvelope.setTileMinLongitude(tileCoordinates[0]);
        tileEnvelope.setTileMinLatitude(tileCoordinates[1]);
        tileEnvelope.setTileMaxLongitude(tileCoordinates[2]);
        tileEnvelope.setTileMaxLatitude(tileCoordinates[3]);
        return vectorTileMapper.findByTileCoordinates(tileEnvelope);
    }
}

