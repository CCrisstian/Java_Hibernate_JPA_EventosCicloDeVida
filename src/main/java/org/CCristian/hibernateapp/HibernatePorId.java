package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el id: ");
        Long id = scanner.nextLong();

        EntityManager em = JpaUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);   /*'find' usa la PRIMARY KEY*/

        System.out.println(cliente);

        Cliente cliente2 = em.find(Cliente.class, id);  /*El objeto obtenido previamente queda guardado en la memoria de la session*/
        System.out.println(cliente2);
        em.close();
    }
}
