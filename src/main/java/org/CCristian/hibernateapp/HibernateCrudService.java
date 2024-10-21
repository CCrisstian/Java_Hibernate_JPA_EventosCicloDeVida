package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.services.ClienteService;
import org.CCristian.hibernateapp.services.ClienteServiceImpl;
import org.CCristian.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        ClienteService service = new ClienteServiceImpl(em);

        System.out.println("==============Listar=================");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);

        System.out.println("\n==============Obtener por Id=================");
        Optional<Cliente> optionalCliente = service.porId(1L);
        optionalCliente.ifPresent(System.out::println);

        System.out.println("\n==============Insertar nuevo Cliente=================");
        Cliente cliente = new Cliente();
        cliente.setNombre("Luci");
        cliente.setApellido("Mena");
        cliente.setFormaPago("paypal");
        service.guardar(cliente);
        System.out.println("Cliente guardado con exito");
        service.listar().forEach(System.out::println);

        System.out.println("\n==============Editar Cliente=================");
        Long id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            c.setFormaPago("mercado pago");
            service.guardar(c);
            System.out.println("Cliente editado con exito");
            service.listar().forEach(System.out::println);
        });

        System.out.println("\n==============Eliminar Cliente=================");
        id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            service.eliminar(c.getId());
            System.out.println("Cliente eliminado con exito");
            service.listar().forEach(System.out::println);
        });

        em.close();
    }
}
