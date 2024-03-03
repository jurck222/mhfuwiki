import { Component, input, signal } from '@angular/core';
import { QuestListComponent } from '../quest-list/quest-list.component';

@Component({
  selector: 'app-stars-row',
  standalone: true,
  templateUrl: './stars-row.component.html',
  styles: ``,
  imports: [QuestListComponent],
})
export class StarsRowComponent {
  row = input.required<string[]>();
  stars = input.required<number>();
  toggleList = signal(false);
  rank = 'LR';
}
