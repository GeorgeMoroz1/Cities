import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Введите название города:");
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket(ServerConfig.HOST, ServerConfig.PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println(in.readLine());//принимаем ??? или название города от сервера
            out.println(String.format(scanner.nextLine()));
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
