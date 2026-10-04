import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

@Component({
  imports: [RouterOutlet, FormsModule],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {

  listaSelecionada = '';
  tituloAtividade = '';
  descricaoAtividade = '';
  


  

cartoes:any[] = [];
 

 cartaoSelecionado: any = null;
mostrarMover: boolean = false;
 quadroDestino = '';
 
  adicionarCartao(lista: string) {

    
    this.listaSelecionada = lista;
  }
  moverCartao(cartao: any) {
  this.cartaoSelecionado = cartao;
  this.mostrarMover = true;

  
}
   moverAtividade()   {
    if (this.quadroDestino === ''){
      alert('Escolha um quadro!')
      return;
    }
    this.cartaoSelecionado.lista = this.quadroDestino;
    

    this.cartaoSelecionado = null;
    this.quadroDestino = ''
    this.mostrarMover = false;

   }
    cancelarMovimento() {
  this.cartaoSelecionado = null;
  this.quadroDestino = '';
  this.mostrarMover = false;

}




  

  adicionarAtividade() {

    if(this.editando){
      this.salvarEdicao();
      return;
    }



    this.cartoes.push({
      titulo: this.tituloAtividade,
      descricao: this.descricaoAtividade,
      lista: this.listaSelecionada
    });
    

    this.tituloAtividade = '';
    this.descricaoAtividade = '';
    this.listaSelecionada = '';

  }

  cancelarAtividade() {
    this.listaSelecionada = ''
  }


menuAberto = false;
cartaoMenu: any = null;
cartaoEditando: any = null;
editando: boolean = false;


abrirMenu(cartao: any) {
  this.cartaoMenu = cartao;
  this.menuAberto = true;
}

fecharMenu() {
  this.cartaoMenu = null;
  this.menuAberto = false;
}

excluirCartao(cartao: any) {
  this.cartoes = this.cartoes.filter(c => c !== cartao);

  this.fecharMenu();
}
editarCartao(cartao: any){
  this.cartaoEditando = cartao;

  this.tituloAtividade = cartao.titulo;
  this.descricaoAtividade = cartao.descricao;

  this.listaSelecionada = cartao.lista;
  this.editando = true;

  this.fecharMenu();

}

salvarEdicao(){
  this.cartaoEditando.titulo = this.tituloAtividade;
  this.cartaoEditando.descricao = this.descricaoAtividade;

  this.tituloAtividade = '';
  this.descricaoAtividade = '';

  this.cartaoEditando = null;
  this.editando = false;
  this.listaSelecionada = '';


}
}
