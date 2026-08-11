import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 4444;
        Tree<Integer> sharedTree = new Tree<>();

        try (ServerSocket serverSocket = new ServerSocket(port)){
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Znaleziono połączenie: " + socket.getInetAddress());

                ClientHandler threadForTheClient = new ClientHandler(socket, sharedTree);
                threadForTheClient.start();
            }
        } catch(Exception e) {
            System.out.println("Znaleziono błąd: " + e.getMessage());
        }
    }
}
