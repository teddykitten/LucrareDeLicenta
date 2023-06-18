import {Component} from '@angular/core';
import {ActorService} from ".././_service/actor-service";
import {Actor} from "../model/actor";
import {DomSanitizer} from "@angular/platform-browser";
import {FileHandle} from "../model/file-handler";

@Component({
  selector: 'app-actor-add',
  templateUrl: './actor-add.component.html',
  styleUrls: ['./actor-add.component.css']
})
export class ActorAddComponent {
  actor: Actor = {
    name: '',
    actorImage: []
  };
  submitted = false;

  constructor(public actorService: ActorService,
              private sanitizer: DomSanitizer) {
  }

  goBack() {
    window.history.back();
  }

  saveActor(): void {
    const formData = new FormData();
    formData.append(
      "actor",
      new Blob([JSON.stringify(this.actor)], {type: "application/json"})
    )
    formData.append(
      "imageFile",
      this.actor.actorImage[0].file,
      this.actor.actorImage[0].file.name
    )
    this.actorService.create(formData)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newActor(): void {
    this.submitted = false;
    this.actor = {
      name: '',
      actorImage: []
    };
  }

  onSelectedFile({event}: { event: any }) {
    if (event.target.files) {
      const file = event.target.files[0];

      const fileHandle: FileHandle = {
        file: file,
        url: this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file)
        )
      }
      this.actor.actorImage.push(fileHandle)
    }
  }
}
