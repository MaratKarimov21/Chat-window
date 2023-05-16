import ru.smak.net.Client;
import ru.smak.net.GraphicIO;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            var client = new Client("localhost", 5003);

            GraphicIO OIHandler = new GraphicIO();

            client.setIOHandler(OIHandler);

            client.startProcessing();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
