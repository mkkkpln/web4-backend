package com.example.web4.point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PointsController {
    @Autowired
    PointRepository pointRepository = null;


    @GetMapping("/get")
    private ResponseEntity<List<Point>> getPoints() {
        List<Point> points = pointRepository.findAll();
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @PostMapping ("/add")
    public Point addPoint(@RequestBody RawPoint point) {

        Point point1 = new Point(point.getX(), point.getY(), point.getR(), true, 12, 12);

        final long curr_time = System.currentTimeMillis();
        final long startTime = System.nanoTime();
        point1.setResult(AreaResultChecker.isPointInArea(point1));
        long endTime = System.nanoTime();
        double totalTime = (double)(endTime - startTime)/1000/1000;
        point1.setCreatedAt(curr_time);
        point1.setExecutionTime(totalTime);

        pointRepository.save(point1);
        return point1;
    }

    @DeleteMapping("/delete")
    public void deleteAll() {
        pointRepository.deleteAll();
    }



}
