import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

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

  usuarios = [
    { nome: 'Ana Souza', email: 'ana@taskflow.com', senha: '123456' },
    { nome: 'Bruno Lima', email: 'bruno@taskflow.com', senha: '123456' },
    { nome: 'Carla Mendes', email: 'carla@taskflow.com', senha: '123456' }
  ];

  entrar() {
    if (this.email === '' || this.senha === '') {
      this.erro = 'Preencha o e-mail e a senha.';
      return;
    }

    const usuario = this.usuarios.find(u => u.email === this.email && u.senha === this.senha);

    if (usuario === undefined) {
      this.erro = 'E-mail ou senha incorretos.';
      return;
    }

    this.erro = '';
    this.entrou.emit(usuario.nome);
  }
}
