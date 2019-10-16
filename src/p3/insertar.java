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
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class insertar {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner teclado =new Scanner(System.in);
        File fichero = new File("AleatorioP3.dat");
        
        Double salario;
          String dni;
          String codigo;
          String nombre;
          
          RandomAccessFile file = new RandomAccessFile(fichero, "rw");
          long posicion;
          int id=0,reg=0;
          boolean flag=false;         
          long i=0;
          
         System.out.print("Introduzca id: ");
         id=teclado.nextInt();
          
          posicion=(id-1)*52;
        
             for(;;)
             {
              file.seek(i);
              reg=file.readInt();
              if(id==reg){
                  flag=true;
              }
              i=file.getFilePointer()+48;
              if(i==file.length())
                  break;
             }
              if(flag==false)
              {
                  System.out.print("\nDNI: ");
                  dni=teclado.next();
                  System.out.print("\nNombre: ");
                  nombre=teclado.next();
                  System.out.println("\nCódigo postal: ");
                  codigo=teclado.next();

                StringBuffer bufferDNI = null;//buffer para almacenar dni
                StringBuffer bufferNOM = null;// buffer para almacenar nombre
                StringBuffer bufferCOD = null;//buffer para almacenar  el código postal                                   
                  
                file.seek(posicion);
                  file.writeInt(id);
                 
                  bufferDNI =new StringBuffer(dni);
                  bufferDNI.setLength(9);
                  file.writeChars(bufferDNI.toString());
                  
                  
                   bufferNOM =new StringBuffer(nombre);
                  bufferNOM.setLength(10);
                  file.writeChars(bufferNOM.toString());
                  
                   bufferCOD =new StringBuffer(codigo);
                  bufferCOD.setLength(5);
                  file.writeChars(bufferCOD.toString());
              }
              else{

                  System.out.println("ID: "+id+" YA EXISTE ESTE ID");                
              }       
                   
              
          file.close();
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
