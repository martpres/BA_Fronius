import {NgModule} from "@angular/core";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatButtonModule} from "@angular/material/button";

@NgModule({
  imports: [
    MatFormFieldModule,
    MatDatepickerModule,
    MatButtonModule
  ],
  exports: [
    MatFormFieldModule,
    MatDatepickerModule,
    MatButtonModule
  ]
})
export class MaterialModule { }
