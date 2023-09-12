import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  // Attributi messi in <app-header>
  @Input() pageTitle!: string;
  @Input() logoSrc!: string;

}
