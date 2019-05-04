package sample;


import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainServer {
    private Map<String, ArrayList<Socket>> Administrador;
    private ArrayList<Reunion> Reuniones;

    public Map<String, ArrayList<Socket>> getAdministrador() {
        return Administrador;
    }

    public void setAdministrador(Map<String, ArrayList<Socket>> administrador) {
        Administrador = administrador;
    }

    public ArrayList<Reunion> getReuniones() {
        return Reuniones;
    }

    public void setReuniones(ArrayList<Reunion> reuniones) {
        Reuniones = reuniones;
    }



    public MainServer(){}

    public MainServer(Map<String, ArrayList<Socket>> administrador, ArrayList<Reunion> reuniones) {
        Administrador = administrador;
        Reuniones = reuniones;

    }







}
