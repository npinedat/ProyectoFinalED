package Clases.Main;

import java.io.FileOutputStream;
import Clases.appClasses.FileHandler;
import Clases.appClasses.InterfazUsuario;
import Clases.appClasses.Objetivo;
import Clases.appClasses.Usuario;
import Clases.listClasses.Nodo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.swing.JFrame;

@SuppressWarnings("unchecked")

/**
 *
 * @author Admin
 */

public class Main {
	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	public static FileHandler fileHandler;

	public static Usuario iniciarSesion(String usuario, String contrasena) {
		String id = usuario;
		String ps = contrasena;
		Usuario login = null;
		for (Usuario i : usuarios) {
			if (i.encontrarUsuario(id, ps)) {
				login = i;
				return login;
			}
		}
		{
			return null;
		}
	}

	public static boolean registrarse(String usuario, String contrasena) {
		for (Usuario i : usuarios) {
			if (i.usuario.equals(usuario)) {
				return false;
			}
		}
		String nom = usuario;
		String con = contrasena;
		Usuario usr = new Usuario(nom, con);
		usuarios.add(usr);
		return true;
	}

	public static int diasemana(String d) {
		if ("Lunes".equals(d)) {
			return 1;
		}
		if ("Martes".equals(d)) {
			return 2;
		}
		if ("Miercoles".equals(d)) {
			return 3;
		}
		if ("Jueves".equals(d)) {
			return 4;
		}
		if ("Viernes".equals(d)) {
			return 5;
		}
		if ("Sabado".equals(d)) {
			return 6;
		} else if ("Domingo".equals(d)) {
			return 0;
		}
		return 50;
	}

	public static String nombreDia(int d) {
		if (d == 1) {
			return "Lunes";
		}
		if (d == 2) {
			return "Martes";
		}
		if (d == 3) {
			return "Miercoles";
		}
		if (d == 4) {
			return "Jueves";
		}
		if (d == 5) {
			return "Viernes";
		}
		if (d == 6) {
			return "Sabado";
		} else if (d == 0) {
			return "Domingo";
		}
		return "";
	}

	public static int consultaHashCercano (Usuario login) {
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.get(Calendar.HOUR_OF_DAY);
		c.setTime(date);
		int diaAct = (c.get(Calendar.DAY_OF_WEEK) - 1); 
		int horaAct = c.get(Calendar.HOUR_OF_DAY); 
		int hashDiaHora = (diaAct * 24) + horaAct;
		int diferencia = 168;
		int hashCercano = 168;
		if (login.objetivosVacio()) {
			for (Objetivo j : login.arbolObjetivos.toArray(login.arbolObjetivos.root)) {
				Nodo i = j.getIde().getNodoRaiz();
				while (i != null) {
					if(Math.abs(i.getValor() - hashDiaHora) == 0 || Math.abs(i.getValor() - hashDiaHora) < diferencia) {
						diferencia = Math.abs(i.getValor() - hashDiaHora);
						hashCercano = i.getValor();
					}
					i = i.getSiguiente();
				}
			}
		}
		return hashCercano;
	}

	public static Objetivo consultaObjCercano (Usuario login){
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.get(Calendar.HOUR_OF_DAY);
		c.setTime(date);
		int diaAct = (c.get(Calendar.DAY_OF_WEEK) - 1); 
		int horaAct = c.get(Calendar.HOUR_OF_DAY); 
		int hashDiaHora = (diaAct * 24) + horaAct;
		int diferencia = 168;
		Objetivo k = null;
		if (login.objetivosVacio()) {
			for (Objetivo j : login.arbolObjetivos.toArray(login.arbolObjetivos.root)) {
				Nodo i = j.getIde().getNodoRaiz();
				while (i != null) {
					if(Math.abs(i.getValor() - hashDiaHora) == 0 || Math.abs(i.getValor() - hashDiaHora) < diferencia) {
						k = j;
						diferencia = Math.abs(i.getValor() - hashDiaHora);
					}
					i = i.getSiguiente();
				}
			}
		}
		return k;
	}

	public static ArrayList<Integer> consultaHash(Usuario login) {
		ArrayList<Integer> listaHash = new ArrayList<Integer>();
		if (login.objetivosVacio()) {
			for (Objetivo j : login.arbolObjetivos.toArray(login.arbolObjetivos.root)) {
				Nodo i = j.getIde().getNodoRaiz();
				while (i != null) {
					listaHash.add(i.getValor());
					i = i.getSiguiente();
				}
			}
		}
		System.out.println(listaHash);
		Collections.sort(listaHash);
		return listaHash;
	}

	public static ArrayList<Integer> consultaUnHash(Usuario login, String nomObj) {
		ArrayList<Integer> listaHash = new ArrayList<Integer>();
		if (login.encontrarObjetivo(nomObj) != null) {
			Objetivo j = login.encontrarObjetivo(nomObj).objetivo;
			Nodo i = j.getIde().getNodoRaiz();
			while (i != null) {
				listaHash.add(i.getValor());
				i = i.getSiguiente();
			}
		}
		Collections.sort(listaHash);
		return listaHash;
	}

	public static void agregarObjetivo(Usuario login, String nombreDeObjetivo, String descripcionDelObjetivo,
			String metodo, int horas, ArrayList<String> arregloDias, ArrayList<Integer> arregloHoras, JFrame pagina) {
		Objetivo obj = new Objetivo(nombreDeObjetivo, descripcionDelObjetivo, metodo, horas);
		obj.llenarBloquesRestantes();
		login.arbolObjetivos.root = login.arbolObjetivos.insert(login.arbolObjetivos.root, obj.id, obj, pagina);
		for (int i = 0; i < arregloHoras.size(); i++) {
			int hash = ((diasemana(arregloDias.get(i))) * 24) + arregloHoras.get(i);
			obj.getIde().encolar(hash);
		}
		obj.programarBloque();
	}

	public static void guardarDatos(FileHandler fileHandler, ArrayList<Usuario> users) {
		FileOutputStream file;
		if (fileHandler.findFile("data.txt")) {
			fileHandler.deleteFile("data.txt");
			file = fileHandler.createFile("data.txt");
			fileHandler.writeFile(file, users);
		} else {
			file = fileHandler.createFile("data.txt");
			fileHandler.writeFile(file, users);
		}
	}

	public static void eliminarObjetivo(Usuario login, String nombreObjetivo) {
		if (login.encontrarObjetivo(nombreObjetivo) != null) {
			login.arbolObjetivos.root = login.arbolObjetivos.delete(login.arbolObjetivos.root,
					login.encontrarObjetivo(nombreObjetivo).key);
		}
	}

	/*
	 * static void vaciarArbol(Objetivo obj) { //Toca buscarlo para mirarlo int i =
	 * 0; while (!obj.bloquesRestantes.esVacia()) {
	 * System.out.println(obj.bloquesRestantes.tamanio); while
	 * (obj.bloquesProgramados.root != null){
	 * System.out.println(obj.bloquesProgramados.root.key+" raiz iteración "+i);
	 * obj.reencolarBloque(obj.bloquesProgramados.root.key); obj.horasDedicadas =
	 * obj.horasDedicadas + 1; i++; } obj.programarBloque(); }
	 * System.out.println("Pila vacia"); }
	 */

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		fileHandler = new FileHandler();
		if (fileHandler.findFile("data.txt")) {
			usuarios = (ArrayList<Usuario>) fileHandler.readFile("data.txt");
		} else {
			usuarios = new ArrayList<Usuario>();
		}

		InterfazUsuario interfaz = new InterfazUsuario();
		interfaz.getPaginaInicioSesion();
	}
}