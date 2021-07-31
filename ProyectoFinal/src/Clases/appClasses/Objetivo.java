package Clases.appClasses;

import Clases.AVLTreesClasses.*;
import Clases.listClasses.*;


public class Objetivo {
    public int id;
    public String nombre;
    public String descripcion;
    public String tecnica;
    public int horasTotales;
    public int horasDedicadas;
    public int horasaDedicar;
    public Pila bloquesRestantes;
    public AVLTree bloquesProgramados;
    Cola ide;

    public Objetivo(){
        this.nombre = null;
        this.descripcion = null;
        this.tecnica = null;
        this.horasTotales = 0;
        this.horasDedicadas = 0;
        this.bloquesRestantes = null;
        this.bloquesRestantes = null;
        this.ide = null;
        recalcularHorasADedicar();
    }

    public Objetivo(String nombre, String descripcion, String tecnica, int horasTotales) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tecnica = tecnica;
        this.horasTotales = horasTotales;
        this.horasDedicadas = 0;
        this.bloquesRestantes = new Pila();
        this.bloquesProgramados = new AVLTree();
        this.ide = new Cola();
        recalcularHorasADedicar();
        for(char i: nombre.toCharArray()){
            id+=(int)i;
        }
    }

    public void setIde(Cola ide) {
        this.ide = ide;
    }

    public Cola getIde() {
        return ide;
    }

    public void recalcularHorasADedicar(){
        this.horasaDedicar = horasTotales - horasDedicadas;

    }
    
    public Boolean hayObjetivo(String us){
        if(nombre==us){ 
            return true;
        }else {
            return false;
        }
    }

    public Boolean existeObjetivo(int us) {
        if (bloquesProgramados.find(us) != null){
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

    /*public void programarBloque() {
        while(!ide.colaVacia()) {
            bloquesRestantes.retirar();
            bloquesProgramados.root = bloquesProgramados.insert(bloquesProgramados.root, ide.getRaiz());
            ide.desencolar();
        }
    }*/

    public void reencolarBloque(int key) {
        ide.encolar(key);
        bloquesProgramados.root = bloquesProgramados.delete(bloquesProgramados.root, key);
    }
}