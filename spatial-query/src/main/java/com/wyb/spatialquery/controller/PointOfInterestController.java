package com.wyb.spatialquery.controller;

import com.wyb.spatialquery.domain.PointOfInterest;
import com.wyb.spatialquery.service.impl.PointOfInterestServiceImpl;
import com.wyb.spatialquery.utils.CustomResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "POI(兴趣点)相关接口")
@Slf4j
public class PointOfInterestController {

    private final PointOfInterestServiceImpl pointOfInterestService;

    @Autowired
    public PointOfInterestController(PointOfInterestServiceImpl pointOfInterestService) {
        this.pointOfInterestService = pointOfInterestService;
    }

    @ApiOperation("根据传入范围查询POI")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "extent", value = "查询范围", required = true, dataType = "string", paramType = "query", example = "POLYGON((105 32,105.3 32,105.3 32.3,105 32.3,105 32))")
    )
    @GetMapping("/poi")
    public CustomResponse pointOfInterestByExtent(@RequestParam("extent") String extent) {
        List<PointOfInterest> result = this.pointOfInterestService.queryByExtent(extent);
        return CustomResponse.ok(result);
    }
}
