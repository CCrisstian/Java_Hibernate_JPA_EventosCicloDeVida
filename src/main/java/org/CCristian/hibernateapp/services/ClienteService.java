package org.CCristian.hibernateapp.services;

import org.CCristian.hibernateapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    /*lógica de negocio a nivel de cliente*/
    List<Cliente> listar();
    Optional<Cliente> porId(Long id);
    void guardar(Cliente cliente);
    void eliminar(Long id);
}
