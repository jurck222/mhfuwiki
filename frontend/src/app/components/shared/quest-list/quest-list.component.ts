import { Component, inject, input } from '@angular/core';
import { computedAsync } from 'ngxtension/computed-async';
import { QuestService } from '../../../services/quest.service';

@Component({
  selector: 'app-quest-list',
  standalone: true,
  imports: [],
  templateUrl: './quest-list.component.html',
  styles: ``,
})
export class QuestListComponent {
  rank = input.required<string>();
  stars = input.required<number>();

  readonly #questService = inject(QuestService);

  quests = computedAsync(() => (this.rank() && this.stars() ? this.#getQuests$(this.rank(), this.stars()) : null));

  #getQuests$(rank: string, stars: number) {
    return this.#questService.getQuestsByRankAndStar(rank, stars);
  }
}
