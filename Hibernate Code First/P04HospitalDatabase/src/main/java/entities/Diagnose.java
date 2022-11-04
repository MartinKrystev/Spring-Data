package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static common.ExceptionMessages.INCORRECT_COMMENT;
import static common.ExceptionMessages.INCORRECT_FULL_NAME;

@Entity
@Table(name = "diagnoses")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comments;

    @ManyToMany(mappedBy = "diagnoses")
    private List<Patient> patients;

    public Diagnose() {
        this.patients = new ArrayList<>();
    }

    public Diagnose(String name, String comments) {
        this();
        this.setName(name);
        this.setComments(comments);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(INCORRECT_FULL_NAME);
        }
        this.name = name;
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
