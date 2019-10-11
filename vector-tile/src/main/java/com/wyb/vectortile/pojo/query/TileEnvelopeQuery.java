package com.wyb.vectortile.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每个瓦片坐标
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 21:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TileEnvelopeQuery {

    private Double tileMaxLongitude;

    private Double tileMinLongitude;

    private Double tileMaxLatitude;

    private Double tileMinLatitude;

}

