package controlador;

import servico.MeuCliente;

public class GerenciadorChat {
    private MeuCliente cliente;

    public GerenciadorChat() {
        
    }
    
    public String enviarMensagem(String msg){
        cliente = new MeuCliente();
        return cliente.enviaMensagem(msg);
    }
}
