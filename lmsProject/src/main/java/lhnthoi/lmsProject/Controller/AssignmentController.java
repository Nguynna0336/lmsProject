package lhnthoi.lmsProject.Controller;

import lhnthoi.lmsProject.DTOs.AssignmentDTO;
import lhnthoi.lmsProject.Enums.FileUploadDestination;
import lhnthoi.lmsProject.Components.FileFunction;
import lhnthoi.lmsProject.Models.Assignment;
import lhnthoi.lmsProject.Models.Course;
import lhnthoi.lmsProject.Services.AssignmentService;
import lhnthoi.lmsProject.Services.CourseService;
import lhnthoi.lmsProject.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("assignment")
@RequiredArgsConstructor
public class AssignmentController {
    private final UserService userService;
    private final CourseService courseService;
    private final FileFunction fileFunction;
    private final AssignmentService assignmentService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createAssignment(@RequestParam("file") MultipartFile file,
                                                 @RequestBody AssignmentDTO assignmentDTO) {
        try {
            Course existingCourse = courseService.getCourseById(assignmentDTO.getCourseId());
            String fileUrl = file == null ? "" :  fileFunction.storeFile(file, String.valueOf(FileUploadDestination.ASSIGNMENT));
            Assignment assignment = assignmentService.createAssignment(assignmentDTO,existingCourse,fileUrl);
            return ResponseEntity.ok(assignment);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
