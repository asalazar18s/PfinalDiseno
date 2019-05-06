package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController implements Initializable{
    //this class handles client UI and client connections to both servers
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private DataInputStream fromServerClient;
    private DataOutputStream toServerClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public Button boton1;
    @FXML
    public Button boton2;
    @FXML
    public TextField Nombre;
    @FXML
    public TextField Tema;
    @FXML
    public TextField Invitados;
    @FXML
    public TextField lugar;
    @FXML
    public TextField Finicio;
    @FXML
    public TextField Ffinal;
    @FXML
    public TextArea mensajeArea;


    public void onPress() throws IOException {
        System.out.println("");

        //TODO: decide how or when to connect to the servers???
        connectToServers();
    }

    public void onPress2() throws IOException {
        toServerClient.writeUTF(mensajeArea.getText());
    }


    private void connectToServers() {

        try
        {
            // Create a socket to connect to the Mainserver
            Socket socket = new Socket("localhost", 8080);

            //establishes a clientServer with a random port
            ClienteServidor cs = new ClienteServidor(0);

            //create a socket to the clientServer
            Socket socketToCServer = new Socket("localhost", cs.socketNumber);

            // Create an input stream to receive data from the clientServer
            fromServerClient = new DataInputStream(socketToCServer.getInputStream());

            // Create an output stream to send data to the MainServer
            toServer = new DataOutputStream(socket.getOutputStream());

            //send the port that was established for the clientServer to the MainServer
            toServer.writeInt(cs.socketNumber);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
