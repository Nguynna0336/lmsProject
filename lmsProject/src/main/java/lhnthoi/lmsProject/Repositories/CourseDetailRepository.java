package lhnthoi.lmsProject.Repositories;

import lhnthoi.lmsProject.Models.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {
    List<CourseDetail> findByCourseIdAndLectureId(String courseId, int lectureId);
    Optional<CourseDetail> findByCourseIdAndStudentId(String courseId, int studentId);
}
