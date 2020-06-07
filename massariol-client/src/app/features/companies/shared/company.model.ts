export class Company {
  constructor(public id?: number,
    public corporateName?: string,
    public tradeName?: string,
    public cnpj?: string) {
  }
}

export class CompanyCreateCommand {
  public cnpj: string;
  public corporateName: string;
  public tradeName: string;
  public cellPhone: string;
  public email: string;
  constructor(company: any) {
    this.cnpj = company.cnpj;
    this.corporateName = company.corporateName;
    this.tradeName = company.tradeName;
    this.cellPhone = company.cellPhone;
    this.email = company.email;
  }
}

export class CompanyUpdateComand {
  public id: number;
  public corporateName: string;
  public tradeName: string;
  public cellPhone: string;
  public email: string;
  constructor(company: any, id: number) {
    this.corporateName = company.corporateName;
    this.tradeName = company.tradeName;
    this.cellPhone = company.cellPhone;
    this.email = company.email;
    this.id = id;
  }
}


