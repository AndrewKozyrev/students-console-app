package org.landsreyk.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.landsreyk.app.event.CommandEvent;
import org.landsreyk.app.model.Student;
import org.landsreyk.app.repository.StudentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class StudentCommands {

    private final StudentRepository studentRepository;

    private final ApplicationEventPublisher publisher;

    @ShellMethod(key = {"list", "ls"})
    public void listStudents() {
        for (Student student : studentRepository.findAll()) {
            System.out.println(student);
        }
    }

    @ShellMethod(key = "add")
    public void addStudent(String firstName, String lastName, Integer age) {
        Student student = studentRepository.save(new Student(firstName, lastName, age));
        publisher.publishEvent(new CommandEvent<>(this, "add", student));
    }

    @ShellMethod(key = "delete")
    public void deleteStudent(Long id) {
        boolean isDeleted = studentRepository.deleteById(id);
        if (isDeleted) {
            publisher.publishEvent(new CommandEvent<>(this, "delete", id));
        }
    }

    @ShellMethod(key = {"purge", "delete-all", "wipe"})
    public void clearStudents() {
        List<Long> ids = studentRepository.findAll().stream().map(Student::getId).toList();
        ids.forEach(this::deleteStudent);
    }
}
