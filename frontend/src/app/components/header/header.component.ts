import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [NgbModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './header.component.html',
  styles: `
    div {
      font-size: 24px;
    }
    .menu {
      padding-right: 20px;
      padding-top: 5px;
      padding-bottom: 5px;
      color: white;
    }
    .menu:hover {
      color: wheat;
      cursor: pointer;
    }

    img {
      width: 24px;
      height: 24px;
    }

    .dropdown:hover > .dropdown-menu {
      display: block;
    }

    .dropdown-menu {
      overflow: hidden;
      overflow-y: auto;
      max-height: calc(100vh - 150px);
      width: 200px;
    }

    .dropdown-menu::-webkit-scrollbar {
      display: none;
    }
  `,
})
export class HeaderComponent {}
