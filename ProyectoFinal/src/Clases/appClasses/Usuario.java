package Clases.appClasses;

import Clases.AVLTreesClasses.AVLTree;
import Clases.AVLTreesClasses.TreeNode;

import java.io.Serializable;

public class Usuario implements Serializable{
    public String usuario;
    public String contrasena;
    public AVLTree arbolObjetivos = new AVLTree();
    public int id;

    public Usuario(String user, String contrasena) {
        this.usuario = user;
        this.contrasena = contrasena;
    }

    public Boolean encontrarUsuario(String usuario, String contrasena){
        if((contrasena.equals(this.contrasena)) && (usuario.equals(this.usuario))){
            //Usuario id = new Usuario(user,contrasena,objetivos); 
            return true;
        } else {
            return false;
        }
    }

    public Boolean objetivosVacio() {
        if (arbolObjetivos.empty()) {
            return false;
        } else {
            return true;
        }
    }

    public TreeNode encontrarObjetivo(String nombre) {
        int id = 0;
        for(char i: nombre.toCharArray()){
            id += (int)i;
        }
        return arbolObjetivos.find(id);
    }
}