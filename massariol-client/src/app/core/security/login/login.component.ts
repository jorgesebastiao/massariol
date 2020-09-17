import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../shared';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  invalidGrant: string;
  isLoading: boolean;
  showPassword: boolean;
  classes: string[];

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router) {
  }

  ngOnInit() {
    this.authService.cleanAcessToken();
    this.initForm();
  }

  initForm() {
    this.showPassword = false;
    this.classes = ['fas fa-eye-slash'];
    this.invalidGrant = '';
    this.loginForm = this.formBuilder.group({
      user: this.formBuilder.control('', [Validators.required]),
      password: this.formBuilder.control('', [Validators.required])
    });
  }

  login() {
    this.isLoading = true;
    this.authService.login(this.loginForm.get('user').value, this.loginForm.get('password').value)
      .then(() => {
        this.router.navigate(['']);
        this.isLoading = false;
      }).catch(error => {
        if (error.status === 400) {
          if (error.error.error === 'invalid_grant') {
            this.invalidGrant = 'E-mail ou senha inválidos!';
          }
        }
        this.isLoading = false;
      });
  }


  onShowPassword() {
    this.showPassword = !this.showPassword;
    this.classes = this.showPassword ? ['fas fa-eye'] : ['fas fa-eye-slash'];
  }

}
