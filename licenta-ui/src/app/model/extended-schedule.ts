import {Schedule} from "./schedule";
import {Hall} from "./hall";

export class ExtendedSchedule{
  schedule: Schedule;
  hallRequestBody: Hall;
  seats: string[];
}
