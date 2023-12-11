package backend.point;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface PointRepository extends CrudRepository<Point, Integer> {
    List<Point> findAllById(int id);
    void deleteAllById(int id);
}
