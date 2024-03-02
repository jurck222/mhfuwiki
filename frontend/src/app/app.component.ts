import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { VillageQuestsComponent } from './components/village-quests/village-quests.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [RouterOutlet, HeaderComponent, FooterComponent, VillageQuestsComponent, HttpClientModule],
  providers: [HttpClientModule],
})
export class AppComponent {
  title = 'frontend';
}
