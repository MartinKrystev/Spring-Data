package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {

    @Column(name = "average_grade", nullable = false)
    private Double averageGrade;

    @Column
    private int attendance;

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;

    public Student() {
        super();
        this.courses = new HashSet<>();
    }

    public Student(String firstName, String lastName, String phoneNumber, Double averageGrade, int attendance) {
        this();
        this.averageGrade = averageGrade;
        this.attendance = attendance;
        this.courses = new HashSet<>();
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
