package com.example.web4.point;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("points")
public class Point {
    @Id
    private int id;
    private double x;
    private double y;
    private double r ;
    private boolean result;
    private long createdAt;
    private double executionTime;

    public Point(double x, double y, double r, boolean result, long createdAt, double executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.createdAt = createdAt;
        this.executionTime = executionTime;
    }

    public static record Coordinates (double x, double y, double r, boolean result) { }


    public Coordinates getCoordinates() {
        return new Coordinates(x, y, r, result);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }

}
