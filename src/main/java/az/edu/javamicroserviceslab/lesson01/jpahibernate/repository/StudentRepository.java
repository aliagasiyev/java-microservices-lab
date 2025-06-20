package az.edu.javamicroserviceslab.lesson01.jpahibernate.repository;

import az.edu.javamicroserviceslab.lesson01.jpahibernate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
