# Sistema de Gerenciamento para Clínica de Nutrição

![sistemaClinicaNutricao](images/image-2.png)

## 🎥 Demonstração do Projeto

[![Veja o projeto em execução](https://img.youtube.com/vi/hCzEURZyjM4/0.jpg)](https://youtu.be/hCzEURZyjM4)

## 📌 Sobre o Projeto

Este projeto consiste em um **sistema web para gerenciamento de clínicas de nutrição**. Ele permite que profissionais da área registrem pacientes, agendem consultas, gerenciem históricos de medidas e cadastrem dietas personalizadas.

## 🚀 Tecnologias Utilizadas

- [Java 17](https://www.oracle.com/java/technologies/downloads/)
- [Spring Framework](https://spring.io/projects/spring-framework)
- [Spring Security](https://spring.io/projects/spring-security)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Bootstrap](https://getbootstrap.com/)
- [JavaScript](https://developer.mozilla.org/pt-BR/docs/Web/JavaScript)
- [CSS](https://developer.mozilla.org/pt-BR/docs/Web/CSS)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)

## 🎯 Funcionalidades

✅ Cadastro de pacientes com informações detalhadas  
✅ Registro de medidas físicas (peso, IMC, percentual de gordura) com gráficos dinâmicos  
✅ Cadastro de dietas e planejamento alimentar dos pacientes  
✅ Controle de consultas: agendamento, cancelamento e visualização das consultas  
✅ Sistema de autenticação com Spring Security  
✅ Interface responsiva utilizando Bootstrap e Thymeleaf  

## 🛠 Como Rodar o Projeto

### 🔹 1. Rodando com Docker

**Requisitos:**
- [Docker instalado](https://www.docker.com/products/docker-desktop)

**Passos:**
1. Clone o repositório e entre na pasta `aplicacaonutricao`
2. Execute o comando:
   ```bash
   docker-compose up --build
   ```
3. O sistema estará acessível em: [http://localhost:8080](http://localhost:8080)

### 📌2. Usuário de Teste
Caso seja necessário testar a aplicação com login:

- **Usuário:** admin  
- **Senha:** admin  


### 🔹 3. Executando os Testes Unitários

**Requisitos:**
- [Maven instalado](https://maven.apache.org/download.cgi)

**Passos:**
1. Abra o terminal na pasta `aplicacaonutricao`
2. Instale as dependências:
   ```bash
   mvn clean install -DskipTests
   ```
3. Execute os testes:
   ```bash
   mvn test
   ```

## 🧪 Testes Unitários

Este projeto conta com **88 testes unitários** desenvolvidos com JUnit 5 e Mockito.
Os testes validam a lógica de negócio e interações entre as camadas da aplicação.  

- Para passar pela autenticação do Spring Security, foi utilizada a anotação `@WithMockUser`.

## 📂 Estrutura do Código

A aplicação segue uma estrutura baseada em **camadas MVC**:

📂 `controller/` → Controladores que recebem requisições e chamam os serviços  
📂 `service/` → Camada de negócio e processamento dos dados  
📂 `repository/` → Interface que gerencia as interações com o banco de dados  
📂 `mapper/` → Conversão entre entidades e DTOs  


## 📜 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

Gostou do projeto? ⭐ Dê um star no repositório! 😊

