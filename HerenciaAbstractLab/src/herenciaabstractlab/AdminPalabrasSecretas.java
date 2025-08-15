package herenciaabstractlab;

import javax.swing.*;
import java.util.ArrayList;

public class AdminPalabrasSecretas extends JFrame {

    public static ArrayList<String> palabras = new ArrayList<>();

    public static boolean primeraVez = true;
    public static String palabraFija = null;

    private JTextField ingreso;
    private JButton agregar, reset, finalizar;
    private JLabel info;
    private int contador = 0;
    private final int MAX_PALABRAS = 10;

    public AdminPalabrasSecretas() {
        super("Administrador de Palabras");
        palabras.clear();
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ingreso = new JTextField();
        ingreso.setBounds(50, 50, 200, 30);
        add(ingreso);

        agregar = new JButton("Agregar");
        agregar.setBounds(260, 50, 100, 30);
        add(agregar);

        reset = new JButton("Resetear");
        reset.setBounds(50, 100, 100, 30);
        add(reset);

        finalizar = new JButton("Finalizar");
        finalizar.setBounds(160, 100, 100, 30);
        finalizar.setEnabled(false);
        add(finalizar);

        info = new JLabel("Palabras agregadas: 0 / 10");
        info.setBounds(50, 150, 300, 30);
        add(info);

        agregar.addActionListener(e -> {
            String palabra = ingreso.getText().trim();
            if (!palabra.isEmpty()) {
                if (!palabras.contains(palabra)) {
                    palabras.add(palabra);
                    contador++;
                    info.setText("Palabras agregadas: " + contador + " / " + MAX_PALABRAS);
                    ingreso.setText("");
                    if (contador >= MAX_PALABRAS) {
                        agregar.setEnabled(false);
                        finalizar.setEnabled(true);
                        JOptionPane.showMessageDialog(this,
                                "Has alcanzado 10 palabras, puedes finalizar.");
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "La palabra ya existe");
                }
            }
        });

        reset.addActionListener(e -> {
            palabras.clear();
            contador = 0;
            info.setText("Palabras agregadas: 0 / 10");
            agregar.setEnabled(true);
            finalizar.setEnabled(false);
        });

        finalizar.addActionListener(e -> {
            if (contador < MAX_PALABRAS) {
                JOptionPane.showMessageDialog(this,
                        "Debes agregar exactamente 10 palabras antes de finalizar.");
                return;
            }
            primeraVez = true;
            JOptionPane.showMessageDialog(this, "Palabras guardadas correctamente.");
            new Main().setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    public static String getPalabra() {
        if (palabras.isEmpty()) {
            return "DEFAULT";
        }
        int random = (int) (Math.random() * palabras.size());
        return palabras.get(random);
    }

    public static ArrayList<String> getListaPalabras() {
        return palabras;
    }
    
    public static String palabraFija()
    {
    return palabraFija;
    }

    public static void main(String[] args) {
        new AdminPalabrasSecretas();
    }
}
