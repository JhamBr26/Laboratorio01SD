package TCP;
import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        String fraseCliente;
        String fraseEnMayusculas;

        try {
            ServerSocket socketRecepcion = new ServerSocket(6789);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket socketConexion = socketRecepcion.accept();
                System.out.println("Cliente conectado desde " + socketConexion.getInetAddress());


                //Crea flujo de entrada, conectado al socket
                BufferedReader deCliente = new BufferedReader(new InputStreamReader(socketConexion.getInputStream()));
                //Crea flujo de salida, conectado al socket
                DataOutputStream paraCliente = new DataOutputStream(socketConexion.getOutputStream());

                //lee linea al socket
                fraseCliente = deCliente.readLine();
                System.out.println("Se recibe la frase del cliente "+fraseCliente);
                
                fraseEnMayusculas = fraseCliente.toUpperCase() + '\n';
                System.out.println("Se convierte a mayuscula "+fraseEnMayusculas);                
                
                //se escribe en la linea al socket
                paraCliente.writeBytes(fraseEnMayusculas);
                System.out.println("Mensaje devuelto");

                socketConexion.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
