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


                if (command.equals("INSERT")) {
                    int value = Integer.parseInt(parts[1]);
                    tree.insert(value);
                    output.println("Dodano do drzewa binarnego wartość: " + value);
                } else if (command.equals("SEARCH")) {
                    int value = Integer.parseInt(parts[1]);
                    boolean result;
                    result = tree.search(value);
                    if (result) {
                        output.println("Znaleziono wartość: " + value);
                    } else {
                        output.println("Nie znaleziono wartości: " + value);
                    }
                } else if (command.equals("DELETE")){
                    int value = Integer.parseInt(parts[1]);
                    tree.deleteNode(value);
                    output.println("Usunięto wartość: " + value);
                } else if (command.equals("DRAW")) {
                    String drawnTree = tree.draw();
                    String[] lines = drawnTree.split("\n");
                    output.println(lines.length);

                    for(String line : lines) {
                        output.println(line);
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }
}
