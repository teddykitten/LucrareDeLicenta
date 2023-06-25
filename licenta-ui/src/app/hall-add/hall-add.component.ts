import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HallService} from "../_service/hall-service";

@Component({
  selector: 'app-hall',
  templateUrl: './hall-add.component.html',
  styleUrls: ['./hall-add.component.css']
})
export class HallAddComponent implements OnInit {
  form: FormGroup;
  currentStep = 1;
  submitted = false;
  seats: { label: string, type: string, name: string }[] = [];

  constructor(private formBuilder: FormBuilder,
              private hallService: HallService) {
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      rowNumber: ['', Validators.required]
    });
  }

  nextStep() {
    if (this.form.valid) {
      this.generateFields();
      this.currentStep++;
    }
  }

  generateFields() {
    const rowNumber = this.form.get('rowNumber')!.value;
    this.seats = [];
    for (let i = 1; i <= rowNumber; i++) {
      const fieldName = `row${i}`;
      this.seats.push({
        label: `Row ${i}`,
        type: 'text',
        name: fieldName
      });

      this.form.addControl(fieldName, this.formBuilder.control(''));
    }
  }

  submitForm() {
    if (this.form.valid) {
      console.log(this.form.value);
      this.submitted = true;
    }
  }
}
