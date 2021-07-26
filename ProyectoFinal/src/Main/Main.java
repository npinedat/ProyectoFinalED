
package Main;

import Clases.AVLTreesClasses.TreeNode;
import Clases.appClasses.Objetivo;
import Clases.appClasses.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
*
* @author Admin
*/
public class Main {

	static int diasemana(String d){
    if ("lunes".equals(d)){
    return 0;
    }   if ("martes".equals(d)){
    return 1;
    }   if ("miercoles".equals(d)){
    return 2;
    }   if ("jueves".equals(d)){
    return 3;
    }   if ("viernes".equals(d)){
    return 4;
    }   if ("sabado".equals(d)){
    return 5;
    }else   if ("domindo".equals(d)){
    return 6;
    }
    return 50;
    }
	
	static void vaciarArbol(Objetivo obj) {
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
   
/**
* @param args the command line arguments
*/
public static void main(String[] args) {
	ArrayList<Usuario> users= new ArrayList<Usuario>();
	Scanner sc =new Scanner(System.in);
	int hashDiaHoraAnterior = 0;
	int log;
	do {
		System.out.println("si ya esta registrado escriba 1, si no escriba 2, si quiere salir escriba 0");
		log=sc.nextInt();
		sc.nextLine();
		switch(log){
			case 1 :
				System.out.println("Inserte Usario,contraseña");
				String user=sc.next();
				String[] dt=user.split(",");  
				String id = dt[0];
				String ps = dt[1];
				Usuario login = null;
				Calendar c = Calendar.getInstance();
				Date date = new Date();
				c.setTime(date);
				for (Usuario i : users){
					if(i.hayUser(id,ps)){
						login = i;
						System.out.println("Iniciaste sesion");
						break;
					}
				}
				int ob2;
				do {
					System.out.println("Inserte: 1 si se quiere añadir objetivo, 2 para consultar objetivo, 3 Eliminar, 0 para cerrar sesión");
					int diaAct = c.get(Calendar.DAY_OF_WEEK);
					int horaAct = c.get(Calendar.HOUR);
					int hashDiaHora = (diaAct * 24) + horaAct;
					Objetivo objetivoARealizar = null;
					TreeNode temp;
					if (hashDiaHora != hashDiaHoraAnterior) {
						hashDiaHoraAnterior = hashDiaHora - 1;
					}
					ob2=sc.nextInt();
					sc.nextLine();
					switch(ob2){
						case 1 :
							System.out.println("Escriba el nombre del objetivo:");
							String nombreDeObjetivo=sc.nextLine();
							System.out.println("Escriba la describcion del objetivo:");
							String descripcionDelObjetivo=sc.nextLine();
							System.out.println("Escriba el numero de horas que le quiere dedicar");
							int hora=sc.nextInt();
							sc.nextLine();
							System.out.println("Escriba la tecnica,Pomodoro,POSEC,Matriz");
							String metodo=sc.nextLine();
							Objetivo obj = new Objetivo(nombreDeObjetivo,descripcionDelObjetivo,metodo,hora);
							obj.llenarBloquesRestantes();
							login.objetivos.add(obj);
							System.out.println("ahora seleccione los dias de la semana que va a dedicar:");
							System.out.println("Para esto escriba los dias separados por comas por ejemplo: martes,jueves,domingo :");
							String dias=sc.nextLine();
							String[] diasArr=dias.split(",");
							System.out.println("ahora seleccione la hora de inicio que le va a dedicar cada dia");
							System.out.println("Para esto escriba las horas de cada dia separadas por comas por ejemplo: 12,5,12:");
							String horas=sc.nextLine();
							String[] horaArr=horas.split(",");
							int[] horaArrInt= new int[horaArr.length];
							for (int i=0;i<horaArr.length;i++){
								horaArrInt[i]= Integer.parseInt(horaArr[i]);
								int hash=((diasemana(diasArr[i]))*24)+ horaArrInt[i];
								obj.getIde().insertarCol(hash);
							}
							obj.programarBloque();
							//vaciarArbol(obj);
							break;
						case 2:
							if (login.hayObjetivos(login) == false) {
								System.out.println("No hay objetivos");
							} else {
								System.out.println("Inserte el nombre del objetivo");
								String nomObj = sc.nextLine();
								Objetivo tempo = new Objetivo();
								for (Objetivo j : login.objetivos) {
									if (nomObj.equals(j.nombre)) {
										tempo = j;
										System.out.println("Nombre: " + j.nombre);
										System.out.println("Descripcion: " + j.descripcion);
										System.out.println("Tecnica: " + j.tecnica);
										System.out.println("Horas totales: " + j.horasTotales);
										System.out.println("Horas dedicadas: " + j.horasDedicadas);
										System.out.println("Horas restantes: " + j.horasaDedicar);
										break;
									} else {
										System.out.println("El objetivo no existe");
									}
								}
							}
							
							break;
						case 3:
							System.out.println("Escriba el nombre objetivo");
							String nombreDeObjetivoE=sc.nextLine();
							for (Objetivo j : login.objetivos ){
								if(!j.hayObjetivo(nombreDeObjetivoE)){
									System.out.println("Entra a eliminar");
									Objetivo objetivoRemover=j;
									login.objetivos.remove(objetivoRemover);
									break;
								}
							}
							break;
						}

						do {
							if (!login.objetivos.isEmpty()) {
								for (Objetivo j : login.objetivos ){
									if(j.existeObjetivo(hashDiaHora)){
										objetivoARealizar = j;
										System.out.println("¿Estas realizando el objetivo actual? 1 para si, 0 para no");
										int reali = sc.nextInt();
										sc.nextLine();
										if (reali == 1){
											objetivoARealizar.programarBloque();
											temp = objetivoARealizar.bloquesProgramados.delete(objetivoARealizar.bloquesProgramados.root, hashDiaHora);
											//objetivoARealizar.reencolarBloque(temp);
											objetivoARealizar.horasDedicadas = objetivoARealizar.horasDedicadas + 1;
											hashDiaHoraAnterior = hashDiaHora;
										}
										break;
									}
								}
							}
							break;
						} while (hashDiaHoraAnterior != hashDiaHora);
				} while (ob2 != 0);
	
			case 2 :
				System.out.println("Para registrarse inserte un nuevo usuario,contraseña");
				String usuario = sc.next();
				String[] dato = usuario.split(",");
				String nom = dato[0];
				String con = dato[1];
				Usuario usr = new Usuario(nom, con);
				users.add(usr);
				System.out.println("Ya estas registrado");
				break;
			}     
		} while (log != 0);
	} 
}