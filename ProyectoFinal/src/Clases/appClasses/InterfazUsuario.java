package Clases.appClasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
public class InterfazUsuario {

    PaginaPrincipal paginaPrincipal;
    PaginaInicioSesion paginaInicioSesion;
    PaginaCreacion paginaCreacion;
    PaginaConsulta paginaConsulta;
    Font fuenteGrande = new Font("Arial", Font.PLAIN, 30);
    Font fuentePequeña = new Font("Arial", Font.PLAIN, 12);

    public InterfazUsuario() {
        //paginaPrincipal = new PaginaPrincipal();
        paginaInicioSesion = new PaginaInicioSesion();
        paginaCreacion = new PaginaCreacion();
        paginaConsulta = new PaginaConsulta();
    }
    public class PaginaPrincipal extends JFrame{
        JPanel panelCalendario;
        JPanel panelNorte;
        JPanel panelSur;
        JPanel panelOeste;
        JPanel panelEste;
        JPanel contenedoresBloque;
        JButton botonCreacion;
        JButton botonConsulta;
        JButton botonEliminar;
        JLabel etiquetaTitulo;
        JLabel etiquetaDiasHoras;
        JLabel etiquetaLunes;
        JLabel etiquetaMartes;
        JLabel etiquetaMiercoles;
        JLabel etiquetaJueves;
        JLabel etiquetaViernes;
        JLabel etiquetaSabado;
        JLabel etiquetaDomingo;
        PaginaPrincipal() {
            panelCalendario = new JPanel();
            panelCalendario.setBackground(Color.red);

            panelNorte = new JPanel();
            //panelNorte.setLayout(null);
            panelNorte.setBackground(Color.pink);

            panelSur = new JPanel();
            //panelSur.setLayout();
            panelSur.setBackground(Color.BLUE);

            panelOeste = new JPanel();
            panelOeste.setBackground(Color.CYAN);

            panelEste = new JPanel();
            panelEste.setBackground(Color.MAGENTA);

            panelCalendario = new JPanel();
            panelCalendario.setLayout(new GridLayout(25, 8));
            panelCalendario.setBackground(Color.GREEN);

            botonCreacion = new JButton("Crear objetivo");
            //botonCreacion.setSize(100, 50);
            panelSur.add(botonCreacion);

            botonConsulta = new JButton("Consultar objetivo");
            //botonConsulta.setSize(100, 50);
            panelSur.add(botonConsulta);

            botonEliminar = new JButton("Eliminar objetivo");
            //botonEliminar.setSize(100, 50);
            panelSur.add(botonEliminar);

            etiquetaTitulo = new JLabel("TimeHero", SwingConstants.CENTER);
            etiquetaTitulo.setFont(fuenteGrande);
            //etiquetaTitulo.setSize(30,30);
            //etiquetaTitulo.setBackground(Color.CYAN);
            panelNorte.add(etiquetaTitulo);

            etiquetaDiasHoras = new JLabel("Horas/Días");
            etiquetaDiasHoras.setBorder(new LineBorder(Color.BLACK));
            panelCalendario.add(etiquetaDiasHoras);

            etiquetaLunes = new JLabel("Lunes");
            panelCalendario.add(etiquetaLunes);

            etiquetaMartes = new JLabel("Martes");
            panelCalendario.add(etiquetaMartes);

            etiquetaMiercoles = new JLabel("Miercoles");
            panelCalendario.add(etiquetaMiercoles);

            etiquetaJueves = new JLabel("Jueves");
            panelCalendario.add(etiquetaJueves);

            etiquetaViernes = new JLabel("Viernes");
            panelCalendario.add(etiquetaViernes);

            etiquetaSabado = new JLabel("Sabado");
            panelCalendario.add(etiquetaSabado);

            etiquetaDomingo = new JLabel("Domingo");
            panelCalendario.add(etiquetaDomingo);

            for(int i = 1; i <= 24; i++) {
                JLabel hora = new JLabel(i + ":" + (i+1));
                panelCalendario.add(hora);
                for(int j = 1; j <= 7; j++) {
                    JButton bloque = new JButton(i + "/" + j);
                    panelCalendario.add(bloque);
                }
            }

            setLayout(new BorderLayout());
            setBounds(200, 200, 800, 600);
            getContentPane().setBackground(Color.CYAN);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            add(panelCalendario, BorderLayout.CENTER);
            add(panelNorte, BorderLayout.NORTH);
            add(panelSur, BorderLayout.SOUTH);
            add(panelOeste, BorderLayout.WEST);
            add(panelEste, BorderLayout.EAST);
            add(panelCalendario, BorderLayout.CENTER);
        }
    }

    public class PaginaInicioSesion extends JFrame{
        JPanel panelNorte;
        JPanel panelCentro;
        JLabel etiquetaTitulo;
        JLabel etiquetaUsuario;
        JLabel etiquetaContrasena;
        JTextField campoUsuario;
        JPasswordField campoContrasena;
        JButton botonIngreso;
        PaginaInicioSesion() {
            panelNorte = new JPanel();
            panelNorte.setBackground(Color.BLUE);

            panelCentro = new JPanel();
            panelCentro.setFont(fuentePequeña);
            panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
            panelCentro.setBackground(Color.GREEN);

            etiquetaTitulo = new JLabel("Inicio sesion");
            etiquetaTitulo.setFont(fuenteGrande);
            panelNorte.add(etiquetaTitulo);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 100)));

            etiquetaUsuario = new JLabel("Usuario:", SwingConstants.CENTER);
            etiquetaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            etiquetaUsuario.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(etiquetaUsuario);

            campoUsuario = new JTextField();
            campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            campoUsuario.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(campoUsuario);

            etiquetaContrasena = new JLabel("Contraseña:", SwingConstants.CENTER);
            etiquetaContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
            etiquetaContrasena.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(etiquetaContrasena);

            campoContrasena = new JPasswordField();
            campoContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
            campoContrasena.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(campoContrasena);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

            botonIngreso = new JButton("Ingresar");
            botonIngreso.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(botonIngreso);

            setLayout(new BorderLayout());
            setBounds(200, 200, 800, 600);
            getContentPane().setBackground(Color.CYAN);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            add(panelNorte, BorderLayout.NORTH);
            add(panelCentro, BorderLayout.CENTER);
        }
    }

    public class PaginaCreacion {
        PaginaCreacion() {

        }
    }

    public class PaginaConsulta {
        PaginaConsulta() {
            
        }
    }
}
