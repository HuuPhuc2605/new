

public interface CourseRepository extends JpaRepository<Course, Long> {
List<Course> findByNameContainingOrCategoryContainingOrDescriptionContaining(String name, String category, String description);
}
