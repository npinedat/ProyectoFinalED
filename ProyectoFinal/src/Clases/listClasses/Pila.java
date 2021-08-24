
package Clases.listClasses;

import java.io.Serializable;

public class Pila implements Serializable {
    private Nodo tope;

    public int tamanio;

    public Pila() {
        tope = null;
        tamanio = 0;
    }

    public boolean esVacia() {
        if (tope == null) {
            return true;
        } else {
            return false;
        }
    }

    public int getTamanio() {
        return tamanio;
    }

    public void apilar(int valor) {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (esVacia()) {
            tope = nuevo;
        } else {
            nuevo.setSiguiente(tope);
            tope = nuevo;
        }
        tamanio++;
    }

    public void retirar() {
        if (!esVacia()) {
            tope = tope.getSiguiente();
            tamanio--;
        }
    }

    public Nodo cima() {
        if (!esVacia()) {
            return tope;
        } else {
            return null;
        }
    }

    public boolean buscar(int referencia) {
        Nodo aux = tope;
        boolean existe = false;
        while (existe != true && aux != null) {
            if (referencia == aux.getValor()) {
                existe = true;
            } else {
                aux = aux.getSiguiente();
            }
        }
        return existe;
    }

    public void remover(int referencia) {
        if (buscar(referencia)) {
            Nodo pilaAux = null;
            while (referencia != tope.getValor()) {
                Nodo temp = new Nodo();
                temp.setValor(tope.getValor());
                if (pilaAux == null) {
                    pilaAux = temp;
                } else {
                    temp.setSiguiente(pilaAux);
                    pilaAux = temp;
                }
                retirar();
            }
            retirar();
            while (pilaAux != null) {
                apilar(pilaAux.getValor());
                pilaAux = pilaAux.getSiguiente();
            }
            pilaAux = null;
        }
    }

    public void editar(int referencia, int valor) {
        if (buscar(referencia)) {
            Nodo pilaAux = null;
            while (referencia != tope.getValor()) {
                Nodo temp = new Nodo();
                temp.setValor(tope.getValor());
                if (pilaAux == null) {
                    pilaAux = temp;
                } else {
                    temp.setSiguiente(pilaAux);
                    pilaAux = temp;
                }
                retirar();
            }
            tope.setValor(valor);
            while (pilaAux != null) {
                apilar(pilaAux.getValor());
                pilaAux = pilaAux.getSiguiente();
            }
            pilaAux = null;
        }
    }

    public void eliminar() {
        tope = null;
        tamanio = 0;
    }

    public void listar() {
        Nodo aux = tope;

        while (aux != null) {
            System.out.println("|\t" + aux.getValor() + "\t|");
            System.out.println("-----------------");
            aux = aux.getSiguiente();
        }
    }
}