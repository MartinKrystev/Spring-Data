import entities.BankAccount;
import entities.CreditCard;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static entities.CardType.VIP;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        User user1 = new User("Dark", "Templar", "dt@blizzard.com", "From the shadows I come");

        CreditCard creditCard1 = new CreditCard(12345654321L, user1, VIP, 11, 2025);
        BankAccount bankAccount1 = new BankAccount(123456789L, user1,"Blizzard Bank", "AIUR BG SF XXX");

        user1.getBillingDetails().add(creditCard1);
        user1.getBillingDetails().add(bankAccount1);

        entityManager.persist(creditCard1);
        entityManager.persist(user1);
        entityManager.persist(bankAccount1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
