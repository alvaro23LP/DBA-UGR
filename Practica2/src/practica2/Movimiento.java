
package practica2;

// Interfaz que define los métodos que deben implementar los movimientos
public interface Movimiento {
    
    public double calculaValorMovimiento();
    
    public int getFila();
    
    public int getColumna();

    double getUtility() ;
}
