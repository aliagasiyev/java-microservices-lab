package az.edu.javamicroserviceslab;

import az.edu.javamicroserviceslab.lesson01.jpahibernate.entity.Student;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public abstract class JavaMicroservicesLabApplication implements CommandLineRunner {

    private final EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(JavaMicroservicesLabApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Smith");
        entityManager.persist(student);
    }

    //(JPA)-> (Hibernate)Session ->(Hibernate)SessionImpl
    //(JPA)-> (Hibernate)SessionFactory
    //ConnectionPool
    //(Open)Session->(Acquire)Connection(POOL) ->DB
}

