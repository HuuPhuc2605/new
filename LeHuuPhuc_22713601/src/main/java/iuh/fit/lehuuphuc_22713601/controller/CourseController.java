package iuh.fit.lehuuphuc_22713601.controller;

import iuh.fit.lehuuphuc_22713601.model.Course;
import iuh.fit.lehuuphuc_22713601.service.Impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CourseServiceImpl courseService;

    @GetMapping("/courses")
    public String showList(Model model, Authentication authentication, @RequestParam(required = false) String keyword) {
        String key = keyword == null ? "" : keyword.trim();
        String roleName = authentication
                .getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(","));
        model.addAttribute("name", authentication.getName());
        model.addAttribute("roleName", roleName);
        model.addAttribute("courses", courseService.search(key));
        model.addAttribute("keyword", key);

        return "course-list";

    }
    @GetMapping("/courses/form")
    @PreAuthorize("hasRole('ADMIN')")
    public String showForm(Model model,  @RequestParam(required = false) Long id) {
       if(id != null){
           Course course = courseService.getById(id);
           if(course == null)
               model.addAttribute("course", new Course());
               else
                   model.addAttribute("course", course);

       }else
           model.addAttribute("course", new Course());


        return "course-form";
    }
    @PostMapping("/courses")
    @PreAuthorize("hasRole('ADMIN')")
public String save(Course course){
        courseService.upsert(course);
        return "redirect:/courses";
    }
    @PostMapping("/courses/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id){
        courseService.delete(id);
        return "redirect:/courses";
    }
}
