package ru.smak.net;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private String _host;
    private int _port;
    private Socket s;
    private NetIO nio;
    private UI OIHandler;
    public Client(String host, int port) throws IOException {
        _host = host;
        _port = port;
        s = new Socket(_host, _port);
        nio = new NetIO(s);
    }

    public void startProcessing(){
        new Thread(()->{
            try {
                nio.startReceiving(this::parse);
            } catch (IOException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }).start();
        new Thread(()->{
            try {
                OIHandler.startSending(this::send);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public Void parse(String d){
        var data = d.split(":", 2); // INTRODUCE:представьтесь
        Command cmd = null;
        try {
            cmd = Command.valueOf(data[0]);
        } catch (Exception ignored){
        }
        switch (cmd) {
            case INTRODUCE -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
//                    OIHandler.displaySystemMessage(data[1]);
                } else {
//                    OIHandler.displaySystemMessage("Представьтесь, пожалуйста:");
                }
            }
            case MESSAGE -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    OIHandler.displayMessage(data[1]);
                }
            }
            case LOGGED_IN -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    OIHandler.displayMessage("Пользователь " + data[1] + " вошёл в чат");
                }
            }
            case LOGGED_OUT -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    OIHandler.displayMessage("Пользователь " + data[1] + " покинул чат");
                }
            }
            case null -> {

            }
        }
        return null;
    }

    public Void send(String userData) {
        try {
            nio.sendData(userData);

        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
        return null;
    }

    public void setIOHandler(UI OIHandler) {
        this.OIHandler = OIHandler;
    }
}
