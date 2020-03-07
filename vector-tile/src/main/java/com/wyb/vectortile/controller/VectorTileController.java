package com.wyb.vectortile.controller;

import com.wyb.vectortile.service.VectorTileService;
import com.wyb.vectortile.service.impl.VectorTileServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * 矢量瓦片查询接口
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 23:08
 **/
@RestController
@RequestMapping("/api")
@Api(tags = "通过x,y,z获取矢量瓦片")
@Slf4j
public class VectorTileController {

    private final VectorTileService vectorTileService;

    @Autowired
    public VectorTileController(VectorTileServiceImpl vectorTileService) {
        this.vectorTileService = vectorTileService;
    }

    @ApiOperation("根据xyz获取mvt格式矢量瓦片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "瓦片类型", dataType = "integer", paramType = "path", defaultValue = "-1",example = "0"),
            @ApiImplicitParam(name = "z", value = "级别", required = true, dataType = "integer", paramType = "path", example = "12"),
            @ApiImplicitParam(name = "x", value = "行号", required = true, dataType = "integer", paramType = "path", example = "6743"),
            @ApiImplicitParam(name = "y", value = "列好", required = true, dataType = "integer", paramType = "path", example = "3101")
    })
    @GetMapping("/tile")
    public HttpEntity<byte[]> findTileByCode(@RequestParam(name = "type", defaultValue = "0") Integer type,
                                             @RequestParam(name = "z") Integer z,
                                             @RequestParam(name = "x") Integer x,
                                             @RequestParam(name = "y") Integer y) {

        byte[] bytes = this.vectorTileService.findByTileCode(x, y, z, type);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/vnd.mapbox-vector-tile");
        headers.setContentLength(bytes.length);
        return new HttpEntity<>(bytes, headers);
    }

    /**
     *
     * 请求矢量瓦片
     *
     * @author wangyongbing
     * @since 2019/12/10 下午10:47
     *
     * @param z 层级
     * @param x 行号
     * @param y 列号
     *
     * @return pbf二进制数据
     *
     */
    @ApiOperation("根据xyz获取Mercator矢量瓦片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "z", value = "级别", required = true, dataType = "integer", paramType = "path", allowableValues = "1,2,3,...,20", example = "13"),
            @ApiImplicitParam(name = "x", value = "行号", required = true, dataType = "integer", paramType = "path", example = "6744"),
            @ApiImplicitParam(name = "y", value = "列号", required = true, dataType = "integer", paramType = "path",example = "3102")
    })
    @GetMapping("/mtile/{z}/{x}/{y}.pbf")
    public HttpEntity<byte[]> findMercatorTileByCode(
                                             @PathVariable(value = "z", name = "z") Integer z,
                                             @PathVariable(value = "x", name = "x") Integer x,
                                             @PathVariable(value = "y", name = "y") Integer y) {

        byte[] bytes = this.vectorTileService.findMercatorTileByXYZ(x, y, z);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/vnd.mapbox-vector-tile");
        headers.setContentLength(bytes.length);
        return new HttpEntity<>(bytes, headers);
    }
}

