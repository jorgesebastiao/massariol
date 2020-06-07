import {Company} from '../../companies/shared/company.model';

export class User {
  public id?: any;
  public user?: string;
  public password?: string;
  public email?: string;
  public company?: Company;
}

export class CompanyUserCreateCommand {
  public email: string;
  public name: string;
  public password: string;
  public companyId: number;
  constructor(user: any, companyId: number) {
    this.email = user.email;
    this.name = user.name;
    this.password = user.password;
    this.companyId = companyId;
  }
}


export class CompanyUserUpdateCommand {
  public id: number;
  public email: string;
  public name: string;
  public password: string;
  public companyId: number;
  constructor(user: any, userId: number, companyId: number) {
    this.id = userId;
    this.name = user.name;
    this.email = user.email;
    this.password = user.password;
    this.companyId = companyId;
  }
}

