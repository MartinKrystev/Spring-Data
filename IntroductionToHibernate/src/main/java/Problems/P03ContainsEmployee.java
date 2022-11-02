package Problems;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P03ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        String[] fullName = scanner.nextLine().split(" ");

        String firstName = fullName[0];
        String lastName = fullName[1];

        Long matchCount = entityManager.createQuery("SELECT count(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln", Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();

        if (matchCount != 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.close();
    }
}
