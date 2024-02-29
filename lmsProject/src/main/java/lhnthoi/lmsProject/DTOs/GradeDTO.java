package lhnthoi.lmsProject.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashMap;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    @JsonProperty("course_id")
    private String courseId;
    @JsonProperty("grade")
    private HashMap<Integer, Float> gradeBook;
}
