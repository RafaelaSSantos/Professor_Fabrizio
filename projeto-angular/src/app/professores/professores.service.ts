import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfessoresService {

  // httpClient é um serviço angular que permite acessar endereços REST
  constructor(private httpClient: HttpClient) { }

  getAll(){
    return this.httpClient.get('http://localhost:8080/professores');
  }

  save(professorObj: any){
    return this.httpClient.post('http://localhost:8080/professores', professorObj);
  }

  delete(id : number){
    //return this.httpClient.delete('http://localhost:8080/professores/' + id);
    return this.httpClient.delete(`http://localhost:8080/professores/${id}`);
  }

  getOne(id : number){
    return this.httpClient.get(`http://localhost:8080/professores/${id}`);
  }

  update(id: number, professorObj: any){
    return this.httpClient.patch(`http://localhost:8080/professores/${id}`, professorObj);
  }
}
