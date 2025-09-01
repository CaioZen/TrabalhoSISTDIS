/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Caio
 */
public class TableModelUsuario extends AbstractTableModel {

    private List listaUsr = new ArrayList();

    @Override
    public int getRowCount() {
        return listaUsr.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
    
    public void Atualizar(String nome, boolean isOnline){
        if (nome == null || nome.isEmpty()){
            return;
        }
        if(isOnline && !listaUsr.contains(nome)){
            listaUsr.add(nome);
            fireTableRowsInserted(listaUsr.size() - 1, listaUsr.size() - 1);
        } else if(!isOnline && listaUsr.contains(nome)){
            int index = listaUsr.indexOf(nome);
            listaUsr.remove(nome);
            fireTableRowsDeleted(index, index);
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String item = (String) listaUsr.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String nomes[] = {"Usuarios Online"};
        return nomes[column];
    }

}
