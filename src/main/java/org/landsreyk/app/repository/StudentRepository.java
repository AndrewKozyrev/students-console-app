package org.landsreyk.app.repository;

import org.landsreyk.app.model.Student;

import java.util.Set;

public interface StudentRepository {
    Set<Student> findAll();
    Student save(Student student);
    void deleteById(Long id);

    void deleteAll();
}
