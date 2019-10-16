/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Usuario
 */
public class P3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
      File fichero = new File("AleatorioP3.dat");
   //declara el fichero de acceso aleatorio
   RandomAccessFile file = new RandomAccessFile(fichero, "rw");
   //arrays con los datos
   String dni[] = {"75709653R","80153417G","13456789X"};//dni 
   String nombre[] = {"maría","rubén","alberto"};       //nombreartamentos
   String cod[]={"14200","14222","10019"};//cod
   
   StringBuffer bufferDNI = null;//buffer para almacenar dni
   StringBuffer bufferNOM = null;// buffer para almacenar nombre
   StringBuffer bufferCOD = null;//buffer para almacenar  el código postal
   int n=dni.length;//numero de elementos del array
    
   for (int i=0;i<n; i++){ //recorro los arrays          	  
	 file.writeInt(i+1); //uso i+1 para identificar empleado
    bufferDNI = new StringBuffer( dni[i] );      
    bufferDNI.setLength(9); //9 caracteres para el dni
    file.writeChars(bufferDNI.toString());//insertar dni
     
    bufferNOM = new StringBuffer( nombre[i] );      
    bufferNOM.setLength(10); //10 caracteres para el nombre
    file.writeChars(bufferNOM.toString());//insertar nombre
	
     bufferCOD = new StringBuffer( cod[i] );      
    bufferCOD.setLength(5); //5 caracteres para el codigo post
    file.writeChars(bufferCOD.toString());//insertar código post
    
   }     
   file.close();  //cerrar fichero 
   leer();
    }
    
    
    public static void leer() throws FileNotFoundException, IOException{
         File fichero = new File("AleatorioP3.dat");
  
   RandomAccessFile file = new RandomAccessFile(fichero, "r");
  
   int  id,posicion;  
   char dni[] = new char[9];
   char nombre[] = new char[10], aux; 
   char codigo[] = new char[5];

   posicion = 0;  

   for(;;){ 
	file.seek(posicion); 
	id = file.readInt();   	  	  
      
      for (int i = 0; i < dni.length; i++) {         
         aux = file.readChar(); 
         dni[i] = aux;    
      }

      String dniS = new String(dni); 

      for (int i = 0; i < nombre.length; i++) {         
         aux = file.readChar(); 
         nombre[i] = aux;    
      }

     
      String nombreS = new String(nombre);
      
      for (int i = 0; i < codigo.length; i++) {         
         aux = file.readChar(); 
         codigo[i] = aux;    
      }

     
      String codigoS = new String(codigo);
      
      
	if(id >0)
        System.out.printf("ID: %s, DNI: %s, Nombre: %s, Código Postal: %s \n",
        	id,   dniS.trim(),nombreS.trim(),codigoS.trim());     
	
      //me posiciono para el sig empleado, cada empleado ocupa 36 bytes
      posicion= posicion + 52;	 

	//Si he recorrido todos los bytes salgo del for	 	  
      if (file.getFilePointer() == file.length())
          break;
   
   }//fin bucle for 
   file.close(); 
    }
    
}
