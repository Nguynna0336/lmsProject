package lhnthoi.lmsProject.Services;

import lhnthoi.lmsProject.Exceptions.DataNotFoundException;
import lhnthoi.lmsProject.Models.Course;
import lhnthoi.lmsProject.Models.CourseDetail;
import lhnthoi.lmsProject.Models.User;
import lhnthoi.lmsProject.Repositories.CourseDetailRepository;
import lhnthoi.lmsProject.Repositories.CourseRepository;
import lhnthoi.lmsProject.Repositories.UserRepository;
import lhnthoi.lmsProject.Response.CourseDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseDetailService implements ICourseDetailService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseDetailRepository courseDetailRepository;

    @Override
    public CourseDetail findByCourseId(String courseId) {
        return null;
    }

    @Override
    public CourseDetail enrollCourse(int studentId, String courseId) throws Exception {
        Course existingCourse = courseRepository.findByCourseId(courseId).orElseThrow(()-> new DataNotFoundException("Cannot find course with id: " + courseId));
        if (existingCourse.getCurrentQuantity() == existingCourse.getMaxQuantity()) {
            throw new Exception("this course is full");
        }
        User existingUser = userRepository.findByUserId(studentId)
                .orElseThrow(()->  new DataNotFoundException("Cannot find student with id: " + studentId));
        CourseDetail courseDetail = CourseDetail.builder()
                .course(existingCourse)
                .student(existingUser)
                .build();
        courseDetailRepository.save(courseDetail);
        existingCourse.setCurrentQuantity(existingCourse.getCurrentQuantity()+1);
        courseRepository.save(existingCourse);
        return courseDetail;
    }

    @Override
    public String editGrade(HashMap<Integer, Float> gradeBook, String courseId) throws DataNotFoundException {
        if(!courseRepository.existsByCourseId(courseId)) {
            throw new DataNotFoundException("Cannot find course with id: "+ courseId);
        }
        Course existingCourse = courseRepository.findByCourseId(courseId)
                .orElseThrow(()-> new DataNotFoundException("Cannot find course with id: "+ courseId));
        for (Map.Entry<Integer, Float> pair : gradeBook.entrySet()) {
            int studentId = pair.getKey();
            Float grade = pair.getValue();
            User existingStudent = userRepository.findByUserId(studentId).orElseThrow(()-> new DataNotFoundException("Cannot find student with id: "+ studentId));
            CourseDetail existingCourseDetail = courseDetailRepository.findByCourseAndStudent(existingCourse,existingStudent)
                    .orElseThrow(()-> new DataNotFoundException("Cannot find student with id:" + studentId +" in course: " + courseId));
            existingCourseDetail.setGrade(grade);
        }
        return "Grade has been edited";
    }

    @Override
    public List<CourseDetailResponse> getGradeBookByCourseId(String courseId, int lectureId) throws DataNotFoundException {
        Course existintCourse = courseRepository.findByCourseId(courseId).orElseThrow(()-> new DataNotFoundException("Can not find course with id: "+ courseId));
        User lecturer = userRepository.findByUserId(lectureId).orElseThrow(()-> new DataNotFoundException("Can not find lecturer with id: "+ lectureId));
        List<CourseDetail> courseDetailList = courseDetailRepository.findByCourseAndLecturer(existintCourse, lecturer );
        List<CourseDetailResponse> gradeBook = new ArrayList<>();
        for(CourseDetail courseDetail : courseDetailList) {
            CourseDetailResponse courseDetailResponse = CourseDetailResponse.fromCourseDetail(courseDetail);
            gradeBook.add(courseDetailResponse);
        }
        return gradeBook;
    }

    @Override
    public String deleteCourseDetail(int studentId, String courseId) throws Exception {
        Course existingCourse = courseRepository.findByCourseId(courseId)
                .orElseThrow(()-> new DataNotFoundException("Cannot find course with id: " + courseId));
        User existingStudent = userRepository.findByUserId(studentId)
                .orElseThrow(()-> new DataNotFoundException("Cannot find student with id: "+ studentId));
        CourseDetail courseDetail = courseDetailRepository.findByCourseAndStudent(existingCourse,existingStudent)
                .orElseThrow(()-> new DataNotFoundException("Student with id " + studentId + " hasn't enroll course " + courseId));
        if(existingCourse.getStartDate().before(new Date()) || existingCourse.getEndDate().after(new Date()))
        {
            throw new Exception("Cannot delete course's detail now, please wait to the end of semester");
        }
        courseDetailRepository.delete(courseDetail);
        return "Course's detail has been deleted";
    }
}
