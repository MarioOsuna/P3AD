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
public class eliminar {
      public static void main(String[] args) throws FileNotFoundException, IOException
     {
          Scanner teclado =new Scanner(System.in);

          File fichero = new File("AleatorioP3.dat");
          
          RandomAccessFile file = new RandomAccessFile(fichero, "rw");
           long posicion;
          int id=0,reg=0;  
             
         leer();
         System.out.print("Introduzca id que desea borrar: ");
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
                  file.close();
                  System.exit(3);
              }
              else
              {                   
                                          
                  file.seek(posicion);                  
                  file.writeInt(-1);
                  posicion=posicion+4;
                 
                  StringBuffer buffer=null; 
                 
                  buffer =new StringBuffer(Integer.toString(0));
                  buffer.setLength(9);
                  
                  file.writeChars(buffer.toString());
                   posicion=posicion+18;
                    file.seek(posicion);
                
                    buffer =new StringBuffer(Integer.toString(0));
                  buffer.setLength(10);
                  
                  file.writeChars(buffer.toString());
                  posicion=posicion+20;
                    file.seek(posicion);
                    
                      buffer =new StringBuffer(Integer.toString(0));
                  buffer.setLength(5);
                  
                  file.writeChars(buffer.toString());
                  posicion=posicion+10;
                    file.seek(posicion);
                  
                  
                  leer();

              }
         }
          file.close();
          
         
}
      public static void leerBorrados() throws FileNotFoundException, IOException{
           File fichero = new File("AleatorioP3.dat");

        RandomAccessFile file = new RandomAccessFile(fichero, "r");
            Double salario;
          int dep,id;
          char aux;
          char[] apellido=new char[10];
          long posicion=0;         
          
          System.out.println("Registros borrados:");
        for(;;){ 
         file.seek(posicion); 
         id = file.readInt(); 	  	  


       for (int i = 0; i < apellido.length; i++) {         
          aux = file.readChar(); 
          apellido[i] = aux;    
       }

     
       String apellidos = new String(apellido); 
         dep = file.readInt();        
         salario = file.readDouble(); 

         if(id <0)
         System.out.printf("ID: %s, Apellido: %s, Departamento: %d, Salario: %.2f %n",
                 id,   apellidos.trim(), dep, salario);     
      

       posicion= posicion + 36;	 

		  
         if (file.getFilePointer() == file.length())break;
   
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
