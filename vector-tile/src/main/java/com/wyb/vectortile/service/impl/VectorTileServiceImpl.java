package com.wyb.vectortile.service.impl;

import com.wyb.vectortile.dao.VectorTileMapper;
import com.wyb.vectortile.pojo.domain.VectorTileDo;
import com.wyb.vectortile.pojo.query.TileEnvelopeQuery;
import com.wyb.vectortile.service.VectorTileService;
import com.wyb.vectortile.utils.GoogleTileAlgorithm;
import com.wyb.vectortile.utils.TileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 矢量瓦片
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 23:02
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
@CacheConfig(cacheNames = "VectorTile")
public class VectorTileServiceImpl implements VectorTileService {

    private final VectorTileMapper vectorTileMapper;

    @Autowired
    public VectorTileServiceImpl(VectorTileMapper vectorTileMapper) {
        this.vectorTileMapper = vectorTileMapper;
    }


    @Override
    @Cacheable(value = "VectorTile", key = "#z + '-' + #x + '-' + #y")
    public byte[] findByTileCode(Integer x, Integer y, Integer z, Integer type) {
        TileEnvelopeQuery tileEnvelope = new TileEnvelopeQuery();
        double[] tileCoordinates;
        List<VectorTileDo> result;
        if (type == 0) {
            tileCoordinates = TileUtil.tileToEnvelope(x, y, z);
            tileEnvelope.setZoom(z);
            tileEnvelope.setTileMinLongitude(tileCoordinates[0]);
            tileEnvelope.setTileMinLatitude(tileCoordinates[1]);
            tileEnvelope.setTileMaxLongitude(tileCoordinates[2]);
            tileEnvelope.setTileMaxLatitude(tileCoordinates[3]);
        }else if (type == 1){
            tileCoordinates = GoogleTileAlgorithm.code2Coordinate(x, y, z);
            tileEnvelope.setZoom(z);
            tileEnvelope.setTileMinLongitude(tileCoordinates[0]);
            tileEnvelope.setTileMinLatitude(tileCoordinates[3]);
            tileEnvelope.setTileMaxLongitude(tileCoordinates[2]);
            tileEnvelope.setTileMaxLatitude(tileCoordinates[1]);
        }else {
            result = this.vectorTileMapper.findByXYZ(x, y, z);
            return result.get(0).getTile();
        }
        result = (type == 0) ? this.vectorTileMapper.findByTileCoordinates(tileEnvelope) : this.vectorTileMapper.findByWGS84TileCoordinates(tileEnvelope);
                this.vectorTileMapper.findByTileCoordinates(tileEnvelope);
        return result.get(0).getTile();
    }

    @Override
    @Cacheable(value = "MercatorTile", key = "#z + '-' + #x + '-' + #y")
    public byte[] findMercatorTileByXYZ(Integer x, Integer y, Integer z) {
        return this.vectorTileMapper.findMercatorTileByXYZ(x, y, z).getTile();
    }
}

