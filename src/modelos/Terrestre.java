
package modelos;

public class Terrestre extends Envio {

    public Terrestre(String codigo, String cliente,
            double peso, double distancia) {

        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (getDistancia() * 1500) + (getPeso() * 2000);
    }

    @Override
    public String toString() {
        return "Terrestre - Codigo: " + getCodigo()
                + ", Cliente: " + getCliente();
    }
}
