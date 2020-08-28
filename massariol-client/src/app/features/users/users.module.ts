import { SharedModule } from '../../shared/shared.module';
import { NgModule } from '@angular/core';
import { UsersRoutingModule } from './users-routing.module';
import { UsersComponent } from './users.component';
import { UsersListComponent } from './users-list/users-list.component';
import { UserEditComponent } from './user-edit/user-edit-component';

@NgModule({
    declarations: [UsersComponent, UsersListComponent,UserEditComponent],
    imports: [
      SharedModule,
      UsersRoutingModule
    ],
    entryComponents: [UserEditComponent]
  })
  export class UsersModule { }