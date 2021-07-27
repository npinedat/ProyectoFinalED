package Clases.appClasses;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class FIleHandler {
    File fileCreator;
    FileWriter fileWriter;
    Scanner fileReader;

    public File createFile(String fileName) {
        try{
            fileCreator = new File(fileName);
            if(fileCreator.createNewFile()){
                System.out.println("Archivo creado con éxito");
                return fileCreator;
            }else{
                System.out.println("El archivo ya existe");
                return null;
            }
        }catch(IOException e) {
            System.out.println("No se ha podido crear el archivo " + e);
            return null;
        }
    }

    public void writeFile(File file, String user, String password, ArrayList <Objetivo> objetivos) {
        try{
            fileWriter = new FileWriter(file.getName());
            fileWriter.write(user);
            fileWriter.write(password);
            for(Objetivo i: objetivos){
                fileWriter.write(i.nombre);
            }

        }catch(IOException e){
            System.out.println("No se ha podido escribir en el archivo " + e);
        }
    }
}
