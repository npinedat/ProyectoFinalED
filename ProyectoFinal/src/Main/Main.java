package Main;

import java.io.FileOutputStream;
import Clases.appClasses.FileHandler;
import Clases.appClasses.InterfazUsuario;
import Clases.appClasses.Objetivo;
import Clases.appClasses.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

@SuppressWarnings("unchecked")

/**
*
* @author Admin
*/


public class Main {
	static Usuario iniciarSesion(ArrayList<Usuario>usua) {
		Scanner reader =new Scanner(System.in);
		if (usua.isEmpty()){
			System.out.println("No hay usuarios registrados");
			return null;
		}
		System.out.println("Inserte Usario,contraseña");
		String user = reader.nextLine();
		String[] dt=user.split(",");  
		String id = dt[0];
		String ps = dt[1];
		Usuario login = null;
		for (Usuario i : usua){
			if(i.encontrarUsuario(id,ps)){
				login = i;
				System.out.println("Iniciaste sesion");
				return login;
			}
		}
		{
			System.out.println("Usuario no encontrado, registrese");
			return null;
		}
	}

	static void registrarse(ArrayList<Usuario>usua) {
		Scanner reader =new Scanner(System.in);
		System.out.println("Para registrarse inserte un nuevo usuario,contraseña");
		String usuario = reader.next();
		String[] dato = usuario.split(",");
		String nom = dato[0];
		String con = dato[1];
		Usuario usr = new Usuario(nom, con);
		usua.add(usr);
		System.out.println("Ya estas registrado");
	}

	static int diasemana(String d){
		if ("lunes".equals(d)){
		return 2;
		}   if ("martes".equals(d)){
		return 3;
		}   if ("miercoles".equals(d)){
		return 4;
		}   if ("jueves".equals(d)){
		return 5;
		}   if ("viernes".equals(d)){
		return 6;
		}   if ("sabado".equals(d)){
			return 7;
		} else   if ("domingo".equals(d)){
			return 1;
		}
		return 50;
    }

	static void agregarObjetivo(Usuario login) {
		Scanner reader =new Scanner(System.in);
		System.out.println("Escriba el nombre del objetivo:");
		String nombreDeObjetivo=reader.nextLine();
		System.out.println("Escriba la descripción del objetivo:");
		String descripcionDelObjetivo=reader.nextLine();
		System.out.println("Escriba el número de horas que le quiere dedicar");
		int hora=reader.nextInt();
		reader.nextLine();
		System.out.println("Escriba la tecnica: Pomodoro,POSEC,Matriz");
		String metodo=reader.nextLine();
		Objetivo obj = new Objetivo(nombreDeObjetivo,descripcionDelObjetivo,metodo,hora);
		obj.llenarBloquesRestantes();
		login.arbolObjetivos.root = login.arbolObjetivos.insert(login.arbolObjetivos.root, obj.id, obj);
		System.out.println("Ahora seleccione los dias de la semana que va a dedicar:");
		System.out.println("Para esto escriba los dias separados por comas por ejemplo: martes,jueves,domingo :");
		String dias=reader.nextLine();
		String[] diasArr=dias.split(",");
		System.out.println("Ahora seleccione la hora de inicio que le va a dedicar cada dia");
		System.out.println("Para esto escriba las horas de cada dia separadas por comas por ejemplo: 12,5,12:");
		String horas=reader.nextLine();
		String[] horaArr=horas.split(",");
		int[] horaArrInt= new int[horaArr.length];
		for (int i = 0; i < horaArr.length; i++){
			horaArrInt[i]= Integer.parseInt(horaArr[i]);
			int hash=((diasemana(diasArr[i]))*24)+ horaArrInt[i];
			obj.getIde().encolar(hash);
		}
		obj.programarBloque();
	}
	
	static void consultaObjetivo(Usuario login) {
		Scanner reader =new Scanner(System.in);
		if (login.objetivosVacio() == false) {
			System.out.println("No hay objetivos");
		} else {
			System.out.println("Inserte el nombre del objetivo");
			String nomObj = reader.nextLine();
			System.out.println("Id del objetivo: " + login.arbolObjetivos.root.key);
			if(login.encontrarObjetivo(nomObj) != null){
				Objetivo j = login.encontrarObjetivo(nomObj).objetivo;
				System.out.println("Nombre: " + j.nombre);
				System.out.println("Descripcion: " + j.descripcion);
				System.out.println("Tecnica: " + j.tecnica);
				System.out.println("Horas totales: " + j.horasTotales);
				System.out.println("Horas dedicadas: " + j.horasDedicadas);
				System.out.println("Horas restantes: " + j.horasaDedicar);
			}else{
				System.out.println("No existe el objetivo");
			}
		}

	}
	
