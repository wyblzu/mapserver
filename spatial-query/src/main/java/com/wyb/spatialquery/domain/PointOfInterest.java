package com.wyb.spatialquery.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * POI相关
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019/9/8 19:59
 */
@Data
public class PointOfInterest implements Serializable {

    private String id;

    private String name;

    private String hashcode;

    private Object location;

    @Override
    public String toString() {
        return "PointOfInterest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", geohash='" + hashcode + '\'' +
                ", geometry='" + location + '\'' +
                '}';
    }
}
