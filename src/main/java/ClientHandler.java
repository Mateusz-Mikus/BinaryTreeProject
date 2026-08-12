import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket socket;
    private Tree<Integer> integerTree;
    private Tree<String> stringTree;
    private Tree<Double> doubleTree;
    private Tree<Person> personTree;
    public ClientHandler(Socket socket, Tree<Integer> integerTree, Tree<String> stringTree, Tree<Double> doubleTree, Tree<Person> personTree) {
        this.socket = socket;
        this.integerTree = integerTree;
        this.stringTree = stringTree;
        this.doubleTree = doubleTree;
        this.personTree = personTree;
    }


    @Override
    public void run(){
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = input.readLine()) != null) {
                String[] parts = message.split(" ");
                String type = parts[0].toUpperCase();
                String command = parts[1].toUpperCase();
                String theValue = (parts.length > 2) ? parts[2] : "";

                switch (type) {
                    case "INTEGER":
                        commandForInteger(command, output, theValue, integerTree);
                        break;
                    case "STRING":
                        commandForString(command, output, theValue, stringTree);
                        break;
                    case "DOUBLE":
                        commandForDouble(command, output, theValue, doubleTree);
                        break;
                    case "PERSON":
                        commandForPerson(command, output, theValue, personTree);
                        break;

                }


            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }

    public void commandForInteger(String command, PrintWriter output, String theValue, Tree<Integer> integerTree){
        if (command.equals("DRAW")) {
            String drawnTree = integerTree.draw();
            String[] lines = drawnTree.split("\n");
            output.println(lines.length);

            for(String line : lines) {
                output.println(line);
            }
            return;
        }
        try {
            int value = Integer.parseInt(theValue);
            if (command.equals("INSERT")) {

                integerTree.insert(value);
                output.println("Dodano do drzewa binarnego wartość: " + value);
            } else if (command.equals("SEARCH")) {

                boolean result;
                result = integerTree.search(value);
                if (result) {
                    output.println("Znaleziono wartość: " + value);
                } else {
                    output.println("Nie znaleziono wartości: " + value);
                }
            } else if (command.equals("DELETE")){

                integerTree.deleteNode(value);
                output.println("Usunięto wartość: " + value);
            }
        } catch(NumberFormatException e) {
            output.println("Musisz wpisać typ integer");
        }
    }

    public void commandForString(String command, PrintWriter output, String theValue, Tree<String> stringTree){
        if (command.equals("INSERT")) {

            stringTree.insert(theValue);
            output.println("Dodano do drzewa binarnego wartość: " + theValue);
        } else if (command.equals("SEARCH")) {

            boolean result;
            result = stringTree.search(theValue);
            if (result) {
                output.println("Znaleziono wartość: " + theValue);
            } else {
                output.println("Nie znaleziono wartości: " + theValue);
            }
        } else if (command.equals("DELETE")){

            stringTree.deleteNode(theValue);
            output.println("Usunięto wartość: " + theValue);
        } else if (command.equals("DRAW")) {
            String drawnTree = stringTree.draw();
            String[] lines = drawnTree.split("\n");
            output.println(lines.length);

            for(String line : lines) {
                output.println(line);
            }
        }
    }

    public void commandForDouble(String command, PrintWriter output, String theValue, Tree<Double> doubleTree){
        if (command.equals("DRAW")) {
            String drawnTree = doubleTree.draw();
            String[] lines = drawnTree.split("\n");
            output.println(lines.length);

            for(String line : lines) {
                output.println(line);
            }
            return;
        }

        try {
            double value = Double.parseDouble(theValue);

            if (command.equals("INSERT")) {

                doubleTree.insert(value);
                output.println("Dodano do drzewa binarnego wartość: " + value);
            } else if (command.equals("SEARCH")) {

                boolean result;
                result = doubleTree.search(value);
                if (result) {
                    output.println("Znaleziono wartość: " + value);
                } else {
                    output.println("Nie znaleziono wartości: " + value);
                }
            } else if (command.equals("DELETE")){

                doubleTree.deleteNode(value);
                output.println("Usunięto wartość: " + value);
            }
        } catch(NumberFormatException e) {
            output.println("Musisz wpisać typ double");
        }

    }

    public void commandForPerson(String command, PrintWriter output, String theValue, Tree<Person> personTree){
        if (command.equals("DRAW")) {
            String drawnTree = personTree.draw();
            String[] lines = drawnTree.split("\n");
            output.println(lines.length);

            for(String line : lines) {
                output.println(line);
            }
            return;
        }

        try {
            String[] parts = theValue.split("_");
            int age = Integer.parseInt(parts[1]);

            String name = parts[0];
            Person p = new Person(age, name);
            if (command.equals("INSERT")) {

                personTree.insert(p);
                output.println("Dodano do drzewa binarnego wartość: " + p.toString());
            } else if (command.equals("SEARCH")) {

                boolean result;
                result = personTree.search(p);
                if (result) {
                    output.println("Znaleziono wartość: " + p);
                } else {
                    output.println("Nie znaleziono wartości: " + p);
                }
            } else if (command.equals("DELETE")) {

                personTree.deleteNode(p);
                output.println("Usunięto wartość: " + p);
            }
        } catch(Exception e) {
            output.println("Musisz wpisać typ Person. Przykładowo: Marek_25");
        }
    }


}
