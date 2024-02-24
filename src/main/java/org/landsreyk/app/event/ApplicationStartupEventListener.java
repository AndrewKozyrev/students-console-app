package org.landsreyk.app.event;

import lombok.extern.slf4j.Slf4j;
import org.landsreyk.app.model.Student;
import org.landsreyk.app.repository.StudentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@ConditionalOnProperty(name = "app.enable-startup-listener", havingValue = "true")
@Slf4j
public class ApplicationStartupEventListener {

    private static final Random RANDOM = new Random();

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.debug("INIT STARTED");
        StudentRepository studentRepository = event.getApplicationContext().getBean(StudentRepository.class);
        int numStudents = RANDOM.nextInt(100);
        for (int i = 1; i <= numStudents; i++) {
            String firstName = "firstName" + i;
            String lastName = "lastName" + i;
            int age = RANDOM.nextInt(15, 35);
            Student student = new Student(firstName, lastName, age);
            studentRepository.save(student);
        }
        log.debug("INIT DONE");
    }
}
