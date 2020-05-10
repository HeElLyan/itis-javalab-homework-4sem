package ru.he.interfaces.impls;

import ru.he.interfaces.JlmqConnector;
import ru.he.interfaces.JlmqConnectorBuilder;
import ru.he.transport.DefaultTransportSetting;
import ru.he.transport.TransportClient;
import ru.he.transport.TransportClientProvider;
import ru.he.transport.TransportSession;

@SuppressWarnings("FieldCanBeLocal")
public class DefaultJlmqConnectorBuilder implements JlmqConnectorBuilder {

    private final TransportClientProvider transportClientProvider;

    private String  port;
    private boolean usingStomp;

    public DefaultJlmqConnectorBuilder(TransportClientProvider clientProvider) {
        this.transportClientProvider = clientProvider;
    }

    @Override
    public JlmqConnectorBuilder port(String port) {
        this.port = port;
        return this;
    }

    @Override
    public JlmqConnectorBuilder usingStomp(boolean usingStomp) {
        this.usingStomp = usingStomp;
        return this;
    }

    @Override
    public JlmqConnector connect() {
        TransportClient client = getTransportClientInstance();
        TransportSession session = client.connect(new DefaultTransportSetting(port));
        return new DefaultJlmqConnector(session);
    }

    private TransportClient getTransportClientInstance() {
        return transportClientProvider.provide(usingStomp);
    }

}
