import {NgModule} from "@angular/core";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatButtonModule} from "@angular/material/button";
import {MatNativeDateModule} from "@angular/material/core";
import {MatInputModule} from "@angular/material/input";

@NgModule({
  imports: [
    MatFormFieldModule,
    MatDatepickerModule,
    MatButtonModule,
    MatNativeDateModule,
    MatInputModule
  ],
  exports: [
    MatFormFieldModule,
    MatDatepickerModule,
    MatButtonModule,
    MatNativeDateModule,
    MatInputModule
  ],
  providers: [
    MatDatepickerModule
  ]
})
export class MaterialModule { }
