package backend.point;

import java.util.List;

public class PointService {

    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public List<Point> findAllById(int id) {
        return pointRepository.findAllById(id);
    }

    public void deleteAll(int id) {
        pointRepository.deleteAllById(id);
    }

}
