package entities;

import javax.persistence.*;
import java.time.LocalDate;

import static common.ExceptionMessages.INCORRECT_COMMENT;
import static common.ExceptionMessages.INVALID_DATE;

@Entity
@Table(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    private Patient patients;

    public Visitation() {}

    public Visitation(LocalDate date, String comments) {
        this();
        this.setDate(date);
        this.setComments(comments);
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null || date.isAfter(LocalDate.now())) {
            throw new NullPointerException(INVALID_DATE);
        }
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        if (comments == null || comments.trim().isEmpty()) {
            throw new NullPointerException(INCORRECT_COMMENT);
        }
        this.comments = comments;
    }

    public Patient getPatients() {
        return patients;
    }

    public void setPatients(Patient patients) {
        this.patients = patients;
    }
}
