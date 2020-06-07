import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { StudentSharedModule } from './shared/student-shared.module';
import { StudentsRoutingModule } from './students-routing.module';
import { StudentsListComponent } from './students-list/students-list.component';
import { StudentEditComponent } from './student-edit/student-edit.component';

@NgModule({
    declarations: [
        StudentsListComponent, 
        StudentEditComponent
    ],
    imports: [
        StudentsRoutingModule,
        SharedModule,
        StudentSharedModule
    ],
    entryComponents: [
        StudentEditComponent
    ]
})
export class StudentsModule { }