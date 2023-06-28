import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HallService} from "../_service/hall-service";
import {Hall} from "../model/hall";

@Component({
  selector: 'app-hall',
  templateUrl: './hall-add.component.html',
  styleUrls: ['./hall-add.component.css']
})
export class HallAddComponent implements OnInit {
  rowsForm: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder,
              private hallService: HallService) {

    this.rowsForm = this.fb.group({
      name: '',
      rows: this.fb.array([]),
    });

  }

  ngOnInit(): void {
  }

  get rows(): FormArray {
    return this.rowsForm.get("rows") as FormArray
  }

  newRow(): FormGroup {
    return this.fb.group({
      seat: '',
    })
  }

  addRows() {
    this.rows.push(this.newRow());
  }

  removeRows(i: number) {
    this.rows.removeAt(i);
  }

  goBack() {
    window.history.back();
  }
  onSubmit(): void {
    console.log(this.rowsForm.value)
    const data = {
      name: this.rowsForm.get("name")?.value,
      rows: this.rowsForm.get("rows")?.value
    }
    console.log(data)
    this.hallService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }
}
