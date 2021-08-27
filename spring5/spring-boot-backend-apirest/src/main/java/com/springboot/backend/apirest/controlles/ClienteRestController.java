package com.springboot.backend.apirest.controlles;

import com.springboot.backend.apirest.model.entity.Cliente;
import com.springboot.backend.apirest.model.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = {"http://192.168.0.4:4200"}) // Indico que angular(4200) con el host "192.168.0.100" se puede conectar a api rest ClienteController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins = "*", allowCredentials = "true", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    // Listar clientes
    @GetMapping("/clientes")
    public List<Cliente> index(){

        return clienteService.findAll();
    }

    // Busca un cliente por su ID
    @GetMapping("/clientes/{id}")
    public Cliente show(@PathVariable Long id){

        return clienteService.findById(id);
    }

    //Cramos un cliente
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente){

        return clienteService.save(cliente);
    }

    //Actualizar un cliente por su ID
    @PutMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id){
        Cliente clienteActual = clienteService.findById(id);

        clienteActual.setApellido(cliente.getApellido());
        clienteActual.setNombre(cliente.getNombre());
        clienteActual.setEmail(cliente.getEmail());

        return clienteService.save(clienteActual);
    }

    //Eliminar un cliente por su ID
    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id){

        clienteService.delete(id);
    }

}
