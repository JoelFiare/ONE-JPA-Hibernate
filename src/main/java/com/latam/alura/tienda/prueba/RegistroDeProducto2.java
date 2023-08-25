package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class RegistroDeProducto2 {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES"); // Instanciamos estado TRANSIENTE

	    EntityManager em = JPAUtil.getEntityManager();

	    em.getTransaction().begin();

		em.persist(celulares);  // realiza el INSERT con los parametros que creamos la instancia //estado MANAGED

		celulares.setNombre("LIBROS"); // realiza un UPDATE
	    
	    //em.getTransaction().commit(); // COMMIT (valores definitivos)envia a la base de datos
	    em.flush(); // FLUSH (se puede hacer rollback)
		//em.close(); // CLOSE (cierra) estado DETACHED no se guardan las nuevas alteraciones
		em.clear(); // CLEAR (envia entidades a detached)

		//DETACHED -> UPDATE
		celulares = em.merge(celulares); //con merge trajimos el valor para actualizar
		celulares.setNombre("SOFTWARE"); // le asignamos un nuevo valor

		em.flush(); //lo enviamos
		em.clear();

		celulares = em.merge(celulares); //traigo los valores
		//REMOVED -> DELETE
		em.remove(celulares); //removio la entidad.

		em.flush();



	}

}
