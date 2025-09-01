package controlador;

import java.awt.event.WindowAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import visao.FramePrincipal;

public class Atualizador extends Thread {

    private FramePrincipal frame = null;
    private FramePrincipal frame2 = null;

    public Atualizador() {
        frame = GerenciadorIG.getInstancia().abrirFramePrincipal();
        frame2 = GerenciadorIG.getInstancia().abrirFramePrincipal();

        // Maracutaia pra eu conseguir fechar um dos JFrame sem fechar todo o programa
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (!frame2.isDisplayable()) {
                    System.exit(0); // Encerra o programa se o outro frame também estiver fechado
                }
            }
        });
        frame2.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (!frame.isDisplayable()) {
                    System.exit(0); // Encerra o programa se o outro frame também estiver fechado
                }
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            try {
                frame.atualizarMensagens();
                frame2.atualizarMensagens();
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Atualizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
