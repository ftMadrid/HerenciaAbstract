package herenciaabstractlab;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    
    public Main(){
        initVentana();
        initComponentes();
    }
    
    private void initVentana(){
        
        setSize(800, 700);
        setTitle("AHORCADO LABORATORIO");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
    }
    
    private void initComponentes(){
        
        titulo.setBounds(145, 60, 500, 147); //X , Y , WIDTH , HEIGHT
        titulo.setIcon(new ImageIcon(getClass().getResource("/herenciaabstractlab/imagenes/titulo.png")));
        
        jugar.setBounds(260, 270, 260, 70); //X , Y , WIDTH , HEIGHT
        jugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jugar.setFont(new Font("Kefa", Font.BOLD, 26));
        jugar.addActionListener(e -> jugarAction());
        
        admin.setBounds(260, 350, 260, 70); //X , Y , WIDTH , HEIGHT
        admin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        admin.setFont(new Font("Kefa", Font.BOLD, 22));
        admin.addActionListener(e -> adminAction());
        
        salir.setBounds(260, 430, 260, 70); //X , Y , WIDTH , HEIGHT
        salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salir.setFont(new Font("Kefa", Font.BOLD, 26));
        salir.addActionListener(e -> salirAction());
        
        add(titulo);
        add(jugar);
        add(admin);
        add(salir);
        
    }
    
    private void jugarAction(){
        
        int resultado = JOptionPane.showConfirmDialog(null, juegos, "MODO DE JUEGO", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            
            String seleccion = (String) juegos.getSelectedItem();
            JOptionPane.showMessageDialog(null, "Modo de Juego: " + seleccion);
            
            switch(seleccion){
                case "Al Azar":
                    break;
                case "Fijo":
                    new JuegoAhorcadoFijo();
                    break;
            }
            
            dispose();
            
        }
        
    }
    
    private void adminAction(){
        dispose();
        new AdminPalabrasSecretas().setVisible(true);
    }
    
    private void salirAction(){
        JOptionPane.showMessageDialog(null, "Hasta luego inge :D!", "DESPEDIDA", JOptionPane.INFORMATION_MESSAGE );
        dispose();
        System.exit(0);
    }
    
    private final JLabel titulo = new JLabel();
    private final JButton jugar = new JButton("JUGAR");
    private final JButton admin = new JButton("PALABRAS SECRETAS");
    private final JButton salir = new JButton("SALIR");
    private final JComboBox<String> juegos = new JComboBox<>(new String[] {"Al Azar", "Fijo"});

    public static void main(String[] args) {
        
        new Main().setVisible(true);
        
    }
    
}
