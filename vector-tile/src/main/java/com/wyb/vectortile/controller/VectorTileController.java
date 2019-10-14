package com.wyb.vectortile.controller;

import com.wyb.vectortile.service.VectorTileService;
import com.wyb.vectortile.service.impl.VectorTileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 矢量瓦片查询接口
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 23:08
 **/
@RestController
@RequestMapping("/api")
@Api(tags = "通过x,y,z获取矢量瓦片", description = "谷歌地图形式瓦片")
@Slf4j
public class VectorTileController {

    private final VectorTileService vectorTileService;

    @Autowired
    public VectorTileController(VectorTileServiceImpl vectorTileService) {
        this.vectorTileService = vectorTileService;
    }

    @ApiOperation("根据xyz获取mvt格式矢量瓦片")
    @GetMapping("/{z}/{x}/{y}.pbf")
    public HttpEntity<byte[]> findTileByCode(@ApiParam(name = "z", value =
            "级别",
            required =
                    true, example = "13") @PathVariable(name = "z") Integer z,
                                     @ApiParam(name = "x", value = "行号", required = true, example = "6744") @PathVariable(name = "x") Integer x,
                                     @ApiParam(name = "y", value = "列号",
                                                 required = true, example = "3102") @PathVariable(name = "y") Integer y) {

        byte[] bytes = this.vectorTileService.findByTileCode(x, y, z);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/vnd.mapbox-vector-tile");
        headers.setContentLength(bytes.length);
        return new HttpEntity<>(bytes, headers);
    }
}

