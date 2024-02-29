package lhnthoi.lmsProject.Services;

import lhnthoi.lmsProject.DTOs.AssignmentDTO;
import lhnthoi.lmsProject.Models.Assignment;
import lhnthoi.lmsProject.Models.Course;
import lhnthoi.lmsProject.Repositories.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentService implements IAssignmentService{
    private final AssignmentRepository assignmentRepository;

    @Override
    public Assignment createAssignment(AssignmentDTO assignmentDTO, Course course, String fileUrl) {
        Assignment assignment = Assignment.builder()
                .assignmentName(assignmentDTO.getAssignmentName())
                .course(course)
                .startDate(assignmentDTO.getStartDate())
                .endDate(assignmentDTO.getEndDate())
                .description(assignmentDTO.getDescription())
                .filePath(fileUrl)
                .build();
        return assignmentRepository.save(assignment);
    }
}
