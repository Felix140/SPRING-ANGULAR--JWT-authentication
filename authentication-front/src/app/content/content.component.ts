import { Component } from '@angular/core';
import { AxiosService } from '../service/axios.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss']
})
export class ContentComponent {

  constructor(private axiosService: AxiosService) { }

  showComponent: string = "welcome";

  onLogin(input: any): void {
    this.axiosService.request(
      "POST",
      "/login",
      {
        login: input.login,
        password: input.password
      }
    ).then(res => {
      this.axiosService.setAuthToken(res.data.token);
      this.showComponent = "messages";
    });
  }
  onRegister(input: any): void {
    this.axiosService.request(
      "POST",
      "/register",
      {
        firstName: input.firstName,
        lastName: input.lastName,
        login: input.login,
        password: input.password
      }
    ).then(res => {
      this.axiosService.setAuthToken(res.data.token);
      this.showComponent = "messages";
    });
  }

  OnShowComponent(showComponent: string) {
    this.showComponent = showComponent;
  }

}
