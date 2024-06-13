package accesoadatos;

import entidadesdenegocio.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAL {

    // Método que muestra todos los registros de la tabla
    public static ArrayList<Producto> obtenerTodos() throws SQLException {
        ArrayList<Producto> lista = new ArrayList<>();
        Producto producto;
        try {
            String sql = "SELECT id, nombre, tipo, precio FROM producto";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()) {
                producto = new Producto(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4));
                lista.add(producto);
            }
            conexion.close();
            ps.close();
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // Método que permite guardar un nuevo registro
    public static int guardar(Producto producto) throws SQLException {
        int result = 0;
        try {
            String sql = "INSERT INTO producto(nombre, tipo, precio) VALUES(?, ?, ?)";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setDouble(3, producto.getPrecio());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // Método que permite modificar un registro existente
    public static int modificar(Producto producto) throws SQLException {
        int result = 0;
        try {
            String sql = "UPDATE producto SET nombre = ?, tipo = ?, precio = ? WHERE id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // Método que permite eliminar un registro existente
    public static int eliminar(Producto producto) throws SQLException {
        int result = 0;
        try {
            String sql = "DELETE FROM producto WHERE id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setInt(1, producto.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // Método para consultar mediante criterios
    public static ArrayList<Producto> obtenerDatosFiltrados(Producto producto) throws SQLException {
        ArrayList<Producto> lista = new ArrayList<>();
        Producto prod;
        try {
            String sql = "SELECT id, nombre, tipo, precio FROM producto WHERE id = ? or nombre like '%" + producto.getNombre() + "%' or tipo like '%" + producto.getTipo() + "%'";
            Connection connection = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(connection, sql);
            ps.setInt(1, producto.getId());
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()) {
                prod = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                lista.add(prod);
            }
            connection.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
