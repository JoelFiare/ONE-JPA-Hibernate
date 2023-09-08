package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public Pedido consultaPorId(Long id) {
		return em.find(Pedido.class, id);
	}

	public List<Pedido> consultarTodos(){
		String jpql = "select p from Pedido as p";
		return em.createQuery(jpql,Pedido.class).getResultList();
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "select sum(p.valorTotal) from Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}

	public Double valorTPromedioVendido() {
		String jpql = "select avg(p.valorTotal) from Pedido p";
		return em.createQuery(jpql,Double.class).getSingleResult();
	}
}
