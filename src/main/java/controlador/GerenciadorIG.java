package controlador;

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import servico.MeuCliente;
import servico.MeuServidor;
import visao.DialogUsuario;
import visao.FramePrincipal;

public class GerenciadorIG {

    private static final GerenciadorIG instancia = new GerenciadorIG();
    
    private FramePrincipal framePrincipal = null;
    private DialogUsuario dialogUsuario = null;
    private static MeuCliente cliente = null;
    private static MeuServidor servidor = null;
    private static Atualizador atualizador = null;
    private GerenciadorChat gerChat = null;

    public static GerenciadorIG getInstancia() {
        return instancia;
    }

    private GerenciadorIG() {
        gerChat = new GerenciadorChat();
    }

    public GerenciadorChat getGerChat() {
        return gerChat;
    }

    public JDialog abrirJanela(java.awt.Frame parent, JDialog dlg, Class classe) {
        if (dlg == null) {
            try {
                dlg = (JDialog) classe.getConstructor(Frame.class, boolean.class).newInstance(parent, true);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                JOptionPane.showMessageDialog(parent, "Erro ao abrir a janela " + classe.getName() + ". " + ex.getMessage());
            }
        }
        dlg.setVisible(true);
        return dlg;
    }
    
    public FramePrincipal abrirFramePrincipal(){
        framePrincipal = new FramePrincipal();
        framePrincipal.setVisible(true);
        framePrincipal.setNomeServer(cliente.getNomeDNS());
        return framePrincipal;
    }
    
    public DialogUsuario abrirCadastro(){
        return dialogUsuario = (DialogUsuario) abrirJanela(framePrincipal, dialogUsuario, DialogUsuario.class);
    }
    
    public static void main(String args[]){
        GerenciadorIG gerIG = GerenciadorIG.getInstancia();
        servidor = new MeuServidor();
        cliente = new MeuCliente();
        atualizador = new Atualizador();
        atualizador.start();
    }
}
