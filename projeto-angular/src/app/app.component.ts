import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'projeto-angular';
  empresa='NAVA';

  corFundo = "background-color:yellow;"


  getColor(){
    let color='yellow'
    return 'yellow'
  }

  clicar(){
    alert("botão clicado")
  }

  getTextoDigitado(elemento : any){
    console.log(elemento.value)
    this.empresa =elemento.value;
  }
}
