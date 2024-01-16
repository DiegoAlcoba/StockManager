package controlador;

import java.awt.Dimension;
import java.math.BigDecimal;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import modelo.db.OperacionesBD_pedido;
import modelo.db.OperacionesBD_producto;
import modelo.entidad.Pedido;
import modelo.entidad.Producto;
import modelo.entidad.Usuario;

public class ControladorPedidos extends JFrame{
    
    public void hacerPedido(Usuario logger, javax.swing.table.DefaultTableModel dtmPedido){
               Producto[] productos = OperacionesBD_producto.getListaProductos_BD();
        
        // Crear un JComboBox con los nombres de los productos existentes
        JComboBox<String> productoComboBox = new JComboBox<>();
        for (Producto producto : productos) {
            productoComboBox.addItem(producto.getName());
        }
        
        JPanel ingresaPedido = new JPanel();
        ingresaPedido.setLayout(new BoxLayout(ingresaPedido, BoxLayout.Y_AXIS)); // Establecer el layout a BoxLayout
        ingresaPedido.setPreferredSize(new Dimension(300, 250)); // Establecer el tamaño preferido del panel
        ingresaPedido.add(new JLabel("Producto"));
        ingresaPedido.add(productoComboBox);
        JSpinner cantidad = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        ingresaPedido.add(new JLabel("Cantidad"));
        ingresaPedido.add(cantidad);
        
        int result = JOptionPane.showConfirmDialog(null, ingresaPedido, "Realizar pedido", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String selectedProduct = (String) productoComboBox.getSelectedItem();
            // Aquí puedes agregar la lógica para realizar el pedido con el producto seleccionado
            Pedido pedido = new Pedido(logger.getSSId(), OperacionesBD_producto.getProducto_BD(selectedProduct));
            BigDecimal cantidadValue = new BigDecimal(cantidad.getValue().toString());
            pedido.setDistribuidor(OperacionesBD_producto.getProducto_BD(selectedProduct).getDistribId());
            Pedido[] pedidos = OperacionesBD_pedido.getListaPedidos_BD();
           if(OperacionesBD_pedido.addPedido_BD(pedido)){
                dtmPedido.addRow(new Object[]{pedidos[pedidos.length-1].getPedidoId(), pedidos[pedidos.length-1].getUserId(), pedidos[pedidos.length-1].getFecha(), selectedProduct,pedidos[pedidos.length-1].getDistribuidor()});
              }
              else{
                JOptionPane.showMessageDialog(null, "El pedido no se ha podido añadir", "Error de acceso", JOptionPane.ERROR_MESSAGE);
              
           }
        } 
    }
}
