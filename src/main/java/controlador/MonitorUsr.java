/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import visao.FramePrincipal;

/**
 *
 * @author Caio
 */
public class MonitorUsr extends Thread {

    private FramePrincipal frame;

    public MonitorUsr(FramePrincipal frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        while (true) {
            if (frame != null && frame.isVisible()) {
                frame.atualizarUsuarios(true);
            } else {
                frame.atualizarUsuarios(false);
            }
            try {
                Thread.sleep(1000); // Verifica a cada 1 segundo
            } catch (InterruptedException e) {
                System.out.println("Thread interrompida: " + e.getMessage());
            }
        }
    }

}
