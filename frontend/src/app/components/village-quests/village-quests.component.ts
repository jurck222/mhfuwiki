import { Component, OnInit, signal } from '@angular/core';
import { StarsRowComponent } from '../shared/stars-row/stars-row.component';

@Component({
  selector: 'app-village-quests',
  standalone: true,
  templateUrl: './village-quests.component.html',
  styles: ``,
  imports: [StarsRowComponent],
})
export class VillageQuestsComponent implements OnInit {
  numberOfStars = signal(6);
  starsArray: string[][] = [];
  stars = 0;

  ngOnInit(): void {
    this.generateStars(this.numberOfStars());
  }

  generateStars(numberOfStars: number) {
    for (let i = 1; i <= numberOfStars; i++) {
      const icons = Array(i).fill('fa-solid fa-star-of-life');
      this.starsArray.push(icons);
    }
  }
}
