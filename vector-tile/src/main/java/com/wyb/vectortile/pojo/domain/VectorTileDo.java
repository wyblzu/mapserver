package com.wyb.vectortile.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VectorTileDo {
    /**
     * pbf二进制数据
     */
    byte[] tile;
}
