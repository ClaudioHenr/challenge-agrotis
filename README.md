# Desafio Agrotis

O teste consiste em construir uma API para ser utilizada na construção de uma
tela
## Endpoints

Padrão: `http://localhost:8080/`

### CRUD

| **REQUISITO** | **ENDPOINT**   | **MÉTODO HTTP** | **ENVIO (JSON)**                                       | **RETORNO**          |
| ------------- | -------------- | --------------- | ------------------------------------------------------ | -------------------- |
| create        | /person/create | POST            | objeto Pessoa    objeto Propriedade objeto Laboratorio | objeto Pessoa criada |
| list          | /person/list   | GET             |                                                        | lista de Pessoas     |
| read          | /person/{id}   | GET             |                                                        | objeto Pessoa        |
| update        | /person/{id}   | PUT             | Todas as informações                                   | objeto Pessoa        |
| delete        | /person/{id}   | DELETE          |                                                        |                      |
### Propriedade

| **REQUISITO** | **ENDPOINT**     | **MÉTODO HTTP** | **ENVIO (JSON)** | **RETORNO (JSON)** |
| ------------- | ---------------- | --------------- | ---------------- | ------------------ |
| create        | /property/create | GET             | id, nome         |                    |
| /listview     | /property/create | POST            |                  | id, nome           |
### Laboratórios

| **REQUISITO**            | **ENDPOINT**       | **MÉTODO HTTP** | **Querys obrigatórias** | **Querys opcionais**                                                                                                     | **ENVIO (JSON)** | **RETORNO (JSON)**     |
| ------------------------ | ------------------ | --------------- | ----------------------- | ------------------------------------------------------------------------------------------------------------------------ | ---------------- | ---------------------- |
| create                   | /laboratory/create | GET             |                         |                                                                                                                          | id, nome         |                        |
| /listview                | /laboratory/create | POST            |                         |                                                                                                                          |                  | id, nome               |
| Listagem de laboratórios | /laboratory/search | GET             | `minNumberPeople`       | `sortByInicialDate`<br>`findWordObservation`<br>`inicialDateIni`<br>`inicialDateFin`<br>`finalDateIni`<br>`finalDateFin` |                  | id, nome, total_person |

Considerações:
	Código do Laboratório = id
	Nome laboratório em MAIÚSCULO
	Quantidade de Pessoas cadastradas neste laboratório = total_person

#### Ordenação
**Obrigatória**:
Da **maior** quantidade de pessoas cadastradas para a **menor**

**Opcional**:
Da Data Inicial mais **antiga** para a mais **recente**
	**Ordenado apenas caso seja informado no filtro**

#### Query Strings
`minNumberPeople` (obrigatório)
`sortByInicialDate (Boolean)` (opcional)
`findWordObservation` (opcional)
`inicialDateIni` (opcional)
`inicialDateFin` (opcional)
`finalDateIni` (opcional)
`finalDateFin` (opcional)

#### Filtros da listagem de laboratórios
Os filtros foram feitos considerando a **filtragem de Laboratórios**, ou seja, a lista retorna os **Laboratórios** em que todos os filtros correspondam.

**Obrigatórios**:
	Quantidade **mínima** de pessoas cadastradas no Laboratório

**Opcionais**:
	Faixa para **Data Inicial** da Pessoa (começo e fim)
	Faixa para **Data Final** da Pessoa (começo e fim)
	Busca de palavras em **Observações**

**Os campos opcionais só farão parte da consulta caso sejam informados na
querystring do endpoint.**

## Collection Postman
Os teste foram feitos usando o Insomnia e depois importados para o Postman

Link Postman: https://www.postman.com/cryosat-observer-21830937/challenge-agrotis/collection/vnsfxus/challenge-agrotis?action=share&creator=37778986

## JSON de chamada para o serviço de cadastro de Pessoa

```
{
	nome: 'Jon doe',
	dataInicial: '2022-02-02T17:41:44Z',
	dataFinal: '2022-02-02T17:41:44Z',
	infosPropriedade: {
		id: 12345,
		nome: 'Nome Exemplo da fazenda'
	},
	laboratorio: {
		id: 1234,
		nome: 'Laboratorio exemplo'
	},
	observacoes: 'Observacao exemplo de teste'
}
```

## Diagramas
### DER
Os dados necessários são:
**Pessoa**: 
	nome
	data inicial
	data final
	Observações (sobre ou da pessoa cadastrada)

**Informações de propriedade**:
	id
	nome

**Laboratório**:
	id
	nome

### Considerações de consistência dos dados
Uma Pessoa tem apenas uma propriedade
Um Laboratório tem uma ou várias pessoas
