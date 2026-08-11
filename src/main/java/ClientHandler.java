import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket socket;
    private Tree<Integer> tree;
    public ClientHandler(Socket socket, Tree<Integer> tree) {
        this.socket = socket;
        this.tree = tree;
    }


    @Override
    public void run(){
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            output.println("Utworzono dla ciebie nowy wątek");
            String message = input.readLine();
            int number = Integer.parseInt(message);
            tree.insert(number);
            System.out.println(tree.draw());
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }
}
