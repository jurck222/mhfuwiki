import { Component, inject, input } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { computedAsync } from 'ngxtension/computed-async';
import { QuestService } from '../../../services/quest.service';

@Component({
  selector: 'app-quest-list',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './quest-list.component.html',
  styles: `
    .row {
      margin-left: 15px;
      margin-right: 15px;
    }
    .col {
      padding: 0;
      margin: 0;
    }
    p {
      border-bottom: solid wheat 1px;
    }
  `,
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
