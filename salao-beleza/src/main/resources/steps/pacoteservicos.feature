# language: pt

Funcionalidade: Gerenciamento de Pacotes de Serviços
  Como um usuário do sistema
  Eu quero ser capaz de gerenciar pacotes de serviços

  Cenário: Listar todos os pacotes de serviços
    Dado que eu quero ver todos os pacotes de serviços cadastrados
    Quando eu faço uma requisição GET para "/pacoteservicos"
    Então a resposta deve ser uma lista de pacotes de serviços

  Cenário: Obter um pacote de serviços pelo ID
    Dado que eu quero obter informações de um pacote de serviços específico pelo seu ID
    Quando eu faço uma requisição GET para "/pacoteservicos/{id}"
    Então a resposta deve ser os detalhes do pacote de serviços correspondente

  Cenário: Criar um novo pacote de serviços
    Dado que eu quero criar um novo pacote de serviços com os dados necessários
    Quando eu faço uma requisição POST para "/pacoteservicos" com os dados do pacote de serviços
    Então o pacote de serviços deve ser criado e a resposta deve ser os detalhes do pacote de serviços criado

  Cenário: Atualizar um pacote de serviços existente
    Dado que eu quero atualizar os dados de um pacote de serviços existente pelo seu ID
    Quando eu faço uma requisição PUT para "/pacoteservicos/{id}" com os novos dados do pacote de serviços
    Então os dados do pacote de serviços devem ser atualizados e a resposta deve ser os detalhes do pacote de serviços atualizado

  Cenário: Deletar um pacote de serviços
    Dado que eu quero deletar um pacote de serviços existente pelo seu ID
    Quando eu faço uma requisição DELETE para "/pacoteservicos/{id}"
    Então o pacote de serviços deve ser deletado e a resposta deve ser um status de sucesso