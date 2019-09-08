package com.wyb.spatialquery.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class PointOfInterest implements Serializable {

    private String id;

    private String name;

    private String hashcode;

    private String location;

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
