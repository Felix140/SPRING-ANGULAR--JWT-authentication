import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-login-content',
  templateUrl: './login-content.component.html',
  styleUrls: ['./login-content.component.scss']
})
export class LoginContentComponent {

  //? creo un campo di output cosi il metodo di SUBMIT sarà dentro il component PADRE
  @Output() onSubmitLoginEvent = new EventEmitter();
  //* Avere nel componente padre(CONTENT) la richiesta di LOGIN data in (OUTPUT)-> mi permetterà di GESTIRE
  //* la risposta e portarla poi nella ->(AUTH-COMPONENT) una volta fatta l'autenticazione

  //? creo i campi input
  login: string = "";
  password: string = "";


  onSubmitLogin(): void {
    this.onSubmitLoginEvent.emit({
      login: this.login,
      password: this.password
    });
  }

}
