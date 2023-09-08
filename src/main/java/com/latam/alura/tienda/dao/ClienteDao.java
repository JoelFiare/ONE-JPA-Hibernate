package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Cliente;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ClienteDao {

	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public Cliente consultaPorId(Long id) {
		return em.find(Cliente.class, id);
	}

	public List<Cliente> consultarTodos(){
		String jpql = "select p from Cliente as p";
		return em.createQuery(jpql,Cliente.class).getResultList();
	}

	public List<Cliente> consultaPorNombre(String nombre){
		String jpql = "select p from Cliente as p where p.nombre=:nombre";
		return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
	}

	public List<Cliente> consultaPorNombreDeCategoria(String nombre){
		String jpql = "select p from Cliente as p where p.categoria.nombre=:nombre";
		return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
	}

	public BigDecimal consultarPrecioPorNombreDeCliente (String nombre) {
		String jpql = "select p.precio from Cliente as p where p.nombre=:nombre";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}
}
