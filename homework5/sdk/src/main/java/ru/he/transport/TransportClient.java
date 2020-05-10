package ru.he.transport;

public interface TransportClient {

    TransportSession connect(TransportSettings setting);

}
