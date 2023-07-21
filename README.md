# sistemaClinicaNutricao
SPRING MVC - JQUERY

## TECNOLOGIAS UTILIZADAS
- SPRING FRAMEWORK
- JAVA 17
- LOMBOK
- BOOTSTRAP
- THYMELEAF
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
