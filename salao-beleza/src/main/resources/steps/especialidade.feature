Feature: Especialidade management

  Scenario: Client requests all especialidades
    Given the system has especialidades
    When the client calls "/especialidades"
    Then the client receives status code of 200
    And the client receives a list of especialidades

  Scenario: Client creates a new especialidade
    Given the client has especialidades data
    When the client submits the new especialidades to "/especialidades"
    Then the client receives status code of 200
    And the client receives the created especialidades