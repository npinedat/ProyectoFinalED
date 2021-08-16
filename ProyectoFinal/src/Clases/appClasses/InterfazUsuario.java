package Clases.appClasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class InterfazUsuario {

    PaginaPrincipal paginaPrincipal;
    PaginaInicioSesion paginaInicioSesion;
    PaginaCreacion paginaCreacion;
    PaginaConsulta paginaConsulta;
    Font fuenteGrande = new Font("Arial", Font.PLAIN, 30);
    Font fuentePequeña = new Font("Arial", Font.PLAIN, 12);

    public InterfazUsuario() {
        //paginaPrincipal = new PaginaPrincipal();
        //paginaInicioSesion = new PaginaInicioSesion();
        //paginaCreacion = new PaginaCreacion();
        //paginaConsulta = new PaginaConsulta();
        //paginaEliminacion = new PaginaEliminacion();
    }
    public class PaginaPrincipal extends JFrame{
        JPanel panelCentro;
        JPanel panelNorte;
        JPanel panelSur;
        JPanel panelOeste;
        JPanel panelEste;
        ImageIcon imagenPlay;
        JButton botonPlay;
        JButton botonCreacion;
        JButton botonConsulta;
        JButton botonEliminar;
        JLabel etiqueta;
        PaginaPrincipal() {
            panelNorte = new JPanel();
            panelNorte.setBackground(Color.pink);

            panelSur = new JPanel();
            panelSur.setBackground(Color.BLUE);

            panelOeste = new JPanel();
            panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));
            panelOeste.setBackground(Color.CYAN);

            panelEste = new JPanel();
            panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
            panelEste.setBackground(Color.MAGENTA);

            panelCentro = new JPanel();
            panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
            panelCentro.setBackground(Color.ORANGE);

            etiqueta = new JLabel("Procastinator", SwingConstants.CENTER);
            etiqueta.setFont(new Font("Arial", Font.BOLD, 30));
            panelNorte.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            /*
            etiqueta = new JLabel("Usuario123", SwingConstants.CENTER);
            etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
            panelOeste.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 60)));

            etiqueta = new JLabel("Objetivo1");
            panelOeste.add(etiqueta);
            etiqueta = new JLabel("Horas restantes: x");
            panelOeste.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 60)));

            etiqueta = new JLabel("Objetivo2");
            panelOeste.add(etiqueta);
            etiqueta = new JLabel("Horas restantes: x");
            panelOeste.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 60)));

            etiqueta = new JLabel("Objetivo3");
            panelOeste.add(etiqueta);
            etiqueta = new JLabel("Horas restantes: x");
            panelOeste.add(etiqueta);
            */ 

            panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

            etiqueta = new JLabel("Bienvenido a Procastinator");
            etiqueta.setFont(new Font("Arial", Font.BOLD, 26));
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(etiqueta);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

            etiqueta = new JLabel("Su próxima sesión es: ");
            etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 40)));

            etiqueta = new JLabel("Día x Mes x Hora x");
            etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 40)));

            botonPlay = new JButton("Iniciar rutina");
            //imagenPlay = new ImageIcon("ProyectoFinal/src/Img/play.png");
            //botonPlay.setIcon(imagenPlay);
            //botonPlay.setMaximumSize(new Dimension(200, 200));
            botonPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(botonPlay);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            botonCreacion = new JButton("Crear objetivo");
            panelSur.add(botonCreacion);

            botonConsulta = new JButton("Consultar objetivo");
            panelSur.add(botonConsulta);

            botonEliminar = new JButton("Eliminar objetivo");
            panelSur.add(botonEliminar);

            setLayout(new BorderLayout());
            setBounds(200, 200, 800, 600);
            getContentPane().setBackground(Color.CYAN);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            add(panelCentro, BorderLayout.CENTER);
            add(panelNorte, BorderLayout.NORTH);
            add(panelSur, BorderLayout.SOUTH);
            add(panelOeste, BorderLayout.WEST);
            add(panelEste, BorderLayout.EAST);
            add(panelCentro, BorderLayout.CENTER);
        }
    }

    public class PaginaInicioSesion extends JFrame {
        JPanel panelNorte;
        JPanel panelCentro;
        JLabel etiqueta;
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

            etiqueta = new JLabel("Inicio sesion Procastinator");
            etiqueta.setFont(fuenteGrande);
            panelNorte.add(etiqueta);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 100)));

            etiqueta = new JLabel("Usuario:", SwingConstants.CENTER);
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            etiqueta.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(etiqueta);

            campoUsuario = new JTextField();
            campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            campoUsuario.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(campoUsuario);

            etiqueta = new JLabel("Contraseña:", SwingConstants.CENTER);
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            etiqueta.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(etiqueta);

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

    public class PaginaCreacion extends JFrame{
        JPanel panelNorte;
        JPanel panelCentro;
        JPanel panelOeste;
        JPanel panelEste;
        JPanel panelSur;
        JLabel etiqueta;
        JTextArea descripcionMetodologia;
        JTextField campoNombre;
        JTextArea campoDescripcion;
        JTextField campoHoras;
        ButtonGroup metodologias;
        JRadioButton pomodoro;
        JRadioButton posec;
        JRadioButton eissenhower;
        JCheckBox lunes;
        JCheckBox martes;
        JCheckBox miercoles;
        JCheckBox jueves;
        JCheckBox viernes;
        JCheckBox sabado;
        JCheckBox domingo;
        JButton botonAceptar;
        JButton botonCancelar;      

        PaginaCreacion() {
            panelNorte = new JPanel();
            panelNorte.setBackground(Color.BLUE);

            panelCentro = new JPanel();
            panelCentro.setLayout(new GridLayout(25, 8));
            panelCentro.setBackground(Color.GREEN);

            panelOeste = new JPanel();
            panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));
            panelOeste.setBackground(Color.MAGENTA);

            panelEste = new JPanel();
            panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
            panelEste.setBackground(Color.ORANGE);

            panelSur = new JPanel();
            panelSur.setBackground(Color.RED);

            etiqueta = new JLabel("Creación de objetivo", SwingConstants.CENTER);
            etiqueta.setFont(fuenteGrande);
            panelNorte.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 20)));

            etiqueta = new JLabel("Nombre del objetivo:");
            panelOeste.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            campoNombre = new JTextField();
            campoNombre.setMaximumSize(new Dimension(340, 30));
            panelOeste.add(campoNombre);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            etiqueta = new JLabel("Descripción del objetivo:");
            panelOeste.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            campoDescripcion = new JTextArea();
            campoDescripcion.setMaximumSize(new Dimension(340, 400));
            panelOeste.add(campoDescripcion);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            panelEste.add(Box.createRigidArea(new Dimension(0, 20)));

            etiqueta = new JLabel("Escoja una metodología:");
            panelEste.add(etiqueta);

            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

            metodologias = new ButtonGroup();

            pomodoro = new JRadioButton("Pomodoro");
            pomodoro.setBackground(Color.ORANGE);
            posec = new JRadioButton("POSEC");
            posec.setBackground(Color.ORANGE);
            eissenhower = new JRadioButton("Eissenhower");
            eissenhower.setBackground(Color.ORANGE);
            metodologias.add(pomodoro);
            panelEste.add(pomodoro);
            metodologias.add(posec);
            panelEste.add(posec);
            metodologias.add(eissenhower);
            panelEste.add(eissenhower);

            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));
            
            /*
            etiqueta = new JLabel("Escoja los días que tiene disponibles:");
            panelEste.add(etiqueta);

            
            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

            lunes = new JCheckBox("Lunes");
            lunes.setBackground(Color.ORANGE);
            panelEste.add(lunes);
            martes = new JCheckBox("Martes");
            martes.setBackground(Color.ORANGE);
            panelEste.add(martes);
            miercoles = new JCheckBox("Miercoles");
            miercoles.setBackground(Color.ORANGE);
            panelEste.add(miercoles);
            jueves = new JCheckBox("Jueves");
            jueves.setBackground(Color.ORANGE);
            panelEste.add(jueves);
            viernes = new JCheckBox("Viernes");
            viernes.setBackground(Color.ORANGE);
            panelEste.add(viernes);
            sabado = new JCheckBox("Sabado");
            sabado.setBackground(Color.ORANGE);
            panelEste.add(sabado);
            domingo = new JCheckBox("Domingo");
            domingo.setBackground(Color.ORANGE);
            panelEste.add(domingo);
            */

            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

            etiqueta = new JLabel("Defina las horas que va a dedicar:");
            panelEste.add(etiqueta);

            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

            campoHoras = new JTextField();
            campoHoras.setFont(fuentePequeña);
            campoHoras.setMaximumSize(new Dimension(340, 30));
            panelEste.add(campoHoras);

            /*
            descripcionMetodologia = new JTextArea("La técnica pomodoro busca fragmentar " + 
            "\nnuestros esfuerzos en pequeños lapsos de 25 " +
            "\nminutos con 5 minutos de descanso, así podemos " +
            "\nser más productivos sin desgastarnos.");
            descripcionMetodologia.setBorder(new LineBorder(Color.BLACK));
            descripcionMetodologia.setMaximumSize(new Dimension(350, 100));
            descripcionMetodologia.setBackground(Color.GREEN);
            descripcionMetodologia.setAlignmentX(Component.CENTER_ALIGNMENT);
            descripcionMetodologia.setEditable(false);
            panelCentro.add(descripcionMetodologia);
            */

            etiqueta = new JLabel("Horas/Días");
            panelCentro.add(etiqueta);

            etiqueta = new JLabel("Lunes");
            panelCentro.add(etiqueta);

            etiqueta = new JLabel("Martes");
            panelCentro.add(etiqueta);

            etiqueta = new JLabel("Miercoles");
            panelCentro.add(etiqueta);

            etiqueta = new JLabel("Jueves");
            panelCentro.add(etiqueta);

            etiqueta = new JLabel("Viernes");
            panelCentro.add(etiqueta);

            etiqueta = new JLabel("Sabado");
            panelCentro.add(etiqueta);

            etiqueta = new JLabel("Domingo");
            panelCentro.add(etiqueta);

            for(int i = 0; i <= 23; i++) {
                JLabel hora = new JLabel(i + ":" + (i+1));
                panelCentro.add(hora);
                for(int j = 1; j <= 7; j++) {
                    JButton bloque = new JButton(i + "/" + j);
                    panelCentro.add(bloque);
                }
            }

            botonAceptar = new JButton("Aceptar");
            botonAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);;
            panelSur.add(botonAceptar);
            
            botonCancelar = new JButton("Cancelar");
            botonAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelSur.add(botonCancelar);

            setLayout(new BorderLayout());
            setBounds(200, 200, 1200, 900);
            getContentPane().setBackground(Color.CYAN);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            add(panelNorte, BorderLayout.NORTH);
            add(panelCentro, BorderLayout.CENTER);
            add(panelOeste, BorderLayout.WEST);
            add(panelEste, BorderLayout.EAST);
            add(panelSur, BorderLayout.SOUTH);
        }
    }

    public class PaginaConsulta {
        JPanel panelCentro;
        JPanel panelOeste;
        JPanel panelEste;
        JPanel panelSur;
        JLabel etiqueta;
        JButton botonVolver;
        JTextArea descripcion;
        
        PaginaConsulta() {
            
        }
    }
}