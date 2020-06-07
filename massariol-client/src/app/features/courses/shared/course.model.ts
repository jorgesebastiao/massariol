export class Course {
    constructor(public id?: number,
        public name?: string,
        public guideline?: string,
        public courseIdentification?: string,
        public workload?: number,
        public validityInYears?: number) {
    }
}

export class CourseCreateCommand {
    public name: string;
    public guideline: string;
    public courseIdentification: string;
    public workload: number;
    public validityInYears: number;

    constructor(course: any) {
        this.name = course.name;
        this.guideline = course.guideline;
        this.courseIdentification = course.courseIdentification;
        this.workload = course.workload;
        this.validityInYears = course.validityInYears;
    }
}

export class CourseUpdateCommand {
    public id: number;
    public name: string;
    public guideline: string;
    public courseIdentification: string;
    public workload: number;
    public validityInYears: number;

    constructor(course: any, id: number, guideline: string) {
        this.name = course.name;
        this.guideline = guideline;
        this.courseIdentification = course.courseIdentification;
        this.workload = course.workload;
        this.validityInYears = course.validityInYears;
        this.id = id;
    }
}
