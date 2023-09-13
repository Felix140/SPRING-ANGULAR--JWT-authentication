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
  @Output() onSubmitRegisterEvent = new EventEmitter();

  //? creo i campi input
  login: string = "";
  password: string = "";

  active: string = 'login';
  firstName: string = "";
  lastName: string = "";

  //? Questi metodi saranno utilizzati per switchare da un form all'altro
  onLoginTab(): void {
    this.active = 'login';
  }
  onRegisterTab(): void {
    this.active = 'register';
  }



  //? Il metodo per quando clicco sul SUBMIT LOGIN
  onSubmitLogin(): void {
    this.onSubmitLoginEvent.emit({
      login: this.login,
      password: this.password
    });
  }
  //? Il metodo per quando clicco sul SUBMIT REGISTER
  onSubmitRegister(): void {
    this.onSubmitRegisterEvent.emit({
      firsName: this.firstName,
      lastName: this.lastName,
      login: this.login,
      password: this.password
    });
  }

}
