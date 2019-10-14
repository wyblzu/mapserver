package com.wyb.vectortile.service.impl;

import com.wyb.vectortile.dao.VectorTileMapper;
import com.wyb.vectortile.pojo.domain.VectorTileDo;
import com.wyb.vectortile.pojo.query.TileEnvelopeQuery;
import com.wyb.vectortile.service.VectorTileService;
import com.wyb.vectortile.utils.GoogleTileAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 矢量瓦片
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 23:02
 **/
@Service
@Slf4j
@CacheConfig(cacheNames = "VectorTile")
public class VectorTileServiceImpl implements VectorTileService {

    private final VectorTileMapper vectorTileMapper;

    @Autowired
    public VectorTileServiceImpl(VectorTileMapper vectorTileMapper) {
        this.vectorTileMapper = vectorTileMapper;
    }


    @Override
    @Cacheable(key = "#z + '-' + #x + '-' + #y")
    public byte[] findByTileCode(Integer x, Integer y, Integer z) {
        Double[] tileCoordinates = GoogleTileAlgorithm.code2Coordinate(x, y, z);
        TileEnvelopeQuery tileEnvelope = new TileEnvelopeQuery();
        tileEnvelope.setTileMinLongitude(tileCoordinates[0]);
        tileEnvelope.setTileMinLatitude(tileCoordinates[1]);
        tileEnvelope.setTileMaxLongitude(tileCoordinates[2]);
        tileEnvelope.setTileMaxLatitude(tileCoordinates[3]);
        List<VectorTileDo> result =
                this.vectorTileMapper.findByTileCoordinates(tileEnvelope);
        return result.get(0).getTile();
    }
}

