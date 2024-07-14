# Sistema de Salão de Beleza

Este projeto é um Sistema de Salão de Beleza desenvolvido para atender gerenciar serviços de beleza, oferecendo uma solução completa para agendamento de serviços relacionados à beleza e bem-estar. Utilizando tecnologias modernas como Java 17, Spring, Docker, Testes Unitários (TDD), BDD, Testes de Integração e Continuous Integration (CI), Testes Não Funcionais, Ambientes Deploy ECS AWS este sistema abrange desde o agendamento de serviços até a avaliação dos clientes, desenvolvido com base em uma arquitetura limpa e modular, seguindo as melhores práticas de desenvolvimento de software. O sistema foi dividido em módulos para facilitar a manutenção e a evolução do código, além de garantir a escalabilidade e a flexibilidade do sistema.

## Tecnologias Adotadas

- **Java 17**: Linguagem de programação utilizada para o desenvolvimento do sistema.
- **Spring**: Framework utilizado para a construção de aplicativos Java.
- **Docker**: Plataforma de software que permite a criação, o envio e a execução de aplicativos em contêineres.
- **MySQL**: Banco de dados relacional utilizado para armazenar outras informações do sistema.
- **Testes Unitários (TDD)**: Cobertura de testes com Junit e Mockito.
- **BDD**: Desenvolvimento orientado a comportamento com Cucumber.
- **Testes de Integração**: Testes de integração com Spring Boot Test.
- **Continuous Integration (CI)**: Integração contínua com GitHub Actions.
- **Testes Não Funcionais**: Testes de desempenho com JMeter.
- **Ambientes Deploy ECS AWS**: Deploy do sistema em ambientes AWS.
- **Swagger**: Documentação da API com Swagger.
- **Postman**: Testes de API com Postman.
- **Git**: Sistema de controle de versão distribuído.
- **GitHub**: Plataforma de hospedagem de código-fonte.
- **IDE**: IntelliJ IDEA.
- **Maven**: Gerenciador de dependências.
- **Lombok**: Biblioteca Java que ajuda a reduzir o código boilerplate.
- **ModelMapper**: Framework Java que simplifica a conversão de objetos.
- **Spring Security**: Framework Java que fornece autenticação e autorização.
- **Spring Web**: Framework Java que fornece suporte para aplicativos web.
- **Spring Boot Actuator**: Framework Java que fornece recursos para monitoramento e gerenciamento de aplicativos.
- **Spring Boot DevTools**: Framework Java que fornece ferramentas de desenvolvimento.
- **Spring Boot Test**: Framework Java que fornece suporte para testes de aplicativos.
- **Spring Boot Validation**: Framework Java que fornece validação de dados.
- **Spring Boot Starter Data JPA**: Framework Java que fornece suporte para acesso a dados.
- **Cloud AWS**: Framework Java que fornece suporte para serviços em nuvem.
- **Cucumber**: Framework Java que fornece suporte para BDD.
- **Clean Architecture**: Arquitetura de software que separa as responsabilidades em camadas.

## Dificuldades Encontradas
Muitas dificuldades foram encontradas durante o desenvolvimento do projeto, como a configuração do ambiente de desenvolvimento, a integração de serviços, a implementação de testes, a criação de pipelines de CI/CD, a documentação da API, a execução de testes de desempenho e a implantação do sistema em ambientes AWS. No entanto, muitas dessas dificuldades foram superadas com a ajuda de tutoriais e documentações.

## Estrutura do Projeto
A estrutura do projeto foi organizada de acordo com os princípios da arquitetura limpa, seguindo as melhores práticas de desenvolvimento de software. O projeto foi dividido em módulos para facilitar a manutenção e a evolução do código, além de garantir a escalabilidade e a flexibilidade do sistema. A estrutura do projeto é composta por quatro módulos principais: `agendamento`, `profissional`, `especialidade` e `estabelecimento`.

### Módulos do Sistema

### 1. Especialidade

Comandos curls para teste em linha de comando:

### Este módulo permite o cadastramento da especialidade o preço e descrição.

<p></p>
<p>Criar Especialidade</p>

curl --request POST \
  --url http://localhost:8081/especialidade \
  --header 'Content-Type: application/json' \
  --data '{
"descricao": "Descrição da especialidade",
"preco": 50.00
}'

<p>Listar Especialidades</p>

curl --request GET \
--url http://localhost:8081/especialidade \
--header 'Content-Type: application/json'

<p>Listar Especialidade por ID</p>

curl --request GET \
--url http://localhost:8081/especialidade/1 \
--header 'Content-Type: application/json'

