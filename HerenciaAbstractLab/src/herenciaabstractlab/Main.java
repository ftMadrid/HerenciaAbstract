package herenciaabstractlab;

import herenciaabstractlab.AdminPalabrasSecretas;
import static herenciaabstractlab.AdminPalabrasSecretas.palabras;
import herenciaabstractlab.JuegoAhorcadoFijo;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    static boolean first = true;

    public Main() {
        initVentana();
        initComponentes();
        first();
    }

    private void initVentana() {
        setSize(800, 700);
        setTitle("AHORCADO LABORATORIO");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initComponentes() {

        titulo.setBounds(145, 60, 500, 147);
        titulo.setIcon(new ImageIcon(getClass().getResource("/herenciaabstractlab/imagenes/titulo.png")));

        jugar.setBounds(260, 270, 260, 70);
        jugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jugar.setFont(new Font("Kefa", Font.BOLD, 26));
        jugar.addActionListener(e -> jugarAction());

        admin.setBounds(260, 350, 260, 70);
        admin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        admin.setFont(new Font("Kefa", Font.BOLD, 22));
        admin.addActionListener(e -> adminAction());

        salir.setBounds(260, 430, 260, 70);
        salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salir.setFont(new Font("Kefa", Font.BOLD, 26));
        salir.addActionListener(e -> salirAction());

        add(titulo);
        add(jugar);
        add(admin);
        add(salir);
    }

    private void jugarAction() {

        int resultado = JOptionPane.showConfirmDialog(null, juegos, "MODO DE JUEGO",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {

            String seleccion = (String) juegos.getSelectedItem();

            switch (seleccion) {
                case "Al Azar":
                    new JuegoAhorcadoAzar();
                    break;

                case "Fijo":
                    palabr.removeAllItems();
                    for (String p : AdminPalabrasSecretas.getListaPalabras()) {
                        System.out.println("b");
                        palabr.addItem(p);
                    }

                    if (AdminPalabrasSecretas.primeraVez) {
                        int seleccionPalabra = JOptionPane.showConfirmDialog(null, palabr, "PALABRA", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        String palabraElegida;
                        if (seleccionPalabra == JOptionPane.OK_OPTION && palabr.getSelectedItem() != null) {
                            palabraElegida = (String) palabr.getSelectedItem();
                            AdminPalabrasSecretas.palabraFija = palabraElegida;
                            AdminPalabrasSecretas.primeraVez = false;
                            System.out.println(AdminPalabrasSecretas.palabraFija);
                        } else if (seleccionPalabra == JOptionPane.CANCEL_OPTION) {
                            return;
                        }

                    }

                    JuegoAhorcadoFijo juegito = new JuegoAhorcadoFijo();//Palabra fija agregar?
                    break;

            }

        }

        dispose();
    }

    private void adminAction() {
        AdminPalabrasSecretas ventanaAdmin = new AdminPalabrasSecretas();
        ventanaAdmin.setVisible(true);
        dispose();
        
        
    }

    private void salirAction() {
        JOptionPane.showMessageDialog(null, "Hasta luego inge :D!", "DESPEDIDA",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
        System.exit(0);
    }

    public void first() {
        if (first) {
            palabras.add("Comida");
            palabras.add("Beber");
            palabras.add("Hola");
            palabras.add("Adios");
            palabras.add("Programacion");
            palabras.add("Laboratorio");
            palabras.add("Geometria");
            palabras.add("Madrid");
            palabras.add("Barcelona");
            palabras.add("Energia");
            System.out.println("A");
            first = false;
        }
    }

    private final JLabel titulo = new JLabel();
    private final JButton jugar = new JButton("JUGAR");
    private final JButton admin = new JButton("PALABRAS SECRETAS");
    private final JButton salir = new JButton("SALIR");
    private final JComboBox<String> juegos = new JComboBox<>(new String[]{"Al Azar", "Fijo"});
    private final JComboBox<String> palabr = new JComboBox<>();

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
