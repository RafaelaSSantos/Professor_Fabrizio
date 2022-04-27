import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfessoresGpService{

  constructor(private httpClient: HttpClient) { }

  getAll(){
    return this.httpClient.get('http://cursos.grandeporte.com.br:8080/professores');
  }

  save(professorObj: any){
    return this.httpClient.post('http://cursos.grandeporte.com.br:8080/professores', professorObj);
  }
}