<p>Alterar Especialidade</p>

curl --request PUT \
--url http://localhost:8081/especialidade/1 \
--header 'Content-Type: application/json' \
--data '{
"descricao": "Descrição da especialidade",
"preco": 50.00
}'

<p>Excluir Especialidade por Id</p>

curl --request DELETE \
--url http://localhost:8081/especialidade/3 \
--header 'Content-Type: application/json'

### Este módulo permite o cadastramento do profissional, suas especialidades e os horários disponíveis.

<p>Criar Profissional associado as suas especialidades</p>

curl --request POST \
--url http://localhost:8081/profissional \
--header 'Content-Type: application/json' \
--data '{
"nome": "Lucianne",
"email": "lucianne_Ballico@gmail.com",
"telefone": "997117525",
"especialidades": [
{
"id": 1,
"descricao": "Serviço 1",
"preco": 10
},
{
"id": 2,
"descricao": "Serviço 2",
"preco": 20
}
],
"horariosDisponiveis": "10.10.2024"
}'

<p>Listar Profissionais</p>

curl --request GET \
--url http://localhost:8081/profissional \
--header 'Content-Type: application/json'

<p>Buscar Profissional  por ID</p>

curl --request GET \
--url http://localhost:8081/profissional/2 \
--header 'Content-Type: application/json'

<p>Alterar Profissional</p>

curl --request PUT \
--url http://localhost:8081/profissional/1 \
--header 'Content-Type: application/json' \
--data '{
"nome": "Lucianne",
"email": "lucas@cgmail.com",
"telefone": "997117525",
"especialidades": [
{
"id": 1,
"descricao": "Serviço 1",
"preco": 10
},
{
"id": 2,
"descricao": "Serviço 2",
"preco": 20
}
]
}'

<p>Excluir Profissional por ID</p>

curl --request DELETE \
--url http://localhost:8081/profissional/2 \
--header 'Content-Type: application/json'

### Este módulo permite o cadastramento do cliente com todos os seus dados.

<p>Criar Cliente</p>

curl --request POST \
--url http://localhost:8081/clientes \
--header 'Content-Type: application/json' \
--data '{
"nome": "Nome do Cliente 44",
"email": "cliente@example.com",
"telefone": "123456789"
}'

<p>Listar Clientes</p>

curl --request GET \
--url http://localhost:8081/clientes \
--header 'Content-Type: application/json'

<p>Buscar Cliente por ID</p>

curl --request GET \
--url http://localhost:8081/clientes \
--header 'Content-Type: application/json'

<p>Atualizar Cliente</p>

curl --request PUT \
--url http://localhost:8081/clientes/1 \
--header 'Content-Type: application/json' \
--data '{
"nome": "Lucianne",
"email": "luballico@gmail.com",
"telefone": "996117525"
}'

<p>Excluir Cliente por ID</p>	

curl --request DELETE \
--url http://localhost:8081/clientes/2 \
--header 'Content-Type: application/json'

### Este módulo permite o cadastramento de avaliação dos clientes para um profissional, com nota mencionando o profissional que esta sendo avaliado.

<p>Criar Avaliação do profissional</p>

curl --request POST \
--url http://localhost:8081/avaliacoes \
--header 'Content-Type: application/json' \
--data '{
"cliente_id": {
"id": 1
},
"profissional_id": {
"id": 2
},
"nota": 5,
"comentario": "Excelente serviço e atendimento."
}'

### Criação de Pacote de serviços com cadastramento de cada especialidade preço de cada uma e o valor total com dsconto

<p>Criar Pacote de Serviços</p>

curl --request POST \
--url http://localhost:8081/pacoteservicos \
--header 'Content-Type: application/json' \
--data '{
"nome": "Pacote Premium",
"especialidades": [
{
"id": 1
},
{
"id": 2
}
]
}'

<p>Listar Pacote de Serviços</p>

curl --request GET \
--url http://localhost:8081/pacoteservicos \
--header 'Content-Type: application/json'

<p>Listar Pacote de Serviços por Id</p>

curl --request GET \
--url http://localhost:8081/pacoteservicos/1 \
--header 'Content-Type: application/json'

<p>Excluir Pacote de Serviços por ID</p>

curl --request DELETE \
--url http://localhost:8081/pacoteservicos/1 \
--header 'Content-Type: application/json'

<p>Atualizar Pacote de Serviços</p>

curl --request PUT \
--url http://localhost:8081/pacoteservicos/1 \
--header 'Content-Type: application/json' \
--data '{
"nome": "Pacote Premium",
"especialidades": [
{
"id": 1
},
{
"id": 2
}
]
}'

