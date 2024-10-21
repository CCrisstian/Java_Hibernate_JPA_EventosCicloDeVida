package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {

        /*Para poder trabajar con JPA Hibernate*/
        EntityManager em = JpaUtil.getEntityManager();

        String query;

        query = "SELECT c FROM Cliente c";
        List<Cliente> clientes = em.createQuery(query, Cliente.class).getResultList();
        clientes.forEach(System.out::println);


        em.close(); /*Cerrar conexión a la BD*/
    }
}
