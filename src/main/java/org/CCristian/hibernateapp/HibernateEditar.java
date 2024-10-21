package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

import javax.swing.*;

public class HibernateEditar {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el Id del Cliente a Modificar"));
            Cliente cliente = em.find(Cliente.class, id);

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre: ", cliente.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido: ", cliente.getApellido());
            String pago = JOptionPane.showInputDialog("Ingrese la forma de pago: ", cliente.getFormaPago());

            em.getTransaction().begin();

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setFormaPago(pago);

            em.merge(cliente);  /*Actualiza los datos del objeto en el contexto de persistencia*/
            em.getTransaction().commit();   /*Realiza los cambios en la BD*/

            System.out.println(cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }
}
