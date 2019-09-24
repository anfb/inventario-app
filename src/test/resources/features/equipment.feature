#Author: adriely.nara@gmail.com
Feature: Management of equipments
  I want to use this API to register, delete, find em view all equipments of my inventory

  Scenario Outline: User makes call to GET /equipment
    Given user has access to the /equipment API
    When user calls GET equipment
    Then user receives status code of <status> and the list of equipments

    Examples: 
      | status |
      |    200 |   
   
  Scenario Outline: User makes call to POST /equipment
    Given user has access to the /equipment API
    When user calls POST equipments with "<modelEquipment>", <valEquipment>, <dtEquipment>, <typeEquipment>, <imageEquipment>
    Then user receives status code of <status> after register equipment

    Examples: 
      | modelEquipment | valEquipment | dtEquipment | typeEquipment | imageEquipment | status |
      | name1          |            5 |             |               |                |    201 |
    
  Scenario Outline: User makes call to DELETE /equipment/<codeEquipment>
    Given user has access to the /equipment API
    When user calls DELETE equipment <codeEquipment>
    Then user receives status code of <status> after delete equipment

    Examples: 
      | codeEquipment | status |
      |             1 |    200 |
    
  Scenario Outline: User makes call to GET /equipment/<codeEquipment>
    Given user has access to the /equipment API
    When user calls GET equipment <codeEquipment>
    Then user receives status code of <status> and one equipment

    Examples: 
      | codeEquipment | status |
      |             1 |    200 |
