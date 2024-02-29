package lhnthoi.lmsProject.Services;

import lhnthoi.lmsProject.Exceptions.DataNotFoundException;
import lhnthoi.lmsProject.Models.CourseDetail;
import lhnthoi.lmsProject.Response.CourseDetailResponse;

import java.util.HashMap;
import java.util.List;

public interface ICourseDetailService {
    CourseDetail findByCourseId(String courseId);
    CourseDetail enrollCourse(int studentId, String courseId) throws Exception;
    String editGrade(HashMap<Integer, Float> gradeBook, String courseId) throws DataNotFoundException;
    List<CourseDetailResponse> getGradeBookByCourseId(String CourseId, int lectureId) throws DataNotFoundException;
    String deleteCourseDetail(int studentId, String courseId) throws Exception;
}
