import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        String city = " ";

        try (ServerSocket serverSocket = new ServerSocket(ServerConfig.PORT);) { // стартуем сервер один(!) раз
            System.out.println("Да начнутся игры!");
            while (true) { // в цикле(!) принимаем подключения
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    out.println(String.format((city == " ") ? "???" : "Последний город: " + city));
                    String inMsg = in.readLine();
                    System.out.println(inMsg);
                    if (city == " " || inMsg.toLowerCase().charAt(0) == city.toLowerCase().charAt(city.length() - 1)) {
                        city = inMsg;
                        out.println("OK");
                    } else {
                        out.println("Not OK");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
