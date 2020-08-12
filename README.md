# xhapex
Given this pipeline: 
  - CopyValue name to localName
  - Duplicate by color
  - OptionalOperation (length id > 5) Delete
  - Update
  
And given this document: 
```json
{
  "id": 1, 
  "name": "shirt", 
  "color": ["red", "blue", "pink"]
}
```

We will obtain: 
```json
[
  {
    "operationType": "delete", 
    "id": "1-blue"
  },
  {
    "operationType": "delete", 
    "id": "1-pink"
  }, 
  {
    "operationType": "update", 
    "id": "1-red",
    "content": {
      "id": "1-red",
      "localName": "shirt"
    }
  }
]
```
