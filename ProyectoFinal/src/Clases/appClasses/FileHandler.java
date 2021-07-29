package Clases.appClasses;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class FileHandler {
    FileOutputStream fileCreator;
    ObjectOutputStream objectWriter;
    FileInputStream fileReader;
    ObjectInputStream objectReader;
    File fileHandler;

    public FileOutputStream createFile(String fileName) {
        try{
            fileCreator = new FileOutputStream(fileName);
            System.out.println("Archivo creado con éxito");
            return fileCreator;
        }catch(IOException e) {
            System.out.println("No se ha podido crear el archivo " + e);
            return null;
        }
    }

    public void writeFile(FileOutputStream file, ArrayList <Usuario> usuarios) {
        try{
            objectWriter = new ObjectOutputStream(file);
            objectWriter.writeObject(usuarios);
            objectWriter.close();
            System.out.println("Archivo escrito con éxito");
        }catch(IOException e){
            System.out.println("No se ha podido escribir en el archivo " + e);
        }
    }

    public ArrayList <Usuario> readFile(String fileName) {
        try{
            fileReader = new FileInputStream(fileName);
            objectReader = new ObjectInputStream(fileReader);
            @SuppressWarnings("unchecked")
            ArrayList <Usuario> arrayList = (ArrayList <Usuario>) objectReader.readObject();
            objectReader.close();
            System.out.println("Archivo leido con éxito");
            return arrayList;
        }catch(IOException | ClassNotFoundException e){
            System.out.println("No se pudo leer el archivo " + e);
            return null;
        }
    }

    public void deleteFile(String fileName) {
        fileHandler = new File(fileName);
        fileHandler.delete();
    }

    public boolean findFile(String fileName) {
        fileHandler = new File(fileName);
        return fileHandler.exists();
    }
}