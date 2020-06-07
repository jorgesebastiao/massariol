export class Supervisor {
  constructor(public id?: number,
              public cpf?: string,
              public name?: string,
              public cellPhone?: string,
              public email?: string) {
  }
}

export class SupervisorCreateCommand {
  public cpf: string;
  public name: string;
  public cellPhone?: string;
  public email?: string;

  constructor(cpf: string, name: string) {
    this.cpf = cpf;
    this.name = name;
  }
}

export class SupervisorUpdateCommand {
  public id: number;
  public name: string;
  public cellPhone?: string;
  public email?: string;

  constructor(supervisor: any, id: number) {
    this.name = supervisor.name;
    this.cellPhone = supervisor.cellPhone;
    this.email = supervisor.email;
    this.id = id;
  }
}