package herenciaabstractlab;

import javax.swing.*;
import java.awt.*;

public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    private AdminPalabrasSecretas admin;

    public JuegoAhorcadoFijo() {

        ventana.setSize(1000, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        
        panel.setBounds(50, 35, 550, 500);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ventana.add(panel);

        ingreso.setBounds(650, 100, 150, 50);
        ingreso.setFont(new Font("Kefa", Font.PLAIN, 28));
        ingreso.setEnabled(false);
        ventana.add(ingreso);

        palabraL.setBounds(655, 230, 300, 30);
        palabraL.setFont(new Font("Kefa", Font.PLAIN, 28));
        palabraL.setVisible(false);
        ventana.add(palabraL);

        intentosL.setBounds(655, 280, 300, 30);
        intentosL.setFont(new Font("Kefa", Font.PLAIN, 26));
        intentosL.setVisible(false);
        ventana.add(intentosL);

        botonEnviar.setBounds(810, 100, 150, 50);
        botonEnviar.setFont(new Font("Kefa", Font.BOLD, 26));
        botonEnviar.setForeground(new Color(22, 201, 52));
        botonEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonEnviar.setEnabled(false);
        ventana.add(botonEnviar);

        botonJugar.setBounds(720, 400, 150, 50);
        botonJugar.setFont(new Font("Kefa", Font.BOLD, 22));
        botonJugar.setForeground(new Color(5, 189, 245));
        botonJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ventana.add(botonJugar);

        botonJugar.addActionListener(e -> jugar());

        botonEnviar.addActionListener(e -> enviarAction());

        ventana.setVisible(true);
    }

    private void enviarAction() {
        String texto = ingreso.getText().trim();
        if (!texto.isEmpty()) {
            char letra = texto.charAt(0);
            procesarLetra(letra);
        }
        ingreso.setText("");
    }

    private void procesarLetra(char letra) {
        letra = Character.toLowerCase(letra);

        if (letraUsadas.contains(letra)) {
            JOptionPane.showMessageDialog(ventana, "Ya usaste esa letra");
            return;
        }

        letraUsadas.add(letra);

        if (verificarletra(letra)) {
            actualizarPalabraActual(letra);
            palabraL.setText(formatearConEspacios(palabraActual.toUpperCase()));
        } else {
            intentos--;
            intentosL.setText("Intentos restantes: " + intentos);
        }

        if (hasGanado()) {
            JOptionPane.showMessageDialog(ventana, "¡Ganaste!");
            ventana.dispose();
            new Main().setVisible(true);
        } else if (intentos <= 0) {
            JOptionPane.showMessageDialog(ventana, "¡Perdiste! La palabra era: " + palabraSecreta);
            ventana.dispose();
            new Main().setVisible(true);
        }
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        char[] temp = palabraActual.toCharArray();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                temp[i] = letra;
            }
        }
        palabraActual = new String(temp);
    }

    @Override
    public boolean verificarletra(char letra) {
        return palabraSecreta.indexOf(letra) >= 0;
    }

    @Override
    public boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }

    @Override
    public void inicializarPalabraSecreta() {
        palabraSecreta = admin.getPalabra().toLowerCase();
        palabraActual = "_".repeat(palabraSecreta.length());
        palabraL.setText(formatearConEspacios(palabraActual.toUpperCase()));
        palabraL.setVisible(true);
        letraUsadas.clear();
        intentos = limiteIntentos;
        intentosL.setText("Intentos restantes: " + intentos);
        intentosL.setVisible(true);
        ingreso.setEnabled(true);
        botonEnviar.setEnabled(true);
        botonJugar.setEnabled(false);
    }

    private String formatearConEspacios(String texto) {
        return texto.replaceAll("", " ").trim();
    }

    @Override
    public void jugar() {
        inicializarPalabraSecreta();
    }

    private final JFrame ventana = new JFrame("Juego del Ahorcado | Modo Fijo");
    private final JPanel panel = new JPanel();
    private final JTextField ingreso = new JTextField();
    private final JLabel palabraL = new JLabel();
    private final JLabel intentosL = new JLabel();
    private final JButton botonEnviar = new JButton("Enviar");
    private final JButton botonJugar = new JButton("Jugar");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoAhorcadoFijo());
    }
}
