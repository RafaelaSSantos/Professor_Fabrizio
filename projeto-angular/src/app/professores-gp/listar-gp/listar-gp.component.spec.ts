import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarGpComponent } from './listar-gp.component';

describe('ListarGpComponent', () => {
  let component: ListarGpComponent;
  let fixture: ComponentFixture<ListarGpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarGpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarGpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
