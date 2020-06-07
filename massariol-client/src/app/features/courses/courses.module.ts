import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { CoursesRoutingModule } from './courses-routing.module';
import { CourseSharedModule } from './shared/course-shared.module';
import { CoursesListComponent } from './courses-list/courses-list.component';
import { CourseEditComponent } from './course-edit/course-edit.component';
import { LMarkdownEditorModule } from 'ngx-markdown-editor';

import 'brace';
import 'brace/mode/markdown';


@NgModule({
    declarations: [
        CoursesListComponent,
        CourseEditComponent
    ],
    imports: [
        CoursesRoutingModule,
        SharedModule,
        CourseSharedModule,
        LMarkdownEditorModule,
        FormsModule
    ],
    entryComponents: [
        CourseEditComponent
     ]
})
export class CoursesModule { }
