# Rest Calls

## City
### Get all cities
**URL**: /cities
**Method**: GET
**Example Request**: /cities 
**Example Response**:
```[
  {
    "id": 2,
    "name": "Köln"
  },
  {
    "id": 3,
    "name": "Gummersbach"
  },
  {
    "id": 5,
    "name": "Dortmund"
  },
  {
    "id": 7,
    "name": "Krefeld"
  },
  {
    "id": 8,
    "name": "Hagen"
  }
]```

### Get City by Id
**URL**: /cities/*id*
**Method**: GET
**Example Request**: /cities/2
**Example Response**:
```{
  "id": 2,
  "name": "Köln"
}```

### Create City (or get existing with same name)
**URL**: /cities/create
**Method**: POST
**Example Request**: /cities/create
```{
  "name": "Köln"
}```
**Example Response**:
```{
  "id": 2,
  "name": "Köln"
}```

## Address
### Get Address by Id
**URL**: /addresses/*id*
**Method**: GET
**Example Request**: /addresses/1
**Example Response**:
```{
  "id": 1,
  "roadName": "Bachstraße",
  "houseNumber": "25",
  "postCode": "51063",
  "latitude": 50.9622,
  "longitude": 6.999369,
  "city": {
    "id": 2,
    "name": "Köln"
  }
}```

### Update Address
**URL**: /addresses/*id*
**Method**: PUT
**Example Request**: /addresses/1
```{
  "id": 1,
  "roadName": "Bachstraße",
  "houseNumber": "25",
  "postCode": "51063",
  "latitude": 50.9622,
  "longitude": 6.999369,
  "city": 2
}```
**Example Response**:
```{
  "id": 1,
  "roadName": "Bachstraße",
  "houseNumber": "25",
  "postCode": "51063",
  "latitude": 50.9622,
  "longitude": 6.999369,
  "city": {
    "id": 2,
    "name": "Köln"
  }
}```


### Delete Address
**URL**: /addresses/*id*
**Method**: DELETE
**Example Request**: /addresses/1
**Example Response**:
(Some success message)

### Create Address (or get existing with same roadName, houseNumber, cityId and postCode)
**URL**: /addresses/create
**Method**: POST
**Example Request**: /addresses/create
```{
  "roadName": "Bachstraße",
  "houseNumber": "25",
  "postCode": "51063",
  "latitude": 50.9622,
  "longitude": 6.999369,
  "city": 2
}```
**Example Response**:
```{
  "id": 1,
  "roadName": "Bachstraße",
  "houseNumber": "25",
  "postCode": "51063",
  "latitude": 50.9622,
  "longitude": 6.999369,
  "city": {
    "id": 2,
    "name": "Köln"
  }
}```

## User
### Get User by Id
**URL**: /users/*id*
**Method**: GET
**Example Request**: /users/1
**Example Response**:
```{
  "id": 1,
  "userName": "Laura",
  "userRole": "Grün",
  "city": {
    "id": 2,
    "name": "Köln"
  },
  "lastActivity": 1494711324000,
  "commentCount": 0,
  "avatar": {
    "id": 1,
    "type": "png",
    "fileDir": "test",
    "name": "20170513-test"
  }
}```

### Delete User
**URL**: /users/*id*
**Method**: DELETE
**Example Request**: /users/1
**Example Response**:
(Some success message)

### Create User (or get existing with same userName)
**URL**: /users/create
**Method**: POST
**Example Request**: /users/create
```{
	"userName": "Hannah",
	"password": "secret",
	"city": 2,
	"avatar": 1
}```

**Example Response**:
```{
	"id": 10,
	"userName": "Hannah",
	"userRole": "Grüner Daumen",
	"city":{
		"id": 2,
		"name": "Köln"
	},
	"lastActivity": 1494716852000,
	"commentCount": 0,
	"avatar":{
		"id": 1,
		"type": "png",
		"fileDir": "test",
		"name": "20170513-test"
	}
}```

## Spot
### Get Spot by Id
**URL**: /spots/*id*
**Method**: GET
**Example Request**: /spots/1
**Example Response**:
```{
  "id": 1,
  "name": "Gemüsegarten",
  "description": "Bau dein eigenes Gemüse an.",
  "size": 32,
  "owner": {
    "id": 1,
    "userName": "Laura",
    "userRole": "Grün",
    "city": {
      "id": 2,
      "name": "Köln"
    },
    "lastActivity": 1494711324000,
    "commentCount": 0,
    "avatar": {
      "id": 1,
      "type": "png",
      "fileDir": "test",
      "name": "20170513-test"
    }
  },
  "address": {
    "id": 1,
    "roadName": "Bachstraße",
    "houseNumber": "25",
    "postCode": "51063",
    "latitude": 50.9622,
    "longitude": 6.999369,
    "city": {
      "id": 2,
      "name": "Köln"
    }
  }
}```

### Delete Spot
**URL**: /spots/*id*
**Method**: DELETE
**Example Request**: /spots/1
**Example Response**:
(Some success message)

### Create User (or get existing with same userName)
**URL**: /spots/create
**Method**: POST
**Example Request**: /spots/create
```{
  "name": "Ringgarten",
  "description": "Frohe Natur direkt am Hansaring.",
  "size": 32,
  "owner": 8,
  "address": 3
}```

**Example Response**:
```{
  "id": 2,
  "name": "Ringgarten",
  "description": "Frohe Natur direkt am Hansaring.",
  "size": 32,
  "owner": {
    "id": 8,
    "userName": "Ali",
    "userRole": "Grün",
    "city": {
      "id": 2,
      "name": "Köln"
    },
    "lastActivity": 1494711324000,
    "commentCount": 0,
    "avatar": {
      "id": 1,
      "type": "png",
      "fileDir": "test",
      "name": "20170513-test"
    }
  },
  "address": {
    "id": 3,
    "roadName": "Hansaring",
    "houseNumber": "135",
    "postCode": "50670",
    "latitude": 50.949932,
    "longitude": 6.95511,
    "city": {
      "id": 2,
      "name": "Köln"
    }
  }
}```
