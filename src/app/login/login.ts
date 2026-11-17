import { ChangeDetectorRef, Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  @Output() entrou = new EventEmitter<string>();

  email = '';
  senha = '';
  erro = '';
  sucesso = '';
  nome = '';
  cadastrando = false;

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  entrar() {
    if (this.email === '' || this.senha === '') {
      this.erro = 'Preencha o e-mail e a senha.';
      return;
    }

    const dados = { email: this.email, senha: this.senha };

    this.http.post<any>('http://localhost:8080/auth/login', dados).subscribe({
      next: usuario => {
        this.erro = '';
        this.entrou.emit(usuario.nome);
      },
      error: erro => {
        if (erro.status === 401) {
          this.erro = 'E-mail ou senha incorretos.';
        } else {
          this.erro = 'Não foi possível conectar ao servidor.';
        }
        this.cd.detectChanges();
      }
    });
  }

  mostrarCadastro() {
    this.cadastrando = true;
    this.erro = '';
    this.sucesso = '';
  }

  mostrarLogin() {
    this.cadastrando = false;
    this.erro = '';
  }

  cadastrar() {
    if (this.nome === '' || this.email === '' || this.senha === '') {
      this.erro = 'Preencha nome, e-mail e senha.';
      return;
    }

    const dados = { nome: this.nome, email: this.email, senha: this.senha };

    this.http.post<any>('http://localhost:8080/auth/cadastro', dados).subscribe({
      next: () => {
        this.erro = '';
        this.sucesso = 'Conta criada! Entre com seu e-mail e senha.';
        this.cadastrando = false;
        this.nome = '';
        this.senha = '';
        this.cd.detectChanges();
      },
      error: erro => {
        if (erro.status === 409) {
          this.erro = 'Já existe uma conta com esse e-mail.';
        } else if (erro.status === 400) {
          this.erro = 'Preencha nome, e-mail e senha.';
        } else {
          this.erro = 'Não foi possível conectar ao servidor.';
        }
        this.cd.detectChanges();
      }
    });
  }
}
