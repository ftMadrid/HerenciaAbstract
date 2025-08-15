package herenciaabstractlab;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class AdminPalabrasSecretas extends JFrame{

    static ArrayList<String> palabras = new ArrayList<>();
    Random random = new Random();

    public AdminPalabrasSecretas() {
        palabras.add("Hola");
        palabras.add("Adios");
        palabras.add("Bonjour");

    }

    public String getPalabra() {
        int numRandom = random.nextInt(palabras.size() - 1);
        return palabras.get(numRandom);
    }

    public boolean agregarPalabra(String palabra) {
        if (buscarPalabra(palabra)) {
            JOptionPane.showMessageDialog(null, "Ya existe esa palabra");
            return false;

        }
        palabras.add(palabra);
        return true;
    }

    public boolean buscarPalabra(String palabra) {
        for (String p : palabras) {
            if (p.equals(palabra)) {
                return false;
            }
        }
        return true;
    }

}


