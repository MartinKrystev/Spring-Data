import entities.Course;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Student student1 = new Student
                ("Pesho", "Peshov", "0889-888-888", 5.5, 1);
        Teacher teacher1 = new Teacher
                ("Ivan", "Ivanov", "+1-531-231-22-54", "ivan.ivanov@softuni.bg", new BigDecimal(150));

        Course course1 = new Course
                ("Java-DB", LocalDate.parse("2022-11-03"), LocalDate.parse("2022-12-03"), 5);
        Course course2 = new Course
                ("Java-WEB", LocalDate.parse("2023-01-03"), LocalDate.parse("2023-02-03"), 10);


        entityManager.persist(student1);
        entityManager.persist(course1);
        entityManager.persist(course2);
        entityManager.persist(teacher1);

        student1.getCourses().add(course2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
