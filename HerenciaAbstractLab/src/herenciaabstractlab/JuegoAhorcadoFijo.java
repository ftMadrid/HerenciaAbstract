package herenciaabstractlab;

import javax.swing.*;
import java.awt.*;

public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    private final PanelAhorcado panel;

    public JuegoAhorcadoFijo() {

        ventana.setSize(1000, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        panel = new PanelAhorcado();
        panel.setBounds(50, 35, 550, 500);
        ventana.add(panel);

        ingreso.setBounds(650, 100, 150, 50);
        ingreso.setFont(new Font("Arial", Font.PLAIN, 28));
        ingreso.setEnabled(false);
        ventana.add(ingreso);

        palabraL.setBounds(655, 230, 300, 30);
        palabraL.setFont(new Font("Arial", Font.PLAIN, 28));
        palabraL.setVisible(false);
        ventana.add(palabraL);

        intentosL.setBounds(655, 280, 300, 30);
        intentosL.setFont(new Font("Arial", Font.PLAIN, 26));
        intentosL.setVisible(false);
        ventana.add(intentosL);

        botonEnviar.setBounds(810, 100, 150, 50);
        botonEnviar.setFont(new Font("Arial", Font.BOLD, 26));
        botonEnviar.setForeground(new Color(22, 201, 52));
        botonEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonEnviar.setEnabled(false);
        ventana.add(botonEnviar);

        botonJugar.setBounds(720, 400, 150, 50);
        botonJugar.setFont(new Font("Arial", Font.BOLD, 22));
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
            panel.setErrores(limiteIntentos - intentos + 1);
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
        palabraSecreta = AdminPalabrasSecretas.palabraFija().toLowerCase();
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

    private class PanelAhorcado extends JPanel {

        private int errores = 0;
        private final Image[] partes;

        public PanelAhorcado() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            // Tamaño que queremos para cada parte dentro del panel
            int ancho = 80;   // ajusta según prefieras
            int alto = 80;

            partes = new Image[]{
                new ImageIcon(getClass().getResource("/herenciaabstractlab/imagenes/pie1.png")).getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH),
                new ImageIcon(getClass().getResource("/herenciaabstractlab/imagenes/pie2.png")).getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH),
                new ImageIcon(getClass().getResource("/herenciaabstractlab/imagenes/brazo1.png")).getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH),
                new ImageIcon(getClass().getResource("/herenciaabstractlab/imagenes/brazo2.png")).getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH),
                new ImageIcon(getClass().getResource("/herenciaabstractlab/imagenes/torso.png")).getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH),
                new ImageIcon(getClass().getResource("/herenciaabstractlab/imagenes/cabeza.png")).getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)
            };
        }

        public void setErrores(int errores) {
            this.errores = errores;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int baseX = 100; // posición X inicial
            int baseY = 50;  // posición Y inicial

            // Dibujar cada parte en posiciones separadas
            for (int i = 0; i < errores && i < partes.length; i++) {
                int offsetX = 0;
                int offsetY = 0;

                switch (i) {
                    case 0:
                        offsetX = 150;
                        offsetY = 400;
                        break; // pie1
                    case 1:
                        offsetX = 250;
                        offsetY = 400;
                        break; // pie2
                    case 2:
                        offsetX = 100;
                        offsetY = 250;
                        break; // brazo1
                    case 3:
                        offsetX = 300;
                        offsetY = 250;
                        break; // brazo2
                    case 4:
                        offsetX = 200;
                        offsetY = 250;
                        break; // torso
                    case 5:
                        offsetX = 200;
                        offsetY = 150;
                        break; // cabeza
                }

                g.drawImage(partes[i], offsetX, offsetY, null);
            }
        }
    }

    private final JFrame ventana = new JFrame("Juego del Ahorcado | Modo Fijo");
    private final JTextField ingreso = new JTextField();
    private final JLabel palabraL = new JLabel();
    private final JLabel intentosL = new JLabel();
    private final JButton botonEnviar = new JButton("Enviar");
    private final JButton botonJugar = new JButton("Jugar");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoAhorcadoFijo());
    }
}
