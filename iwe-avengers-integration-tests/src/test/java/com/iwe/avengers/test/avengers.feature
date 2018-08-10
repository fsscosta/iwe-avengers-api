Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://ktjhl7rihl.execute-api.us-east-2.amazonaws.com/dev'

Scenario: Get Avenger by Id

Given path 'avengers', 'sdsa-sasa-asas-sasa'
When method get
Then status 200
And match response == {id: '#string', name: 'Iron Man', secretIdentity: 'Tony Stark'}

Scenario: Registry a new Avenger

Given path 'avengers'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201

Scenario: Match a new Avenger

Given path 'avengers'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201
And match response == {id: '#string', name: 'Iron Man', secretIdentity: 'Tony Stark'}

Scenario: Delete a Avenger

Given path 'avengers', 'sdsa-sasa-asas-sasa'
When method delete
Then status 204

Scenario: Update Avenger by Id

Given path 'avengers', 'sdsa-sasa-asas-sasa'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method put
Then status 200

Scenario: Must return 400 foi invalid Registration Payload

Given path 'avengers'
And request {secretIdentity: 'Steve Rogers'}
When method post
Then status 400

Scenario: Update Avenger with invalid Payload

Given path 'avengers', 'sdsa-sasa-asas-sasa'
And request {secretIdentity: 'Steve Rogers'}
When method put
Then status 400
