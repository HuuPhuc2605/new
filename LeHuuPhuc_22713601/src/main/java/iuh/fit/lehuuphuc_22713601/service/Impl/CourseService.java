package iuh.fit.lehuuphuc_22713601.service.Impl;

import iuh.fit.lehuuphuc_22713601.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAll();

    Course getById(Long id);

    Course upsert(Course course);

    void delete(Long id);

    List<Course> search(String keyword);
}
