package ru.smak.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class ConsoleIO implements UI{
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displaySystemMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void startSending(Function<String,Void> send) throws IOException {
        var stop = false;
        var s = new BufferedReader(new InputStreamReader(System.in));
        while (!stop){
            var userData = s.readLine();
            if (userData.equals("STOP")) {
                stop = true;
            } else
                send.apply(userData);
        }
    }
}
