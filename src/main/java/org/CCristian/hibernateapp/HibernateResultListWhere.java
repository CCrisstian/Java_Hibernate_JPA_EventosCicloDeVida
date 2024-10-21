package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class HibernateResultListWhere {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.formaPago=?1", Cliente.class);

        System.out.println("\nIngrese una forma de pago");
        String pago = scanner.next();
        query.setParameter(1, pago);

        List<Cliente> clientes = query.getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}
