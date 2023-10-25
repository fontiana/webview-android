import { Component } from '@angular/core';
declare var window: any;

var promisesCallback: [{ chave: string, promiseResolve: any, promiseReject: any}];

@Component({
  selector: 'app-cartoes',
  templateUrl: './cartoes.component.html',
  styleUrls: ['./cartoes.component.css']
})
export class CartoesComponent {
  data: [string] = [''];
  resultadoTela: string = '';

  async mostrarToast() {
    const parametro = {
      modulo: "Alerta",
      metodo: "Toast",
      params: { mensagem: "Hello World!" },
      chave: ''
    };
    this.comunicacaoNativo(parametro).then((retorno: any) => {
      this.data.push(retorno.mensagem + retorno.chave);
      this.resultadoTela = JSON.stringify(this.data);
    });
  }

  async abrirCamera() {
    const parametro = {
      modulo: "Camera",
      metodo: "Tirar",
      params: {},
      chave: ''
    };
    this.comunicacaoNativo(parametro).then((retorno: any) => {
      this.data.push(retorno.mensagem + retorno.chave);
      this.resultadoTela = JSON.stringify(this.data);
    });
  }

  comunicacaoNativo(parametro: any) {
    let chavePromise = Math.floor(Math.random() * 99999).toString();
    parametro.chave = chavePromise;
    window['Native']['execute'](JSON.stringify(parametro));

    return new Promise((resolve, reject) => {
      if (promisesCallback) {
        promisesCallback.push({ chave: chavePromise, promiseResolve: resolve, promiseReject: reject})
      } else {
        promisesCallback = [{ chave: chavePromise, promiseResolve: resolve, promiseReject: reject}];
      }
    });
  }
}

window.callbackSucesso =  (mensagem: string, chave: string) => {
  var promise = promisesCallback.find(item => {
    return item.chave == chave;
  });


  promise?.promiseResolve({mensagem, chave});
}