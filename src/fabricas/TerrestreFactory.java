
package fabricas;

import modelos.Envio;
import modelos.DatosEnvio;
import modelos.Terrestre;

public class TerrestreFactory extends EnvioFactory {

    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Terrestre(
            datos.codigo(),
            datos.cliente(),
            datos.peso(),
            datos.distancia()
        );
    }
}
