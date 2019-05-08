package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ClienteController implements Initializable{
    //this class handles client UI and client connections to both servers
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private DataInputStream fromServerClient;
    private DataOutputStream toServerClient;
    private ObjectOutputStream objectToServer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public Button boton1;
    @FXML
    public Button boton2;
    @FXML
    public Button boton3;
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
        String invitados = Invitados.getText();
        String[] temp = invitados.split(",");
        List<String> invitadosLista = Arrays.asList(temp);
        //invitadosLista.add(Nombre.getText());
        Reunion R = new Reunion(new ArrayList<>(invitadosLista), Tema.getText(),Nombre.getText(),
                lugar.getText(), Finicio.getText(), Ffinal.getText());

        objectToServer.writeObject(R);


    }
    public void onPress3() throws IOException {
        //TODO:Rodrigo porfavor no te brajes haz el combobox y el refresh

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
            objectToServer = new ObjectOutputStream(socket.getOutputStream());


            //capture client name from textBox
            String clientName = Nombre.getText();

            String toSend = cs.socketNumber + ":" + clientName;

            //send the port  and the name of the client that was established for the clientServer to the MainServer
            toServer.writeUTF(toSend);

            new Thread(new HandleAclient(socketToCServer)).start();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class HandleAclient implements Runnable {
        private Socket clientSocket;


        public HandleAclient(Socket clientSocket) {
            this.clientSocket = clientSocket;


        }

        //thread to be run
        public void run() {
            try {
                //input/output handler for clientSocket might have to change to objectInput...
                //only read from never write to
                DataInputStream inputFromClient = new DataInputStream(
                        clientSocket.getInputStream());

                //input/output handler for serverSocket might have to change to objectInput...
                //only write to


                ObjectOutputStream objectOutputToClient = new ObjectOutputStream(
                        clientSocket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());


                //TODO: we need to determine a proper way to handle listening events
                //this loop handles listening of changes from mainServer side.

                while (true)
                {
                    Reunion reunion = (Reunion) objectInputStream.readObject();
                    //Platform.runLater(new Runnable() {public void run() {mensajeArea.appendText(reunion.toString());}});
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            String mensaje = reunion.toString();
                            mensajeArea.appendText(mensaje);
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}


