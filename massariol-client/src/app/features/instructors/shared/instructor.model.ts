export class Instructor {
  constructor(public id?: number,
    public cpf?: string,
    public name?: string,
    public cellPhone?: number,
    public email?: string) {
  }
}

export class InstructorCreateCommand {
  public cpf: string;
  public name: string;
  public cellPhone?: number;
  public email?: string;

  constructor(instructor: any) {
    this.cpf = instructor.cpf;
    this.name = instructor.name;
    this.cellPhone = instructor.cellPhone;
    this.email = instructor.email;
  }
}

export class InstructorUpdateCommand {
  public id: number;
  public name: string;
  public cellPhone?: number;
  public email?: string;

  constructor(instructor: any, id: number) {
    this.name = instructor.name;
    this.cellPhone = instructor.cellPhone;
    this.email = instructor.email;
    this.id = id;
  }
}

