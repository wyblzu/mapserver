package com.wyb.spatialquery.domain;

import lombok.Data;

/**
 * cluster
 *
 * @author wangyongbing
 * @version 1.0.0
 * @since 2020-01-13 下午8:53
 **/
@Data
public class PointOfCluster {

    Object result;

    @Override
    public String toString() {
        return "PointOfCluster{" +
                "result=" + result +
                '}';
    }
}
