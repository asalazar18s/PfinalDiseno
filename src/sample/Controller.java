package sample;

import javafx.fxml.Initializable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    //This handles the Main Server UI and the init of the main server


    //Data model that has to hold the sockets that are connected to mainServer
    MainServer mServer = new MainServer();
    Integer portNumber = 8081;
    static String clientName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Method that runs at start of mainServer UI

        //this thread handles the creation of the MainServer and
        //the loop to constantly accept new connections
        new Thread( ()-> {
            try {
                //Start the server
                ServerSocket serverSocket = new ServerSocket(8080);

                //show in terminal server has started
                System.out.println("Server Started: at " + new Date() + "\n");

                //main loop that will accept connections
                while (true) {
                    //accept a new socket connection, this socket comes from client
                    Socket clientSocket = serverSocket.accept();

                    //thread that will handle what to do with new socket
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
                //clientSocket only writes to MainServer
                //establish the inputStream
                DataInputStream inputFromClient = new DataInputStream(
                        clientSocket.getInputStream());

                //Instance of Reunion
                ObjectInputStream objectFromClient=new ObjectInputStream(
                        clientSocket.getInputStream());


                //get from the client the port that was established for the client server
                String  receivedInfo = inputFromClient.readUTF();

                //parse the receivedInfo into port number and clientName
                clientName = receivedInfo.split(":")[1];
                int portToConnect = Integer.valueOf(receivedInfo.split(":")[0]);

                System.out.println("port: " +portToConnect + " Name: " + clientName);

                //create a connection socket to the clientServer
                Socket toCServerSocket = new Socket("localhost",portToConnect);

                //create an output stream to the clientServer Socket


                //store client and socket info into Administrador dictionary
                ArrayList<Socket> holder = new ArrayList<>();
                holder.add(clientSocket);
                holder.add(toCServerSocket);
                //[0] socket that listens to client
                //[1] socket that writes to clientServer
                mServer.Administrador.put(clientName,holder);

                //TODO: we need to determine a proper way to handle listening events
                while (true) {
                    //String type = inputFromClient.readUTF();
                    //outputToClient.writeUTF(type);

                    Reunion reunionReceived = (Reunion) objectFromClient.readObject();
                    mServer.Reuniones.add(reunionReceived);

                    for (int i = 0; i < reunionReceived.getInvitados().size(); i++)
                    {
                        String nombre = reunionReceived.getInvitados().get(i).trim();
                        if(mServer.Administrador.containsKey(nombre))
                        {
                            Socket socket = mServer.Administrador.get(nombre).get(1);
                            ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
                            stream.writeObject(reunionReceived);

                            //socket.close();
                        }
                    }

                    System.out.println(reunionReceived.toString());



                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}


