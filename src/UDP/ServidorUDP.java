package UDP;

import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            //crea socket para datagrama en la puerta 9876
            DatagramSocket socketServidor = new DatagramSocket(9876);
            byte[] datosRecibidos = new byte[1024];
            byte[] datosEnviados = new byte[1024];

            System.out.println("Servidor esperando conexiones...");

            while (true) {
                //asigname memoria para recibir datagrama
                DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);

                socketServidor.receive(paqueteRecibido);

                String frase = new String(paqueteRecibido.getData());
                System.out.println("Mensaje recibido del cliente: " + frase);
                
                //Obtiene dirección IP, no. de puerta del remitente
                InetAddress IPAddress = paqueteRecibido.getAddress();
                int port = paqueteRecibido.getPort();

                String fraseEnMayusculas = frase.toUpperCase();

                datosEnviados = fraseEnMayusculas.getBytes();
                //Crea datagrama para enviar al cliente
                DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length, IPAddress, port);

                //Escribe datagrama al socket
                socketServidor.send(paqueteEnviado);
                
                System.out.println("Mensaje enviado al cliente: " + fraseEnMayusculas);

                //Fin del while, vuelve al inicio y espera la llegada de otro datagrama
            }
        } catch (IOException e) {
        	//e.printStackTrace();
        	System.out.println("Se ha presentado un error");
        }
    }
}

