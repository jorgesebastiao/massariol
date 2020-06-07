import { NgModule } from '@angular/core';

import { SupervisorsRoutingModule } from './supervisors-routing.module';
import { SupervisorsComponent } from './supervisors.component';
import {SharedModule} from '../../shared/shared.module';
import {SupervisorSharedModule} from './shared/supervisor-shared.module';
import { SupervisorsListComponent } from './supervisors-list/supervisors-list.component';
import { SupervisorEditComponent } from './supervisor-edit/supervisor-edit.component';

@NgModule({
  declarations: [SupervisorsComponent, SupervisorsListComponent, SupervisorEditComponent],
  imports: [
    SharedModule,
    SupervisorSharedModule,
    SupervisorsRoutingModule
  ],
  entryComponents: [
    SupervisorEditComponent
  ]
})
export class SupervisorsModule { }
