package lhnthoi.lmsProject.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollDTO {
    @JsonProperty("student_id")
    private int studentId;
    @JsonProperty("course_id")
    private String courseId;
}
