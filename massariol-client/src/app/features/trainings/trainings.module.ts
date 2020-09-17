import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { TrainingsRoutingModule } from './trainings-routing.module';
import { TrainingSharedModule } from './shared/training-shared.module';
import { TrainingsListComponent } from './trainings-list/trainings-list.component';
import { TrainingEditComponent } from './training-edit/training-edit.component';
import { StudentSharedModule } from '../students/shared/student-shared.module';
import { CertificateSharedModule } from '../certificates/shared/certificate-shared.module';

@NgModule({
    declarations: [TrainingsListComponent, TrainingEditComponent],
    imports: [
      SharedModule,
      TrainingSharedModule,
      TrainingsRoutingModule,
      CertificateSharedModule,
      StudentSharedModule,
    ],
    entryComponents: [ TrainingEditComponent ]
  })
  export class TrainingsModule { }
  