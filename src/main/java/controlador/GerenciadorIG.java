package controlador;

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import servico.MeuCliente;
import servico.MeuServidor;
import visao.DialogUsuario;
import visao.FramePrincipal;

public class GerenciadorIG {

    private static final GerenciadorIG instancia = new GerenciadorIG();

    private FramePrincipal framePrincipal = null;
    private static FramePrincipal frame = null;
    private static FramePrincipal frame2 = null;
    private DialogUsuario dialogUsuario = null;
    private static MeuCliente cliente = null;
    private static MeuServidor servidor = null;
    private static Atualizador atualizador = null;
    private static Atualizador atualizador2 = null;
    private static MonitorUsr monitorUsr = null;
    private static MonitorUsr monitorUsr2 = null;
    private GerenciadorChat gerChat = null;
    private TableModelUsuario tableModel = null;

    public static GerenciadorIG getInstancia() {
        return instancia;
    }

    private GerenciadorIG() {
        gerChat = new GerenciadorChat();
        tableModel = new TableModelUsuario();
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

    public FramePrincipal abrirFramePrincipal() {
        framePrincipal = new FramePrincipal(tableModel);
        framePrincipal.setVisible(true);
        framePrincipal.setNomeServer(cliente.getNomeDNS());
        return framePrincipal;
    }

    public DialogUsuario abrirCadastro() {
        return dialogUsuario = (DialogUsuario) abrirJanela(framePrincipal, dialogUsuario, DialogUsuario.class);
    }

    public static void main(String args[]) {
        GerenciadorIG gerIG = GerenciadorIG.getInstancia();
        servidor = new MeuServidor();
        cliente = new MeuCliente();
        frame = gerIG.abrirFramePrincipal();
        frame2 = gerIG.abrirFramePrincipal();

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
        atualizador = new Atualizador(frame);
        atualizador.start();
        atualizador2 = new Atualizador(frame2);
        atualizador2.start();
        monitorUsr = new MonitorUsr(frame);
        monitorUsr.start();
        monitorUsr2 = new MonitorUsr(frame2);
        monitorUsr2.start();
    }
}
