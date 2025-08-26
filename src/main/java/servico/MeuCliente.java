package servico;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MeuCliente {

    private String nomeDNS;
    private int serverPort;
    private byte[] meuIP;

    public MeuCliente() {
        try {
            InetAddress endereco = InetAddress.getLocalHost();
            nomeDNS = endereco.getHostName();
            meuIP = endereco.getAddress();
        } catch (UnknownHostException e) {
            System.out.println("Host Desconhecido: "+e.getMessage());
        }
        
        serverPort = 6789;
    }

    public MeuCliente(String nomeDNS) {
        nomeDNS = "Usuario-PC";
        meuIP = null;
        serverPort = 6789;
    }

    public String getNomeDNS() {
        return nomeDNS;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getMeuIP() {
        String s = new String(meuIP);
        return s;
    }
    
    
    
    public String enviaMensagem (String mensagem){
        DatagramSocket aSocket = null;
        String resposta = new String("");
        
        try {
            aSocket = new DatagramSocket();
            byte []m = mensagem.getBytes();
            InetAddress aHost = InetAddress.getByName(nomeDNS);
            DatagramPacket request = new DatagramPacket(m,m.length, aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[600];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            resposta = new String(reply.getData());
            resposta = resposta + "\n";
            
        } catch (SocketException ex) {
            Logger.getLogger(MeuCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MeuCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MeuCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(aSocket != null)
                aSocket.close();
        }
        return resposta;
    }

}
