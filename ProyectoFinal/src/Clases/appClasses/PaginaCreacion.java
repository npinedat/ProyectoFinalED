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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Clases.Main.Main;

public class PaginaCreacion extends InterfazUsuario implements ActionListener {
    JPanel panelNorte, panelCentro, panelOeste, panelEste, panelSur;
    JLabel etiqueta;
    JTextArea descripcionMetodologia;
    JTextField campoNombre, campoHoras;
    JScrollPane panelDescripcion;
    JTextArea campoDescripcion;
    ButtonGroup metodologias;
    JRadioButton pomodoro, posec, eissenhower;
    JButton botonAceptar, botonCancelar;
    ArrayList<JButton> bloquesSeleccionados = new ArrayList<JButton>();

    PaginaCreacion() {
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

        etiqueta = new JLabel("Creación de objetivo", SwingConstants.CENTER);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 30));
        panelNorte.add(etiqueta);

        panelOeste.add(Box.createRigidArea(new Dimension(0, 20)));

        etiqueta = new JLabel("Nombre del objetivo:");
        panelOeste.add(etiqueta);

        panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

        campoNombre = new JTextField();
        campoNombre.setMaximumSize(new Dimension(330, 30));
        panelOeste.add(campoNombre);

        panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta = new JLabel("Descripción del objetivo:");
        panelOeste.add(etiqueta);

        panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

        campoDescripcion = new JTextArea();
        campoDescripcion.setWrapStyleWord(true);
        campoDescripcion.setLineWrap(true);
        panelDescripcion = new JScrollPane(campoDescripcion);
        panelDescripcion.setViewportView(campoDescripcion);
        panelDescripcion.setMaximumSize(new Dimension(330, 500));
        panelOeste.add(panelDescripcion);

        panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

        panelEste.add(Box.createRigidArea(new Dimension(0, 20)));

        etiqueta = new JLabel("Escoja una metodología:");
        panelEste.add(etiqueta);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        metodologias = new ButtonGroup();

        pomodoro = new JRadioButton("Pomodoro");
        // pomodoro.addChangeListener(this);
        pomodoro.setBackground(colorFondo);
        pomodoro.setSelected(true);
        posec = new JRadioButton("POSEC");
        // posec.addChangeListener(this);
        posec.setBackground(colorFondo);
        eissenhower = new JRadioButton("Eissenhower");
        // eissenhower.addChangeListener(this);
        eissenhower.setBackground(colorFondo);
        metodologias.add(pomodoro);
        panelEste.add(pomodoro);
        metodologias.add(posec);
        panelEste.add(posec);
        metodologias.add(eissenhower);
        panelEste.add(eissenhower);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta = new JLabel("Defina las horas que va a dedicar:");
        panelEste.add(etiqueta);

        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

        campoHoras = new JTextField();
        campoHoras.setFont(new Font("Arial", Font.PLAIN, 12));
        campoHoras.setMaximumSize(new Dimension(340, 30));
        panelEste.add(campoHoras);

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

        Main.login.arbolObjetivos.objetivos.clear();
        ArrayList<Integer> listaHash = Main.consultaHash(Main.login);
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
                bloque.setFont(new Font("Arial", Font.BOLD, 0));
                bloque.setBackground(colorSeleccionar);
                bloque.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (bloque.getBackground() == colorSeleccionado) {
                            bloque.setBackground(colorSeleccionar);
                            if (duplicado((JButton) e.getSource()) == 170) {
                                // Nada
                            } else {
                                bloquesSeleccionados.remove(e.getSource());
                            }
                        } else {
                            bloque.setBackground(colorSeleccionado);
                            bloquesSeleccionados.add((JButton) e.getSource());
                        }
                    }
                });
                
                for (int k : listaHash) {
                    if (k == ((j - 1) * 24 + i)) {
                        bloque.setBackground(colorBloqueado);
                        bloque.setEnabled(false);
                    }
                }
                panelCentro.add(bloque);
            }
        }

        botonAceptar = new JButton("Aceptar");
        botonAceptar.setBackground(colorBotones);
        botonAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonAceptar.addActionListener(this);
        panelSur.add(botonAceptar);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.setBackground(colorBotones);
        botonCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCancelar.addActionListener(this);
        panelSur.add(botonCancelar);

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
        if (e.getSource() == botonCancelar) {
            PaginaPrincipal paginaPrincipal = new PaginaPrincipal();
            this.dispose();
        } else if (e.getSource() == botonAceptar) {
            int horas;
            String nombreObjetivo = campoNombre.getText();
            String descripcionObjetivo = campoDescripcion.getText();
            String metodologia;
            if (pomodoro.isSelected() == true) {
                metodologia = "Pomodoro";
            } else if (eissenhower.isSelected() == true) {
                metodologia = "Eissenhower";
            } else {
                metodologia = "POSEC";
            }
            if (campoHoras.getText().matches("[+-]?\\d*(\\.\\d+)?") && !campoHoras.getText().equals("")) {
                horas = Integer.parseInt(campoHoras.getText());
                ArrayList<String> arregloDias = new ArrayList<String>();
                ArrayList<Integer> arregloHoras = new ArrayList<Integer>();
                for (JButton i : bloquesSeleccionados) {
                    String[] texto = i.getText().split("/");
                    if (texto[1].equals("1")) {
                        arregloDias.add("Domingo");
                    } else if (texto[1].equals("2")) {
                        arregloDias.add("Lunes");
                    } else if (texto[1].equals("3")) {
                        arregloDias.add("Martes");
                    } else if (texto[1].equals("4")) {
                        arregloDias.add("Miercoles");
                    } else if (texto[1].equals("5")) {
                        arregloDias.add("Jueves");
                    } else if (texto[1].equals("6")) {
                        arregloDias.add("Viernes");
                    } else {
                        arregloDias.add("Sabado");
                    }
                    arregloHoras.add(Integer.parseInt(texto[0]));
                }
                if (nombreObjetivo.equals("") || descripcionObjetivo.equals("") || horas == 0
                        || bloquesSeleccionados.size() == 0) {
                    JOptionPane.showMessageDialog(this, "Por favor llene todos los campos");
                } else {
                    Main.agregarObjetivo(Main.login, nombreObjetivo, descripcionObjetivo, metodologia, horas,
                            arregloDias, arregloHoras, this);
                    // Main.consultaHash(login);
                    PaginaPrincipal paginaPrincipal = new PaginaPrincipal();
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Datos no validos, el campo de horas debe tener un número entero y no puede ser 0");
            }
        }
    }

    public int duplicado(JButton boton) {
        for (int i = 0; i <= bloquesSeleccionados.size(); i++) {
            if (boton.getText() == bloquesSeleccionados.get(i).getText()) {
                return i;
            }
        }
        return 170;
    }

    /*
     * public void stateChanged(ChangeEvent e) { if(e.getSource() == pomodoro) {
     * panelEste.add(Box.createRigidArea(new Dimension(0, 10)));
     * 
     * JTextArea campoPomodoro = new
     * JTextArea("La técnica pomodoro separa su sesión de trabajo en bloques de 25 minutos con descansos de 5 minutos."
     * ); campoPomodoro.setLineWrap(true); campoPomodoro.setEditable(false);
     * JScrollPane panelPomodoro = new JScrollPane(campoPomodoro);
     * panelPomodoro.setViewportView(campoPomodoro);
     * panelPomodoro.setMaximumSize(new Dimension(330, 200));
     * panelOeste.add(panelPomodoro); }else if(e.getSource() == posec) {
     * panelEste.removeAll(); panelEste.add(Box.createRigidArea(new Dimension(0,
     * 10)));
     * 
     * JLabel etiqueta = new JLabel("Escriba aquí las tareas pequeñas: ");
     * panelEste.add(etiqueta);
     * 
     * panelEste.add(Box.createRigidArea(new Dimension(0, 10)));
     * 
     * JTextArea campo1 = new JTextArea(); campo1.setLineWrap(true);
     * 
     * JScrollPane panel1 = new JScrollPane(campo1); panel1.setViewportView(campo1);
     * panel1.setMaximumSize(new Dimension(330, 200)); panelOeste.add(panel1);
     * 
     * etiqueta = new JLabel("Escriba aquí las tareas medianas: ");
     * panelEste.add(etiqueta);
     * 
     * panelEste.add(Box.createRigidArea(new Dimension(0, 10)));
     * 
     * JTextArea campo2 = new JTextArea(); campo2.setLineWrap(true); JScrollPane
     * panel2 = new JScrollPane(campo2); panel2.setViewportView(campo2);
     * panel2.setMaximumSize(new Dimension(330, 200)); panel2.add(panel2);
     * 
     * etiqueta = new JLabel("Escriba aquí las tareas grandes: ");
     * panelEste.add(etiqueta);
     * 
     * panelEste.add(Box.createRigidArea(new Dimension(0, 10))); }else
     * if(e.getSource() == eissenhower) {
     * 
     * }else {
     * 
     * } }
     */
}
