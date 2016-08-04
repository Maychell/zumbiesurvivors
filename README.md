# [ZSSN (Zombie Survival Social Network)](https://gist.github.com/akitaonrails/711b5553533d1a14364907bbcdbee677) - Java (Spring) solution

## Getting Started

* [Spring - Environment Setup](http://www.tutorialspoint.com/spring/spring_environment_setup.htm)
* Import the project to eclipse IDE

## API Resources

### Survivor Controller

Survivor Controller is responsible for add a survivor, update survivor location and list a single or every survivor. Following there are the available resources:

  - [POST /survivor](#post-survivor)
  - [PUT /survivor/[id]](#put-survivor)
  - [GET /survivor](#get-survivor)
  - [GET /survivor/[id]](#get-survivor)

#### POST /survivor

Example: Add Survivor - http://localhost:9999/ZubieSurvivalSocialNetwork/survivor

Request body:

    {
      "name": "teste",
      "age": 12,
      "gender": "f",
      "latitude": "324324",
      "longitude": "43243",
      "infected": false
    }

Response body:

    {
      "id": 16,
      "name": "teste",
      "age": 12,
      "gender": "f",
      "latitude": "324324",
      "longitude": "43243",
      "infected": false
    }
  
#### PUT /survivor

  Example: Set Survivor's Location - http://localhost:9999/ZubieSurvivalSocialNetwork/survivor/[id]

  Request body:
  
    {
      "latitude": "123",
      "longitude": "123"
    }
  
  Response body:
  
    {
      "id": 16,
      "name": "teste",
      "age": 12,
      "gender": "f",
      "latitude": "123",
      "longitude": "123",
      "infected": false
    }

#### GET /survivor

  Example: List Survivors - http://localhost:9999/ZubieSurvivalSocialNetwork/survivor
  
  Response body:
  
    [
      {
        "id": 1,
        "name": "dsadad",
        "age": 12,
        "gender": "m",
        "latitude": "123",
        "longitude": "123",
        "infected": true
      },
      {
        "id": 2,
        "name": "dsadad",
        "age": 12,
        "gender": "m",
        "latitude": "123",
        "longitude": "123",
        "infected": true
      }
    ]
    
#### GET /survivor/{id}

  Example: List Survivor - http://localhost:9999/ZubieSurvivalSocialNetwork/survivor/[id]
  
  Response body:
  
    {
      "id": 1,
      "name": "dsadad",
      "age": 12,
      "gender": "m",
      "latitude": "123",
      "longitude": "123",
      "infected": true
    }
    
### Complaint Controller

Complaint Controller is responsible for register a complaint  of survivor infection. By having three complaints, the survivor is flagged as infected. Following there is the available resource:

  - [POST /complaint](#post-complaint)

#### POST /complaint

Example: Complaint a Survivor - http://localhost:9999/ZubieSurvivalSocialNetwork/complaint

Request body:

    {
      "id": "12"
    }

Response body:

    {
      "message": "The survivor has 1 complaint(s)."
    }
    
### Item Controller

Item Controller is responsible for add an item, update an item and list a single or every item. Following there are the available resources:

  - [POST /item](#post-item)
  - [PUT /item/[id]](#put-item)
  - [GET /item](#get-item)
  - [GET /item/[id]](#get-item)

#### POST /item

Example: Add Item - http://localhost:9999/ZubieSurvivalSocialNetwork/item

Request body:

    {
      "name": "Ammunition",
      "points": 1
    }

Response body:

    {
      "id": 5,
      "name": "Ammunition",
      "points": 1
    }
  
#### PUT /item

  Example: Update an Item - http://localhost:9999/ZubieSurvivalSocialNetwork/item/[id]

  Request body:
  
    {
      "name": "Ammunition",
      "points": 1
    }
  
  Response body:
  
    {
      "id": 5,
      "name": "Ammunition",
      "points": 1
    }

#### GET /item

  Example: List Items - http://localhost:9999/ZubieSurvivalSocialNetwork/item
  
  Response body:
  
    [
      {
        "id": 1,
        "name": "Water",
        "points": 4
      },
      {
        "id": 2,
        "name": "Food",
        "points": 3
      }
    ]
    
#### GET /item/{id}

  Example: List an Item - http://localhost:9999/ZubieSurvivalSocialNetwork/survivor/[id]
  
  Response body:
  
    {
      "id": 1,
      "name": "Water",
      "points": 4
    }

### Inventory Controller

Inventory Controller is responsible for list inventories items or show an inventory info. Following there are the available resources:

  - [GET /inventory](#get-inventory)
  - [GET /inventory/[id]](#get-inventory)

#### GET /inventory

Example: List survivor's inventory - http://localhost:9999/ZubieSurvivalSocialNetwork/inventory

Response body:

    [
      {
        "id": 2,
        "survivor": {
          "id": 14,
          "name": "teste",
          "age": 12,
          "gender": "f",
          "latitude": "324324",
          "longitude": "43243",
          "infected": false
        },
        "item": {
          "id": 1,
          "name": "Water",
          "points": 4
        }
      },
      {
        "id": 11,
        "survivor": {
          "id": 16,
          "name": "teste",
          "age": 12,
          "gender": "f",
          "latitude": "324324",
          "longitude": "43243",
          "infected": false
        },
        "item": {
          "id": 1,
          "name": "Water",
          "points": 4
        }
      }
    ]
  
#### GET /inventory{id}

  Example: List an Inventory - http://localhost:9999/ZubieSurvivalSocialNetwork/inventory/[id]
  
  Response body:
  
    {
      "id": 1,
      "survivor": {
        "id": 16,
        "name": "teste",
        "age": 12,
        "gender": "f",
        "latitude": "324324",
        "longitude": "43243",
        "infected": false
      },
      "item": {
        "id": 1,
        "name": "Water",
        "points": 4
      }
    }
    
### Report Controller

Report Controller is responsible for report percentage of infected and non-infected survivors, report the average amount of each item per survivor and report the number of points lost because of infection. Following there are the available resources:

  - [GET /reportInfected](#get-reportInfected)
  - [GET /reportInfected](#get-reportInfected)
  - [GET /reportPointsLost](#get-reportPointsLost)
  - [GET /reportItemsAverage](#get-reportItemsAverage)

#### GET /reportInfected

Example: Report infected percentage - http://localhost:9999/ZubieSurvivalSocialNetwork/reportInfected

Response body:

    {
      "report": "26"
    }
  
#### GET /reportNotInfected

Example: Report non-infected percentage - http://localhost:9999/ZubieSurvivalSocialNetwork/reportNotInfected

Response body:

    {
      "report": "74"
    }

#### GET /reportPointsLost

Example: Report non-infected percentage - http://localhost:9999/ZubieSurvivalSocialNetwork/reportPointsLost

Response body:

    {
      "report": "40"
    }

#### GET /reportItemsAverage

Example: Report average amount of each item - http://localhost:9999/ZubieSurvivalSocialNetwork/reportItemsAverage

Response body:

    "report": {
      "Water": 0.125,
      "Ammunition": 0,
      "Medication": 0.25,
      "Food": 0.25
    }
