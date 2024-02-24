package org.landsreyk.app.repository;

import org.landsreyk.app.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class InMemoryStudentRepository implements StudentRepository {
    private final TreeSet<Student> students = new TreeSet<>(Comparator.comparing(Student::getId));
    private final TreeSet<Long> idPool = new TreeSet<>();

    private long idCounter = 0;

    @Override
    public Set<Student> findAll() {
        return Collections.unmodifiableSortedSet(students);
    }

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            Long id;
            if (idPool.isEmpty()) {
                id = ++idCounter;
            } else {
                id = idPool.pollFirst();
            }
            student.setId(id);
        }
        students.add(student);
        return student;
    }

    @Override
    public void deleteById(Long id) {
        students.removeIf(s -> s.getId().equals(id));
        idPool.add(id);
    }

    @Override
    public void deleteAll() {
        students.clear();
    }
}
