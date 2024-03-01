package lhnthoi.lmsProject.Controller;

import lhnthoi.lmsProject.DTOs.CourseDTO;
import lhnthoi.lmsProject.Models.Course;
import lhnthoi.lmsProject.Services.CourseService;
import jakarta.validation.Valid;
import lhnthoi.lmsProject.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    @PostMapping("")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDTO courseDTO, BindingResult result) {
        try {
            if(result.hasErrors()) {
                List<String> errorsMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorsMessages);
            }
            Course course = courseService.createCourse(courseDTO);
            /// Check user co phai la giang vien k

            ///
            return ResponseEntity.ok(course);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("")
    public ResponseEntity<?> editCourse(@Valid @RequestBody CourseDTO courseDTO, BindingResult result) {
        try {
            if(result.hasErrors()) {
                List<String> errorsMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorsMessages);
            }
            Course course = modelMapper.map(courseDTO, Course.class);
            course.setLecturer(userService.getUserById(courseDTO.getLecturerId()));
            return ResponseEntity.ok(courseService.editCourse(course));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable("id") String courseId)  {
        try {
            return ResponseEntity.ok(courseService.getCourseById(courseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") String courseId)  {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok("Course with id: " + courseId + " has been deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
