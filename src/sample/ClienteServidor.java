package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ClienteServidor {
    //this class handles the clientServer

    public Integer socketNumber;

    ClienteServidor(Integer PortNumber) throws IOException {
        //constructor that establishes the clientServer
        socketNumber = 0;

        //create new server with a randomized portNumber
        ServerSocket serverSocket = new ServerSocket(PortNumber);

        //save the serverPort in the socketNumber so that its sent to the client
        //so that the client sends it to the Server
        socketNumber = serverSocket.getLocalPort();

        //this thread will handle connections to the clientServer
        new Thread( ()-> {
            try {

                System.out.println("Server Started: at " + new Date() + "\n");

                while (true) {

                    //accept a new socket connection from the MainServer
                    Socket mainServerClientSocket = serverSocket.accept();

                    //client handler
                    new Thread(new HandleAclient(mainServerClientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
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


                //TODO: we need to determine a proper way to handle listening events
                //this loop handles listening of changes from mainServer side.
                
                while (true) {

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
