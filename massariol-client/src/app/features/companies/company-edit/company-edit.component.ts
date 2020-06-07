import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { BsModalRef } from 'ngx-bootstrap';
import { FormService } from '../../../shared/services/form.service';
import { CompanyService } from '../shared/company.service';
import { ToastrService } from 'ngx-toastr';
import { CnpjValidator } from '../../../shared/validators';
import { CompanyCreateCommand, CompanyUpdateComand } from '../shared/company.model';
import { Subject } from 'rxjs';


@Component({
    selector: 'app-company-edit',
    templateUrl: './company-edit.component.html',
    styleUrls: ['./company-edit.component.scss']
})
export class CompanyEditComponent implements OnInit {

    public onClose: Subject<boolean>;
    companyForm: FormGroup;
    title: string;
    companyId: number;
    isLoading: boolean;
    isEdit: boolean;
    constructor(public bsModalRef: BsModalRef,
        private formBuilder: FormBuilder,
        private formService: FormService,
        private companyService: CompanyService,
        private toastrService: ToastrService) {

    }

    ngOnInit() {
        this.onClose = new Subject();
        this.isEdit = false;
        this.title = 'Cadastro de empresa';
        this.initForms();
        if (this.companyId) {
            this.isEdit = true;
            this.title = 'Edição de empresa';
            this.getCompany();
        }
    }

    save() {
        if (this.companyForm.valid) {
            this.isLoading = true;
            if (this.isEdit) {
                const companyUpdateComand: CompanyUpdateComand = new CompanyUpdateComand(this.companyForm.value, this.companyId);
                this.companyService.put(companyUpdateComand)
                    .subscribe(() => {
                        this.toastrService.success('Edição realizada com Sucesso!');
                        this.isLoading = false;
                        this.onClose.next(true);
                        this.bsModalRef.hide();                        
                    }, error => {
                        this.isLoading = false;
                        const errorMessage = error.status == 400 ? error.error.message: 'Ocorreu erro ao processar a solicitação';
                        this.toastrService.error(errorMessage);
                    });
            } else {
                const companyCreateCommand: CompanyCreateCommand = new CompanyCreateCommand(this.companyForm.value);
                this.companyService.post(companyCreateCommand)
                    .subscribe(() => {
                        this.toastrService.success('Cadastro realizado com Sucesso!');
                        this.isLoading = false;
                        this.onClose.next(true);
                        this.bsModalRef.hide();
                    }, error => {
                        this.isLoading = false;
                        const errorMessage = error.status == 400 ? error.error.message: 'Ocorreu erro ao processar a solicitação';
                        this.toastrService.error(errorMessage);
                    });
            }
        } else {
            this.formService.validForm(this.companyForm);
        }
    }

    private initForms() {
        this.companyForm = this.formBuilder.group({
            cnpj: this.formBuilder.control('', [this.isValidCnpj]),
            corporateName: this.formBuilder.control('', [Validators.required]),
            tradeName: this.formBuilder.control('', [Validators.required]),
            email: this.formBuilder.control('', [Validators.email, Validators.required]),
            cellPhone: this.formBuilder.control('')
        });
    }

    isValidCnpj(control: AbstractControl): { [p: string]: boolean } {
        return CnpjValidator.validate(control);
    }

    getCompany() {
        this.isLoading = true;
        this.companyService.getById(this.companyId).subscribe(companyResponse => {
            this.companyForm.patchValue(companyResponse);
            this.isLoading = false;
        }, error => {
            this.isLoading = false;
            this.toastrService.error('Ocorreu erro na solicitação');
        });
    }
}