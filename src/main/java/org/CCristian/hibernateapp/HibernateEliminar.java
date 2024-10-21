package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id del Cliente a Eliminar");
        Long id = scanner.nextLong();

        EntityManager em = JpaUtil.getEntityManager();
        try {
            Cliente cliente = em.find(Cliente.class, id);   /*Cliente a Eliminar*/

            em.getTransaction().begin();

            em.remove(cliente); /*Eliminando el Cliente*/

            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }

    }
}
