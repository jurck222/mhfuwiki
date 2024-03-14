import { Component, OnInit, input } from '@angular/core';

@Component({
  selector: 'app-quest',
  standalone: true,
  imports: [],
  templateUrl: './quest.component.html',
  styleUrl: './quest.component.css',
})
export class QuestComponent implements OnInit {
  questId = input();

  ngOnInit(): void {
    console.log(this.questId());
  }
}
