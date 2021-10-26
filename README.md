# API de logística

Uma pequena aplicação de entrega de mercadorias, utilizando endpoints para operações cruds e não cruds. 

Com esse projeto foi possivel aprender os fundamentos essenciais de REST
- REST API usando projetos do ecossistema Spring
- Persistência de dados no projeto usando Jakarta Persistence, Spring Data JPA e Flyway.
- Técnicas e boas práticas para facilitar a manutenção de API's.

## Base URL
Projeto para estudos, inicie o projeto na sua maquina e utilize a base url local:


http://localhost:8080/

---

# Services
## **GET** /clientes

*Lista todos os clientes cadastrados*

## **POST** /clientes

*Postagem de um novo cliente*

### Parameters
Parameter | Type | Data Type | Required
| --- | --- | --- | --- |
| nome | query | string | YES |
| email | query | string | YES |
| telefone | query | string | YES |

## **PUT** /clientes/clienteId

*Faz a atualização de um cliente*

## **DELET** /clientes/clienteId

*Remove um cliente*
