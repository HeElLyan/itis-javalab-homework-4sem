package ru.he;

import ru.he.interfaces.JlmqConnectorBuilder;
import ru.he.interfaces.impls.DefaultJlmqConnectorBuilder;
import ru.he.transport.DefaultTransportClientProvider;

public abstract class Jlmq {

    public static JlmqConnectorBuilder connector() {
        return new DefaultJlmqConnectorBuilder(new DefaultTransportClientProvider());
    }

}
