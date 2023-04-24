import { Component } from '@angular/core';

// primeng importation
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent {
  items!: MenuItem[];
  activeItem!: MenuItem;

  ngOnInit() {
    this.items = [
      { label: 'Home', icon: 'pi pi-fw pi-home', routerLink: '/home' },
      {
        label: 'Liste Personne',
        icon: 'pi pi-fw pi-user',
        routerLink: '/listePersonne',
      },
    ];

    this.activeItem = this.items[0];
  }
}
