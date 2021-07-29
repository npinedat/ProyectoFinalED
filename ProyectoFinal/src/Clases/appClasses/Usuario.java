package Clases.appClasses;
import java.util.ArrayList;
import java.io.Serializable;

public class Usuario implements Serializable{
    public String usuario;
    public String contrasena;
    public ArrayList<Objetivo> objetivos;

    public Usuario(String user, String contrasena) {
        this.usuario = user;
        this.contrasena = contrasena;
        this.objetivos = new ArrayList<>();

    }

    public Boolean encontrarUsuario(String usuario, String contrasena){
        if((contrasena.equals(contrasena)) && (usuario.equals(usuario))){
            //Usuario id = new Usuario(user,contrasena,objetivos); 
            return true;
        } else {
            return false;
        }
    }

    public Boolean encontrarObjetivos(Usuario usuario) {
        if (usuario.objetivos.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}