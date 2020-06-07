import { NgModule } from '@angular/core';

import { InstructorsRoutingModule } from './instructors-routing.module';
import { InstructorsComponent } from './instructors.component';
import {SharedModule} from '../../shared/shared.module';
import { InstructorsListComponent } from './instructors-list/instructors-list.component';
import { InstructorEditComponent } from './instructor-edit/instructor-edit.component';
import {InstructorSharedModule} from './shared/instructor-shared.module';

@NgModule({
  declarations: [InstructorsComponent, InstructorsListComponent, InstructorEditComponent],
  imports: [
    SharedModule,
    InstructorSharedModule,
    InstructorsRoutingModule
  ],
  entryComponents: [
    InstructorEditComponent
  ]
})
export class InstructorsModule { }
