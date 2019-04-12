
package GUI;

import java.io.FileNotFoundException;

// TODO: Auto-generated Javadoc
//import Sockets.Server;

/**
 * The Class MainTest.
 */
public class MainTest {


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		StartWindow inicio = new StartWindow();

		Vent_Inicio frame = new Vent_Inicio();  //Esta linea y la siguiente permiten probar las ventanas de inicio
		frame.setVisible(true);

		//Vent_Inicio frame = new Vent_Inicio();  //Esta linea y la siguiente permiten probar las ventanas de inicio
		//frame.setVisible(true);
//		Ventana2 ventana2 = new Ventana2();
//		String dato = "[{\"Name\":\"Daniel\",\"Fichas\":\"22\"}]" ; 
//		String[] dato2 = dato.split(",") ;
//		for(String i:dato2) {
//			System.out.println(i);	
//		}
//		
}

}