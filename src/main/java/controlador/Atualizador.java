package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import visao.FramePrincipal;

public class Atualizador extends Thread {

    private FramePrincipal frame = null;
    //private FramePrincipal frame2 = null;

    public Atualizador(FramePrincipal frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        while (true) {
            try {
                frame.atualizarMensagens();
                //frame2.atualizarMensagens();
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Atualizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
