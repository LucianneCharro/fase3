Feature: Servico management

  Scenario: Client requests all services
    Given the system has services
    When the client calls "/servicos"
    Then the client receives status code of 200
    And the client receives a list of services

  Scenario: Client creates a new service
    Given the client has service data
    When the client submits the new service to "/servicos"
    Then the client receives status code of 200
    And the client receives the created service