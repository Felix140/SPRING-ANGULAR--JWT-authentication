import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  constructor() {
    //? imposto qui dentro l'endpoint del BE
    axios.defaults.baseURL = "http://localhost:8080";
    //? di default ogni richiesta POST avrà come HEADER "Content-type" un JSON
    axios.defaults.headers.post["Content-type"] = "application/json";
  }

  request(method: string, url: string, data: any): Promise<any> {
    return axios({
      method: method,
      url: url,
      data: data
    });
  }
}
