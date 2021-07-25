package Clases.appClasses;
import java.util.ArrayList;

public class Usuario {
    public String user;
    public String contrasena;
    public ArrayList<Objetivo> objetivos;

    public Usuario(String user, String contrasena) {
        this.user = user;
        this.contrasena = contrasena;
        this.objetivos = new ArrayList<>();

    }

    public Usuario(String user, String contrasena, ArrayList<Objetivo> objetivos) {
        this.user = user;
        this.contrasena = contrasena;
        this.objetivos = objetivos;
    }

    public Boolean hayUser(String us, String contra){
        if((contra.equals(contrasena)) && (us.equals(user))){
            //Usuario id = new Usuario(user,contrasena,objetivos); 
            return true;
        } else {
            return false;
        }
    }

    public Boolean hayObjetivos (Usuario usuario){
        if (usuario.objetivos.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}