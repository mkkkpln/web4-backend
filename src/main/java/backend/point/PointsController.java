package backend.point;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.user.UserService;

import java.util.List;

@RestController
public class PointsController {
    @Autowired
    PointRepository pointRepository = null;

    private PointService pointService;
    private UserService userService;


    @GetMapping("/get")
    private ResponseEntity<List<Point>> getPoints(HttpServletRequest request) {
        List<Point> points = pointService.findAllById();
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @GetMapping("/add")
    public void addPoint(Point point, int userID) {
        point.setUserId(userID);
        pointRepository.save(point);
    }

    @DeleteMapping("/delete")
    public void deleteAll(HttpServletRequest request) {
        pointService.deleteAll();
    }



}
