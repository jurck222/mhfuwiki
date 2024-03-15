import { Component, DestroyRef, inject } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { QuestModel } from '../../model/quest-model';
import { QuestService } from '../../services/quest.service';

@Component({
  selector: 'app-add-quest',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './add-quest.component.html',
  styleUrl: './add-quest.component.css',
})
export class AddQuestComponent {
  questRanks = ['LR', 'HR', 'GR'];
  questGiverLocations = ['Village', 'Guild'];
  mapLocations = [
    'Snowy Mountains',
    'Jungle',
    'Desert',
    'Swamp',
    'Volcano',
    'Great Forest',
    'Forest & Hills',
    'Old Jungle',
    'Old Desert',
    'Old Swamp',
    'Old Volcano',
    'Fortress',
    'Town',
    'Tower',
    'Tower 2',
    'Tower 3',
    'Castle Schrade',
    'Battleground',
    'Arena',
    'Arena 2',
    'Great Arena',
    'Snowy Mountains Peak',
  ];
  dayTimes = ['day', 'night'];

  questFormGroup: FormGroup;

  myForm: FormGroup;
  quest: QuestModel;
  readonly #questService = inject(QuestService);
  readonly #destroyRef = inject(DestroyRef);
  constructor(private fb: FormBuilder) {
    this.myForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });

    this.questFormGroup = this.fb.group({
      title: [''],
      rank: [''],
      star: [''],
      objective: [''],
      questGiverLocation: [''],
      mapLocation: [''],
      description: [''],
      reward: [''],
      fee: [''],
      time: [''],
      requirements: [''],
      otherMonsters: [''],
      timeOfDay: [''],
      keyQuest: [''],
      urgentQuest: [''],
    });
  }

  onSubmit() {
    this.quest = this.questFormGroup.value satisfies QuestModel;
    this.#questService
      .addQuest(this.quest)
      .pipe(takeUntilDestroyed(this.#destroyRef))
      .subscribe({
        next: quest => this.cleanup(),
        error: e => console.error(e),
      });
  }

  cleanup() {
    console.log(this.questFormGroup.value);
    this.questFormGroup.reset();
  }
}
