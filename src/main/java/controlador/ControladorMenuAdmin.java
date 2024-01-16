package controlador;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.db.OperacionesBD_distribuidor;
import modelo.db.OperacionesBD_pedido;
import modelo.db.OperacionesBD_producto;
import modelo.db.OperacionesBD_usuario;
import modelo.entidad.Distribuidor;
import modelo.entidad.Pedido;
import modelo.entidad.Producto;
import modelo.entidad.Usuario;

public class ControladorMenuAdmin extends JFrame{
    
    public void mostrarPedidos(JPanel pedidosPanel, JTable pedidosTable, DefaultTableModel dtmPedido, JPanel menuAdmin){
        Pedido[] pedidos = OperacionesBD_pedido.getListaPedidos_BD();
        dtmPedido.setRowCount(0);
        for(int i = 0; i < pedidos.length; i++){
            dtmPedido.addRow(new Object[]{pedidos[i].getPedidoId(), pedidos[i].getUserId(), pedidos[i].getFecha(), (pedidos[i].getProductos() != null?pedidos[i].getProductos().getName(): ""), pedidos[i].getDistribuidor()});
        }
        pedidosPanel.setVisible(true);
        menuAdmin.setVisible(false);
    }

    public void mostrarProductos(JPanel productosPanel, JTable productosTable, DefaultTableModel dtmProducto, JPanel menuAdmin){
        Producto[] productos = OperacionesBD_producto.getListaProductos_BD();
        dtmProducto.setRowCount(0);
        for(int i = 0; i < productos.length; i++){
            dtmProducto.addRow(new Object[]{productos[i].getName(), productos[i].getTipo(), productos[i].getDistribId(), productos[i].getPrecio(), productos[i].getCantidad()});
        }
        productosPanel.setVisible(true);
        menuAdmin.setVisible(false);
    }

    public void mostrarUsuarios(JPanel usuariosPanel, JTable usuariosTable, DefaultTableModel dtmUsuario, JPanel menuAdmin){
        Usuario[] usuarios = OperacionesBD_usuario.getListaUsuarios_BD();
        dtmUsuario.setRowCount(0);
        for(int i = 0; i < usuarios.length; i++){
            dtmUsuario.addRow(new Object[]{usuarios[i].getNombre(), usuarios[i].getPrivileges()? "admin" : "usuario", usuarios[i].getSSId(), usuarios[i].getEmail(), usuarios[i].getTlfn() });
        }
        usuariosPanel.setVisible(true);
        menuAdmin.setVisible(false);
    }

    public void mostrarDistribuidores(JPanel distribuidoresPanel, JTable distribuidoresTable, DefaultTableModel dtmDistribuidor, JPanel menuAdmin){
        Distribuidor[] distribuidores = OperacionesBD_distribuidor.getListaDistribuidores_BD();
        dtmDistribuidor.setRowCount(0);
        for(int i = 0; i < distribuidores.length; i++){
            dtmDistribuidor.addRow(new Object[]{distribuidores[i].getId(), distribuidores[i].getNombre(), distribuidores[i].getMail(), distribuidores[i].getTlfn()});
        }
        distribuidoresPanel.setVisible(true);
        menuAdmin.setVisible(false);
    }
}
