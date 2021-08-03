package Clases.appClasses;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class InterfazUsuario extends JFrame{

    PaginaPrincipal paginaPrincipal;
    PaginaInicio paginaInicio;
    PaginaCreacion paginaCreacion;
    PaginaConsulta paginaConsulta;

    public InterfazUsuario() {
        paginaPrincipal = new PaginaPrincipal();
        paginaInicio = new PaginaInicio();
        paginaCreacion = new PaginaCreacion();
        paginaConsulta = new PaginaConsulta();
    }
    public class PaginaPrincipal extends JFrame{
        JPanel panelCalendario;
        JPanel contenedoresBloque;
        JButton botonCreacion;
        JButton botonConsulta;
        JButton botonEliminar;
        JLabel etiquetaTitulo;
        PaginaPrincipal() {
            setBounds(200, 200, 800,600);
            panelCalendario = new JPanel();
            panelCalendario.setBounds(/*(int)Math.floor(getBounds().width*0.2)*/ 0, /*(int)Math.floor(getBounds().height*0.2)*/0, 100, 100);
            panelCalendario.setBackground(Color.BLUE);
            add(panelCalendario);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            panelCalendario.setVisible(true);
            setVisible(true);
        }
    }

    public class PaginaInicio {
        PaginaInicio() {

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
