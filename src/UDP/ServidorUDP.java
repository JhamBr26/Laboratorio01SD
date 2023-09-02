package UDP;

import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socketServidor = new DatagramSocket(9876);
            byte[] datosRecibidos = new byte[1024];
            byte[] datosEnviados = new byte[1024];

            System.out.println("Servidor esperando conexiones...");

            while (true) {
                DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);

                socketServidor.receive(paqueteRecibido);

                String frase = new String(paqueteRecibido.getData());
                System.out.println("Mensaje recibido del cliente: " + frase);
                
                InetAddress IPAddress = paqueteRecibido.getAddress();
                int port = paqueteRecibido.getPort();

                String fraseEnMayusculas = frase.toUpperCase();

                datosEnviados = fraseEnMayusculas.getBytes();

                DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length, IPAddress, port);

                socketServidor.send(paqueteEnviado);

                
                System.out.println("Mensaje enviado al cliente: " + fraseEnMayusculas);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

