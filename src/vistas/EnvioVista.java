package vistas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EnvioVista extends JFrame {

    // Campos del formulario
    private JTextField txtNumero, txtCliente, txtPeso, txtDistancia;

    // Tipo de envío
    private JComboBox<String> cmbTipoEnvio;

    // Botones
    private JButton btnGuardar, btnEliminar;

    // Tabla
    private JTable tblEnvios;

    public EnvioVista() {

        setTitle("Operador logístico");
        setSize(700, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- BARRA DE HERRAMIENTAS CON ICONOS ---
        JToolBar tbEnvio = new JToolBar();
        tbEnvio.setFloatable(false);

        JButton btnTransporteAereo = new JButton();
        btnTransporteAereo.setIcon(new ImageIcon(getClass().getResource("/iconos/TransporteAereo.png")));
        btnTransporteAereo.setToolTipText("Transporte Aereo");
        btnTransporteAereo.addActionListener(evt -> {
            btnTransporteAereoClick();
        });
        tbEnvio.add(btnTransporteAereo);

        JButton btnTransporteMaritimo = new JButton();
        btnTransporteMaritimo.setIcon(new ImageIcon(getClass().getResource("/iconos/TransporteMaritimo.png")));
        btnTransporteMaritimo.setToolTipText("Transporte Maritimo");
        btnTransporteMaritimo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTransporteMaritimoClick();
            }
        });
        tbEnvio.add(btnTransporteMaritimo);

        JButton btnTransporteTerrestre = new JButton();
        btnTransporteTerrestre.setIcon(new ImageIcon(getClass().getResource("/iconos/TransporteTerrestre.png")));
        btnTransporteTerrestre.setToolTipText("Transporte Terrestre");
        btnTransporteTerrestre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTransporteTerrestreClick();
            }
        });
        tbEnvio.add(btnTransporteTerrestre);

        JPanel pnlFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Número y Tipo de envío
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlFormulario.add(new JLabel("Número"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        txtNumero = new JTextField(12);
        pnlFormulario.add(txtNumero, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        pnlFormulario.add(new JLabel("Tipo"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        cmbTipoEnvio = new JComboBox<>(new String[] { "Aéreo", "Marítimo", "Terrestre" });
        pnlFormulario.add(cmbTipoEnvio, gbc);

        // Fila 1: Cliente y Distancia (km)
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlFormulario.add(new JLabel("Cliente"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtCliente = new JTextField(12);
        pnlFormulario.add(txtCliente, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        pnlFormulario.add(new JLabel("Distancia en Km"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        txtDistancia = new JTextField(12);
        pnlFormulario.add(txtDistancia, gbc);

        // Fila 2: Peso y Botones de Guardar / Cancelar en la misma línea compacta
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlFormulario.add(new JLabel("Peso"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtPeso = new JTextField(12);
        pnlFormulario.add(txtPeso, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JPanel pnlBotonesAccion = new JPanel(new java.awt.GridLayout(1, 2, 5, 0));

        btnGuardar = new JButton("Guardar envio");
        btnEliminar = new JButton("Eliminar envio"); // Funciona como cancelar/eliminar según tu lógica

        pnlBotonesAccion.add(btnGuardar);
        pnlBotonesAccion.add(btnEliminar);
        pnlFormulario.add(pnlBotonesAccion, gbc);

        JPanel pnlSuperior = new JPanel(new BorderLayout());
        pnlSuperior.add(tbEnvio, BorderLayout.NORTH);
        pnlSuperior.add(pnlFormulario, BorderLayout.CENTER);

        tblEnvios = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tblEnvios);

        // Ventana principal
        add(pnlSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
    }

    private void btnTransporteAereoClick() {
        cmbTipoEnvio.setSelectedItem("Aéreo");
    }

    private void btnTransporteMaritimoClick() {
        cmbTipoEnvio.setSelectedItem("Marítimo");
    }

    private void btnTransporteTerrestreClick() {
        cmbTipoEnvio.setSelectedItem("Terrestre");
    }


    // Getters
    public String getNumero() {
        return txtNumero.getText().trim();
    }

    public String getCliente() {
        return txtCliente.getText().trim();
    }

    public double getPeso() {
        return Double.parseDouble(txtPeso.getText().trim());
    }

    public double getDistancia() {
        return Double.parseDouble(txtDistancia.getText().trim());
    }

    public String getTipoEnvio() {
        return (String) cmbTipoEnvio.getSelectedItem();
    }

    public int getFilaSeleccionada() {
        return tblEnvios.getSelectedRow();
    }

    // Eventos
    public void setGuardarEnvioClick(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void setEliminarEnvioClick(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }

    // TABLA: muestra la información recibida del servicio
    public void mostrarEnvios(String[][] datos, String[] encabezados) {
        DefaultTableModel modelo = new DefaultTableModel(datos, encabezados) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tblEnvios.setModel(modelo);
    }

    // Mensajes
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarFormulario() {
        txtNumero.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipoEnvio.setSelectedIndex(0);
    }
}