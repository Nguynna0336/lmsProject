package lhnthoi.lmsProject.Controller;

import lhnthoi.lmsProject.DTOs.EnrollDTO;
import lhnthoi.lmsProject.DTOs.GradeDTO;
import lhnthoi.lmsProject.Models.CourseDetail;
import lhnthoi.lmsProject.Services.CourseDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("coursesdetail")
@RequiredArgsConstructor
public class CourseDetailController {
    private final CourseDetailService courseDetailService;
    @PostMapping("")
    public ResponseEntity<?> enrollCourse(@Valid @RequestBody EnrollDTO enrollDTO) {
        try {
             CourseDetail  courseDetail = courseDetailService.enrollCourse(enrollDTO.getStudentId(), enrollDTO.getCourseId());
            return ResponseEntity.ok(courseDetail);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("")
    public ResponseEntity<String> editGrade(@Valid @RequestBody GradeDTO gradeDTO) {
        try {
            return ResponseEntity.ok(courseDetailService.editGrade(gradeDTO.getGradeBook(), gradeDTO.getCourseId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseGradeBook(@PathVariable String courseId, @RequestParam("lecture_id") int lectureId) {
        try {
            return ResponseEntity.ok(courseDetailService.getGradeBookByCourseId(courseId, lectureId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<?> unenrollCourse(@Valid @RequestBody EnrollDTO enrollDTO) {
        try {
            return ResponseEntity.ok(courseDetailService.deleteCourseDetail(enrollDTO.getStudentId(), enrollDTO.getCourseId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
