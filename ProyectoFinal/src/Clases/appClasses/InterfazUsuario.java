package Clases.appClasses;

import java.awt.Color;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Main.Main;

public class InterfazUsuario {

    PaginaPrincipal paginaPrincipal;
    PaginaInicioSesion paginaInicioSesion;
    PaginaCreacion paginaCreacion;
    PaginaConsulta paginaConsulta;
    PaginaEliminacion paginaEliminacion;
    PaginaRegistro paginaRegistro;

    //Toolkit t = Toolkit.getDefaultToolkit();

    Usuario login;

    public PaginaInicioSesion getPaginaInicioSesion() {
        return paginaInicioSesion = new PaginaInicioSesion();
    }

    public class PaginaPrincipal extends JFrame implements ActionListener {
        JPanel panelCentro, panelNorte, panelSur, panelOeste, panelEste;
        ImageIcon imagenPlay;
        JButton botonPlay, botonCreacion, botonConsulta, botonEliminar, botonCerrarSesion;
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
             * etiqueta = new JLabel("Usuario123", SwingConstants.CENTER);
             * etiqueta.setFont(new Font("Arial", Font.BOLD, 24)); panelOeste.add(etiqueta);
             * 
             * panelOeste.add(Box.createRigidArea(new Dimension(0, 60)));
             * 
             * etiqueta = new JLabel("Objetivo1"); panelOeste.add(etiqueta); etiqueta = new
             * JLabel("Horas restantes: x"); panelOeste.add(etiqueta);
             * 
             * panelOeste.add(Box.createRigidArea(new Dimension(0, 60)));
             * 
             * etiqueta = new JLabel("Objetivo2"); panelOeste.add(etiqueta); etiqueta = new
             * JLabel("Horas restantes: x"); panelOeste.add(etiqueta);
             * 
             * panelOeste.add(Box.createRigidArea(new Dimension(0, 60)));
             * 
             * etiqueta = new JLabel("Objetivo3"); panelOeste.add(etiqueta); etiqueta = new
             * JLabel("Horas restantes: x"); panelOeste.add(etiqueta);
             */

            panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

            etiqueta = new JLabel("Bienvenido a Procastinator");
            etiqueta.setFont(new Font("Arial", Font.BOLD, 26));
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(etiqueta);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

            Objetivo objetivoCercano = Main.consultaObjCercano(login);
            int hashCercano = Main.consultaHashCercano(login);

