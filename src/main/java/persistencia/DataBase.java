package persistencia;

import java.util.ArrayList;

public class DataBase {

    private ArrayList lista = null;

    public DataBase() {
        lista = new ArrayList();
    }

    public void insere(String msg) {
        lista.add(msg.trim());
    }

    public String le() {
        String s = "\n";
        int fim = lista.size();
        for (int pos = 0; pos < fim; pos++) {
            s = s + "["+(pos + 1)+"] " + (String)lista.get(pos)+ "\n";
        }
        return s;
    }

}