import { Component, OnInit } from '@angular/core';
import { Cliente } from "./cliente";
import { ClienteService } from "./cliente.service";
import { Router, ActivatedRoute } from "@angular/router";

import Swal from "sweetalert2";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
  public cliente: Cliente = new Cliente();
  public titulo: string = "Crear Cliente";

  constructor( private clienteService: ClienteService,
               private router: Router,
               private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarCliente();
  }

  private cargarCliente(): void{
    this.activateRoute.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.clienteService.getCliente(id).subscribe((cliente) => this.cliente = cliente)
      }
    })

  }

  public create(): void{
    // console.log("Clicked!");
    // console.log(this.cliente);
    this.clienteService.create(this.cliente).subscribe(
      cliente => {
        this.router.navigate(['/clientes'])
        Swal.fire('Nuevo Cliente',`El cliente  ${cliente.nombre} ha sido creado con éxito!`, 'success');
      }
    );
  }

  public update(): void{
    this.clienteService.update(this.cliente).subscribe(
      json => {
        this.router.navigate(['/clientes'])
        Swal.fire('Cliente Actualizado',`${json.mensaje}: ${json.cliente.nombre}`, 'success');
        }
      );
  }

}
