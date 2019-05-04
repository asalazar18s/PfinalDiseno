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

        connectToServers();
        //toServer.writeUTF("connected");



        /*new Thread(() ->
        {

            try
            {
                while(true) {
                    String mensaje = fromServerClient.readUTF();
                    mensajeArea.appendText(mensaje);

                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }).start();*/

    }

    public void onPress2() throws IOException {
        toServerClient.writeUTF(mensajeArea.getText());
    }


    private void connectToServers() {

        try
        {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8080);

            //crea clienteServidor con puerto aleatorio
            ClienteServidor cs = new ClienteServidor(0);


            Socket socketToCServer = new Socket("localhost", cs.socketNumber);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());
            fromServerClient = new DataInputStream(socketToCServer.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
            toServerClient = new DataOutputStream(socketToCServer.getOutputStream());

            toServer.writeInt(cs.socketNumber);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        // Control the game on a separate thread

    }
}
