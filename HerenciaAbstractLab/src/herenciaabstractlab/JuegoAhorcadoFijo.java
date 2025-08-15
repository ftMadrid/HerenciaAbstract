/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        ingreso = new JTextField();
        ingreso.setBounds(100, 100, 50, 50);
        ingreso.setVisible(true);
        ventana.add(ingreso);
        
        
        
        
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
