package com.wyb.vectortile.dao;

import com.wyb.vectortile.pojo.domain.VectorTileDo;
import com.wyb.vectortile.pojo.query.TileEnvelopeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
     * 根据Mercator瓦片坐标得到pbf
     *
     * @param tileEnvelope 瓦片坐标
     * @return 参考 {@link VectorTileDo}
     * @author wangyongbing
     * @date 2019/10/10 21:41
     */
    List<VectorTileDo> findByTileCoordinates(TileEnvelopeQuery tileEnvelope);

    /**
     *
     * 请根据WGS84瓦片坐标得到pbf
     *
     * @author wangyongbing
     * @date 2019/10/14 22:19
     *
     * @param tileEnvelopeQuery 瓦片坐标
     *
     * @return 参考 {@link VectorTileDo}
     *
     */
    List<VectorTileDo> findByWGS84TileCoordinates(TileEnvelopeQuery tileEnvelopeQuery);
    
    /**
     *
     * 根据xyz请求瓦片
     *
     * @author wangyongbing
     * @since 2019/12/1 下午7:09
     *
     * @param x 行号
     * @param y 列号
     * @param z 层级
     *
     * @return {@see VectorTileDo}
     *
     */
    List<VectorTileDo> findByXYZ(Integer x, Integer y, Integer z);

    /**
     *
     * 根据zyx获取Mercator瓦片
     *
     * @author wangyongbing
     * @since 2019/12/9 下午10:19
     *
     * @param x 行号
     * @param y 列好
     * @param z 级别
     *
     * @return {@see VectorTileDo}
     *
     */
    VectorTileDo  findMercatorTileByXYZ(Integer x, Integer y, Integer z);

}
