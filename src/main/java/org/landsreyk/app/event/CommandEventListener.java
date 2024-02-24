package org.landsreyk.app.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CommandEventListener {

    @EventListener
    public <T> void listen(CommandEvent<T> event) {
        System.out.println(event);
    }
}
