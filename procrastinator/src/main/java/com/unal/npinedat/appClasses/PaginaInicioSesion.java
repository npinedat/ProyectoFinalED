package com.unal.npinedat.appClasses;

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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.unal.npinedat.App;

public class PaginaInicioSesion extends InterfazUsuario implements ActionListener {
    JPanel panelNorte, panelCentro;
    JLabel etiqueta;
    JTextField campoUsuario;
    JPasswordField campoContrasena;
    JButton botonIngreso, botonRegistro;

    public PaginaInicioSesion() {
        panelNorte = new JPanel();
        panelNorte.setBackground(colorFondo);

        panelCentro = new JPanel();
        panelCentro.setFont(new Font("Arial", Font.PLAIN, 12));
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(colorFondo);

        etiqueta = new JLabel("Inicio sesión Procrastinator");
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
        botonIngreso.setBackground(colorBotones);
        botonIngreso.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonIngreso.addActionListener(this);
        panelCentro.add(botonIngreso);

        panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

        botonRegistro = new JButton("Registrarse");
        botonRegistro.setBackground(colorBotones);
        botonRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonRegistro.addActionListener(this);
        panelCentro.add(botonRegistro);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                App.guardarDatos(App.fileHandler, App.usuarios);
            }
        });

        setLayout(new BorderLayout());
        setBounds(0, 0, 800, 600);
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
                JOptionPane.showMessageDialog(this, "Debe ingresar todos los campos");
            } else {
                App.login = App.iniciarSesion(usuario, contrasena);
                if (App.login == null) {
                    JOptionPane.showMessageDialog(this,
                            "Los datos son incorrectos, intente de nuevo");
                } else {
                    try {
                        if(hilo.isAlive()){

                        }else{
                            hilo.start();
                        }
                        App.cronometroActivo = true;
                    } catch (Exception ev) {
                        System.out.println(ev);
                    }
                    PaginaPrincipal paginaPrincipal = new PaginaPrincipal();
                    this.dispose();
                }
            }
        } else if (e.getSource() == botonRegistro) {
            PaginaRegistro paginaRegistro = new PaginaRegistro();
            try {
                this.dispose();
            }catch(Exception ev) {
                System.out.println(ev);
            } 
            
        }
    }
}