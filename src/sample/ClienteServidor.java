package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ClienteServidor {

    public Integer socketNumber;

    ClienteServidor(Integer PortNumber) throws IOException {
        socketNumber = 0;
        ServerSocket serverSocket = new ServerSocket(PortNumber);
        System.out.println(serverSocket.toString());
        socketNumber = serverSocket.getLocalPort();
        new Thread( ()-> {
            try {
                //Start Server with desired port on the socket

                //Maybe add info to pop up in terminal that server has been created
                System.out.println("Server Started: at " + new Date() + "\n");

                //runLater commands can be avoided unless required for th GUI

                //loop to maintain server up and running

                while (true) {
                    //accept a new socket connection
                    Socket clientSocket = serverSocket.accept();



                    //create new socket connection to clientServer
                    //Socket toCServerSocket = new Socket("localhost", portNumber);



                    //portNumber ++;
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
                //same idea on serverSocket???
                while (true) {
                    //String type = inputFromClient.readUTF();

                    //System.out.println(type);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
