package UDP;

import java.io.*;
import java.net.*;

 public class Cliente2UDP {
    public static void main(String[] args) {
        try {
            BufferedReader doUsuario = new BufferedReader(new InputStreamReader(System.in));

            DatagramSocket socketCliente = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost"); 
            byte[] sendData = new byte[1024];;
            byte[] receiveData = new byte[1024];

            System.out.print("Escribe una frase: ");
            String frase = doUsuario.readLine();
            sendData = frase.getBytes();

            System.out.println("Enviando paquete...");
            DatagramPacket paqueteEnviado = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            socketCliente.send(paqueteEnviado);
            
            System.out.println("Recibiendo paquete...");
            DatagramPacket paqueteRecibido = new DatagramPacket(receiveData, receiveData.length);
            socketCliente.receive(paqueteRecibido);
            
            
            String fraseModificada = new String(paqueteRecibido.getData());

            System.out.println("Del Servidor: " + fraseModificada+"test");

            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

