import { Routes } from '@angular/router';
import { AddQuestComponent } from './components/add-quest/add-quest.component';
import { VillageQuestsComponent } from './components/village-quests/village-quests.component';

export const routes: Routes = [
  { path: 'add-quest', component: AddQuestComponent },
  { path: 'village-quests', component: VillageQuestsComponent },
];
