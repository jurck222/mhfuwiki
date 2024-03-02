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

  getQuestsByRankAndStar(): Observable<QuestModel[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getQuestsForRankAndStar/?rank=LR&star=3`);
  }
}
