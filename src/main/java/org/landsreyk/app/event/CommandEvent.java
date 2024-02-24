package org.landsreyk.app.event;

import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@ToString
public class CommandEvent<T> extends ApplicationEvent {

    private final String command;
    private final T data;

    public CommandEvent(Object source, String command, T data) {
        super(source);
        this.command = command;
        this.data = data;
    }
}