	static void eliminarObjetivo(Usuario login) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Escriba el nombre objetivo");
		String nombreDeObjetivoE = reader.nextLine();
		if(login.encontrarObjetivo(nombreDeObjetivoE) != null){
			login.arbolObjetivos.root = login.arbolObjetivos.delete(login.arbolObjetivos.root, login.encontrarObjetivo(nombreDeObjetivoE).key);
			System.out.println("Objetivo eliminado");
		}
	}
	
	/*static void vaciarArbol(Objetivo obj) {
		//Toca buscarlo para mirarlo
		int i = 0;
		while (!obj.bloquesRestantes.esVacia()) {
			System.out.println(obj.bloquesRestantes.tamanio);
			while (obj.bloquesProgramados.root != null){
				System.out.println(obj.bloquesProgramados.root.key+" raiz iteración "+i);
				obj.reencolarBloque(obj.bloquesProgramados.root.key);
				obj.horasDedicadas = obj.horasDedicadas + 1;
				i++;
			}
			obj.programarBloque();
		}
		System.out.println("Pila vacia");
	}
   */

/**
* @param args the command line arguments
*/
	public static void main(String[] args) {
		InterfazUsuario interfaz = new InterfazUsuario();
		FileHandler fileHandler = new FileHandler();
		ArrayList<Usuario> users;
		if (fileHandler.findFile("data.txt")){
			users = (ArrayList <Usuario>) fileHandler.readFile("data.txt");
		} else {
			users = new ArrayList<Usuario>();
		}
		Scanner sc =new Scanner(System.in);
		int horaPreguntada = -1;
		int log;
		do {
			System.out.println("Si ya esta registrado escriba 1, si no escriba 2, si quiere salir escriba 3");
			log=sc.nextInt();
			sc.nextLine();
			switch(log){
				case 1 :
					Calendar c = Calendar.getInstance();
					Date date = new Date();
					c.setTime(date);
					Usuario login = iniciarSesion(users);
					if (login == null){
						break;
					}
					int ob2;
					Boolean horaHecha = false;
					do {
						System.out.println("Inserte: 1 si se quiere añadir objetivo, 2 para consultar objetivo, 3 Eliminar, 0 para cerrar sesión");
						int diaAct = c.get(Calendar.DAY_OF_WEEK);
						int horaAct = c.get(Calendar.HOUR_OF_DAY);
						int hashDiaHora = (diaAct * 24) + horaAct;
						if ((horaHecha == true) && (horaPreguntada != hashDiaHora)) {
							horaPreguntada = hashDiaHora;
							horaHecha = false;
						}
						ob2=sc.nextInt();
						sc.nextLine();
						switch(ob2){
							case 1 :
								agregarObjetivo(login);
								//vaciarArbol(obj);
								break;
							case 2:
								consultaObjetivo(login);
								break;
							case 3:
								eliminarObjetivo(login);
								break;
						}
						if (!login.arbolObjetivos.empty()) {
							for (Objetivo j : login.arbolObjetivos.toArray(login.arbolObjetivos.root)){									
								if(j.encontrarBloqueTiempo(hashDiaHora) && (!horaHecha)){
									System.out.println("¿Estas realizando el objetivo " + j.nombre + "? 1 para si, 0 para no");
									int reali = sc.nextInt();
									sc.nextLine();
									if (reali == 1){
										if(j.hs.list[hashDiaHora].getSiguiente() == null){
											j.hs.list[hashDiaHora] = null;
										}else if(!(j.hs.list[hashDiaHora].getSiguiente() == null)){
											j.hs.list[hashDiaHora] = j.hs.list[hashDiaHora].getSiguiente();
										}
										horaPreguntada = hashDiaHora;											
										horaHecha = true;
										j.horasDedicadas++;
										j.recalcularHorasADedicar();
									}
									break;
								}
							}
						}
					} while (ob2 != 0);
					break;
	
				case 2 :
					registrarse(users);
					break;
			
				case 3 :
					FileOutputStream file;
					if(fileHandler.findFile("data.txt")) {
						fileHandler.deleteFile("data.txt");
						file = fileHandler.createFile("data.txt");
						fileHandler.writeFile(file, users);
					}else{
						file = fileHandler.createFile("data.txt");
						fileHandler.writeFile(file, users); 
					}
					log = 0;
					break;
			}     
		} while (log != 0);
		sc.close();
	} 
}