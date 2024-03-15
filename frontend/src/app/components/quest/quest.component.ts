import { DatePipe, NgClass } from '@angular/common';
import { Component, inject, input, numberAttribute } from '@angular/core';
import { computedAsync } from 'ngxtension/computed-async';
import { QuestService } from '../../services/quest.service';

@Component({
  selector: 'app-quest',
  standalone: true,
  imports: [DatePipe, NgClass],
  templateUrl: './quest.component.html',
  styleUrl: './quest.component.css',
})
export class QuestComponent {
  readonly #questService = inject(QuestService);

  questId = input(0, { alias: 'id', transform: numberAttribute }); // url param id

  questInfo = computedAsync(() => (this.questId() ? this.#getQuestInfo(this.questId()) : null));

  #getQuestInfo(id: number) {
    return this.#questService.getQuestById(id);
  }
}
