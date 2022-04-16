# Virtuslab-Internship
http://localhost:8080/basket- endpoint

## Request body
```json

{    
    "basket"  :  
[
    "Steak", 
    "Apple", 
    "Cereals", 
    "Cereals", 
    "Apple", 
    "Bread", 
    "Steak"
    ] 
}
```
  
  ## Response (Receipt)
  ```json
  [
    {
        "entries": [
            {
                "product": {
                    "name": "Cereals",
                    "type": "GRAINS",
                    "price": 8
                },
                "quantity": 2,
                "totalPrice": 16
            },
            {
                "product": {
                    "name": "Bread",
                    "type": "GRAINS",
                    "price": 5
                },
                "quantity": 1,
                "totalPrice": 5
            },
            {
                "product": {
                    "name": "Apple",
                    "type": "FRUITS",
                    "price": 2
                },
                "quantity": 2,
                "totalPrice": 4
            },
            {
                "product": {
                    "name": "Steak",
                    "type": "MEAT",
                    "price": 50
                },
                "quantity": 2,
                "totalPrice": 100
            }
        ],
        "discounts": [
            "FifteenPercentDiscount",
            "TenPercentDiscount"
        ],
        "totalPrice": 95.625
    }
]
```
