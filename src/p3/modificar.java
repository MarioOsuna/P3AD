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
public class modificar {
    public static void main(String[] args) throws FileNotFoundException, IOException{
          
         Scanner teclado =new Scanner(System.in);
        
         File fichero = new File("AleatorioP3.dat");          
         
         RandomAccessFile file = new RandomAccessFile(fichero, "rw");
         
          long posicion;
          int id=0,reg=0;
         
         // Double salario,NSalario;
          int op;
          char aux;
          
          char[] dni=new char[9];
          char[] nombre=new char[10];
          char[] codigo=new char[5];
          
          String Ndni;
          String Ncod;
          String Nnom;
         
          StringBuffer bufferDNI = null;//buffer para almacenar dni
          StringBuffer bufferNOM = null;// buffer para almacenar nombre
          StringBuffer bufferCOD = null;//buffer para almacenar  el código postal
         
          leer();
         System.out.print("Introduzca id: ");
         id=teclado.nextInt();
              
         
         posicion=(id-1)*52;      
         if(posicion>=file.length()){
               System.out.println("REGISTRO INEXISTENTE");
             file.close();
             System.exit(3);
         }   
         else
         {
             file.seek(posicion);
              reg=file.readInt();
              if(reg!=id){
                  System.out.println("ID: "+id+", NO EXISTE EMPLEADO - HUECO...");
                  System.exit(3);
              }
              else
              {
                  System.out.println("\n Qué desea modificar? 1-DNI    2-Nombre    3-Código postal     4-TODO: ");
                op=teclado.nextInt();     
                switch(op){
                    case 1:
                        System.out.println("Ingrese nuevo DNI:");
                        Ndni=teclado.next();
                        
                       
                         
                         posicion=posicion+4;
                         file.seek(posicion);
                         
                          bufferDNI =new StringBuffer(Ndni);
                         bufferDNI.setLength(9);
                        
                        file.writeChars(bufferDNI.toString());//insertar dni
                        
                         leer();
                        break;
                    case 2:
                         System.out.println("Ingrese nuevo NOMBRE:");
                        Nnom=teclado.next();
                        
                       
                         
                         posicion=posicion+22;
                         file.seek(posicion);
                         
                          bufferNOM =new StringBuffer(Nnom);
                         bufferNOM.setLength(10);
                        
                        file.writeChars(bufferNOM.toString());//insertar dni
                        
                         leer();
                        break;
                    case 3:
                         System.out.println("Ingrese nuevo CÓDIGO POSTAL:");
                        Ncod=teclado.next();
                        
                       
                         
                         posicion=posicion+42;
                         file.seek(posicion);
                         
                          bufferCOD =new StringBuffer(Ncod);
                         bufferCOD.setLength(5);
                        
                        file.writeChars(bufferCOD.toString());//insertar dni
                        
                         leer();
                        break;
                    case 4:
                         System.out.print("\nDNI: ");
                    Ndni=teclado.next();
                    System.out.print("\nNombre: ");
                    Nnom=teclado.next();
                    System.out.println("\nCódigo postal: ");
                    Ncod=teclado.next();

                     posicion=posicion+4;
                     file.seek(posicion);
                    
                 
                  bufferDNI =new StringBuffer(Ndni);
                  bufferDNI.setLength(9);
                  file.writeChars(bufferDNI.toString());
                  
                  
                   bufferNOM =new StringBuffer(Nnom);
                  bufferNOM.setLength(10);
                  file.writeChars(bufferNOM.toString());
                  
                   bufferCOD =new StringBuffer(Ncod);
                  bufferCOD.setLength(5);
                  file.writeChars(bufferCOD.toString());
                         leer();
                        break;
                    default:
                        System.out.println("Opción no reconocida por el sistema");
                        break;
                        
                }

              }
         }
                    
         
          
          file.close();
          
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
