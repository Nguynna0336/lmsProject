package lhnthoi.lmsProject.Services;

import lhnthoi.lmsProject.DTOs.CourseDTO;
import lhnthoi.lmsProject.Exceptions.DataNotFoundException;
import lhnthoi.lmsProject.Models.Course;

public interface ICourseService {
    Course createCourse(CourseDTO courseDTO) throws DataNotFoundException;
    Course getCourseById(String courseId) throws DataNotFoundException;
    Course editCourse(Course course) throws DataNotFoundException;
    void deleteCourse(String courseId) throws DataNotFoundException;
}
