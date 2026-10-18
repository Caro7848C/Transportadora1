
package controladores;

import modelos.DatosEnvio;
import modelos.Envio;
import modelos.TipoEnvio;
import servicios.EnvioServicio;
import vistas.EnvioVista;
import javax.swing.JOptionPane;

public class TransportadoraControlador {

    private EnvioVista vista;

    public TransportadoraControlador(EnvioVista vista) {
        this.vista = vista;

        this.vista.setGuardarEnvioClick(
                evento -> guardarEnvio());

        this.vista.setEliminarEnvioClick(
                evento -> eliminarEnvio());

        mostrarEnvios();
    }

    private void mostrarEnvios() {
        vista.mostrarEnvios(
                EnvioServicio.getDatos(),
                EnvioServicio.getEncabezados());
    }

    private void guardarEnvio() {
        try {
            TipoEnvio tipo = TipoEnvio.valueOf(
                    vista.getTipoEnvio()
                            .trim()
                            .toUpperCase()
                            .replace("Á", "A")
                            .replace("É", "E")
                            .replace("Í", "I")
                            .replace("Ó", "O")
                            .replace("Ú", "U"));

            String codigo = vista.getNumero();
            String cliente = vista.getCliente();
            double peso = vista.getPeso();
            double distancia = vista.getDistancia();

            if (codigo.isEmpty() || cliente.isEmpty()) {
                vista.mostrarMensaje(
                        "Debe ingresar el número y el cliente.");
                return;
            }

            if (peso <= 0 || distancia <= 0) {
                vista.mostrarMensaje(
                        "El peso y la distancia deben ser mayores que cero.");
                return;
            }

            DatosEnvio datos = new DatosEnvio(
                    codigo,
                    cliente,
                    peso,
                    distancia);

            Envio envioAgregado = EnvioServicio.agregar(tipo, datos);

            if (envioAgregado != null) {
                mostrarEnvios();
                vista.limpiarFormulario();

                vista.mostrarMensaje(
                        "Envío registrado correctamente.\nTarifa: "
                                + envioAgregado.calcularTarifa());
            }

        } catch (NumberFormatException e) {
            vista.mostrarMensaje(
                    "El peso y la distancia deben ser números válidos.");
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje(
                    "Seleccione un tipo de envío válido e ingrese datos correctos.");
        }
    }

    private void eliminarEnvio() {
        int fila = vista.getFilaSeleccionada();

        if (fila < 0) {
            vista.mostrarMensaje(
                    "Debe seleccionar un envío de la tabla.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                vista,
                "¿Está seguro de que desea eliminar este envío?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            if (EnvioServicio.eliminar(fila)) {
                mostrarEnvios();
                vista.mostrarMensaje(
                        "Envío eliminado correctamente.");
            }
        }
    }
}
