
package modelos;

public class Aereo extends Envio {

    public Aereo(String codigo, String cliente,
            double peso, double distancia) {

        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (getDistancia() * 5000) + (getPeso() * 4000);
    }

    @Override
    public String toString() {
        return "Aéreo - Código: " + getCodigo()
                + ", Cliente: " + getCliente();
    }
}
