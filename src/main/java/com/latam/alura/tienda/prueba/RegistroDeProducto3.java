package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDeProducto3 {

    public static void main(String[] args) {
        registrarProducto();
        EntityManager em = JPAUtil.getEntityManager();
        ProductoDao produtoDao = new ProductoDao(em);
        Producto producto = produtoDao.consultaPorId(1L);
        System.out.println(producto.getNombre());

        List<Producto> productos = produtoDao.consultarTodos();
        productos.forEach(prod -> System.out.println(prod.getDescripcion()));

        List<Producto> productos2 = produtoDao.consultaPorNombre("Xiaomi Redmi");
        productos2.forEach(prod -> System.out.println(prod.getDescripcion()));

        List<Producto> productos3 = produtoDao.consultaPorNombreDeCategoria("CELULARES");
        productos3.forEach(prod -> System.out.println(prod.getDescripcion()));

        BigDecimal productos4 = produtoDao.consultarPrecioPorNombreDeProducto("Xiaomi Redmi");
        System.out.println(productos4);
    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");

        Producto celular = new Producto("Xiaomi Redmi", "Muy bueno", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProductoDao produtoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.guardar(celulares);
        produtoDao.guardar(celular);

        em.getTransaction().commit();
        em.close();
    }

}
