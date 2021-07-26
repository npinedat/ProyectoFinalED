package Clases.appClasses;

import Clases.AVLTreesClasses.*;
import Clases.listClasses.*;


public class Objetivo {
    public String nombre;
    public String descripcion;
    public String tecnica;
    public int horasTotales;
    public int horasDedicadas;
    public int horasADedicar = horasTotales - horasDedicadas;
    public Pila bloquesRestantes;
    public AVLTree bloquesProgramados;
    private Cola ide;

    public Objetivo(String nombre, String descripcion, String tecnica, int horasTotales) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tecnica = tecnica;
        this.horasTotales = horasTotales;
        this.horasDedicadas = 0;
        this.bloquesRestantes = new Pila();
        this.bloquesProgramados = new AVLTree();
        this.ide = new Cola();
    }

    public void setIde(Cola ide) {
        this.ide = ide;
    }

    public Cola getIde() {
        return ide;
    }
    
    
    public Boolean encontrarObjetivo(String user){
        if(nombre == user){ 
            return true;
        }else {
            return false;
        }
    }

    public Boolean encontrarBloqueTiempo(int id) {
        if (bloquesProgramados.find(id)){
            return true;
        } else {
            return false;
        }
    }

    public void llenarBloquesRestantes() {
        int i = 0;
        while( i <= horasTotales) {
            bloquesRestantes.apilar(i);
            i++;
        }
    }

    public void programarBloque() {
        while(!ide.colaVacia()) {
            bloquesRestantes.retirar();
            bloquesProgramados.root = bloquesProgramados.insert(bloquesProgramados.root, ide.getRaiz());
            ide.desencolar();
        }
    }

    public void reencolarBloque(int key) {
        ide.encolar(key);
        bloquesProgramados.root = bloquesProgramados.delete(bloquesProgramados.root, key);
    }
}