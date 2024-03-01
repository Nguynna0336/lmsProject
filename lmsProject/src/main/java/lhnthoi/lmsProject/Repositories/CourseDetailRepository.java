package lhnthoi.lmsProject.Repositories;

import lhnthoi.lmsProject.Models.Course;
import lhnthoi.lmsProject.Models.CourseDetail;
import lhnthoi.lmsProject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {
    List<CourseDetail> findByCourseAndLecturer(Course course, User lecturer);
    Optional<CourseDetail> findByCourseAndStudent(Course course, User student);
}
