import accesoadatos.ProductoDAL;
import entidadesdenegocio.Producto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ProductoDALTest {

    @Test
    public void guardarTest() throws SQLException {
        Producto producto = new Producto();
        producto.setNombre("Laptop");
        producto.setTipo("Electrónica");
        producto.setPrecio(1200.50);

        int esperado = 1;
        int actual = ProductoDAL.guardar(producto);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerTodosTest() throws SQLException {
        int esperado = 2;
        int actual = ProductoDAL.obtenerTodos().size();
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void modificarTest() throws SQLException {
        Producto producto = new Producto();
        producto.setId(2);
        producto.setNombre("Laptop");
        producto.setTipo("Electrónica Actualizada");
        producto.setPrecio(1150.75);

        int esperado = 1;
        int actual = ProductoDAL.modificar(producto);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void eliminarTest() throws SQLException {
        Producto producto = new Producto();
        producto.setId(1);

        int esperado = 1;
        int actual = ProductoDAL.eliminar(producto);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerDatosFiltradosTest() throws SQLException {
        Producto producto = new Producto();
        producto.setNombre("Laptop");
        producto.setTipo("");
        producto.setPrecio(0.0);

        int actual = ProductoDAL.obtenerDatosFiltrados(producto).size();
        Assertions.assertNotEquals(0, actual);
    }
}
