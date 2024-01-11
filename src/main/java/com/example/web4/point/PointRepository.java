package com.example.web4.point;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface PointRepository extends CrudRepository<Point, Integer> {
    @Query("SELECT * FROM points")
    List<Point> findAll();

}
