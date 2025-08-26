package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import visao.FramePrincipal;

public class Atualizador extends Thread {
    private FramePrincipal frame = null;

    public Atualizador() {
        frame = GerenciadorIG.getInstancia().abrirFramePrincipal();
    }

    @Override
    public void run() {
        while(true){
            try {
                frame.atualizarMensagens();
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Atualizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
