# Sistema de Salão de Beleza

O projeto Sistema de Salão de Beleza é uma aplicação desenvolvida para gerenciar serviços de beleza, abrangendo desde o agendamento de serviços até a avaliação dos clientes. Utiliza uma arquitetura limpa e modular, seguindo as melhores práticas de desenvolvimento de software. O sistema é dividido em módulos para facilitar a manutenção, evolução do código, escalabilidade e flexibilidade.

## Tecnologias Adotadas

- **Java 17**: Linguagem de programação principal.
- **JSpring Framework**: Para construção de aplicativos Java, incluindo Spring Boot para microserviços, Spring Security para autenticação/autorização, e Spring Data JPA para acesso a dados.
- **JMaven**: Gerenciamento de dependências e build do projeto.
- **JDocker**: Para containerização e isolamento do ambiente de execução.
- **JMySQL**: Banco de dados relacional para persistência de dados.
- **JJUnit e Mockito**: Para testes unitários seguindo a metodologia TDD.
- **JCucumber**: Para testes BDD, permitindo a descrição de funcionalidades em linguagem natural.
- **JSpring Boot Test**: Para testes de integração.
- **JGitHub Actions**: Para Continuous Integration (CI), automatizando testes e builds.
- **JJMeter**: Para testes de desempenho.
- **JAWS ECS**: Para deploy do sistema em ambientes de cloud.
- **JavaDoc**: Para documentação do código.
- **JPostman**: Para testes de API.
- **JLombok**: Para redução de código boilerplate.
- **JModelMapper**: Para simplificar a conversão de objetos.
- **JIDE**: IntelliJ IDEA, recomendado para desenvolvimento.

## Desafios e Soluções
Durante o desenvolvimento, foram enfrentados desafios como configuração do ambiente, integração de serviços, implementação de testes, criação de pipelines de CI/CD, documentação da API, execução de testes de desempenho e implantação em ambientes AWS. Soluções foram encontradas através de tutoriais, documentações e melhores práticas da comunidade.
Este detalhamento técnico oferece uma visão geral do projeto, suas tecnologias, estrutura, e como ele é executado e testado, além de abordar os desafios encontrados e como foram superados.

## Estrutura do Projeto
O projeto é organizado em módulos principais como agendamento, profissional, especialidade, e estabelecimento, cada um responsável por uma parte específica da lógica de negócio e funcionalidades do sistema.

## Execução e Testes
O sistema pode ser executado localmente seguindo os passos de clonagem do repositório, importação como projeto Maven, execução via Docker Compose para os serviços necessários, e inicialização da aplicação principal através da IDE. A aplicação está configurada para rodar na porta 8081, conforme especificado no arquivo application.yaml.

### Módulos do Sistema

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
   git clone https://github.com/LucianneCharro/fase3.git

2. **Importe o Projeto na sua IDE:**

Abra o projeto em sua IDE favorita (por exemplo, IntelliJ IDEA, Eclipse) e importe-o como um projeto Maven.

3.**Execute o Docker Compose:**

Certifique-se de ter o Docker instalado em sua máquina. Navegue até o diretório raiz do projeto clonado e execute o seguinte comando para iniciar os contêineres do sistema:
docker-compose up -d

4.**Inicie os Serviços:**

Inicie os serviços necessários para o funcionamento do sistema executando a aplicação principal do projeto na sua IDE.

Após seguir essas etapas, o sistema estará em execução em sua máquina local e você poderá acessá-lo através dos endpoints especificados na documentação. Certifique-se de verificar se todos os serviços estão em execução corretamente antes de utilizar o sistema.

## Informações Adicionais

### Atividade Susbstitutiva da Fase 3:

- Lucianne Cristhina Ballico Charro - RM 349475

### Recursos do Projeto:

- Link do projeto no GitHub:(https://github.com/LucianneCharro/fase3)
- Documentação da API: A documentação completa da API está disponível em Javadoc, que pode ser acessada através do diretório  target/site/apidocs/index.html após a execução do projeto. mvn javadoc:javadoc
- Código Fonte: O código fonte do projeto está disponível no GitHub, permitindo que qualquer pessoa possa contribuir ou fazer fork do projeto para uso próprio.
- Testes: O projeto inclui testes unitários e de integração, garantindo a qualidade e a funcionalidade das aplicações.
- Docker: Arquivos Dockerfile e docker-compose.yml estão disponíveis para facilitar a implantação e execução do projeto em ambientes isolados.
- CI/CD: Configurações para integração e entrega contínuas estão disponíveis através do uso de GitHub Actions, automatizando o processo de build e deploy.
- AWS: O projeto está configurado para ser implantado em ambientes AWS, utilizando serviços como ECS para orquestração de contêineres.

- Este README fornece uma visão geral do sistema e das tecnologias utilizadas, além de recursos adicionais para mais informações sobre o projeto .