package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

import javax.swing.*;

public class HibernateCrear {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            /*Crear un nuevo Cliente*/
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido");
            String formaPago = JOptionPane.showInputDialog("Ingrese una forma de pago");

            em.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setFormaPago(formaPago);

            em.persist(cliente);

            em.getTransaction().commit();

            /*Mostrar el objeto ingresado para verificar la inserción en la tabla 'Clientes'*/
            System.out.println("El Id del cliente registrado es: " + cliente.getId());
            cliente = em.find(Cliente.class, cliente.getId());
            System.out.println(cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
