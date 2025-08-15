package herenciaabstractlab;

import javax.swing.*;

/**
 *
 * @author user
 */
public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    AdminPalabrasSecretas admin;
    String palabraJugar;
    int intentos;
    JFrame ventana;
    JButton boton;
    JTextField ingreso;

    public JuegoAhorcadoFijo() {
    ventana = new JFrame("Juego del Ahorcado");
    ventana.setSize(500, 500);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setLayout(null);
    ventana.setResizable(false);
    ventana.setLocationRelativeTo(null);

    // Inicializamos el administrador de palabras
    admin = new AdminPalabrasSecretas();

    // Crear JTextField
    ingreso = new JTextField();
    ingreso.setBounds(100, 100, 150, 30); // 
    ventana.add(ingreso);

    // Crear botón para enviar letra
    boton = new JButton("Enviar");
    boton.setBounds(260, 100, 100, 30);
    ventana.add(boton);

    // Mostrar ventana al final
    ventana.setVisible(true);
}


    @Override
    public char actualizarPalabraActual(char letra) {
        if (verificarletra(letra)) {

        }
        return 'a';

    }

    @Override
    public boolean verificarletra(char letra) {
        return true;

    }

    @Override
    public boolean hasGanado() {
        return true;

    }

    @Override
    public void inicializarPalabraSecreta() {
        palabraJugar = admin.getPalabra();
    }

    @Override
    public void jugar() {

    }

    public static void main(String[] args) {
        JuegoAhorcadoFijo a = new JuegoAhorcadoFijo();
    }

}
