export class Student {
    public id: number;
    public cpf: string;
    public name: string;
}

export class StudentCreateCommand {
    public cpf: string;
    public name: string;
    public office: string ;
    public cellPhone?: number;
    public email?: string;

    constructor(student: any) {
        this.cpf = student.cpf;
        this.name = student.name;
        this.office = student.office;
        this.cellPhone = student.cellPhone;
        this.email = student.email;
    }
}

export class StudentUpdateCommand {
    public id: number;
    public name: string;
    public office: string ;
    public cellPhone?: number;
    public email?: string;

    constructor(student: any, id: number) {
        this.name = student.name;
        this.office = student.office;
        this.cellPhone = student.cellPhone;
        this.email = student.email;
        this.id = id;
    }
}
