package herenciaabstractlab;

import javax.swing.*;

public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    private AdminPalabrasSecretas admin;

    private JFrame ventana;
    private JButton botonEnviar, botonJugar;
    private JTextField ingreso;
    private JLabel palabraL, intentosL;

    public JuegoAhorcadoFijo() {
        
        ventana = new JFrame("Juego del Ahorcado");
        ventana.setSize(500, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        admin = new AdminPalabrasSecretas();

        
        ingreso = new JTextField();
        ingreso.setBounds(100, 100, 150, 30);
        ventana.add(ingreso);

        
        palabraL = new JLabel();
        palabraL.setBounds(100, 200, 300, 30);
        palabraL.setVisible(false);
        ventana.add(palabraL);

        
        intentosL = new JLabel();
        intentosL.setBounds(100, 250, 200, 30);
        intentosL.setVisible(false);
        ventana.add(intentosL);
        
        
        botonEnviar = new JButton("Enviar");
        botonEnviar.setBounds(260, 100, 100, 30);
        ventana.add(botonEnviar);

        
        botonJugar = new JButton("Jugar");
        botonJugar.setBounds(260, 130, 100, 30);
        ventana.add(botonJugar);
        
        botonJugar.addActionListener(e -> {
            jugar();
        });

        
        botonEnviar.addActionListener(e -> {
            String texto = ingreso.getText().trim();
            if (!texto.isEmpty()) {
                char letra = texto.charAt(0);
                procesarLetra(letra);
            }
            ingreso.setText("");
        });

        ventana.setVisible(true);
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
            palabraL.setText(palabraActual);
        } else {
            intentos--;
            intentosL.setText("Intentos restantes: " + intentos);
        }

        if (hasGanado()) {
            JOptionPane.showMessageDialog(ventana, "¡Ganaste!");
            palabraL.setVisible(false);
            intentosL.setVisible(false);
        } else if (intentos <= 0) {
            JOptionPane.showMessageDialog(ventana, "¡Perdiste! La palabra era: " + palabraSecreta);
            palabraL.setVisible(false);
            intentosL.setVisible(false);
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
        palabraL.setText(palabraActual);
        palabraL.setVisible(true);
        letraUsadas.clear();
        intentos = limiteIntentos;
        intentosL.setText("Intentos restantes: " + intentos);
        intentosL.setVisible(true);
    }

    @Override
    public void jugar() {
        inicializarPalabraSecreta();
    }

    public static void main(String[] args) {
        new JuegoAhorcadoFijo();
    }
}
