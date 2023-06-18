import {Injectable} from '@angular/core';
import {Actor} from "../model/actor";
import {FileHandle} from "../model/file-handler";
import {DomSanitizer} from "@angular/platform-browser";

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor(private sanitiezer: DomSanitizer) {
  }

  public createImages(actor: Actor) {
    const actorImages: any[] = actor.actorImage;
    const actorImageHandle: FileHandle[] = [];
    for (let i = 0; i < actorImages.length; i++) {
      const imageFileData = actorImages[i];
      const imageBlob = this.dataURItoBlob(imageFileData.picByte, imageFileData.type);
      const imageFile = new File([imageBlob], imageFileData.name, {type: imageFileData.type})
      const finalFileHandle: FileHandle = {
        file: imageFile,
        url: this.sanitiezer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      };
      actorImageHandle.push(finalFileHandle);
    }
    actor.actorImage = actorImageHandle;
    return actor;
  }

  public dataURItoBlob(picBytes: any, imageType: any) {
    const byteString = window.atob(picBytes);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);

    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);

    }

    return new Blob([int8Array], {type: imageType});


  }
}
