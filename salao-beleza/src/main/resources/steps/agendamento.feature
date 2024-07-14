# language: pt

Funcionalidade: Gerenciamento de Agendamentos
  Como um usuário do sistema
  Eu quero ser capaz de gerenciar agendamentos

  Cenário: Criar um novo agendamento
    Dado que eu quero criar um novo agendamento com os dados necessários
    Quando eu faço uma requisição POST para "/agendamentos" com os dados do agendamento
    Então o agendamento deve ser criado e a resposta deve ser os detalhes do agendamento criado

  Cenário: Listar todos os agendamentos
    Dado que eu quero ver todos os agendamentos cadastrados
    Quando eu faço uma requisição GET para "/agendamentos"
    Então a resposta deve ser uma lista de agendamentos

  Cenário: Obter um agendamento pelo ID
    Dado que eu quero obter informações de um agendamento específico pelo seu ID
    Quando eu faço uma requisição GET para "/agendamentos/{id}"
    Então a resposta deve ser os detalhes do agendamento correspondente

  Cenário: Atualizar um agendamento existente
    Dado que eu quero atualizar os dados de um agendamento existente pelo seu ID
    Quando eu faço uma requisição PUT para "/agendamentos/{id}" com os novos dados do agendamento
    Então os dados do agendamento devem ser atualizados e a resposta deve ser os detalhes do agendamento atualizado

  Cenário: Deletar um agendamento
    Dado que eu quero deletar um agendamento existente pelo seu ID
    Quando eu faço uma requisição DELETE para "/agendamentos/{id}"
    Então o agendamento deve ser deletado e a resposta deve ser um status de sucesso