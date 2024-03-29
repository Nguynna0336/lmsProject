package lhnthoi.lmsProject.Models;

import lhnthoi.lmsProject.Enums.Week;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @Column(name = "course_id")
    private String courseId;

    @ManyToOne
    @JoinColumn(name = "lecturer_id",referencedColumnName = "user_id")
    private User lecturer;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "max_quantity")
    private int maxQuantity;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Week day;

    @Column(name = "duration")
    private float duration;

    @Column(name = "current_quantity")
    private int currentQuantity;
}
