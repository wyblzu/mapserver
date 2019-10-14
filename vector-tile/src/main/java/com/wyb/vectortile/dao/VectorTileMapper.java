package com.wyb.vectortile.dao;

import com.wyb.vectortile.pojo.domain.VectorTileDo;
import com.wyb.vectortile.pojo.query.TileEnvelopeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;

/**
 * 矢量瓦片相关
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019/9/8 19:59
 */
@Repository
@Mapper
public interface VectorTileMapper {

    /**
     * 根据瓦片坐标得到mvt
     *
     * @param tileEnvelope 瓦片坐标
     * @return mvt
     * @author wangyongbing
     * @date 2019/10/10 21:41
     */
    List<VectorTileDo> findByTileCoordinates(TileEnvelopeQuery tileEnvelope);

}
