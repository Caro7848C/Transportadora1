
package fabricas;

import modelos.Envio;
import modelos.DatosEnvio;
import modelos.Aereo;

public class AereoFactory extends EnvioFactory {

    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Aereo(
            datos.codigo(),
            datos.cliente(),
            datos.peso(),
            datos.distancia()
        );
    }
}
