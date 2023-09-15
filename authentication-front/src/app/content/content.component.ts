import { Component } from '@angular/core';
import { AxiosService } from '../service/axios.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss']
})
export class ContentComponent {
  showComponent: string = "welcome";

	constructor(private axiosService: AxiosService) { }

	OnShowComponent(componentToShow: string): void {
		this.showComponent = componentToShow;
	}

	onLogin(input: any): void {
		this.axiosService.request(
			"POST",
			"/login",
			{
				login: input.login,
				password: input.password
			}).then(
				response => {
					this.axiosService.setAuthToken(response.data.token);
					this.showComponent = "messages";
				}).catch(
					error => {
						this.axiosService.setAuthToken(null);
						this.showComponent = "welcome";
					}
				);

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
			}).then(
				response => {
					this.axiosService.setAuthToken(response.data.token);
					this.showComponent = "messages";
				}).catch(
					error => {
						this.axiosService.setAuthToken(null);
						this.showComponent = "welcome";
					}
				);
	}

}
