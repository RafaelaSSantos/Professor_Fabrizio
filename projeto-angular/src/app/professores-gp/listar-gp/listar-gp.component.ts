import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProfessoresGpModel } from '../professores-gp.model';
import { ProfessoresGpService } from '../professores-gp.service';

@Component({
  selector: 'app-listar-gp',
  templateUrl: './listar-gp.component.html',
  styleUrls: ['./listar-gp.component.scss'],
})
export class ListarGpComponent implements OnInit {

  professoresgp: ProfessoresGpModel[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private professoresGPService: ProfessoresGpService
  ) {}

  ngOnInit(): void {
    this.professoresGPService.getAll().subscribe((datagp) => {
      this.professoresgp = datagp;
    });
    this.activatedRoute.params.subscribe((datagp) => { });
  }

  onDelete(id: number) {
    this.professoresGPService.delete(id).subscribe(() => {
      console.log(`deletou registro com id ${id}`);
      this.getAll();
    });
  }

  private getAll() {
    this.professoresGPService.getAll().subscribe((datagp) => {
      this.professoresgp = datagp;
    });
  }
}
