# language: pt

Funcionalidade: Gerenciamento de Profissionais
  Como um usuário do sistema
  Eu quero ser capaz de gerenciar profissionais

  Cenário: Listar todos os profissionais
    Dado que eu quero ver todos os profissionais cadastrados
    Quando eu faço uma requisição GET para "/profissional"
    Então a resposta deve ser uma lista de profissionais

  Cenário: Obter um profissional pelo ID
    Dado que eu quero obter informações de um profissional específico pelo seu ID
    Quando eu faço uma requisição GET para "/profissional/{id}"
    Então a resposta deve ser os detalhes do profissional correspondente

  Cenário: Criar um novo profissional
    Dado que eu quero criar um novo profissional com os dados necessários
    Quando eu faço uma requisição POST para "/profissional" com os dados do profissional
    Então o profissional deve ser criado e a resposta deve ser os detalhes do profissional criado

  Cenário: Atualizar um profissional existente
    Dado que eu quero atualizar os dados de um profissional existente pelo seu ID
    Quando eu faço uma requisição PUT para "/profissional/{id}" com os novos dados do profissional
    Então os dados do profissional devem ser atualizados e a resposta deve ser os detalhes do profissional atualizado

  Cenário: Deletar um profissional
    Dado que eu quero deletar um profissional existente pelo seu ID
    Quando eu faço uma requisição DELETE para "/profissional/{id}"
    Então o profissional deve ser deletado e a resposta deve ser um status de sucesso