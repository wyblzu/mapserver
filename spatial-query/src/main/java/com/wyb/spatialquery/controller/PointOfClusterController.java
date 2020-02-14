package com.wyb.spatialquery.controller;

import com.wyb.spatialquery.service.PointOfClusterService;
import com.wyb.spatialquery.service.impl.PointOfClusterServiceImpl;
import com.wyb.spatialquery.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * cluster query
 *
 * @author wangyongbing
 * @version 1.0.0
 * @since 2020-01-13 下午9:52
 **/
@RestController
@RequestMapping("/api")
@Api(value = "测试", tags = "聚合查询")
public class PointOfClusterController {

    private final PointOfClusterService pointOfClusterService;

    @Autowired
    public PointOfClusterController(PointOfClusterServiceImpl pointOfClusterService) {
        this.pointOfClusterService = pointOfClusterService;
    }

    @ApiOperation("根据传入网格大小查询聚合点")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cellSize", value = "网格大小", dataType = "double", paramType = "query", defaultValue = "0.005")
    )
    @GetMapping("/cluster")
    public CustomResponse pointOfClusterByCellSize(@RequestParam(name = "cellSize",value = "cellSize", required = false, defaultValue = "0.005") Double cellSize) {
        return CustomResponse.ok(this.pointOfClusterService.queryByCluster(cellSize));
    }
}
