package Clases.appClasses;

import java.awt.Font;
import java.awt.BorderLayout;
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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Clases.Main.Main;

public class PaginaEliminacion extends InterfazUsuario implements ActionListener {
    JPanel panelNorte, panelCentro, panelSur;
    JLabel etiqueta;
    JButton botonVolver;
    ArrayList<JCheckBox> objetivosSeleccionados = new ArrayList<JCheckBox>();

    PaginaEliminacion() {
        panelNorte = new JPanel();
        panelNorte.setBackground(colorFondo);

        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(colorFondo);

        panelSur = new JPanel();
        panelSur.setBackground(colorFondo);

        etiqueta = new JLabel("Eliminar objetivos");
        etiqueta.setFont(new Font("Arial", Font.BOLD, 30));
        panelNorte.add(etiqueta);

        panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

        etiqueta = new JLabel("Seleccione los objetivos que desea eliminar: ");
        etiqueta.setFont(new Font("", Font.PLAIN, 25));
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentro.add(etiqueta);

        panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

        Main.login.arbolObjetivos.objetivos.clear();
        for (Objetivo i : Main.login.arbolObjetivos.toArray(Main.login.arbolObjetivos.root)) {
            JCheckBox objetivo = new JCheckBox(i.nombre);
            objetivo.setAlignmentX(Component.CENTER_ALIGNMENT);
            objetivo.setFont(new Font("", Font.PLAIN, 18));
            objetivo.setBackground(colorFondo);
            objetivosSeleccionados.add(objetivo);
            panelCentro.add(objetivo);
            panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        botonVolver = new JButton("Eliminar y volver");
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
        setBounds(0, 0, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonVolver) {
            if (objetivosSeleccionados.size() == 0) {
                JOptionPane.showMessageDialog(this, "No se eliminó ningún objetivo");
                PaginaPrincipal paginaPrincipal = new PaginaPrincipal();
                this.dispose();
            } else {
                for (JCheckBox i : objetivosSeleccionados) {
                    if (i.isSelected()) {
                        Main.eliminarObjetivo(Main.login, i.getText());
                    }
                }
                JOptionPane.showMessageDialog(this, "Objetivos eliminados");
                PaginaPrincipal paginaPrincipal = new PaginaPrincipal();
                this.dispose();
            }
        }
    }
}