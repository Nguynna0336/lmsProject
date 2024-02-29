package lhnthoi.lmsProject.Services;

import lhnthoi.lmsProject.DTOs.AssignmentDTO;
import lhnthoi.lmsProject.Models.Assignment;
import lhnthoi.lmsProject.Models.Course;

public interface IAssignmentService {
     Assignment createAssignment(AssignmentDTO assignmentDTO, Course course, String fileUrl);
}
