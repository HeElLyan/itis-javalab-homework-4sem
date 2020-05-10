package ru.he.transport;

public interface TransportClientProvider {

    TransportClient provide(boolean usingStomp);

}
