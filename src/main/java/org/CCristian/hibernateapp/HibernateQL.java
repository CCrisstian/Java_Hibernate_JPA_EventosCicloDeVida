package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import org.CCristian.hibernateapp.dominio.ClienteDto;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

import java.util.Arrays;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        System.out.println("\n============= Consultar todos =============\n");
        List<Cliente> clientes = em
                .createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n============= Consulta por ID =============\n");
        Cliente cliente = em
                .createQuery("SELECT c FROM Cliente c WHERE c.id =:id", Cliente.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(cliente);

        System.out.println("\n============= Consulta solo por el Nombre =============\n");
        String nombreCliente = em
                .createQuery("SELECT c.nombre FROM Cliente c WHERE c.id =:id", String.class)
                .setParameter("id", 2L)
                .getSingleResult();
        System.out.println(nombreCliente);

        System.out.println("\n============= Consulta solo Campos personalizados =============\n");
        Object[] objetoCliente = em
                .createQuery("SELECT c.id, c.nombre, c.apellido FROM Cliente c WHERE c.id =:id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();
        Long id = (Long) objetoCliente[0];
        String nombre = (String) objetoCliente[1];
        String apellido = (String) objetoCliente[2];
        System.out.println("id = " + id + ", nombre = " + nombre + ", apellido = " + apellido);

        System.out.println("\n============= Consulta Lista Campos personalizados =============\n");
        List<Object[]> registros = em
                .createQuery("SELECT c.id, c.nombre, c.apellido FROM Cliente c", Object[].class)
                .getResultList();

        registros.forEach(reg -> {
            Long idCli = (Long) reg[0];
            String nombreCli = (String) reg[1];
            String apellidoCli = (String) reg[2];
            System.out.println("id = " + idCli + ", nombre = " + nombreCli + ", apellido = " + apellidoCli);
        });


        System.out.println("\n============= Consulta por Cliente y Forma de Pago =============\n");
        registros = em
                .createQuery("SELECT c, c.formaPago FROM Cliente c", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            Cliente c = (Cliente) reg[0];
            String formaPAgo = (String) reg[1];
            System.out.println("FormaPago = " + formaPAgo + ", " + c);
        });


        System.out.println("\n============= Consulta que puebla y devuelve un objeto entity de una clase personalizada =============\n");
        clientes = em
                .createQuery("SELECT new Cliente(c.nombre, c.apellido) FROM Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= Consulta que puebla y devuelve un objeto otro de una clase personalizada =============\n");
        List<ClienteDto> clientesDto = em
                .createQuery("SELECT new org.CCristian.hibernateapp.dominio.ClienteDto(c.nombre, c.apellido) FROM Cliente c", ClienteDto.class)
                .getResultList();
        clientesDto.forEach(System.out::println);


        System.out.println("\n============= Consulta con nombres de Clientes =============\n");
        List<String> nombres = em
                .createQuery("SELECT c.nombre FROM Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);


        System.out.println("\n============= Consulta con nombres Unicos de Clientes =============\n");
        nombres = em
                .createQuery("SELECT DISTINCT (c.nombre) FROM Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("\n============= Consulta con Formas de Pago Únicas =============\n");
        Long TotalformasPago = em
                .createQuery("SELECT COUNT(DISTINCT (c.formaPago)) FROM Cliente c", Long.class)
                .getSingleResult();
        System.out.println("Total Formas de Pago = " + TotalformasPago);


        System.out.println("\n============= Consulta con Nombre y Apellido concatenados =============\n");
//        nombres = em
//                .createQuery("SELECT CONCAT(c.nombre, ' ', c.apellido) AS nombreCompleto FROM Cliente c", String.class)
//                .getResultList();

        nombres = em
                .createQuery("SELECT c.nombre || ' ' || c.apellido AS nombreCompleto FROM Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);


        System.out.println("\n============= Consulta con Nombre y Apellido concatenados en Mayúscula =============\n");
        nombres = em
                .createQuery("SELECT UPPER(CONCAT(c.nombre, ' ', c.apellido)) AS nombreCompleto FROM Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);


        System.out.println("\n============= Consulta con Nombre y Apellido concatenados en Minúscula =============\n");
        nombres = em
                .createQuery("SELECT LOWER(CONCAT(c.nombre, ' ', c.apellido)) AS nombreCompleto FROM Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);


        System.out.println("\n============= Consulta para Buscar por Nombre =============\n");
        String param = "and";
        clientes = em
                .createQuery("SELECT c FROM Cliente c WHERE UPPER(c.nombre) LIKE UPPER(:parametro)", Cliente.class)
                .setParameter("parametro", "%" + param + "%")
                .getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= Consultas por Rangos Numéricos =============\n");
        clientes = em
                .createQuery("SELECT c FROM Cliente c WHERE c.id BETWEEN 2 AND 5", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= Consultas por Rangos de Caracteres =============\n");
        clientes = em
                .createQuery("SELECT c FROM Cliente c WHERE c.nombre BETWEEN 'J' AND 'Q'", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= Consultas con Orden =============\n");
        clientes = em
                .createQuery("SELECT c FROM Cliente c ORDER BY c.nombre ASC, c.apellido ASC", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= Consultas con Total de Registros de la Tabla =============\n");
        Long total = em
                .createQuery("SELECT COUNT(c) AS Total FROM Cliente c", Long.class)
                .getSingleResult();
        System.out.println("Total de Clientes: " + total);

        System.out.println("\n============= Consultas con valor Mínimo del Id =============\n");
        Long minId = em
                .createQuery("SELECT MIN(c.id) AS Mínimo FROM Cliente c", Long.class)
                .getSingleResult();
        System.out.println("ID Mínimo: " + minId);


        System.out.println("\n============= Consultas con valor Máximo/Ultimo del Id =============\n");
        Long maxId = em
                .createQuery("SELECT MAX(c.id) AS Mínimo FROM Cliente c", Long.class)
                .getSingleResult();
        System.out.println("ID Máximo: " + maxId);


        System.out.println("\n============= Consultas con Nombre y su largo =============\n");
        registros = em
                .createQuery("SELECT c.nombre, LENGTH(c.nombre) FROM Cliente c", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre: " + nom + ", Largo: " + largo);
        });


        System.out.println("\n============= Consultas con el Nombre mas corto =============\n");
        Integer minLargoNombre = em
                .createQuery("SELECT MIN(LENGTH(c.nombre)) FROM Cliente c", Integer.class)
                .getSingleResult();
        System.out.println("Nombre mas corto: " + minLargoNombre);


        System.out.println("\n============= Consultas con el Nombre mas lago =============\n");
        Integer maxLargoNombre = em
                .createQuery("SELECT MAX(LENGTH(c.nombre)) FROM Cliente c", Integer.class)
                .getSingleResult();
        System.out.println("Nombre mas largo: " + maxLargoNombre);


        System.out.println("\n============= Consultas resumen funciones agregaciones COUNT, MIN, MAX, AVG, SUM =============\n");
        Object[] estadisticas = em
                .createQuery("SELECT MIN(c.id), MAX(c.id), SUM(c.id), COUNT(c.id), AVG(LENGTH(c.nombre)) FROM Cliente c", Object[].class)
                .getSingleResult();
        Long min = (Long) estadisticas[0];
        Long max = (Long) estadisticas[1];
        Long sum = (Long) estadisticas[2];
        Long count = (Long) estadisticas[3];
        Double avg = (Double) estadisticas[4];
        System.out.println("Min: " + min + ", Max: " + max + " Sum: " + sum + ", Count: " + count + ", Avg: " + avg);


        System.out.println("\n============= Consultas con el nombre mas corto y su largo =============\n");
        registros = em
                .createQuery("SELECT c.nombre, length(c.nombre) FROM Cliente c WHERE length(c.nombre) "
                        + "= (SELECT MIN(length(c.nombre)) FROM Cliente c)", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre: " + nom + ", Largo: " + largo);
        });


        System.out.println("\n============= Consultas con el nombre mas largo y su largo =============\n");
        registros = em
                .createQuery("SELECT c.nombre, length(c.nombre) FROM Cliente c WHERE length(c.nombre) "
                        + "= (SELECT MAX(length(c.nombre)) FROM Cliente c)", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre: " + nom + ", Largo: " + largo);
        });


        System.out.println("\n============= Consultas para obtener el Ultimo Registro =============\n");
        Cliente ultimoCliente = em
                .createQuery("SELECT c FROM Cliente c WHERE c.id = "
                        + "(SELECT MAX(c.id) FROM Cliente c)", Cliente.class)
                .getSingleResult();
        System.out.println(ultimoCliente);


        System.out.println("\n============= Consultas WHERE IN =============\n");
        clientes = em
                .createQuery("SELECT c FROM Cliente c WHERE c.id IN :ids", Cliente.class)
                .setParameter("ids", Arrays.asList(1L, 2L, 10L, 6L))
                .getResultList();

        clientes.forEach(System.out::println);

        em.close();
    }
}
