package Problems;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class P13RemoveTowns {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        String givenName = new Scanner(System.in).nextLine();

        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a WHERE a.town.name = :name", Address.class)
                .setParameter("name", givenName)
                .getResultList();

        entityManager.getTransaction().begin();

        for (Address a : addresses) {
            for (Employee e : a.getEmployees()) {
                e.setAddress(null);
            }
            entityManager.remove(a);
        }

        Town townToDelete = entityManager.createQuery("SELECT t FROM Town t WHERE t.name = :name", Town.class)
                .setParameter("name", givenName)
                .getSingleResult();

        entityManager.remove(townToDelete);

        if (addresses.size() > 1) {
            System.out.printf("%d addresses in %s deleted", addresses.size(), givenName);
        } else {
            System.out.printf("1 address in %s deleted", givenName);
        }

        entityManager.close();
    }
}
