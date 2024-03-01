package lhnthoi.lmsProject.Services;
import lhnthoi.lmsProject.DTOs.CourseDTO;
import lhnthoi.lmsProject.Exceptions.DataNotFoundException;
import lhnthoi.lmsProject.Models.Course;
import lhnthoi.lmsProject.Models.User;
import lhnthoi.lmsProject.Repositories.CourseRepository;
import lhnthoi.lmsProject.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    @Override
    public Course createCourse(CourseDTO courseDTO) throws DataNotFoundException {
        if(courseRepository.existsByCourseId(courseDTO.getCourseId())){
            throw new DataIntegrityViolationException("Course has been created before");
        }
        User lecturer = userRepository.findByUserId(courseDTO.getLecturerId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find lecturer with id: "+courseDTO.getLecturerId() ));
        Course course = Course.builder()
                .courseName(courseDTO.getCourseName())
                .day(courseDTO.getDay())
                .courseId(courseDTO.getCourseId())
                .departmentId(courseDTO.getDepartmentId())
                .duration(courseDTO.getDuration())
                .endDate(courseDTO.getEndDate())
                .lecturer(lecturer)
                .maxQuantity(courseDTO.getMaxQuantity())
                .startDate(courseDTO.getStartDate())
                .build();

        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(String courseId) throws DataNotFoundException {
        return courseRepository.findByCourseId(courseId).orElseThrow(()-> new DataNotFoundException("Cannot find course with id: " + courseId));
    }


}
