import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfessoresGpService } from '../professores-gp.service';

@Component({
  selector: 'app-professores-gp-form',
  templateUrl: './professores-gp-form.component.html',
  styleUrls: ['./professores-gp-form.component.scss'],
})
export class ProfessoresGpFormComponent implements OnInit {
  meuForm: FormGroup = new FormGroup({});

  isEdicao : boolean = false;
  id : number = -1;

  constructor(
    private formBuilder: FormBuilder,
    private professoresGPService: ProfessoresGpService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.meuForm = this.formBuilder.group({
      nome: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
    });

    this.activatedRoute.params.subscribe(
      (parametrogp : any) => {

        if(parametrogp.id){
          console.log(`edição id ${parametrogp.id}`);
          this.isEdicao = true;
          this.id = parametrogp.id;

          this.professoresGPService.getOne(parametrogp.id)
          .subscribe(
            (dadosProfessoresgp) => {
              console.log(dadosProfessoresgp);
              this.meuForm.patchValue(dadosProfessoresgp);
            }
          );
        }else{
          console.log(`criação`);
          this.isEdicao = false;
        }
      }
    );
  }

  onSubmit() {
    if (this.isEdicao == false){
      this.professoresGPService.save(this.meuForm.value)
      .subscribe(
        (datagp) => {
          console.log(datagp);
          this.router.navigate(['/professoresgp']);
        }
      );
    }
    else{
      this.professoresGPService.update(this.id, this.meuForm.value)
        .subscribe(
          (datagp) => {
            console.log(datagp);
            this.router.navigate(['/professoresgp']);
          }
        );
    }
  }
}
