import {Company} from '../../companies/shared/company.model';

export class User {
  public id?: any;
  public user?: string;
  public password?: string;
  public email?: string;
  public company?: Company;
}

export class UserCreateCommand {
  public email: string;
  public name: string;
  public companyId: number;
  constructor(user: any) {
    this.name = user.name;
    this.email = user.email;
    this.companyId = user.companyId;
  }
}

export class UserUpdateCommand {
  public id: number;
  public email: string;
  public name: string;
  public password: string;
  public resendPassword: boolean;
  constructor(user: any, userId: number) {
    this.id = userId;
    this.name = user.name;
    this.email = user.email;
    this.password = user.password;
    this.resendPassword = user.resendPassword;
  }
}

