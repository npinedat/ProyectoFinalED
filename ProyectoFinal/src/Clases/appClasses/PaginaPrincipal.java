package Clases.appClasses;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Clases.Main.Main;



public class PaginaPrincipal extends InterfazUsuario implements ActionListener {
    JPanel panelCentro, panelNorte, panelSur, panelOeste, panelEste;
    ImageIcon imagenPlay;
    JButton botonPlay, botonPosponer, botonCreacion, botonConsulta, botonEliminar, botonCerrarSesion;
    JLabel etiqueta;

    PaginaPrincipal() {
        Main.enSesion = validacion();
        panelNorte = new JPanel();
        panelNorte.setBackground(colorFondo);

        panelSur = new JPanel();
        panelSur.setBackground(colorFondo);

        panelOeste = new JPanel();
        panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));
        panelOeste.setBackground(colorFondo);

        panelEste = new JPanel();
        panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
        panelEste.setBackground(colorFondo);

        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(colorFondo);

        etiqueta = new JLabel("Procrastinator", SwingConstants.CENTER);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 30));
        panelNorte.add(etiqueta);

        panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta = new JLabel(Main.login.usuario + " bienvenido a Procrastinator");
        etiqueta.setFont(new Font("Arial", Font.BOLD, 26));
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentro.add(etiqueta);

        panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

        Objetivo objetivoCercano = Main.consultaObjCercano(Main.login);
        int hashCercano = Main.consultaHashCercano();

        if (Main.login.arbolObjetivos.empty()) {
            etiqueta = new JLabel("No tiene objetivos creados, puede crear uno.");
            etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(etiqueta);
        } else {
            etiqueta = new JLabel("Su próxima sesión del objetivo " + objetivoCercano.nombre + " es : ");
            etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(etiqueta);
            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

            etiqueta = new JLabel("El Día: " + Main.nombreDia((int) Math.floor(hashCercano / 24)) + ", " + "a las: "
                    + (int) (hashCercano - Math.floor(hashCercano / 24) * 24) + ":00");
            etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(etiqueta);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

            botonPlay = new JButton("Iniciar rutina");
            if (Main.enSesion == false) {
                botonPlay.setBackground(colorDeshabilitado);
                botonPlay.setEnabled(false);
            } else if(Main.enSesion == true) {
                botonPlay.setBackground(colorBotones);
                botonPlay.setEnabled(true);
            }
            botonPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonPlay.addActionListener(this);
            panelCentro.add(botonPlay);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        botonCreacion = new JButton("Crear objetivo");
        botonCreacion.setBackground(colorBotones);
        botonCreacion.addActionListener(this);
        panelSur.add(botonCreacion);

        botonConsulta = new JButton("Consultar objetivo");
        botonConsulta.setBackground(colorBotones);
        botonConsulta.addActionListener(this);
        panelSur.add(botonConsulta);

        botonEliminar = new JButton("Eliminar objetivos");
        botonEliminar.setBackground(colorBotones);
        botonEliminar.addActionListener(this);
        panelSur.add(botonEliminar);

        botonCerrarSesion = new JButton("Cerrar sesión");
        botonCerrarSesion.setBackground(colorBotones);
        botonCerrarSesion.addActionListener(this);
        panelSur.add(botonCerrarSesion);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Main.guardarDatos(Main.fileHandler, Main.usuarios);
            }
        });
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(panelCentro, BorderLayout.CENTER);
        add(panelNorte, BorderLayout.NORTH);
        add(panelSur, BorderLayout.SOUTH);
        add(panelOeste, BorderLayout.WEST);
        add(panelEste, BorderLayout.EAST);
        add(panelCentro, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCreacion) {
            PaginaCreacion paginaCreacion = new PaginaCreacion();
            this.dispose();
        } else if (e.getSource() == botonConsulta) {
            PaginaConsulta paginaConsulta = new PaginaConsulta();
            this.dispose();
        } else if (e.getSource() == botonEliminar) {
            PaginaEliminacion paginaEliminacion = new PaginaEliminacion();
            this.dispose();
        } else if (e.getSource() == botonCerrarSesion) {
            try {
                Main.cronometroActivo = false;
            } catch (Exception ev) {
                System.out.println(ev);
            }
            PaginaInicioSesion paginaInicioSesion = new PaginaInicioSesion();
            this.dispose();
        } else if (e.getSource() == botonPlay) {
            PaginaActividad paginaActividad = new PaginaActividad();
            this.dispose();
        }
    }
}