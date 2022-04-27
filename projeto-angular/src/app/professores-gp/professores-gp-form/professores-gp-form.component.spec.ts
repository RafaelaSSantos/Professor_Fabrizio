import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessoresGpFormComponent } from './professores-gp-form.component';

describe('ProfessoresGpFormComponent', () => {
  let component: ProfessoresGpFormComponent;
  let fixture: ComponentFixture<ProfessoresGpFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessoresGpFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessoresGpFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
