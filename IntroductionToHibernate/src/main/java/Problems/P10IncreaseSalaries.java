package Problems;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class P10IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("UPDATE Employee AS e SET e.salary = (e.salary * 1.12) WHERE e.department.id IN" +
                        " (1, 2, 4, 11)")
                        .executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.name IN" +
                " ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                        .getResultList()
                        .forEach(e -> System.out.printf("%s %s ($%.2f)%n",e.getFirstName(), e.getLastName(), e.getSalary()));

        entityManager.close();
    }
}
