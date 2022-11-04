import entities.Diagnose;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Patient patient1 = new Patient("Miroliuba", "Benatova", "Karuchka str.#43", LocalDate.parse("1970-01-01"), false);
        Visitation visitation1 = new Visitation(LocalDate.parse("2022-11-03"),"There is something wring with that patient.");
        Diagnose diagnose1 = new Diagnose("Schizophrenia", "Requires additional and specialized medical attention.");
        Medicament medicament1 = new Medicament("Cariprazine");
        Medicament medicament2 = new Medicament("Aripiprazole");

        entityManager.persist(patient1);
        entityManager.persist(visitation1);
        entityManager.persist(diagnose1);
        entityManager.persist(medicament1);
        entityManager.persist(medicament2);

        patient1.getPrescribedMeds().add(medicament1);
        patient1.getPrescribedMeds().add(medicament2);
        patient1.getDiagnoses().add(diagnose1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
