package Problems;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class P12EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.createQuery("SELECT e.department.name, MAX(e.salary) FROM Employee e GROUP BY e.department.id " +
                        " HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList()
                .forEach(e -> System.out.printf("%s %.2f%n", e[0], e[1]));

        entityManager.close();
    }
}
