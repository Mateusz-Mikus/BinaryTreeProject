import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Klasa klienta implementujaca architekturę JavyFX.
 * Odpowiada za nawiązywanie i obsługę połączenia poprzez gniazda TCP.
 * @author Mateusz Mikus
 */
public class Client extends Application {
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    private TextArea treeArea;
    private ComboBox<String> typeBox;
    private TextField argumentField;
    private Button bConnect, bInsert, bDelete, bSearch, bDraw;

    private TextField messageField;


    /**
     * Metoda rozruchowa.
     *
     * @param args Parametry wiersza poleceń.
     */
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drzewo binarne");

        typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Integer", "Double", "String", "Person");
        typeBox.setValue("Integer");

        bConnect = new Button("Połącz z serwerem");

        argumentField = new TextField();
        argumentField.setPromptText("Argument...");
        argumentField.setPrefWidth(100);

        bInsert = new Button("Insert");
        bDelete = new Button("Delete");
        bSearch = new Button("Search");
        bDraw = new Button("Draw");

        treeArea = new TextArea();
        treeArea.setEditable(false);
        treeArea.setFont(Font.font("Monospaced", 14));

        messageField = new TextField();
        messageField.setEditable(false);
        messageField.setPrefSize(100, 30);
        messageField.setAlignment(Pos.CENTER);

        HBox topPanel = new HBox(10);
        topPanel.getChildren().addAll(typeBox, bConnect);
        topPanel.setAlignment(Pos.CENTER);


        HBox operationPanel = new HBox(10);
        operationPanel.getChildren().addAll(bInsert, bDelete, bSearch, bDraw, argumentField);
        operationPanel.setAlignment(Pos.CENTER);

        VBox controlPanel = new VBox(15);
        controlPanel.getChildren().addAll(topPanel, operationPanel);


        BorderPane root = new BorderPane();

        root.setPadding(new Insets(15));

        root.setTop(controlPanel);
        root.setCenter(treeArea);
        root.setBottom(messageField);

        BorderPane.setMargin(treeArea, new Insets(15, 0, 0, 0));


        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();


        bConnect.setOnAction(e -> {
            establishConnection();
        });

        bInsert.setOnAction(e -> {
            insertValue(argumentField.getText());
        });

        bSearch.setOnAction(e -> {
            searchValue(argumentField.getText());
        });

    }


    public void establishConnection(){
        int port = 4444;
        String ip = "localhost";
        try {
            socket = new Socket(ip, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            messageField.setText("Połączono pomyślnie");

        } catch (Exception e ){
            messageField.setText("Wystąpił błąd: " + e.getMessage());
        }
    }

    public void insertValue(String value) {
        try {
            int intValue = Integer.parseInt(value);
            output.println("INSERT " + intValue);

            String responseFromServer = input.readLine();
            messageField.setText(responseFromServer);

        } catch(NumberFormatException e) {
            messageField.setText("Musisz podać liczbę");
        } catch(Exception e){
            messageField.setText("Wystąpił błąd: " + e.getMessage());
        }
    }

    public void searchValue(String value) {
        try {
            int intValue = Integer.parseInt(value);
            output.println("SEARCH " + intValue);

            String responseFromServer = input.readLine();
            messageField.setText(responseFromServer);

        } catch (NumberFormatException e){
            messageField.setText("Musisz podać liczbę");
        } catch(Exception e ){
            messageField.setText("Wystąpił błąd: " + e.getMessage());

        }
    }
}