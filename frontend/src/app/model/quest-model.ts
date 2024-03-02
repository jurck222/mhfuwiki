export interface QuestModel {
  id: number;
  title: string;
  rank: string;
  questGiverLocation: string;
  mapLocation: string;
  description: string;
  objective: string;
  reward: number;
  fee: number;
  time: number;
  star: number;
  requirements?: string;
  otherMonsters?: string;
  timeOfDay: string;
  keyQuest: boolean;
  urgentQuest: boolean;
}
