package org.landsreyk.app.event;

import lombok.extern.slf4j.Slf4j;
import org.landsreyk.app.model.Student;
import org.landsreyk.app.repository.StudentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@ConditionalOnProperty(name = "app.enableStartupListener", havingValue = "true")
@Slf4j
public class ApplicationStartupEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private static final Random RANDOM = new Random();

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.debug("INIT STARTED");
        StudentRepository studentRepository = event.getApplicationContext().getBean(StudentRepository.class);
        int numStudents = RANDOM.nextInt(100);
        for (int i = 1; i <= numStudents; i++) {
            String firstName = "firstName" + i;
            String lastName = "lastName" + i;
            Student student = new Student(firstName, lastName, RANDOM.nextInt(15, 35));
            studentRepository.save(student);
        }
        log.debug("INIT DONE");
    }
}
