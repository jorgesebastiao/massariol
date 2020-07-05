import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { NotAuthorizedComponent } from './core/not-authorized/not-authorized.component';
import { LayoutComponent } from './core/layout/layout.component';
import { AuthGuard } from './core/security';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: '',
        loadChildren: 'app/features/main/main.module#MainModule',
        canActivate: [AuthGuard]
      },
      {
        path: 'certificates',
        loadChildren: 'app/features/certificates/certificates.module#CertificatesModule',
        canActivate: [AuthGuard]
      },
      {
        path: 'companies',
        loadChildren: 'app/features/companies/companies.module#CompaniesModule',
        canActivate: [AuthGuard]
      },
      {
        path: 'courses',
        loadChildren: 'app/features/courses/courses.module#CoursesModule',
        canActivate: [AuthGuard]
      },
      {
        path: 'instructors',
        loadChildren: 'app/features/instructors/instructors.module#InstructorsModule',
        canActivate: [AuthGuard]
      },
      {
        path: 'students',
        loadChildren: 'app/features/students/students.module#StudentsModule',
        canActivate: [AuthGuard]
      },
      {
        path: 'signatures',
        loadChildren: 'app/features/signatures/signatures.module#SignaturesModule',
        canActivate: [AuthGuard]
      },
      {
        path: 'supervisors',
        loadChildren: 'app/features/supervisors/supervisors.module#SupervisorsModule',
        canActivate: [AuthGuard]
      },
      {
        path: 'trainings',
        loadChildren: 'app/features/trainings/trainings.module#TrainingsModule',
        canActivate: [AuthGuard]
      }
    ]
  },
  {
    path: 'not-authorized',
    component: NotAuthorizedComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
