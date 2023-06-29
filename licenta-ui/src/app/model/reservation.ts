import {Schedule} from "./schedule";
import {Seat} from "./seat";

export class Reservation {
  email: string;
  schedule: Schedule;
  seats: Seat[];

}
