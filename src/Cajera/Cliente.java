package Cajera;

public class Cliente {

    private String nombre;
    private int[] carroCompra;

    // Constructor, getter y setter
    public Cliente(String nombre, int[] compra) {
        this.nombre = nombre;
        this.carroCompra = compra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getCarroCompra() {
        return carroCompra;
    }

    public void setNombre(int[] carro) {
        this.carroCompra = carro;
    }
}
