package bhcc.edu.mandip;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    private TextField tfn = new TextField();
    private TextField tln = new TextField();
    private TextField tun = new TextField();
    private PasswordField tpw = new PasswordField();
    private TextField tun0 = new TextField();
    private PasswordField tpw0 = new PasswordField();

    // Button for sending a student to the server
    private Button btRegister = new Button("Register");
    private Button btlogin = new Button("Login");
    private Button btsignup = new Button("Sign-In");
    private Button btnewuser = new Button("Create a new user");
    // Host name or ip
    String host = "localhost";

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        pane.add(btsignup, 0, 3);

        pane.setVgap(10);
        pane.setHgap(10);
        pane.setAlignment(Pos.CENTER);

        pane.add(new Label("Username"), 0, 0);
        pane.add(tun0, 1, 0);

        pane.add(new Label("Password"), 0, 1);
        pane.add(tpw0, 1, 1);

        pane.setVgap(10);
        pane.setHgap(10);

        pane.add(btnewuser, 1, 3);

        btsignup.setOnAction(new infocheck());
        // User Login Scene;
        GridPane pane2 = new GridPane();
        Scene scene2 = new Scene(pane2, 300, 200);
        btnewuser.setOnAction(e -> {

            primaryStage.setScene(scene2);
            pane2.add(new Label("First Name"), 0, 0);
            pane2.add(tfn, 1, 0);

            pane2.add(new Label("Last Name"), 0, 1);
            pane2.add(tln, 1, 1);

            pane2.add(new Label("Username"), 0, 2);
            pane2.add(tun, 1, 2);

            pane2.add(new Label("Password"), 0, 3);
            pane2.add(tpw, 1, 3);

            pane2.add(btRegister, 1, 4);

            GridPane.setHalignment(btRegister, HPos.RIGHT);
            pane2.setVgap(5);
            pane2.setHgap(5);
            pane2.setAlignment(Pos.CENTER_LEFT);

            btRegister.setOnAction(new Button2Listener());

            pane2.add(btlogin, 1, 4);
            GridPane.setHalignment(btlogin, HPos.LEFT);
            pane2.setVgap(5);
            pane2.setHgap(5);
            pane2.setAlignment(Pos.CENTER_LEFT);

            tfn.setPrefColumnCount(15);
            tln.setPrefColumnCount(15);
            tun.setPrefColumnCount(10);
            tpw.setPrefColumnCount(3);

        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Client Login"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /** Handle button action */
    private class Button2Listener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            try {
                try (// Establish connection with the server
                        Socket socket = new Socket(host, 8000)) {
                    // Create an output stream to the server
                    ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());

                    // Get text field
                    String firstname = tfn.getText().trim();
                    String lastname = tln.getText().trim();
                    String username = tun.getText().trim();
                    String password = tpw.getText().trim();

                    // Create a Student object and send to the server
                    ClientInfo s = new ClientInfo(firstname, lastname, username, password);
                    toServer.writeObject(s);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
