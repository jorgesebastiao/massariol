
export class Training {
  constructor(public id?: number,
    public courseDate?: string,
    public startDate?: string,
    public finishDate?: string,
    public expirationDate?: string,
    public realizationDate?: string,
    public studentId?: number,
    public courseId?: number,
    public companyId?: number,
    public instructorId?: number,
    public supervisorId?: number) {
  }
}

export class TrainingCreateCommand {
  public startDate: string;
  public finishDate: string;
  public realizationDate: string;
  public studentId: number;
  public courseId: number;
  public companyId: number;
  public instructorId: number;
  public supervisorId: number;

  constructor(training: any) {
    this.startDate = training.startDate;
    this.finishDate = training.finishDate;
    this.realizationDate = training.realizationDate;
    this.studentId = training.studentId;
    this.courseId = training.courseId;
    this.companyId = training.companyId;
    this.instructorId = training.instructorId;
    this.supervisorId = training.supervisorId;
  }
}

export class TrainingUpdateCommand {
  public id: number;
  public startDate: string;
  public finishDate: string;
  public realizationDate: string;
  public studentId: number;
  public companyId: number;
  public courseId: number;
  public instructorId: number;
  public supervisorId: number;

  constructor(training: any, trainingId: number) {
    this.id = trainingId;
    this.startDate = training.startDate;
    this.finishDate = training.finishDate;
    this.realizationDate = training.realizationDate;
    this.studentId = training.studentId;
    this.courseId = training.courseId;
    this.companyId = training.companyId;
    this.instructorId = training.instructorId;
    this.supervisorId = training.supervisorId;
  }
}