package Clases.appClasses;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Clases.Main.Main;

public class PaginaConsulta extends InterfazUsuario implements ActionListener {
    JPanel panelNorte, panelCentro, panelOeste, panelEste, panelSur;
    JScrollPane panelDescripcion;
    JLabel etiqueta;
    JTextField campoNombre, campoMetodologia, campoHoras, campoHorasCompletadas, campoHorasRestantes;
    JButton botonVolver;
    JTextArea campoDescripcion;
    ArrayList<JButton> bloquesSeleccionados = new ArrayList<JButton>();

    PaginaConsulta() {
        panelNorte = new JPanel();
        panelNorte.setBackground(colorFondo);

        panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(25, 8));
        panelCentro.setBackground(colorFondo);

        panelOeste = new JPanel();
        panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));
        panelOeste.setBackground(colorFondo);

        panelEste = new JPanel();
        panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
        panelEste.setBackground(colorFondo);

        panelSur = new JPanel();
        panelSur.setBackground(colorFondo);

        etiqueta = new JLabel("Consulta de objetivo");
        etiqueta.setFont(new Font("Arial", Font.BOLD, 30));
        panelNorte.add(etiqueta);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta = new JLabel("Objetivos: ");
        panelEste.add(etiqueta);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        Main.login.arbolObjetivos.objetivos.clear();
        for (Objetivo i : Main.login.arbolObjetivos.toArray(Main.login.arbolObjetivos.root)) {
            JButton boton = new JButton(i.nombre);
            boton.setBackground(colorBotones);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panelCentro.removeAll();
                    campoNombre.setText(i.nombre);
                    campoDescripcion.setText(i.descripcion);
                    campoHoras.setText("" + i.horasTotales);
                    campoHorasCompletadas.setText("" + i.horasDedicadas);
                    campoHorasRestantes.setText("" + i.horasaDedicar);
                    campoMetodologia.setText(i.tecnica);

                    etiqueta = new JLabel("Horas/Días");
                    panelCentro.add(etiqueta);

                    etiqueta = new JLabel("Domingo");
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

                    for (int i = 0; i <= 23; i++) {
                        if (i < 12) {
                            JLabel hora = new JLabel(i + ":" + (i + 1) + " Am");
                            panelCentro.add(hora);
                        } else {
                            JLabel hora = new JLabel(i + ":" + (i + 1) + " Pm");
                            panelCentro.add(hora);
                        }
                        for (int j = 1; j <= 7; j++) {
                            JButton bloque = new JButton(i + "/" + j);
                            bloque.setEnabled(false);
                            bloque.setFont(new Font("Arial", Font.BOLD, 0));
                            bloque.setBackground(colorSeleccionar);
                            ArrayList<Integer> listaHash = Main.consultaUnHash(Main.login, boton.getText());
                            for (int k : listaHash) {
                                if (k == ((j - 1) * 24 + i)) {
                                    bloque.setBackground(colorBloqueado);
                                    bloque.setEnabled(false);
                                }
                            }
                            panelCentro.add(bloque);

                        }
                    }
                    setBounds(0, 0, 1200, 900);
                }
            });
            panelEste.add(boton);
            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        etiqueta = new JLabel("Nombre: ");
        // etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelOeste.add(etiqueta);

        panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

        campoNombre = new JTextField();
        campoNombre.setMaximumSize(new Dimension(330, 30));
        campoNombre.setEditable(false);
        panelOeste.add(campoNombre);

        panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta = new JLabel("Descripcion: ");
        // etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelOeste.add(etiqueta);

        panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

        campoDescripcion = new JTextArea();
        campoDescripcion.setWrapStyleWord(true);
        campoDescripcion.setEditable(false);
        campoDescripcion.setLineWrap(true);
        panelDescripcion = new JScrollPane(campoDescripcion);
        panelDescripcion.setViewportView(campoDescripcion);
        panelDescripcion.setMaximumSize(new Dimension(330, 400));
        panelOeste.add(panelDescripcion);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta = new JLabel("Horas a dedicar: ");
        // etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEste.add(etiqueta);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        campoHoras = new JTextField();
        // campoHoras.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoHoras.setEditable(false);
        campoHoras.setMaximumSize(new Dimension(340, 30));
        panelEste.add(campoHoras);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta = new JLabel("Horas completadas: ");
        panelEste.add(etiqueta);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        campoHorasCompletadas = new JTextField();
        campoHorasCompletadas.setEditable(false);
        campoHorasCompletadas.setMaximumSize(new Dimension(340, 30));
        panelEste.add(campoHorasCompletadas);

        etiqueta = new JLabel("Horas restantes: ");
        panelEste.add(etiqueta);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        campoHorasRestantes = new JTextField();
        campoHorasRestantes.setEditable(false);
        campoHorasRestantes.setMaximumSize(new Dimension(340, 30));
        panelEste.add(campoHorasRestantes);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta = new JLabel("Metodología:  ");
        panelEste.add(etiqueta);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        campoMetodologia = new JTextField();
        campoMetodologia.setEditable(false);
        campoNombre.setEditable(false);
        campoMetodologia.setMaximumSize(new Dimension(340, 30));
        panelEste.add(campoMetodologia);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        botonVolver = new JButton("Volver");
        botonVolver.setBackground(colorBotones);
        botonVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVolver.addActionListener(this);
        panelSur.add(botonVolver);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Main.guardarDatos(Main.fileHandler, Main.usuarios);
            }
        });
        setLayout(new BorderLayout());
        setBounds(0, 0, 900, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelOeste, BorderLayout.WEST);
        add(panelEste, BorderLayout.EAST);
        add(panelSur, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        PaginaPrincipal paginaPrincipal = new PaginaPrincipal();
        this.dispose();
    }
}