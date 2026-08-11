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

            String message;
            while ((message = input.readLine()) != null) {
                String[] parts = message.split(" ");
                String command = parts[0].toUpperCase();
                int value = Integer.parseInt(parts[1]);

                if (command.equals("INSERT")) {
                    tree.insert(value);
                    output.println("Dodano do drzewa binarnego wartość: " + value);
                }
            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }
}
