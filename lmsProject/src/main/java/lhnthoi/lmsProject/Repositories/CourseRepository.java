package lhnthoi.lmsProject.Repositories;

import lhnthoi.lmsProject.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseId(String courseId);
    Optional<Course> findByCourseId(String courseId);
}
