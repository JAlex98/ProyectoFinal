package interfazgrafica;

import com.mysql.cj.xdevapi.Table;
import entidadesdenegocio.Producto;
import logicadenegocio.ProductoBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControlProductos extends JFrame {
    private JPanel panel1;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtTipo;
    private JTextField txtPrecio;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JTable TableProductos;

    ArrayList<Producto> listProductos;
    Producto producto;
    ProductoBL productoBL = new ProductoBL();

    public static void main(String[] args) throws SQLException {
        new ControlProductos();
    }

    public ControlProductos() throws SQLException {
        inicializar();
        actualizarForm();

        // Funcionalidad del botón Nuevo
        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                txtNombre.setEnabled(true);
                txtTipo.setEnabled(true);
                txtPrecio.setEnabled(true);
                txtNombre.grabFocus();
                btnGuardar.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });

        // Funcionalidad del botón Guardar
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                producto = new Producto();
                producto.setNombre(txtNombre.getText());
                producto.setTipo(txtTipo.getText());
                producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                try {
                    productoBL.guardar(producto);
                    JOptionPane.showMessageDialog(null, "Se guardó correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Funcionalidad del botón Salir
        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        // Funcionalidad del botón Cancelar
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Funcionalidad del clic sobre la Tabla
        TableProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = TableProductos.getSelectedRow();
                txtId.setText(TableProductos.getValueAt(fila, 0).toString());
                txtNombre.setText(TableProductos.getValueAt(fila, 1).toString());
                txtTipo.setText(TableProductos.getValueAt(fila, 2).toString());
                txtPrecio.setText(TableProductos.getValueAt(fila, 3).toString());

                txtNombre.setEnabled(true);
                txtTipo.setEnabled(true);
                txtPrecio.setEnabled(true);
                txtNombre.grabFocus();

                btnNuevo.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });

        // Funcionalidad del botón Modificar
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                producto = new Producto();
                producto.setId(Integer.parseInt(txtId.getText()));
                producto.setNombre(txtNombre.getText());
                producto.setTipo(txtTipo.getText());
                producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                try {
                    productoBL.modificar(producto);
                    JOptionPane.showMessageDialog(null, "Se modificó con éxito");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Funcionalidad del botón Eliminar
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                producto = new Producto();
                producto.setId(Integer.parseInt(txtId.getText()));
                try {
                    productoBL.eliminar(producto);
                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    void inicializar() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 700);
        setTitle("Control de Productos");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(panel1);
        setVisible(true);
    }

    void llenarTabla(ArrayList<Producto> productos){
        Object[] obj = new Object[4];
        listProductos = new ArrayList<>();
        String[] encabezado = {"ID", "NOMBRE", "TIPO", "PRECIO"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        listProductos.addAll(productos);
        for(Producto item : listProductos){
            obj[0] = item.getId();
            obj[1] = item.getNombre();
            obj[2] = item.getTipo();
            obj[3] = item.getPrecio();

            tm.addRow(obj);
        }
        TableProductos.setModel(tm);
    }

    void actualizarForm() throws SQLException {
        txtId.setText("");
        txtNombre.setText("");
        txtTipo.setText("");
        txtPrecio.setText("");

        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        txtTipo.setEnabled(false);
        txtPrecio.setEnabled(false);

        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        llenarTabla(productoBL.obtenerTodos());
    }
}
