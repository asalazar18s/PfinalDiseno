package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Reunion implements Serializable {

    private ArrayList<String> Invitados;
    private String Tema;
    private String Organizador;
    private String Lugar;
    private String FechaInicio;
    private String FechaFin;

    public Reunion(ArrayList<String> invitados, String tema, String organizador, String lugar, String fechaInicio, String fechaFin) {
        Invitados = invitados;
        Tema = tema;
        Organizador = organizador;
        Lugar = lugar;
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Invitados: " + Invitados.size() + ", Organizador: " + Organizador;
    }
}

