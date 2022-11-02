package Problems;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P11FindEmployeesByFirstName {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        String pattern = new Scanner(System.in).nextLine();

        String result = entityManager.createQuery("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern.toLowerCase() + "%")
                .getResultStream()
                .map(e -> String.format("%s %s - %s - ($%.2f)",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getName(),
                        e.getSalary()))
                .collect(Collectors.joining(System.lineSeparator()));

        System.out.println(result);

        entityManager.close();
    }
}
