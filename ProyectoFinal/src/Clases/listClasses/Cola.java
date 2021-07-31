
package Clases.listClasses;

import java.io.Serializable;
public class Cola implements Serializable{  
    private Nodo cabeza, cola;
    public Cola() {
        cabeza = null;
        cola = null;
    }

    public boolean colaVacia(){
        if (cabeza == null)
            return true;
        else
            return false;
    }

    public void encolar(int info) {
        Nodo nuevo;
        nuevo = new Nodo ();
        nuevo.setValor(info) ;
        nuevo.setSiguiente(null);
        if (colaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
    }

    public int desencolar() {
        if (!colaVacia()){
            int informacion = cabeza.getValor();
            if (cabeza == cola){
                cabeza = null;
                cola = null;
            } else {
                cabeza = cabeza.getSiguiente();
            }
            return informacion;
        } else
            return Integer.MAX_VALUE;
    }

    public int getRaiz() {
        return this.cabeza.getValor();
    }
}
