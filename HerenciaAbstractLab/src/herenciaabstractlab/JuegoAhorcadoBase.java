package herenciaabstractlab;

import java.util.ArrayList;

public abstract class JuegoAhorcadoBase implements JuegoAhorcado{
    
    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected int limiteIntentos = 6;
    protected ArrayList<Character> letraUsadas = new ArrayList<>();
    protected ArrayList<String> figuraAhorcado = new ArrayList<>();
    
    public abstract void actualizarPalabraActual(char letra);
    
    public abstract boolean verificarletra(char letra);
    
    public abstract boolean hasGanado();
    
    
    
}
