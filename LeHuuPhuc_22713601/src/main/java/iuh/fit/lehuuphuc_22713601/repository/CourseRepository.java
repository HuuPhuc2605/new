package iuh.fit.lehuuphuc_22713601.repository;

import iuh.fit.lehuuphuc_22713601.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
List<Course> findByNameContainingOrCategoryContainingOrDescriptionContaining(String name, String category, String description);
}
