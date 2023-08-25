package com.latam.alura.tienda.dao;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Producto;

import java.math.BigDecimal;
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
		String jpql = "select p.precio from Producto as p where p.nombre=:nombre";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}
}
