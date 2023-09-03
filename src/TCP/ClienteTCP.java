package TCP;
import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) {
        String frase;
        String fraseModificada;

        try {
            //Crea flujo de entrada
            BufferedReader deUsuario = new BufferedReader(new InputStreamReader(System.in));

            //Crea socket de cliente, conexión al servidor
            Socket socketCliente = new Socket("localhost", 6789); 

            //Crea output stream asignado al socket
            DataOutputStream paraServidor = new DataOutputStream(socketCliente.getOutputStream());

            //Crea flujo de entrada conectado al socket
            BufferedReader deServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            System.out.print("Escribe una frase: ");
            frase = deUsuario.readLine();

            //Envía línea al servidor
            paraServidor.writeBytes(frase + '\n');
            
            //Lee línea del servidor
            fraseModificada = deServidor.readLine();
            System.out.println("Del Servidor: " + fraseModificada);

            socketCliente.close();
        } catch (IOException e) {
            //e.printStackTrace();
        	System.out.println("Se ha presentado un error");
        }
    }
}

