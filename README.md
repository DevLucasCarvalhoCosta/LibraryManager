
# Gerenciador de Biblioteca

Este é um projeto de uma aplicação de gerenciamento de biblioteca construída utilizando Spring Boot, Jakarta e PostgreSQL. O sistema possui funcionalidades para cadastro de livros, usuários e empréstimos, além de gerenciar as cópias de livros e identificar usuários inadimplentes.

## Funcionalidades

- Cadastro de **livros** com informações sobre título, autor, ano e número de cópias disponíveis.
- Cadastro de **usuários** com informações como nome, endereço e telefone.
- Gerenciamento de **empréstimos** de livros, com controle de status (ativo, atrasado, devolvido).
- Identificação de **usuários inadimplentes** que estão com livros atrasados.
- Notificação de livros **atrasados** para os usuários inadimplentes.

## Diagrama de Classes

O diagrama de classes abaixo ilustra a estrutura do sistema e os relacionamentos entre as principais entidades:

```plantuml
@startuml
' Definindo a classe Livro
class Livro {
    +Long id
    +String titulo
    +String autor
    +int ano
    +int copiasDisponiveis
    +getId(): Long
    +setId(id: Long): void
    +getTitulo(): String
    +setTitulo(titulo: String): void
    +getAutor(): String
    +setAutor(autor: String): void
    +getAno(): int
    +setAno(ano: int): void
    +getCopiasDisponiveis(): int
    +setCopiasDisponiveis(copiasDisponiveis: int): void
}

' Definindo a classe Usuário
class Usuario {
    +Long id
    +String nome
    +String endereco
    +String telefone
    +List<Emprestimo> emprestimos
    +getId(): Long
    +setId(id: Long): void
    +getNome(): String
    +setNome(nome: String): void
    +getEndereco(): String
    +setEndereco(endereco: String): void
    +getTelefone(): String
    +setTelefone(telefone: String): void
    +getEmprestimos(): List<Emprestimo>
    +setEmprestimos(emprestimos: List<Emprestimo>): void
}

' Definindo a classe Emprestimo
class Emprestimo {
    +Long id
    +Long usuarioId
    +Long livroId
    +Date dataEmprestimo
    +Date dataDevolucao
    +String status
    +getId(): Long
    +setId(id: Long): void
    +getUsuarioId(): Long
    +setUsuarioId(usuarioId: Long): void
    +getLivroId(): Long
    +setLivroId(livroId: Long): void
    +getDataEmprestimo(): Date
    +setDataEmprestimo(dataEmprestimo: Date): void
    +getDataDevolucao(): Date
    +setDataDevolucao(dataDevolucao: Date): void
    +getStatus(): String
    +setStatus(status: String): void
}

' Definindo o serviço Livro
class ServicoLivro {
    +RepositorioLivro repositorioLivro
    +List<Livro> obterTodosLivros(): List<Livro>
    +Livro salvarLivro(livro: Livro): Livro
    +void atualizarCopiasDisponiveis(livroId: Long, ajuste: int): void
    +void excluirLivro(livroId: Long): void
}

' Definindo o serviço Usuario
class ServicoUsuario {
    +RepositorioUsuario repositorioUsuario
    +List<Usuario> obterTodosUsuarios(): List<Usuario>
    +Usuario salvarUsuario(usuario: Usuario): Usuario
    +List<Usuario> obterUsuariosInadimplentes(): List<Usuario>
}

' Definindo as relações
ServicoLivro --> RepositorioLivro : usa
ServicoUsuario --> RepositorioUsuario : usa
ServicoLivro --> Livro : gerencia
Emprestimo --> Usuario : relaciona
Emprestimo --> Livro : relaciona

@enduml
```

## Executando o Projeto

1. Clone este repositório.
2. Configure o banco de dados PostgreSQL.
3. Execute o projeto usando o comando:

   ```
   ./mvnw spring-boot:run
   ```

## Testes

Os testes automatizados estão localizados no diretório `src/test/java/com/librarymanager/service`. Para executar os testes, utilize o comando:

```
./mvnw test
```
