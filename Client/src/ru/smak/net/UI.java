package ru.smak.net;

import java.io.IOException;
import java.util.function.Function;

public interface UI {
    void displayMessage(String message);
    void displaySystemMessage(String message);
    void startSending(Function<String,Void> send) throws IOException;
}
