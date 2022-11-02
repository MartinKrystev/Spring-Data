package Problems;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P08GetEmployeeWithProject {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        int givenId = new Scanner(System.in).nextInt();

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class)
                .setParameter("id", givenId)
                .getResultList()
                .forEach(System.out::println);

        entityManager.close();
    }
}
