package com.wyb.spatialquery.controller;

import com.wyb.spatialquery.domain.PointOfInterest;
import com.wyb.spatialquery.service.impl.PointOfInterestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PointOfInterestController {

    private final PointOfInterestServiceImpl pointOfInterestService;

    @Autowired
    public PointOfInterestController(PointOfInterestServiceImpl pointOfInterestService) {
        this.pointOfInterestService = pointOfInterestService;
    }

    @GetMapping("/poi")
    public List<PointOfInterest> pointOfInterestByExtent(@RequestParam("extent") String extent) {
        return this.pointOfInterestService.queryByExtent(extent);
    }

//    @GetMapping("/poi")
//    public List<PointOfInterest> pointOfInterestByName(@RequestParam("name") String name) {
//        return
//    }
}
