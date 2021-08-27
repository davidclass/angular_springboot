package com.springboot.backend.apirest.model.dao;


import com.springboot.backend.apirest.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ICLienteDao extends CrudRepository<Cliente, Long> {

}
