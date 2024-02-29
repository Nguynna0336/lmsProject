package lhnthoi.lmsProject.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDetail {
    @Id
    @Column(name = "course_detail_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "lecture_id",referencedColumnName = "user_id")
    private User lecturer;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "user_id")
    private User student;

    @Column(name = "grade")
    private float grade;
}
