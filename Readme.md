# Project: Maju Mundur API
API for Maju Mundur Application

## ERD

![ERD](/erd.drawio.png)


## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  cd my-project
```

Install dependencies

```bash
  mvn install
```

Start the server

```bash
  mvn spring-boot:run
```

Start the swagger ui
```bash
  http://localhost:8080/swagger-ui.html
```

## Open with your idea or vscode

# 📁 Collection: api

## 📁 Collection: {id}


## End-point: Get reward by id
### Method: GET
>```
>{{baseUrl}}/api/rewards/:id
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "id": "<string>",
    "name": "<string>",
    "pointRequired": "<integer>",
    "stock": "<integer>"
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Get all rewards
### Method: GET
>```
>{{baseUrl}}/api/rewards
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": [
    {
      "id": "<string>",
      "name": "<string>",
      "pointRequired": "<integer>",
      "stock": "<integer>"
    },
    {
      "id": "<string>",
      "name": "<string>",
      "pointRequired": "<integer>",
      "stock": "<integer>"
    }
  ],
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Update reward
### Method: PUT
>```
>{{baseUrl}}/api/rewards?id=<string>&name=<string>&pointRequired=<integer>&stock=<integer>
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Query Params

|Param|value|
|---|---|
|id|<string>|
|name|<string>|
|pointRequired|<integer>|
|stock|<integer>|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "id": "<string>",
    "name": "<string>",
    "pointRequired": "<integer>",
    "stock": "<integer>"
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Create new reward
### Method: POST
>```
>{{baseUrl}}/api/rewards
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Body (**raw**)

```json
{
  "name": "<string>",
  "pointRequired": "<integer>",
  "stock": "<integer>"
}
```

### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "id": "<string>",
    "name": "<string>",
    "pointRequired": "<integer>",
    "stock": "<integer>"
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
## 📁 Collection: {id}


## End-point: Get product by id
### Method: GET
>```
>{{baseUrl}}/api/products/:id
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "id": "<string>",
    "name": "<string>",
    "price": "<long>",
    "stock": "<integer>",
    "merchant": {
      "id": "<string>",
      "shopName": "<string>",
      "phone": "<string>",
      "address": "<string>"
    }
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Delete product
### Method: DELETE
>```
>{{baseUrl}}/api/products/:id?userAccountId=<string>
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Query Params

|Param|value|
|---|---|
|userAccountId|<string>|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": "<string>",
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Get all products
### Method: GET
>```
>{{baseUrl}}/api/products?direction=asc&orderBy=name&currentPage=1&pageSize=10
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Query Params

|Param|value|
|---|---|
|direction|asc|
|orderBy|name|
|currentPage|1|
|pageSize|10|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": [
    {
      "id": "<string>",
      "name": "<string>",
      "price": "<long>",
      "stock": "<integer>",
      "merchant": {
        "id": "<string>",
        "shopName": "<string>",
        "phone": "<string>",
        "address": "<string>"
      }
    },
    {
      "id": "<string>",
      "name": "<string>",
      "price": "<long>",
      "stock": "<integer>",
      "merchant": {
        "id": "<string>",
        "shopName": "<string>",
        "phone": "<string>",
        "address": "<string>"
      }
    }
  ],
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Update product
### Method: PUT
>```
>{{baseUrl}}/api/products
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Body (**raw**)

```json
{
  "id": "<string>",
  "name": "<string>",
  "userAccountId": "<string>",
  "price": "<long>",
  "stock": "<integer>"
}
```

### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "id": "<string>",
    "name": "<string>",
    "price": "<long>",
    "stock": "<integer>",
    "merchant": {
      "id": "<string>",
      "shopName": "<string>",
      "phone": "<string>",
      "address": "<string>"
    }
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Create new product
### Method: POST
>```
>{{baseUrl}}/api/products
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Body (**raw**)

```json
{
  "name": "<string>",
  "userAccountId": "<string>",
  "price": "<long>",
  "stock": "<integer>"
}
```

### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "id": "<string>",
    "name": "<string>",
    "price": "<long>",
    "stock": "<integer>",
    "merchant": {
      "id": "<string>",
      "shopName": "<string>",
      "phone": "<string>",
      "address": "<string>"
    }
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
## 📁 Collection: history


