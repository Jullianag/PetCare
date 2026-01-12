# 🐾 PetCare API

Java • Spring Boot • REST API


# 📌 Sobre o projeto

O PetCare é uma aplicação back-end desenvolvida em Java com Spring Boot, criada como projeto pessoal com o objetivo de
praticar e consolidar conhecimentos em desenvolvimento de APIs REST, arquitetura em camadas e boas práticas de
organização de código.

O sistema é voltado para a gestão de pets, tutores e cuidados veterinários, permitindo o controle de informações
essenciais como dados do pet, vínculo com o tutor e acompanhamento de cuidados como vacinas, consultas e procedimentos.

O projeto foi estruturado de forma clara e didática, pensando tanto no aprendizado quanto em uma base sólida
para evolução futura.

# 🎯 Funcionalidades

Cadastro, atualização, listagem e remoção de Pets;

Associação de pets a seus respectivos Tutores;

Gerenciamento de cuidados (vacinas, consultas, banho & tosa, etc.);

Visualização de próximos cuidados agendados;

Estrutura preparada para autenticação e evolução futura do sistema.

## Organização em camadas:

• Controller
• Service
• Repository
• DTO
• Entity

Tratamento de exceções e validações de dados

Todas as requisições da API foram testadas utilizando Postman, abrangendo os métodos:
GET, POST, PUT e DELETE.


# 🖥️ Layout Web (Protótipo)

O projeto conta com um layout web responsivo, desenvolvido em HTML e CSS, simulando uma interface administrativa para:

- Dashboard de pets
- Visualização detalhada de um pet
- Próximos cuidados
- Organização visual voltada a sistemas de gestão

***(O front-end tem caráter demonstrativo, focado na integração com a API.)***

## Algumas imagens do que seria o Layout web

![Demonstração 1](https://github.com/Jullianag/PetCare/tree/main/src/main/resources/templates/img/inicio.png)

![Demonstração 2](https://github.com/Jullianag/PetCare/tree/main/src/main/resources/templates/img/detalhe.png)


# 🛠️ Tecnologias utilizadas

## Back end

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- JWT

## Banco de dados

- H2 (ambiente de testes)
- Postgres (Opcional)

## Ferramentas

- Postman
- IntelliJ IDEA


```bash
# clonar repositório
git clone https://github.com/Jullianag/PetCare


# executar o projeto
./mvnw spring-boot:run
```

## Back end
Pré-requisitos: Java 21