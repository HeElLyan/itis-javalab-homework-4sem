package ru.he.interfaces;

public interface JlmqConnectorBuilder {

    JlmqConnectorBuilder port(String port);

    JlmqConnectorBuilder usingStomp(boolean b);

    JlmqConnector connect();

}
