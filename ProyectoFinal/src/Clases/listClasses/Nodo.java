package Clases.listClasses;

public class Nodo {
    // Variable en la cual se va a guardar el valor.
    public int valor;
    // Variable para enlazar los nodos.
    private Nodo siguiente;
    /**
     * Constructor que inicializamos el valor de las variables.
     */
    public Nodo(){
        this.valor = 0;
        this.siguiente = null;
    }
    
    // M�todos get y set para los atributos.
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }   
}