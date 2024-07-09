# language: pt

Funcionalidade: Gerenciamento de Clientes
  Como um usuário do sistema
  Eu quero ser capaz de gerenciar clientes

  Cenário: Criar um novo cliente
    Dado que eu quero criar um novo cliente com os dados necessários
    Quando eu faço uma requisição POST para "/clientes" com os dados do cliente
    Então o cliente deve ser criado e a resposta deve ser os detalhes do cliente criado

  Cenário: Listar todos os clientes
    Dado que eu quero ver todos os clientes cadastrados
    Quando eu faço uma requisição GET para "/clientes"
    Então a resposta deve ser uma lista de clientes

  Cenário: Obter um cliente pelo ID
    Dado que eu quero obter informações de um cliente específico pelo seu ID
    Quando eu faço uma requisição GET para "/clientes/{id}"
    Então a resposta deve ser os detalhes do cliente correspondente

  Cenário: Atualizar um cliente existente
    Dado que eu quero atualizar os dados de um cliente existente pelo seu ID
    Quando eu faço uma requisição PUT para "/clientes/{id}" com os novos dados do cliente
    Então os dados do cliente devem ser atualizados e a resposta deve ser os detalhes do cliente atualizado

  Cenário: Deletar um cliente
    Dado que eu quero deletar um cliente existente pelo seu ID
    Quando eu faço uma requisição DELETE para "/clientes/{id}"
    Então o cliente deve ser deletado e a resposta deve ser um status de sucesso