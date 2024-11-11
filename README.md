
# Gerenciador de Biblioteca

Este é um projeto de uma aplicação de **gerenciamento de biblioteca** desenvolvida utilizando **Spring Boot**, **Jakarta** e **PostgreSQL**. O sistema oferece funcionalidades completas para o cadastro de livros, usuários e empréstimos, além de permitir o gerenciamento de cópias de livros e a identificação de usuários inadimplentes.

## Funcionalidades

- **Cadastro de Livros**: Permite o registro de livros com informações detalhadas, como título, autor, ano de publicação e número de cópias disponíveis.
- **Cadastro de Usuários**: Facilita o cadastro de usuários com informações como nome, endereço e telefone, além de permitir a identificação de inadimplentes.
- **Gerenciamento de Empréstimos**: Controla o empréstimo de livros, incluindo o acompanhamento do status dos empréstimos (ativo, atrasado, devolvido).
- **Identificação de Usuários Inadimplentes**: O sistema verifica automaticamente se há livros atrasados e identifica os usuários que precisam devolver os livros.
- **Notificação de Livros Atrasados**: O sistema envia alertas automáticos para os usuários inadimplentes que estão com livros atrasados.

## Diagrama de Classes

O diagrama de classes a seguir ilustra a estrutura do sistema, destacando as principais entidades e seus relacionamentos:

![Diagrama de Classes](Diagramas/Diagrama%20de%20classes.png)

## Executando o Projeto

1. Clone este repositório para a sua máquina local.
2. Configure o banco de dados PostgreSQL com as credenciais apropriadas.
3. Execute o projeto utilizando o seguinte comando:

   ```bash
   ./mvnw spring-boot:run
   ```

4. O sistema estará disponível em `http://localhost:8080`.

## Testes Automatizados

Os testes automatizados estão localizados no diretório `src/test/java/com/librarymanager/service`. Para executar os testes, utilize o seguinte comando:

```bash
./mvnw test
```

Isso executará todos os testes e garantirá que as funcionalidades do sistema estejam funcionando conforme o esperado.
