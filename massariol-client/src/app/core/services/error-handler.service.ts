import { NotAuthenticatedError } from '../security';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Injectable, ErrorHandler, Injector } from '@angular/core';

@Injectable()
export class ErrorHandlerService implements ErrorHandler{

  constructor(
    private injector: Injector
  ) { }

    handleError(errorResponse: any) {
    const router = this.injector.get(Router);

    let msg: string;

    if (typeof errorResponse === 'string') {
      msg = errorResponse;

    } else if (errorResponse instanceof NotAuthenticatedError) {
      msg = 'Sua sessão expirou!';
      router.navigate(['/login']);

    } else if (errorResponse instanceof HttpErrorResponse
        && errorResponse.status >= 400 && errorResponse.status <= 499) {
      msg = 'Ocorreu um erro ao processar a sua solicitação';

      if (errorResponse.status === 403) {
        msg = 'Você não tem permissão para executar esta ação';
      }

      try {
        msg = errorResponse.error[0].mensagemUsuario;
      } catch (e) { }

      console.error('Ocorreu um erro', errorResponse);

    } else {
      msg = 'Erro ao processar serviço remoto. Tente novamente.';
      console.error('Ocorreu um erro', errorResponse);
    }
  }
}