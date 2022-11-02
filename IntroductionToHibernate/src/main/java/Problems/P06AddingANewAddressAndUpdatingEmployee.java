package Problems;

import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class P06AddingANewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        String givenLastName = new Scanner(System.in).nextLine();
        String newAddressText = "Vitoshka 15";

        entityManager.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText(newAddressText);

        entityManager.persist(newAddress);

        entityManager.createQuery("UPDATE Employee e SET e.address = :newAddress WHERE e.lastName = :ln")
                .setParameter("newAddress", newAddress)
                .setParameter("ln", givenLastName)
                .executeUpdate();


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
