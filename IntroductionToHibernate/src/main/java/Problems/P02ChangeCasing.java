package Problems;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class P02ChangeCasing {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String UPDATE_TOWNS_WITH_LENGTH_MORE_THAN_5 = "UPDATE Town t SET t.name = UPPER(t.name) WHERE LENGTH(t.name) <= 5";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE_NAME);
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(UPDATE_TOWNS_WITH_LENGTH_MORE_THAN_5).executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}

