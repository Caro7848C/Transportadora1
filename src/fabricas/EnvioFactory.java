
package fabricas;

import modelos.Envio;
import modelos.DatosEnvio;
import modelos.TipoEnvio;

public abstract class EnvioFactory {

    public abstract Envio crearEnvio(DatosEnvio datos);

    public static EnvioFactory getFactory(TipoEnvio tipo) {
        return switch (tipo) {
            case AEREO -> new AereoFactory();
            case MARITIMO -> new MaritimoFactory();
            case TERRESTRE -> new TerrestreFactory();
        };
    }
}
