package servico;

import java.net.*;
import java.io.*;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.DataBase;

public class MeuServidor {

    private static DataBase db = null;
    private DatagramSocket aSocket = null;

    public MeuServidor() {
        db = new DataBase();
        iniciarServidor();
    }

    private void iniciarServidor() {
        new Thread(() -> {
            try {
                aSocket = new DatagramSocket(6789);
                System.out.println("Servidor iniciado na porta 6789...");
                while (true) {
                    byte[] buffer = new byte[600];
                    DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                    aSocket.receive(request);
                    String mensagem = new String(request.getData()).trim();
                    if (!mensagem.equalsIgnoreCase("ATT")) {
                        System.out.println("\nINSERIDO: " + mensagem + " TEMPO: " + LocalTime.now());
                        db.insere(mensagem.toUpperCase());
                    }
                    String resposta = db.le();
                    byte[] todasMsg = resposta.getBytes();
                    DatagramPacket reply = new DatagramPacket(todasMsg, todasMsg.length, request.getAddress(), request.getPort());
                    aSocket.send(reply);
                }
            } catch (SocketException e) {
                System.out.println("SocketException: " + e.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(MeuServidor.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (aSocket != null && !aSocket.isClosed()) {
                    aSocket.close();
                    System.out.println("Socket fechado.");
                }
            }
        }).start();
    }
    
    public void close() {
        if (aSocket != null && !aSocket.isClosed()) {
            aSocket.close();
        }
    }
}