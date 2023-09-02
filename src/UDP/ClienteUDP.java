package UDP;

import java.io.*;
import java.net.*;

 public class ClienteUDP {
    public static void main(String[] args) {
        try {
            //Crea flujo de entrada
            BufferedReader doUsuario = new BufferedReader(new InputStreamReader(System.in));
            
            //Crea socket de cliente
            DatagramSocket socketCliente = new DatagramSocket();
            
            //traduce nombre host a la dirección IP usando dns
            InetAddress IPAddress = InetAddress.getByName("localhost"); 
            byte[] sendData = new byte[1024];;
            byte[] receiveData = new byte[1024];

            System.out.print("Escribe una frase: ");
            String frase = doUsuario.readLine();
            sendData = frase.getBytes();

            
            System.out.println("Enviando paquete...");
            //Crea datagrama con datos para enviar longitud, direccion, ip, puerta
            DatagramPacket paqueteEnviado = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            socketCliente.send(paqueteEnviado);
            
            
            System.out.println("Recibiendo paquete...");
            //enviar datagrama al servidor
            DatagramPacket paqueteRecibido = new DatagramPacket(receiveData, receiveData.length);
            //lee datagrama del servidor
            socketCliente.receive(paqueteRecibido);
            
            
            String fraseModificada = new String(paqueteRecibido.getData());

            System.out.println("Del Servidor: " + fraseModificada+"test");

            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

