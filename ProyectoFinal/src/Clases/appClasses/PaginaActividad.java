package Clases.appClasses;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Clases.Main.Main;

class PaginaActividad extends InterfazUsuario implements Runnable {
    JPanel panelNorte, panelCentro, panelSur;
    JLabel etiquetaTitulo, etiquetaMensaje, etiquetaCronometro;
    int minutos = 0;
    int segundos = 0;
    Thread hiloActividad = new Thread(this);
    boolean actividadActiva = true;

    PaginaActividad() {
        panelNorte = new JPanel();
        panelNorte.setBackground(colorFondo);

        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(colorFondo);

        panelSur = new JPanel();
        panelSur.setBackground(colorFondo);

        etiquetaTitulo = new JLabel("Sesión iniciada");
        etiquetaTitulo.setFont(new Font("Arial", Font.PLAIN, 30));
        panelNorte.add(etiquetaTitulo);

        panelCentro.add(Box.createRigidArea(new Dimension(0, 100)));

        etiquetaMensaje = new JLabel("Concentrese en su tarea, por favor.");
        etiquetaMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaMensaje.setFont(new Font("Arial", Font.PLAIN, 30));
        panelCentro.add(etiquetaMensaje);

        panelCentro.add(Box.createRigidArea(new Dimension(0, 100)));

        etiquetaCronometro = new JLabel("0" + minutos + ":" + segundos + "0");
        etiquetaCronometro.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaCronometro.setFont(new Font("", Font.PLAIN, 40));
        panelCentro.add(etiquetaCronometro);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Main.guardarDatos(Main.fileHandler, Main.usuarios);
            }
        });
        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
        hiloActividad.start();
    }

    public void run() {
        int estado = 1;
        int estados = 0;
        while (actividadActiva) {
            try {
                Thread.sleep(1000);
                segundos++;
                if (segundos >= 60) {
                    segundos = 0;
                    minutos++;
                }
                switch (estado) {
                    case 1:
                        if (minutos == 25) {
                            JOptionPane.showMessageDialog(paginaActividad, "Tome un descanso");
                            etiquetaMensaje.setText("Tome su descanso, por favor.");
                            minutos = 0;
                            segundos = 0;
                            estado = 2;
                            estados++;
                        }
                        break;
                    case 2:
                        if (estados == 4) {
                            JOptionPane.showMessageDialog(paginaActividad, "Ha terminado su sesión exitosamente");
                            Objetivo objetivoCercano = Main.consultaObjCercano(login);
                            objetivoCercano.horasDedicadas++;
                            objetivoCercano.recalcularHorasADedicar();
                            if (objetivoCercano.horasaDedicar == 0) {
                                JOptionPane.showMessageDialog(paginaActividad, "Felicidades completo su objetivo");
                                login.arbolObjetivos.root = login.arbolObjetivos.delete(login.arbolObjetivos.root,
                                        login.encontrarObjetivo(objetivoCercano.nombre).key);
                            }
                            actividadActiva = false;
                            paginaPrincipal = new PaginaPrincipal();
                            paginaActividad.dispose();
                        }
                        if (minutos == 5) {
                            JOptionPane.showMessageDialog(paginaActividad,
                                    "Su descanso acabó, regrese a su actividad");
                            etiquetaMensaje.setText("Concentrese en su tarea por favor");
                            minutos = 0;
                            segundos = 0;
                            estado = 1;
                            estados++;
                        }
                        break;
                }

                if (segundos < 10 && minutos < 10) {
                    etiquetaCronometro.setText("0" + minutos + ":" + "0" + segundos);
                } else if (minutos < 10) {
                    etiquetaCronometro.setText("0" + minutos + ":" + segundos);
                } else if (segundos < 10) {
                    etiquetaCronometro.setText(minutos + ":" + "0" + segundos);
                } else {
                    etiquetaCronometro.setText(minutos + ":" + segundos);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}