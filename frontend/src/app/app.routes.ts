import { Routes } from '@angular/router';
import { AddQuestComponent } from './components/add-quest/add-quest.component';
import { QuestComponent } from './components/quest/quest.component';
import { VillageQuestsComponent } from './components/village-quests/village-quests.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

export const routes: Routes = [
  { path: 'add-quest', component: AddQuestComponent },
  { path: 'village-quests', component: VillageQuestsComponent },
  { path: '', component: WelcomeComponent },
  { path: 'quest/:id', component: QuestComponent },
];
