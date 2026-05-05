package iuh.fit.lehuuphuc_22713601.service.Impl;

import iuh.fit.lehuuphuc_22713601.model.Course;
import iuh.fit.lehuuphuc_22713601.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    @Override
    public List<Course> getAll(){
        return courseRepository.findAll();
    }
    @Override
    public Course getById(Long id){
        return courseRepository.findById(id).orElse(null);
    }
    @Override
    public Course upsert(Course course){
        return courseRepository.save(course);
    }
    @Override
    public void  delete(Long id){
        courseRepository.deleteById(id);
    }
    @Override
    public List<Course> search(String keyword){
        return courseRepository.findByNameContainingOrCategoryContainingOrDescriptionContaining(keyword, keyword, keyword);
    }
}
