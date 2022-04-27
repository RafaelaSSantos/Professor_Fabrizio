import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProfessoresGpService } from '../professores-gp.service';

@Component({
  selector: 'app-listar-gp',
  templateUrl: './listar-gp.component.html',
  styleUrls: ['./listar-gp.component.scss']
})
export class ListarGpComponent implements OnInit {

  // professoresgp = [
  //   { id : 1, nome: "Fabrizio", email: "fabrizio@grandeporte.com.br" },
  //   { id : 2, nome: "Nelson", email: "nelson@grandeporte.com.br" }
  // ];

  professoresgp : any = [];

  constructor(private activatedRoute: ActivatedRoute,
    private professoresGPService: ProfessoresGpService) { }

  ngOnInit(): void {
    this.professoresGPService.getAll()
    .subscribe(
      (data) => {
        console.log(data);
        this.professoresgp = data;
      }
    );
    this.activatedRoute.params.subscribe(
      (data) => {
        console.log(data);
      }
    );
  }
}
