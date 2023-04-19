import ru.smak.net.Client;
import ru.smak.net.ConsoleIO;
import ru.smak.net.MainWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {

            var client = new Client("localhost", 5003);
            MainWindow OIHandler = new MainWindow();
            client.setIOHandler(OIHandler);

            client.startReceiving();
            OIHandler.setVisible(true);

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
