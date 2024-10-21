package org.CCristian.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.CCristian.hibernateapp.entity.Cliente;
import org.CCristian.hibernateapp.util.JpaUtil;

import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder criteria = em.getCriteriaBuilder();

        System.out.println("\n============= Listar=============\n");
        CriteriaQuery <Cliente> query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);
        query.select(from);

        List<Cliente> clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE EQUALS =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");
        query.select(from)
                .where(criteria.equal(from.get("nombre"), nombreParam));
        clientes = em.createQuery(query)
                .setParameter("nombre","Andres").getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE LIKE =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from)
                .where(criteria.like(from.get("nombre"), "%and%"));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE LIKE 2 =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParamLike = criteria.parameter(String.class, "nombreParam");
        query.select(from)
                .where(criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreParamLike)));

        clientes = em.createQuery(query)
                .setParameter("nombreParam", "%and%").getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE BETWEEN =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from)
                .where(criteria.between(from.get("id"),2L, 6L));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE IN =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from)
                .where(from.get("nombre").in(Arrays.asList("Andres", "John", "Lou")));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= WHERE IN 2 =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<List> listParam = criteria.parameter(List.class, "nombres");

        query.select(from)
                .where(from.get("nombre").in(listParam));

        clientes = em.createQuery(query)
                    .setParameter("nombres", Arrays.asList("Andres", "John", "Lou"))
                    .getResultList();

        clientes.forEach(System.out::println);


        System.out.println("\n============= Filtrar usando predicados Mayor Igual que =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(criteria.ge(from.get("id"), 3L));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= Filtrar usando predicados Mayor que =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= Predicados Conjunción AND =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        Predicate porNombre = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago = criteria.equal(from.get("formaPago"),"debito");

        query.select(from).where(criteria.and(porNombre, porFormaPago));
        clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);


        System.out.println("\n============= Predicados Disyunción OR =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        Predicate porNombre2 = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago2 = criteria.equal(from.get("formaPago"),"debito");

        query.select(from).where(criteria.or(porNombre2, porFormaPago2));
        clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);


        System.out.println("\n============= Predicados Conjunción AND y Disyunción OR =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        Predicate porNombre3 = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago3 = criteria.equal(from.get("formaPago"),"debito");
        Predicate p3 = criteria.ge(from.get("id"), 4L);

        query.select(from).where(criteria.and(p3, criteria.or(porNombre3, porFormaPago3)));
        clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);


        System.out.println("\n============= Consultas con ORDER BY ASC | DESC =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).orderBy(criteria.asc(from.get("nombre")), criteria.desc(from.get("apellido")));

        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("\n============= Consultas por Id =============\n");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<Long> idParam = criteria.parameter(Long.class, "id");

        query.select(from).where(criteria.equal(from.get("id"), idParam));

        Cliente cliente = em.createQuery(query).setParameter("id",1L).getSingleResult();

        System.out.println(cliente);


        System.out.println("\n============= Consultas por Nombre de Cliente =============\n");
        CriteriaQuery<String> queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(from.get("nombre"));

        List<String> nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);


        System.out.println("\n============= Consultas DISTINCT por Nombre de Cliente =============\n");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(criteria.upper(from.get("nombre"))).distinct(true);

        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);


        System.out.println("\n============= Consultas por Nombre y Apellido de Cliente =============\n");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(criteria.upper(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido"))));

        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);


        System.out.println("\n============= Consultas por Campos Personalizados del Entity Cliente usando WHERE =============\n");
        CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);

        queryObject.multiselect(from.get("id"),from.get("nombre"), from.get("apellido")).where(criteria.like(from.get("nombre"), "%lu%"));

        List<Object[]> registros = em.createQuery(queryObject).getResultList();

        registros.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String) reg[1];
            String apellido = (String) reg[2];
            System.out.println("id = "+id+", nombre = "+nombre+", apellido = "+apellido);
        });


        System.out.println("\n============= Consultas por Campos Personalizados del Entity Cliente usando WHERE Id =============\n");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);

        queryObject.multiselect(from.get("id"),from.get("nombre"), from.get("apellido")).where(criteria.equal(from.get("id"), 2L));

        Object[] registro = em.createQuery(queryObject).getSingleResult();

        Long id = (Long) registro[0];
        String nombre = (String) registro[1];
        String apellido = (String) registro[2];
        System.out.println("id = "+id+", nombre = "+nombre+", apellido = "+apellido);


        System.out.println("\n============= Consultas usando COUNT() =============\n");

        CriteriaQuery<Long> queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteria.count(from.get("id")));

        Long count = em.createQuery(queryLong).getSingleResult();
        System.out.println("count = " + count);

        System.out.println("\n============= Sumar datos de un campo de la Tabla =============\n");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteria.sum(from.get("id")));

        Long sum = em.createQuery(queryLong).getSingleResult();
        System.out.println("sum = " + sum);

        System.out.println("\n============= Consulta Máximo valor de un campo de la Tabla =============\n");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteria.max(from.get("id")));

        Long max = em.createQuery(queryLong).getSingleResult();
        System.out.println("max = " + max);

        System.out.println("\n============= Consulta Mínimo valor de un campo de la Tabla =============\n");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteria.min(from.get("id")));

        Long min = em.createQuery(queryLong).getSingleResult();
        System.out.println("min = " + min);

        System.out.println("\n============= Consulta COUNT(), Mínimo valor, Máximo valor de una Tabla =============\n");

        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);

        queryObject.multiselect(
                criteria.count(from.get("id")),
                        criteria.sum(from.get("id")),
                                criteria.max(from.get("id")),
                                        criteria.min(from.get("id")));

        registro = em.createQuery(queryObject).getSingleResult();
        count = (Long) registro[0];
        sum = (Long) registro[1];
        max = (Long) registro[2];
        min = (Long) registro[3];
        System.out.println("count = "+count+", sum = "+sum+", max = "+max+", min = "+min);

        em.close();
    }
}
