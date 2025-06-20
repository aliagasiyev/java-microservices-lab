package az.edu.javamicroserviceslab.lesson01.jpahibernate.service;

import az.edu.javamicroserviceslab.lesson01.jpahibernate.entity.Student;
import az.edu.javamicroserviceslab.lesson01.jpahibernate.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student tapılmadı"));
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Silinəcək student tapılmadı");
        }
        studentRepository.deleteById(id);
    }
}
