package com.wyb.spatialquery.controller;

import com.wyb.spatialquery.domain.PointOfInterest;
import com.wyb.spatialquery.service.impl.PointOfInterestServiceImpl;
import com.wyb.spatialquery.utils.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * POI接口
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019/9/8 19:59
 */
@RestController
@RequestMapping("/api")
@Api(tags = "POI(兴趣点)相关接口", description = "提供POI查询的 REST API")
@Slf4j
public class PointOfInterestController {

    private final PointOfInterestServiceImpl pointOfInterestService;

    @Autowired
    public PointOfInterestController(PointOfInterestServiceImpl pointOfInterestService) {
        this.pointOfInterestService = pointOfInterestService;
    }

    @ApiOperation("根据传入范围查询POI")
    @GetMapping("/poi")
    public CustomResponse pointOfInterestByExtent(@ApiParam(name = "extent", value = "查询范围", required = true, example = "POLYGON((105 32,105.3 32,105.3 32.3,105 32.3,105 32))") @RequestParam("extent") String extent) {
        List<PointOfInterest> result = this.pointOfInterestService.queryByExtent(extent);
        return CustomResponse.ok(result);
    }
}
