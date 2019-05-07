package sample;


import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainServer {
    public Map<String, ArrayList<Socket>> Administrador;
    public ArrayList<Reunion> Reuniones;

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



    public MainServer(){
        Administrador = new HashMap<String, ArrayList<Socket>>();
        Reuniones = new ArrayList<Reunion>();
    }

    public MainServer(Map<String, ArrayList<Socket>> administrador, ArrayList<Reunion> reuniones) {
        Administrador = administrador;
        Reuniones = reuniones;
    }







}