## End-point: Get Merchant History Transaction
### Method: GET
>```
>{{baseUrl}}/api/transactions/history
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": [
    {
      "id": "<string>",
      "transactionDate": "<string>",
      "point": "<integer>",
      "customerId": "<string>",
      "details": [
        {
          "id": "<string>",
          "quantity": "<integer>",
          "price": "<long>",
          "productId": "<string>"
        },
        {
          "id": "<string>",
          "quantity": "<integer>",
          "price": "<long>",
          "productId": "<string>"
        }
      ]
    },
    {
      "id": "<string>",
      "transactionDate": "<string>",
      "point": "<integer>",
      "customerId": "<string>",
      "details": [
        {
          "id": "<string>",
          "quantity": "<integer>",
          "price": "<long>",
          "productId": "<string>"
        },
        {
          "id": "<string>",
          "quantity": "<integer>",
          "price": "<long>",
          "productId": "<string>"
        }
      ]
    }
  ],
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Get all transactions
### Method: GET
>```
>{{baseUrl}}/api/transactions
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": [
    {
      "id": "<string>",
      "transactionDate": "<string>",
      "point": "<integer>",
      "customerId": "<string>",
      "details": [
        {
          "id": "<string>",
          "quantity": "<integer>",
          "price": "<long>",
          "productId": "<string>"
        },
        {
          "id": "<string>",
          "quantity": "<integer>",
          "price": "<long>",
          "productId": "<string>"
        }
      ]
    },
    {
      "id": "<string>",
      "transactionDate": "<string>",
      "point": "<integer>",
      "customerId": "<string>",
      "details": [
        {
          "id": "<string>",
          "quantity": "<integer>",
          "price": "<long>",
          "productId": "<string>"
        },
        {
          "id": "<string>",
          "quantity": "<integer>",
          "price": "<long>",
          "productId": "<string>"
        }
      ]
    }
  ],
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Create new transaction
### Method: POST
>```
>{{baseUrl}}/api/transactions?customerId=<string>&details=[object Object],[object Object]&point=<integer>
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Query Params

|Param|value|
|---|---|
|customerId|<string>|
|details|[object Object],[object Object]|
|point|<integer>|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "id": "<string>",
    "transactionDate": "<string>",
    "point": "<integer>",
    "customerId": "<string>",
    "details": [
      {
        "id": "<string>",
        "quantity": "<integer>",
        "price": "<long>",
        "productId": "<string>"
      },
      {
        "id": "<string>",
        "quantity": "<integer>",
        "price": "<long>",
        "productId": "<string>"
      }
    ]
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Claim Reward
### Method: POST
>```
>{{baseUrl}}/api/claim-rewards
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Body (**raw**)

```json
{
  "customerId": "<string>",
  "rewardId": "<string>"
}
```

### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "id": "<string>",
    "claimDate": "<string>",
    "customerId": "<string>",
    "rewardId": "<string>"
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
## 📁 Collection: register-merchant


## End-point: RegisterMerchant
### Method: POST
>```
>{{baseUrl}}/api/auth/register-merchant
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Body (**raw**)

```json
{
  "address": "<string>",
  "password": "<string>",
  "phone": "<string>",
  "shopName": "<string>",
  "username": "<string>"
}
```

### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "username": "<string>",
    "roles": [
      "<string>",
      "<string>"
    ]
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
## 📁 Collection: register-customer


## End-point: RegisterCustomer
### Method: POST
>```
>{{baseUrl}}/api/auth/register-customer
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Body (**raw**)

```json
{
  "address": "<string>",
  "name": "<string>",
  "password": "<string>",
  "phone": "<string>",
  "username": "<string>"
}
```

### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "username": "<string>",
    "roles": [
      "<string>",
      "<string>"
    ]
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
## 📁 Collection: login


## End-point: Login
### Method: POST
>```
>{{baseUrl}}/api/auth/login
>```
### Headers

|Content-Type|Value|
|---|---|
|Content-Type|application/json|


### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### Body (**raw**)

```json
{
  "password": "<string>",
  "username": "<string>"
}
```

### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": {
    "id": "<string>",
    "username": "<string>",
    "token": "<string>",
    "roles": [
      "<string>",
      "<string>"
    ]
  },
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Get All Merchant
### Method: GET
>```
>{{baseUrl}}/api/merchants
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": [
    {
      "id": "<string>",
      "shopName": "<string>",
      "phone": "<string>",
      "address": "<string>"
    },
    {
      "id": "<string>",
      "shopName": "<string>",
      "phone": "<string>",
      "address": "<string>"
    }
  ],
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Get All Customer
### Method: GET
>```
>{{baseUrl}}/api/customers
>```
### Headers

|Content-Type|Value|
|---|---|
|Accept|application/json|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|{{bearerToken}}|string|


### Response: 200
```json
{
  "statusCode": "<integer>",
  "message": "<string>",
  "data": [
    {
      "id": "<string>",
      "name": "<string>",
      "address": "<string>",
      "phone": "<string>",
      "pointTransaction": "<integer>"
    },
    {
      "id": "<string>",
      "name": "<string>",
      "address": "<string>",
      "phone": "<string>",
      "pointTransaction": "<integer>"
    }
  ],
  "paginationResponse": {
    "totalPages": "<integer>",
    "totalElements": "<long>",
    "currentPage": "<integer>",
    "pageSize": "<integer>",
    "hasNext": "<boolean>",
    "hasPrevious": "<boolean>"
  }
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
_________________________________________________
Powered By: [postman-to-markdown](https://github.com/bautistaj/postman-to-markdown/)
