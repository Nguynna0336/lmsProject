package lhnthoi.lmsProject.Response;

import lhnthoi.lmsProject.Models.CourseDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDetailResponse {
    @JsonProperty("student_id")
    private int studentId;

    @JsonProperty("student_name")
    private String studentName;
    private float grade;

    public static CourseDetailResponse fromCourseDetail(CourseDetail courseDetail) {
        return CourseDetailResponse.builder()
                .studentId(courseDetail.getStudent().getUserId())
                .studentName(courseDetail.getStudent().getFullName())
                .grade(courseDetail.getGrade())
                .build();
    }
}
