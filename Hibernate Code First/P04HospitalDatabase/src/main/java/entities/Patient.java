package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static common.ExceptionMessages.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private String picture;

    @Column(name = "has_insurance", nullable = false)
    private boolean hasInsurance;

    @OneToMany(mappedBy = "patients")
    private List<Visitation> visitations;

    @ManyToMany
    @JoinTable(name = "patients_diagnoses",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"))
    private List<Diagnose> diagnoses;

    @ManyToMany
    @JoinTable(name = "patients_medicaments",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private List<Medicament> prescribedMeds;

    public Patient() {
        this.visitations = new ArrayList<>();
        this.diagnoses = new ArrayList<>();
        this.prescribedMeds = new ArrayList<>();
    }

    public Patient(String firstName, String lastName, String address, LocalDate birthDate, boolean hasInsurance) {
        this();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setBirthDate(birthDate);
        this.hasInsurance = hasInsurance;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new NullPointerException(INCORRECT_FIRST_NAME);
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new NullPointerException(INCORRECT_LAST_NAME);
        }
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new NullPointerException(INCORRECT_ADDRESS);
        }
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            throw new NullPointerException(INVALID_DATE);
        }
        this.birthDate = birthDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public List<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(List<Visitation> visitations) {
        this.visitations = visitations;
    }

    public List<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<Medicament> getPrescribedMeds() {
        return prescribedMeds;
    }

    public void setPrescribedMeds(List<Medicament> prescribedMeds) {
        this.prescribedMeds = prescribedMeds;
    }
}
