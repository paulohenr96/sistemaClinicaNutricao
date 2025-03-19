# sistemaClinicaNutricao
![alt text](images/image-2.png)

## Rodando o Projeto

![alt text](images/image-1.png)
- Instale o [Docker](https://www.docker.com/products/docker-desktop/)
- Entre na pasta "aplicacaonutricao"
- Execute o comando 
```
docker-compose up

```
## Testes Unitários
- Tenha o [Maven](https://maven.apache.org/download.cgi) instalado na sua máquina
- Abra o terminal na pasta "aplicacaonutricao"
- Instale as dependências
```
mvn clean install -DskipTests
```
- Execute os testes
```
mvn test
```

## TECNOLOGIAS UTILIZADAS
- [SPRING FRAMEWORK](https://spring.io/projects/spring-framework)
- [JAVA 17](https://www.oracle.com/java/technologies/downloads/)
- [BOOTSTRAP](https://getbootstrap.com/)
- [THYMELEAF](https://www.thymeleaf.org/)
- JAVASCRIPT
- CSS

## OBJETIVO
Auxiliar no gerenciamento dos pacientes de uma clinica de nutrição

## FUNCIONALIDADES
- Cadastrar pacientes
- Medidas físicas do paciente (peso,percentual de gordura,imc,altura) com gráfico em javascript
- Gerenciar a alimentação do paciente, cadastrando as refeições do paciente se seus respectivos alimentos
- Gerenciar as consultas (marcar consulta,cancelar consulta, visualizar todas as consultas e visualizar as consultas do dia)
- Sistema de autenticação utilizando Spring Security (password e username).

## CÓDIGO
- O back-end é composto majoritariamente pelas camadas de controller,serviço e repositórios.
- O controller é composto por métodos simples
que tem como objetivo chamar o serviço e retornar a resposta de acordo com a requisição do usuário.
- O serviço comunica com os mappers e com o repositório. Os mappers convertem as entidades para DTO's e vice-versa.
- O repositório realiza a comunicação com o banco de dados.
  ### TESTES UNITÁRIOS
  - Os testes unitários foram realizados utilizando mockito e junit 5
  - Para passar pelo Spring Security foi utilizada a anotação @WithMockUser
