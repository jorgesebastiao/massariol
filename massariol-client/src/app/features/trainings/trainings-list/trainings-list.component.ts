import { BsModalService, BsModalRef, ModalOptions } from 'ngx-bootstrap/modal';
import { Page } from '../../../shared/models';
import { Component, OnInit } from '@angular/core';
import { TrainingService } from '../shared/training.service';
import { TrainingEditComponent } from '../training-edit/training-edit.component';


@Component({
    selector: 'app-trainings-list',
    templateUrl: './trainings-list.component.html',
    styleUrls: ['./trainings-list.component.scss']
})
export class TrainingsListComponent implements OnInit {

    page: Page;
    rows = [];
    isLoading: boolean;

    bsModalRef: BsModalRef;

    constructor(private trainingService: TrainingService,
        private modalService: BsModalService) {
        this.page = new Page();
    }

    ngOnInit() {
        this.setPage(this.pageInfoInitial);
    }

    columns = [
        { prop: 'id', name: 'ID' },
        { prop: 'startDate', name: 'Inico' },
        { prop: 'finishDate', name: 'Finalizado em' },
        { prop: 'expirationDate', name: 'Validade' },
        { prop: 'realizationDate', name: 'Realizado em' }
    ];

    messages: any = {
        emptyMessage: 'Não há dados para exibição',
        totalMessage: 'total',
        selectedMessage: 'selecionado'
    };

    pageInfoInitial = { size: 10, offset: 0 };

    setPage(pageInfo: any, filter?: string): void {
        this.isLoading = true;
        this.page.pageNumber = pageInfo.offset;
        this.trainingService.getAll(this.page, filter).subscribe(pagedData => {
            this.page.size = pagedData.size;
            this.page.pageNumber = pagedData.pageable.pageNumber;
            this.page.totalPages = pagedData.totalPages;
            this.page.totalElements = pagedData.totalElements;
            this.rows = pagedData.content;
            this.isLoading = false;
        });
    }

    searchTraining(search) {
        this.setPage(this.pageInfoInitial, search.search);
    }

    newTraining(newTraining) {
        const initialState = {
            newTraining: newTraining,
        };

        const config: ModalOptions = {
            initialState: initialState,
            class:'modal-lg',
            backdrop: true,
            ignoreBackdropClick: true
        };
        
        this.bsModalRef = this.modalService.show(TrainingEditComponent, config);
        this.bsModalRef.content.onClose.subscribe(result => {
        this.setPage(this.pageInfoInitial);
        });
    }

    updateTraining(trainingId) {
        const initialState = {
            trainingId: trainingId,
          };
          const config: ModalOptions = {
            initialState: initialState,
            class:'modal-lg',
            backdrop: true,
            ignoreBackdropClick: true
          };
          this.bsModalRef = this.modalService.show(TrainingEditComponent, config);
          this.bsModalRef.content.onClose.subscribe(result => {
            this.setPage(this.pageInfoInitial);
          });
    }

    deleteTraining(trainingId) {

    }
}