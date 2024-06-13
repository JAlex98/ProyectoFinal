package logicadenegocio;

import accesoadatos.ProductoDAL;
import entidadesdenegocio.Producto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoBL {
    // Métodos de la clase BL que devuelven la funcionalidad de los métodos de la clase DAL
    public int guardar(Producto producto) throws SQLException {
        return ProductoDAL.guardar(producto);
    }

    public int modificar(Producto producto) throws SQLException {
        return ProductoDAL.modificar(producto);
    }

    public int eliminar(Producto producto) throws SQLException {
        return ProductoDAL.eliminar(producto);
    }

    public ArrayList<Producto> obtenerTodos() throws SQLException {
        return ProductoDAL.obtenerTodos();
    }

    public ArrayList<Producto> obtenerDatosFiltrados(Producto producto) throws SQLException {
        return ProductoDAL.obtenerDatosFiltrados(producto);
    }
}
