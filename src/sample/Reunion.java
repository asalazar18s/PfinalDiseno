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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Invitados.size(); i++)
        {
            sb.append(Invitados.get(i) + "\n");
        }
        String invitados = sb.toString();
        return "Tema: " + Tema + "\n" + "Organizador: " + Organizador + "\n" + "Invitados: "
                + invitados + "Lugar: " + Lugar + "\n" + "Fecha Inicio: " + FechaInicio + "\n" + "Fecha Fin: " + FechaFin + "\n" + "------------------" + "\n";
    }

    public String getOrganizador() {
        return Organizador;
    }
    public ArrayList<String> getInvitados() {
        return Invitados;
    }
}

