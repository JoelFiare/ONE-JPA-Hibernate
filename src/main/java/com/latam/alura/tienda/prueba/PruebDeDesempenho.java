package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class PruebDeDesempenho {
    public static void main(String[] args) throws FileNotFoundException {
        LoadRecords.cargarRegistros();
        EntityManager em = JPAUtils.getEntityManager();

        //Pedido pedido = em.find(Pedido.class, 3l);
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedidoConCliente = pedidoDao.consultarPedidoConCliente(2l);

        em.close();

        //System.out.println(pedido);
        //System.out.println(pedido.getItems().size());
        System.out.println(pedidoConCliente.getCliente().getNombre());

    }
}
