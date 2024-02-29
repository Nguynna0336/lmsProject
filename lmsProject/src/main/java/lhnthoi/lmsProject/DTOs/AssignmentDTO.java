package lhnthoi.lmsProject.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTO {
    @JsonProperty("assignment_name")
    private String assignmentName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("course_id")
    private String courseId;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;
}
