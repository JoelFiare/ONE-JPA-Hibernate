package com.latam.alura.tienda.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.latam.alura.tienda.modelo.Producto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductoDao {
	
	private EntityManager em;
	
	public ProductoDao(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Producto producto) {
		this.em.persist(producto);
	}

	public Producto consultaPorId(Long id) {
		return em.find(Producto.class, id);
	}

	public List<Producto> consultarTodos(){
		String jpql = "select p from Producto as p";
		return em.createQuery(jpql,Producto.class).getResultList();
	}

	public List<Producto> consultaPorNombre(String nombre){
		String jpql = "select p from Producto as p where p.nombre=:nombre";
		return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
	}

	public List<Producto> consultaPorNombreDeCategoria(String nombre){
		String jpql = "select p from Producto as p where p.categoria.nombre=:nombre";
		return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
	}

	public BigDecimal consultarPrecioPorNombreDeProducto (String nombre) {
		return em.createNamedQuery("Producto.consultaDePrecio",BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}

	public List<Producto> consultarPorParametros(String nombre, BigDecimal precio, LocalDate fecha) {
		StringBuilder jpql = new StringBuilder("select p from Producto p where 1=1 ");
		if(nombre != null && !nombre.trim().isEmpty()) {
			jpql.append("and p.nombre=:nombre ");
		}
		if(precio != null && !precio.equals(new BigDecimal(0))) {
			jpql.append("and p.precio=:precio ");
		}
		if(fecha != null) {
			jpql.append("and p.fechaDeRegistro=:fecha ");
		}

		TypedQuery<Producto> query = em.createQuery(jpql.toString(), Producto.class);

		if(nombre != null && !nombre.trim().isEmpty()) {
			query.setParameter("nombre", nombre);
		}
		if(precio != null && !precio.equals(new BigDecimal(0))) {
			query.setParameter("precio", precio);
		}
		if(fecha != null) {
			query.setParameter("fechaDeRegistro", fecha);
		}
		return query.getResultList();
	}
}
