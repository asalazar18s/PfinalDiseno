package sample;

import javafx.fxml.Initializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    MainServer mServer = new MainServer();
    Integer portNumber = 8081;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //socket Initializer
        new Thread( ()-> {
            try {
                //Start Server with desired port on the socket

                ServerSocket serverSocket = new ServerSocket(8080);
                System.out.println(serverSocket.toString());


                //Maybe add info to pop up in terminal that server has been created
                System.out.println("Server Started: at " + new Date() + "\n");

                while (true) {
                    //accept a new socket connection
                    Socket clientSocket = serverSocket.accept();


                    //main thread handler
                    new Thread(new HandleAclient(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }





    class HandleAclient implements Runnable {
        private Socket clientSocket;
        /*p*/

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


                //loop will listen for any clientSocket info
                //if any change is made then we handle the serverSocket to receive the change
                //same idea on sercerSocket???

                int portToConnect = inputFromClient.readInt();
                System.out.println("port: " +portToConnect);
                Socket toCServerSocket = new Socket("localhost",portToConnect);

                DataOutputStream outputToClient = new DataOutputStream(
                        toCServerSocket.getOutputStream());


                while (true) {
                    String type = inputFromClient.readUTF();

                    outputToClient.writeUTF(type);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}


