import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuestModel } from '../model/quest-model';

@Injectable({
  providedIn: 'root',
})
export class QuestService {
  private apiUrl = 'http://localhost:8080/api/v1/quests';

  constructor(private http: HttpClient) {}

  getQuestsByRankAndStar(rank: string, stars: number): Observable<QuestModel[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getQuestsForRankAndStar/?rank=${rank}&star=${stars}`);
  }

  addQuest(quest: QuestModel) {
    return this.http.post(`${this.apiUrl}/`, quest);
  }
}
