package lhnthoi.lmsProject.DTOs;

import lhnthoi.lmsProject.Enums.Week;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @JsonProperty("course_id")
    private String courseId;
    @JsonProperty("lecturer_id")
    private int lecturerId;
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("max_quantity")
    private int maxQuantity;
    @JsonProperty("department_id")
    private String departmentId;
    @JsonProperty("start_date")
    private Date startDate;
    @JsonProperty("end_date")
    private Date endDate;
    private Week day; // ngày học trong tuần :D
    private float duration;
}
