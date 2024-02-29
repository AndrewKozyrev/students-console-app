package org.landsreyk.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.landsreyk.app.event.CommandEvent;
import org.landsreyk.app.model.Student;
import org.landsreyk.app.repository.StudentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class StudentCommands {

    private final StudentRepository studentRepository;

    private final ApplicationEventPublisher publisher;

    @ShellMethod(key = {"list", "list-students"}, value = "Вывод всех студентов в консоль.")
    public void listStudents() {
        for (Student student : studentRepository.findAll()) {
            System.out.println(student);
        }
    }

    @ShellMethod(key = {"add", "add-student"}, value = "Добавить нового студента.")
    public void addStudent(@ShellOption(value = {"--fn", "--first-name"}, help = "First name") String firstName,
                           @ShellOption(value = {"--ln", "--last-name"}, help = "Last name") String lastName,
                           @ShellOption(value = {"--a", "--age"}, help = "Age") Integer age) {
        Student student = studentRepository.save(new Student(firstName, lastName, age));
        publisher.publishEvent(new CommandEvent<>(this, "add", student));
    }

    @ShellMethod(key = {"delete", "delete-student"}, value = "Удалить студента по идентификатору (id).")
    public void deleteStudent(@ShellOption(value = {"--id"}, help = "ID of a student to be deleted") Long id) {
        boolean isDeleted = studentRepository.deleteById(id);
        if (isDeleted) {
            publisher.publishEvent(new CommandEvent<>(this, "delete", id));
        }
    }

    @ShellMethod(key = {"cls", "clear-students"}, value = "Полностью очистить список студентов.")
    public void clearStudents() {
        List<Long> ids = studentRepository.findAll().stream().map(Student::getId).toList();
        ids.forEach(this::deleteStudent);
    }
}
