package Clases.appClasses;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;

import Clases.Main.Main;

public class InterfazUsuario extends JFrame implements Runnable {
    Thread hilo = new Thread(this);
    

    Color colorFondo = new Color(237, 187, 153);
    Color colorBloqueado = new Color(236, 112, 99);
    Color colorSeleccionado = new Color(46, 204, 113);
    Color colorSeleccionar = new Color(171, 178, 185);
    Color colorBotones = new Color(165, 105, 189);
    Color colorDeshabilitado = new Color(232, 218, 239);

    public PaginaInicioSesion getPaginaInicioSesion() {
        PaginaInicioSesion paginaInicioSesion = new PaginaInicioSesion();
        return paginaInicioSesion;
    }

    public void run() {
        while (Main.cronometroActivo) {
            try {
                Main.enSesion = validarPuntualidad() && validacion();
                Thread.sleep(60000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean validacion() {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.get(Calendar.HOUR_OF_DAY);
        c.setTime(date);
        int diaAct = (c.get(Calendar.DAY_OF_WEEK) - 1);
        int horaAct = c.get(Calendar.HOUR_OF_DAY);
        int hashDiaHora = (diaAct * 24) + horaAct;

        int hashCercano = Main.consultaHashCercano();

        if (hashCercano == hashDiaHora) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarPuntualidad() {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.get(Calendar.HOUR_OF_DAY);
        c.setTime(date);
        int diaAct = (c.get(Calendar.DAY_OF_WEEK) - 1);
        int horaAct = c.get(Calendar.HOUR_OF_DAY);
        int minutos = c.get(Calendar.MINUTE);
        int hashDiaHora = (diaAct * 24) + horaAct;
        int hashCercano = Main.consultaHashCercano();
        if ((minutos > 10) && (Main.login.arbolObjetivos.root != null) && (hashDiaHora == hashCercano) ) {
            //Objetivo cercano = Main.consultaObjCercano(login);
            //cercano.reencolarBloque(hashCercano);
            return false;
        } else {
            return true;
        }
    }
}