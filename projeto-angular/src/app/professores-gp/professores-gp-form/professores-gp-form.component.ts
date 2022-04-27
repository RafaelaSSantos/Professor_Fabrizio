import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProfessoresGpService } from '../professores-gp.service';

@Component({
  selector: 'app-professores-gp-form',
  templateUrl: './professores-gp-form.component.html',
  styleUrls: ['./professores-gp-form.component.scss'],
})
export class ProfessoresGpFormComponent implements OnInit {
  meuForm: FormGroup = new FormGroup({});

  constructor(
    private formBuilder: FormBuilder,
    private professoresGPService: ProfessoresGpService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.meuForm = this.formBuilder.group({
      nome: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
    });
  }
  onSubmit() {
    console.log(this.meuForm.value);
    this.professoresGPService.save(this.meuForm.value).subscribe((data) => {
      console.log(data);
      this.router.navigate(['/professores']);
    });
  }
}