### Gestão de Reservas, responsável por gerenciar o cadastro de reservas de serviços de beleza com base no cliente, profissional escolhido, especiliadade e data e hora do agendamento não podendo realizar o agendamento no mesmo horário com intervalo de 1 horas.

<p>Criar Agendamento</p>

curl --request POST \
--url http://localhost:8081/agendamentos \
--header 'Content-Type: application/json' \
--data '{
"cliente": {
"id": 1
},
"profissional": {
"id": 1
},
"especialidade": {
"id": 1
},
"pacoteservicos": {
"id": 1
},
"dataHora": "2024-07-11T19:00:00"
}'

<p>Buscar todos os Agendamentos</p>

curl --request GET \
--url http://localhost:8081/agendamentos \
--header 'Content-Type: application/json'

<p>Buscar Agendamento por ID</p>

curl --request GET \
--url http://localhost:8081/agendamentos/1 \
--header 'Content-Type: application/json'

<p>Excluir Agendamento</p>

curl --request DELETE \
--url http://localhost:8081/agendamentos/1 \
--header 'Content-Type: application/json'

<p>Alterar Agendamento</p>

curl --request PUT \
--url http://localhost:8081/agendamentos/2 \
--header 'Content-Type: application/json' \
--data '{
"cliente": {
"id": 1
},
"profissional": {
"id": 1
},
"servico": {
"id": 1
},
"pacoteservicos": {
"id": 1
},
"dataHora": "2021-09-30T10:00:00"
}'

### Gestão de estabelecimentos com o cadastro do endereço, suas especialidades de atendimento, profissionais disponíveis, horário de atendimento, preço de cada profissional e horário de funcionamento do estabelecimento com fotos.

<p>Criar Estabelecimento</p>

curl --request POST \
--url http://localhost:8081/estabelecimentos \
--header 'Content-Type: application/json' \
--data '{
"nome": "Salão da Lúcia",
"endereco": "Rua das Flores, 123",
"servicosOferecidosIds": [1, 2],
"profissionaisDisponiveisIds": [1, 2],
"horarioFuncionamento": "09:00 - 18:00",
"fotos": ["urlFoto1.jpg", "urlFoto2.jpg"]
}'

<p>Buscar todos os Estabelecimentos</p>

curl --request GET \
--url http://localhost:8081/estabelecimentos \
--header 'Content-Type: application/json'

<p>Buscar Estabelecimento por ID</p>

curl --request GET \
--url 'http://localhost:8081/estabelecimentos/buscar?nome=Sal%C3%A3o%20&endereco=Rua%20' \
--header 'Content-Type: application/json'

<p>Excluir Estabelecimento</p>

curl --request DELETE \
--url http://localhost:8081/estabelecimentos/1 \
--header 'Content-Type: application/json'

<p>Alterar Estabelecimento</p>

curl --request PUT \
--url http://localhost:8081/estabelecimentos/1 \
--header 'Content-Type: application/json' \
--data '{
"nome": "Salão da Lúcia",
"endereco": "Rua das Flores, 123",
"servicosOferecidosIds": [1, 2],
"profissionaisDisponiveisIds": [1, 2],
"horarioFuncionamento": "09:00 - 18:00",
"fotos": ["urlFoto1.jpg", "urlFoto2.jpg"]
}'

## Como Executar o Projeto

Siga as etapas abaixo para executar o projeto em sua máquina local:

1. **Clone o Repositório:
2. **Importe o Projeto na sua IDE:**

Abra o projeto em sua IDE favorita (por exemplo, IntelliJ IDEA, Eclipse) e importe-o como um projeto Maven.

3.**Execute o Docker Compose:**

Certifique-se de ter o Docker instalado em sua máquina. Navegue até o diretório raiz do projeto clonado e execute o seguinte comando para iniciar os contêineres do sistema:

4.**Inicie os Serviços:**

Inicie os serviços necessários para o funcionamento do sistema executando a aplicação principal do projeto na sua IDE.

Após seguir essas etapas, o sistema estará em execução em sua máquina local e você poderá acessá-lo através dos endpoints especificados na documentação. Certifique-se de verificar se todos os serviços estão em execução corretamente antes de utilizar o sistema.

## Informações Adicionais

### Atividade Susbstitutiva da Fase 3:

- Lucianne Cristhina Ballico Charro - RM 349475

### Recursos do Projeto:

- Link do projeto no GitHub:(https://github.com/LucianneCharro/fase3)

Este README fornece uma visão geral do sistema e das tecnologias utilizadas, além de recursos adicionais para mais informações sobre o projeto .