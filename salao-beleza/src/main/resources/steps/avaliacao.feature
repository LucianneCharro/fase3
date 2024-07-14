# language: pt

Funcionalidade: Gerenciamento de Avaliações
  Como um usuário do sistema
  Eu quero ser capaz de gerenciar avaliações

  Cenário: Criar uma nova avaliação
    Dado que eu quero criar uma nova avaliação com os dados necessários
    Quando eu faço uma requisição POST para "/avaliacoes" com os dados da avaliação
    Então a avaliação deve ser criada e a resposta deve ser os detalhes da avaliação criada

  Cenário: Listar todas as avaliações
    Dado que eu quero ver todas as avaliações cadastradas
    Quando eu faço uma requisição GET para "/avaliacoes"
    Então a resposta deve ser uma lista de avaliações

  Cenário: Obter uma avaliação pelo ID
    Dado que eu quero obter informações de uma avaliação específica pelo seu ID
    Quando eu faço uma requisição GET para "/avaliacoes/{id}"
    Então a resposta deve ser os detalhes da avaliação correspondente

  Cenário: Atualizar uma avaliação existente
    Dado que eu quero atualizar os dados de uma avaliação existente pelo seu ID
    Quando eu faço uma requisição PUT para "/avaliacoes/{id}" com os novos dados da avaliação
    Então os dados da avaliação devem ser atualizados e a resposta deve ser os detalhes da avaliação atualizada

  Cenário: Deletar uma avaliação
    Dado que eu quero deletar uma avaliação existente pelo seu ID
    Quando eu faço uma requisição DELETE para "/avaliacoes/{id}"
    Então a avaliação deve ser deletada e a resposta deve ser um status de sucesso