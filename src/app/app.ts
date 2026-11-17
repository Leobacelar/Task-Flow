import { ChangeDetectorRef, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterOutlet, RouterLink} from '@angular/router';
import { Inicio } from './inicio/inicio';
import { Sobre } from './sobre/sobre';
import { Login } from './login/login';
@Component({
  imports: [RouterOutlet, RouterLink, FormsModule, Inicio, Sobre, Login],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})

export class App {

  logado = false;
  nomeUsuario = '';

  url = 'http://localhost:8080/api/atividades';

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  entrar(nome: string) {
    this.nomeUsuario = nome;
    this.logado = true;
    this.telaAtual = 'inicio';
    this.carregar();
  }

  sair() {
    this.logado = false;
    this.nomeUsuario = '';
    this.cartoes = [];
  }

  carregar() {
    this.http.get<any[]>(this.url).subscribe(atividades => {
      this.cartoes = atividades.map(a => ({
        id: a.id,
        titulo: a.titulo,
        descricao: a.descricao,
        lista: a.status
      }));
      this.cd.detectChanges();
    });
  }

  salvar(cartao: any) {
    return { titulo: cartao.titulo, descricao: cartao.descricao, status: cartao.lista };
  }

  listaSelecionada = '';
  tituloAtividade = '';
  descricaoAtividade = '';
  
telaAtual = 'inicio';

  

cartoes:any[] = [];
 

 cartaoSelecionado: any = null;
mostrarMover: boolean = false;
 quadroDestino = '';

 get totalAtividades() {
  return this.cartoes.length;
}

get atividadesAFazer() {
  return this.cartoes.filter(cartao => cartao.lista === 'a-fazer').length;
}

get atividadesAndamento() {
  return this.cartoes.filter(cartao => cartao.lista === 'Em-andamento').length;
}

get atividadesRevisao() {
  return this.cartoes.filter(cartao => cartao.lista === 'Em-revisao').length;
}

get atividadesConcluidas() {
  return this.cartoes.filter(cartao => cartao.lista === 'finalizado').length;
}

get progresso() {
  if (this.totalAtividades === 0) {
    return 0;
  }

  return Math.round(
    (this.atividadesConcluidas / this.totalAtividades) * 100
  );
}
 
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

    // regra: de "A fazer" nao pode ir direto para "Concluido"
    if (this.cartaoSelecionado.lista === 'a-fazer' && this.quadroDestino === 'finalizado') {
      alert('Uma atividade em "A fazer" não pode ir direto para "Concluído".');
      return;
    }

    const dados = {
      titulo: this.cartaoSelecionado.titulo,
      descricao: this.cartaoSelecionado.descricao,
      status: this.quadroDestino
    };

    this.http.put(this.url + '/' + this.cartaoSelecionado.id, dados).subscribe(() => this.carregar());

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



    const novo = {
      titulo: this.tituloAtividade,
      descricao: this.descricaoAtividade,
      lista: this.listaSelecionada
    };

    this.http.post(this.url, this.salvar(novo)).subscribe(() => this.carregar());

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
  this.http.delete(this.url + '/' + cartao.id).subscribe(() => this.carregar());

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

  this.http.put(this.url + '/' + this.cartaoEditando.id, this.salvar(this.cartaoEditando)).subscribe(() => this.carregar());

  this.tituloAtividade = '';
  this.descricaoAtividade = '';

  this.cartaoEditando = null;
  this.editando = false;
  this.listaSelecionada = '';


}
}
