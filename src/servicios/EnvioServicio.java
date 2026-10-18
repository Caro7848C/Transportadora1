
package servicios;

import java.util.ArrayList;
import java.util.List;

import fabricas.EnvioFactory;
import modelos.DatosEnvio;
import modelos.Envio;
import modelos.TipoEnvio;

public class EnvioServicio {

    private static final String[] encabezados = {
        "Tipo", "Código", "Cliente",
        "Peso", "Distancia", "Tarifa"
    };

    private static final List<Envio> envios = new ArrayList<>();

    public static String[] getEncabezados() {
        return encabezados;
    }

    public static Envio get(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            return envios.get(posicion);
        }
        return null;
    }

    public static Envio agregar(TipoEnvio tipo, DatosEnvio datos) {
        EnvioFactory factory = EnvioFactory.getFactory(tipo);

        Envio envio = factory.crearEnvio(datos);

        envios.add(envio);

        return envio;
    }

    public static String[][] getDatos() {
        String[][] datos =
            new String[envios.size()][encabezados.length];

        for (int fila = 0; fila < envios.size(); fila++) {
            Envio envio = envios.get(fila);

            datos[fila][0] =
                envio.getClass().getSimpleName();
            datos[fila][1] = envio.getCodigo();
            datos[fila][2] = envio.getCliente();
            datos[fila][3] = String.valueOf(envio.getPeso());
            datos[fila][4] = String.valueOf(envio.getDistancia());
            datos[fila][5] =
                String.valueOf(envio.calcularTarifa());
        }

        return datos;
    }

    public static boolean eliminar(int posicion) {
        if (posicion >= 0 && posicion < envios.size()) {
            envios.remove(posicion);
            return true;
        }

        return false;
    }
}
