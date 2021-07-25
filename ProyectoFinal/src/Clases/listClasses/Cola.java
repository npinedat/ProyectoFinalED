
package Clases.listClasses;

public class Cola {  
    private Nodo raiz,fondo;
    public Cola() {
        raiz=null;
        fondo=null;
    }

    public boolean vaciaCol (){
        if (raiz == null)
            return true;
        else
            return false;
    }

    public void insertarCol (int info) {
        Nodo nuevo;
        nuevo = new Nodo ();
        nuevo.setValor(info) ;
        nuevo.setSiguiente(null);
        if (vaciaCol ()) {
            raiz = nuevo;
            fondo = nuevo;
        } else {
            fondo.setSiguiente(nuevo);
            fondo = nuevo;
        }
    }

    public Object extraerCol () {
        if (!vaciaCol ()){
            Object informacion = raiz.getValor();
            if (raiz == fondo){
                raiz = null;
                fondo = null;
            } else {
                raiz = raiz.getSiguiente();
            }
            return informacion;
        } else
            return Integer.MAX_VALUE;
    }

    public int getRaiz() {
        return this.raiz.valor;
    }
}
