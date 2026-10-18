import vistas.EnvioVista;
import controladores.TransportadoraControlador;

public class App {
    public static void main(String[] args) throws Exception {
        var vista = new EnvioVista();

        new TransportadoraControlador(vista);

        vista.setVisible(true);
    }
}
