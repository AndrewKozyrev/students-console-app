package org.landsreyk.app;

import org.junit.jupiter.api.Test;
import org.landsreyk.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@ShellTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@MockBean(StudentRepository.class)
class StudentConsoleAppTests {
    @Autowired
    ShellTestClient client;

    @Test
    void test() {
        var session = client
                .interactive()
                .run();

        await().atMost(2, TimeUnit.SECONDS)
                .untilAsserted(() -> ShellAssertions.assertThat(session.screen()).containsText("shell"));

        session.write(session.writeSequence().text("help").carriageReturn().build());

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> ShellAssertions.assertThat(session.screen())
                .containsText("AVAILABLE COMMANDS"));
    }
}
