package org.landsreyk.app.service;

import lombok.RequiredArgsConstructor;
import org.landsreyk.app.event.CommandEvent;
import org.landsreyk.app.model.Student;
import org.landsreyk.app.repository.StudentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
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
        studentRepository.deleteById(id);
        publisher.publishEvent(new CommandEvent<>(this, "delete", id));
    }

    @ShellMethod(key = "clear")
    public void clearStudents() {
        studentRepository.deleteAll();
    }
}
