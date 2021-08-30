package com.unal.npinedat.listClasses;

import java.io.Serializable;

public class Hash implements Serializable {
   public Nodo list[];

   public Hash(){
      this.list = new Nodo[168];
   }
}