            if (login.arbolObjetivos.empty()) {
                etiqueta = new JLabel("No tiene objetivos creados, creé uno.");
                etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
                etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelCentro.add(etiqueta);
            } else {
                etiqueta = new JLabel("Su próxima sesión del objetivo " + objetivoCercano.nombre + " es : ");
                etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
                etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelCentro.add(etiqueta);
                panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));
            }
            etiqueta = new JLabel("Día: " + Math.floor(hashCercano/24) + " Hora: " + (hashCercano - Math.floor(hashCercano/24)*24));
            etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(etiqueta);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

            botonPlay = new JButton("Iniciar rutina");
            // imagenPlay = new ImageIcon("ProyectoFinal/src/Img/play.png");
            // botonPlay.setIcon(imagenPlay);
            // botonPlay.setMaximumSize(new Dimension(200, 200));
            botonPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonPlay.addActionListener(this);
            panelCentro.add(botonPlay);

            botonCreacion = new JButton("Crear objetivo");
            botonCreacion.addActionListener(this);
            panelSur.add(botonCreacion);

            botonConsulta = new JButton("Consultar objetivo");
            botonConsulta.addActionListener(this);
            panelSur.add(botonConsulta);

            botonEliminar = new JButton("Eliminar objetivos");
            botonEliminar.addActionListener(this);
            panelSur.add(botonEliminar);

            botonCerrarSesion = new JButton("Cerrar sesión");
            botonCerrarSesion.addActionListener(this);
            panelSur.add(botonCerrarSesion);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Main.guardarDatos(Main.fileHandler, Main.usuarios);
                }
            });
            setLayout(new BorderLayout());
            setBounds(0, 0, 800, 600);
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

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botonCreacion) {
                paginaCreacion = new PaginaCreacion();
                paginaPrincipal.dispose();
            } else if (e.getSource() == botonConsulta) {
                paginaConsulta = new PaginaConsulta();
                paginaPrincipal.dispose();
            } else if (e.getSource() == botonEliminar) {
                paginaEliminacion = new PaginaEliminacion();
                paginaPrincipal.dispose();
            } else if (e.getSource() == botonCerrarSesion) {
                Main.guardarDatos(Main.fileHandler, Main.usuarios);
                paginaInicioSesion = new PaginaInicioSesion();
                paginaPrincipal.dispose();
            }
        }
    }

    public class PaginaInicioSesion extends JFrame implements ActionListener {
        JPanel panelNorte, panelCentro;
        JLabel etiqueta;
        JTextField campoUsuario;
        JPasswordField campoContrasena;
        JButton botonIngreso, botonRegistro;

        PaginaInicioSesion() {
            panelNorte = new JPanel();
            panelNorte.setBackground(Color.BLUE);

            panelCentro = new JPanel();
            panelCentro.setFont(new Font("Arial", Font.PLAIN, 12));
            panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
            panelCentro.setBackground(Color.GREEN);

            etiqueta = new JLabel("Inicio sesion Procastinator");
            etiqueta.setFont(new Font("Arial", Font.PLAIN, 30));
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

            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

            botonIngreso = new JButton("Ingresar");
            botonIngreso.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonIngreso.addActionListener(this);
            panelCentro.add(botonIngreso);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

            botonRegistro = new JButton("Registrarse");
            botonRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonRegistro.addActionListener(this);
            panelCentro.add(botonRegistro);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Main.guardarDatos(Main.fileHandler, Main.usuarios);
                }
            });
            setLayout(new BorderLayout());
            setBounds(0, 0, 800, 600);
            getContentPane().setBackground(Color.CYAN);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            add(panelNorte, BorderLayout.NORTH);
            add(panelCentro, BorderLayout.CENTER);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botonIngreso) {
                String usuario = campoUsuario.getText();
                String contrasena = new String(campoContrasena.getPassword());
                if (usuario.equals("") || contrasena.equals("")) {
                    JOptionPane.showMessageDialog(paginaInicioSesion, "Debe ingresar todos los campos");
                } else {
                    login = Main.iniciarSesion(usuario, contrasena);
                    if (login == null) {
                        JOptionPane.showMessageDialog(paginaInicioSesion,
                                "Los datos son incorrectos, intente de nuevo");
                    } else {
                        paginaPrincipal = new PaginaPrincipal();
                        paginaInicioSesion.dispose();
                    }
                }
            } else if (e.getSource() == botonRegistro) {
                paginaRegistro = new PaginaRegistro();
                paginaInicioSesion.dispose();
            }
        }
    }

    public class PaginaCreacion extends JFrame implements ActionListener {
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
            pomodoro.setBackground(Color.ORANGE);
            pomodoro.setSelected(true);
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

            login.arbolObjetivos.objetivos.clear();

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
                    bloque.setBackground(Color.GRAY);
                    bloque.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (bloque.getBackground() == Color.RED) {
                                bloque.setBackground(Color.GRAY);
                                if (duplicado((JButton) e.getSource()) == 170) {
                                    // Nada
                                } else {
                                    bloquesSeleccionados.remove(e.getSource());
                                }
                            } else {
                                bloque.setBackground(Color.RED);
                                bloquesSeleccionados.add((JButton) e.getSource());
                            }
                        }
                    });
                    ArrayList<Integer> listaHash = Main.consultaHash(login);
                    for (int k : listaHash) {
                        if (k == ((j - 1) * 24 + i)) {
                            bloque.setBackground(Color.MAGENTA);
                            bloque.setEnabled(false);
                        }
                    }
                    panelCentro.add(bloque);
                }
            }

            botonAceptar = new JButton("Aceptar");
            botonAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonAceptar.addActionListener(this);
            panelSur.add(botonAceptar);

            botonCancelar = new JButton("Cancelar");
            botonAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonCancelar.addActionListener(this);
            panelSur.add(botonCancelar);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Main.guardarDatos(Main.fileHandler, Main.usuarios);
                }
            });
            setLayout(new BorderLayout());
            setBounds(0, 0, 1200, 900);
            getContentPane().setBackground(Color.CYAN);
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
                paginaPrincipal = new PaginaPrincipal();
                paginaCreacion.dispose();
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
                        JOptionPane.showMessageDialog(paginaCreacion, "Por favor llene todos los campos");
                    } else {
                        Main.agregarObjetivo(login, nombreObjetivo, descripcionObjetivo, metodologia, horas,
                                arregloDias, arregloHoras, paginaCreacion);
                        // Main.consultaHash(login);
                        paginaPrincipal = new PaginaPrincipal();
                        paginaCreacion.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(paginaCreacion,
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
    }

    public class PaginaConsulta extends JFrame implements ActionListener {
        JPanel panelNorte, panelCentro, panelOeste, panelEste, panelSur;
        JScrollPane panelDescripcion;
        JLabel etiqueta;
        JTextField campoNombre, campoMetodologia, campoHoras, campoHorasCompletadas, campoHorasRestantes;
        JButton botonVolver;
        JTextArea campoDescripcion;
        ArrayList<JButton> bloquesSeleccionados = new ArrayList<JButton>();

        PaginaConsulta() {
            panelNorte = new JPanel();
            panelNorte.setBackground(Color.BLUE);

            panelCentro = new JPanel();
            panelCentro.setLayout(new GridLayout(25, 8));
            panelCentro.setBackground(Color.GREEN);

            panelOeste = new JPanel();
            panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));
            panelOeste.setBackground(Color.PINK);

            panelEste = new JPanel();
            panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
            panelEste.setBackground(Color.CYAN);

            panelSur = new JPanel();
            panelSur.setBackground(Color.MAGENTA);

            etiqueta = new JLabel("Consulta de objetivo");
            etiqueta.setFont(new Font("Arial", Font.BOLD, 30));
            panelNorte.add(etiqueta);

            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

            etiqueta = new JLabel("Objetivos: ");
            panelEste.add(etiqueta);

            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

            login.arbolObjetivos.objetivos.clear();
            for (Objetivo i : login.arbolObjetivos.toArray(login.arbolObjetivos.root)) {
                JButton boton = new JButton(i.nombre);
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
                                bloque.setBackground(Color.GRAY);
                                ArrayList<Integer> listaHash = Main.consultaUnHash(login, boton.getText());
                                for (int k : listaHash) {
                                    if (k == ((j - 1) * 24 + i)) {
                                        bloque.setBackground(Color.MAGENTA);
                                        bloque.setEnabled(false);
                                    }
                                }
                                panelCentro.add(bloque);

                            }
                        }
                        paginaConsulta.setBounds(0, 0, 1200, 900);
                    }
                });
                panelEste.add(boton);
                panelEste.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            etiqueta = new JLabel("Nombre");
            // etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelOeste.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            campoNombre = new JTextField();
            campoNombre.setMaximumSize(new Dimension(330, 30));
            campoNombre.setEditable(false);
            panelOeste.add(campoNombre);

            etiqueta = new JLabel("Descripcion: ");
            // etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelOeste.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            campoDescripcion = new JTextArea();
            campoDescripcion.setEditable(false);
            campoDescripcion.setLineWrap(true);
            panelDescripcion = new JScrollPane(campoDescripcion);
            panelDescripcion.setViewportView(campoDescripcion);
            panelDescripcion.setMaximumSize(new Dimension(330, 400));
            panelOeste.add(panelDescripcion);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            etiqueta = new JLabel("Horas a dedicar: ");
            // etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelOeste.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            campoHoras = new JTextField();
            // campoHoras.setAlignmentX(Component.CENTER_ALIGNMENT);
            campoHoras.setEditable(false);
            campoHoras.setMaximumSize(new Dimension(340, 30));
            panelOeste.add(campoHoras);

            etiqueta = new JLabel("Horas completadas: ");
            panelOeste.add(etiqueta);

            panelOeste.add(Box.createRigidArea(new Dimension(0, 10)));

            campoHorasCompletadas = new JTextField();
            campoHorasCompletadas.setEditable(false);
            campoHorasCompletadas.setMaximumSize(new Dimension(340, 30));
            panelOeste.add(campoHorasCompletadas);

            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

            etiqueta = new JLabel("Horas restantes: ");
            panelOeste.add(etiqueta);

            panelEste.add(Box.createRigidArea(new Dimension(0, 10)));

            campoHorasRestantes = new JTextField();
            campoHorasRestantes.setEditable(false);
            campoHorasRestantes.setMaximumSize(new Dimension(340, 30));
            panelOeste.add(campoHorasRestantes);

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
            botonVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonVolver.addActionListener(this);
            panelSur.add(botonVolver);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Main.guardarDatos(Main.fileHandler, Main.usuarios);
                }
            });
            setLayout(new BorderLayout());
            setBounds(0, 0, 1200, 900);
            getContentPane().setBackground(Color.CYAN);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            add(panelNorte, BorderLayout.NORTH);
            add(panelCentro, BorderLayout.CENTER);
            add(panelOeste, BorderLayout.WEST);
            add(panelEste, BorderLayout.EAST);
            add(panelSur, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e) {
            paginaPrincipal = new PaginaPrincipal();
            paginaConsulta.dispose();
        }
    }

    public class PaginaEliminacion extends JFrame implements ActionListener, ChangeListener {
        JPanel panelNorte, panelCentro, panelSur;
        JLabel etiqueta;
        JButton botonVolver;
        ArrayList<Objetivo> objetivosSeleccionados = new ArrayList<Objetivo>();

        PaginaEliminacion() {
            panelNorte = new JPanel();
            panelNorte.setBackground(Color.CYAN);

            panelCentro = new JPanel();
            panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
            panelCentro.setBackground(Color.GREEN);

            panelSur = new JPanel();
            panelSur.setBackground(Color.MAGENTA);

            etiqueta = new JLabel("Eliminar objetivos");
            etiqueta.setFont(new Font("Arial", Font.BOLD, 30));
            panelNorte.add(etiqueta);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

            etiqueta = new JLabel("Seleccione los objetivos que desea eliminar: ");
            etiqueta.setFont(new Font("", Font.PLAIN, 25));
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(etiqueta);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

            login.arbolObjetivos.objetivos.clear();
            for (Objetivo i : login.arbolObjetivos.toArray(login.arbolObjetivos.root)) {
                JCheckBox objetivo = new JCheckBox(i.nombre);
                objetivo.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        if (objetivosSeleccionados.size() == 0) {
                            objetivosSeleccionados.add(i);
                        } else {
                            for (int j = 0; j <= objetivosSeleccionados.size(); j++) {
                                if (objetivosSeleccionados.get(j) == i && objetivosSeleccionados.get(j) != null) {
                                    objetivosSeleccionados.remove(j);
                                } else {
                                    objetivosSeleccionados.add(i);
                                }
                            }
                        }
                    }
                });
                objetivo.setAlignmentX(Component.CENTER_ALIGNMENT);
                objetivo.setFont(new Font("", Font.PLAIN, 18));
                objetivo.setBackground(Color.green);
                panelCentro.add(objetivo);
                panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            botonVolver = new JButton("Eliminar y volver");
            botonVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonVolver.addActionListener(this);
            panelSur.add(botonVolver);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Main.guardarDatos(Main.fileHandler, Main.usuarios);
                }
            });
            setLayout(new BorderLayout());
            setBounds(0, 0, 1200, 900);
            getContentPane().setBackground(Color.CYAN);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            add(panelNorte, BorderLayout.NORTH);
            add(panelCentro, BorderLayout.CENTER);
            add(panelSur, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botonVolver) {
                if (objetivosSeleccionados.size() == 0) {
                    JOptionPane.showMessageDialog(paginaEliminacion, "No se eliminó ningún objetivo");
                    paginaPrincipal = new PaginaPrincipal();
                    paginaEliminacion.dispose();
                } else {
                    for (Objetivo i : objetivosSeleccionados) {
                        Main.eliminarObjetivo(login, i.nombre);
                    }
                    JOptionPane.showMessageDialog(paginaEliminacion, "Objetivos eliminados");
                    paginaPrincipal = new PaginaPrincipal();
                    paginaEliminacion.dispose();
                }
            }
        }

        public void stateChanged(ChangeEvent e) {

        }
    }

    public class PaginaRegistro extends JFrame implements ActionListener {
        JPanel panelNorte, panelCentro;
        JLabel etiqueta;
        JTextField campoUsuario;
        JPasswordField campoContrasena;
        JButton botonIngreso, botonVolver;

        PaginaRegistro() {
            panelNorte = new JPanel();
            panelNorte.setBackground(Color.BLUE);

            panelCentro = new JPanel();
            panelCentro.setFont(new Font("Arial", Font.PLAIN, 12));
            panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
            panelCentro.setBackground(Color.GREEN);

            etiqueta = new JLabel("Registro Procastinator");
            etiqueta.setFont(new Font("Arial", Font.PLAIN, 30));
            panelNorte.add(etiqueta);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 100)));

            etiqueta = new JLabel("Escriba un usuario:", SwingConstants.CENTER);
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            etiqueta.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(etiqueta);

            campoUsuario = new JTextField();
            campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
            campoUsuario.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(campoUsuario);

            etiqueta = new JLabel("Escriba una contraseña:", SwingConstants.CENTER);
            etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
            etiqueta.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(etiqueta);

            campoContrasena = new JPasswordField();
            campoContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
            campoContrasena.setMaximumSize(new Dimension(200, 30));
            panelCentro.add(campoContrasena);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

            botonIngreso = new JButton("Registrarse");
            botonIngreso.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonIngreso.addActionListener(this);
            panelCentro.add(botonIngreso);

            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

            botonVolver = new JButton("Vover");
            botonVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonVolver.addActionListener(this);
            panelCentro.add(botonVolver);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Main.guardarDatos(Main.fileHandler, Main.usuarios);
                }
            });
            setLayout(new BorderLayout());
            setBounds(0, 0, 800, 600);
            getContentPane().setBackground(Color.CYAN);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            add(panelNorte, BorderLayout.NORTH);
            add(panelCentro, BorderLayout.CENTER);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botonIngreso) {
                String usuario = campoUsuario.getText();
                String contrasena = new String(campoContrasena.getPassword());
                if (usuario.equals("") || contrasena.equals("")) {
                    JOptionPane.showMessageDialog(paginaRegistro, "Por favor ingrese todos los campos");
                } else {
                    if (Main.registrarse(usuario, contrasena)) {
                        JOptionPane.showMessageDialog(paginaRegistro, "Usuario registrado con éxito");
                        paginaInicioSesion = new PaginaInicioSesion();
                        paginaRegistro.dispose();
                    } else {
                        JOptionPane.showMessageDialog(paginaRegistro, "Este nombre de usuario ya está registrado");
                    }
                }
            } else if (e.getSource() == botonVolver) {
                paginaInicioSesion = new PaginaInicioSesion();
                paginaRegistro.dispose();
            }
        }
    }
}