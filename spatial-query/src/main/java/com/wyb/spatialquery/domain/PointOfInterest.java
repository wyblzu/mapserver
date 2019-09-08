package com.wyb.spatialquery.domain;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * 对应POI表
 * @author wangyongbing
 * @date 2019/9/8 19:59
 * @version 1.0.0
 */
@Data
@ApiModel
public class PointOfInterest implements Serializable {

    @ApiModelProperty(name = "id", notes = "键值", value = "键值", example = "1")
    private String id;

    @ApiModelProperty(name = "name", notes = "名称", value = "名称", example = "北京市")
    private String name;

    @ApiModelProperty(name = "hashcode", notes = "地理hashcode", value = "地理hashcode", example = "wmdz46ydew")
    private String hashcode;

    @ApiModelProperty(name = "location", notes = "位置", value = "位置", example = "{\"type\":\"Point\",\"coordinates\":[105.22514688482,32.1835367006669]}")
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
