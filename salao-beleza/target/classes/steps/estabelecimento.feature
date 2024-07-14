Funcionalidade: Gerenciamento de Estabelecimentos
  Como um usuário do sistema
  Eu quero adicionar, listar, atualizar e deletar estabelecimentos
  Para gerenciar informações sobre estabelecimentos de beleza e bem-estar

  Cenário: Listar todos os estabelecimentos
    Dado que eu quero ver todos os estabelecimentos cadastrados
    Quando eu solicito a lista de estabelecimentos
    Então todos os estabelecimentos devem ser retornados

  Cenário: Adicionar um novo estabelecimento
    Dado que eu quero adicionar um novo estabelecimento
    Quando eu envio os detalhes do estabelecimento
    Então o estabelecimento é adicionado ao sistema

  Cenário: Atualizar um estabelecimento existente
    Dado que eu quero atualizar um estabelecimento existente
    Quando eu envio os detalhes atualizados do estabelecimento
    Então o estabelecimento é atualizado no sistema

  Cenário: Deletar um estabelecimento
    Dado que eu quero deletar um estabelecimento
    Quando eu solicito a deleção do estabelecimento
    Então o estabelecimento é removido do sistema