## POST: addEvent
### endpoint: http://localhost:8080/api/v1/addEvent
#### sample request body:
```
{	
	"location":"7 becca hall trail",
	"longitude":6.9,"latitude":2.1,"activity":"eating food",
	"rating":5,
	"image":"",
	"description":"https://maps.google.com/?cid=5464217128495447518",
	"id":"12345",
	"type":"event",
	"title":"eating sushi",
	"price":5,
	"startTime":{"month":11,"hour":14,"year":2019,"positive":true,"day":16,"minute":0},
	"endTime":{"month":11,"hour":15,"year":2019,"positive":true,"day":16,"minute":0},
	"expectedLength":{"month":0,"hour":2,"year":0,"positive":true,"day":0,"minute":0}
}
```
(no response)
## PUT: deleteEvent
### endpoint: http://localhost:8080/api/v1/deleteEvent
#### sample request body:
```
{
  "eventId": "12345"
}
```
(no response)
## PUT: addRating
### endpoint: http://localhost:8080/api/v1/addRating
#### sample request body
```
{
	"eventId": "12345",
	"rating": "3"
}
```
(no response)
## PUT: deleteRating
### endpoint: http://localhost:8080/api/v1/deleteRating
#### sample request body:
```
{
	"eventId": "12345"
}
```
(no response)
## PUT: addVisitedEvent
### endpoint: http://localhost:8080/api/v1/addVisitedEvent
#### sample request body:
```
{
	"eventId": "12345"
}
```
(no response)
## PUT: deleteVisitedEvent
### endpoint: http://localhost:8080/api/v1/deleteVisitedEvent
#### sample request body:
```
{
	"eventId": "12345"
}
```
(no response)
## PUT: changeMaxBudget
### endpoint: http://localhost:8080/api/v1/changeMaxBudget
#### sample request body:
```
{
	"budget": 1234.5
}
```
(no response)
## PUT: changeMaxDistance
### endpoint: http://localhost:8080/api/v1/changeMaxDistance
#### sample request body:
```
{
	"maxDist": 683.2
}
```
(no response)
## PUT: addTransportation
### endpoint: http://localhost:8080/api/v1/addTransportation
#### sample request body:
```
{
	"Transportation": [
		"Drive",
		"Transit"
	]
}
```
(no response)
## GET: costPerPerson
### endpoint: http://localhost:8080/api/v1/costPerPerson
#### request body: `{}`
#### sample response body:
```
{
    "costPerPerson": "$5.0"
}
```
## GET: getExplorePage
### endpoint: http://localhost:8080/api/v1/getExplorePage
#### request body: `{}`
#### sample response body:
```
[
    {
        "type": "event",
        "title": "Bier Markt",
        "price": 2.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 11,
            "minute": 30,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 24,
            "minute": 0,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "58 The Esplanade, Toronto, ON M5E 1R2, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "restaurant, food, point_of_interest, establishment",
        "rating": 4,
        "image": "",
        "description": "https://maps.google.com/?cid=1186518116228149351",
        "id": "ChIJke8Cci7L1IkRZzBYtfBbdxA"
    },
    {
        "type": "event",
        "title": "The Fifth Grill & Terrace",
        "price": 3.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 17,
            "minute": 30,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 22,
            "minute": 0,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "225 Richmond St W Suite 501b, Toronto, ON M5V 1W2, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "restaurant, food, point_of_interest, establishment",
        "rating": 4,
        "image": "",
        "description": "https://maps.google.com/?cid=5392354957613843086",
        "id": "ChIJ92TkJNA0K4gRjsJjl0N-1Uo"
    },
    {
        "type": "event",
        "title": "Donatello Restaurant",
        "price": 3.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 16,
            "minute": 30,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 24,
            "minute": 0,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "37 Elm St, Toronto, ON M5G 1H1, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "restaurant, food, point_of_interest, establishment",
        "rating": 4,
        "image": "",
        "description": "https://maps.google.com/?cid=3895320006894906536",
        "id": "ChIJ7ZeFXso0K4gRqGxxcej0DjY"
    },
    {
        "type": "event",
        "title": "VOLOS",
        "price": 3.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 17,
            "minute": 0,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 22,
            "minute": 30,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "133 Richmond St W, Toronto, ON M5H 2L3, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "restaurant, food, point_of_interest, establishment",
        "rating": 4,
        "image": "",
        "description": "https://maps.google.com/?cid=2045484793931921884",
        "id": "ChIJc7b06800K4gR3HnGJ5UFYxw"
    },
    {
        "type": "event",
        "title": "The One Eighty",
        "price": 3.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 17,
            "minute": 0,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 24,
            "minute": 0,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "55 Bloor St W 51st Floor, Toronto, ON M4W 1A5, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "restaurant, night_club, bar, food, point_of_interest, establishment",
        "rating": 4,
        "image": "",
        "description": "https://maps.google.com/?cid=10984472876647978729",
        "id": "ChIJKcn41rE0K4gR6RaEEeKvcJg"
    },
    {
        "type": "event",
        "title": "Little India Restaurant",
        "price": 2.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 11,
            "minute": 30,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 22,
            "minute": 30,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "255 Queen St W, Toronto, ON M5V 1Z4, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "restaurant, food, point_of_interest, establishment",
        "rating": 3,
        "image": "",
        "description": "https://maps.google.com/?cid=11197688876692650795",
        "id": "ChIJi5Wv0880K4gRK7ubmLQuZps"
    },
    {
        "type": "event",
        "title": "Chako Barbecue & Izakaya",
        "price": 2.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 11,
            "minute": 30,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 24,
            "minute": 0,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "163 York Blvd, Richmond Hill, ON L4B 4A7, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "restaurant, food, point_of_interest, establishment",
        "rating": 3,
        "image": "",
        "description": "https://maps.google.com/?cid=572365402987227615",
        "id": "ChIJs7q4q0ssK4gR3-nGLk1z8Qc"
    },
    {
        "type": "event",
        "title": "Boston Pizza",
        "price": 2.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 11,
            "minute": 0,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 24,
            "minute": 0,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "180 Rimrock Rd, Toronto, ON M3J 3A6, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "restaurant, food, point_of_interest, establishment",
        "rating": 3,
        "image": "",
        "description": "https://maps.google.com/?cid=7118174104810330914",
        "id": "ChIJHy6COAEyK4gRIpuadqLVyGI"
    },
    {
        "type": "event",
        "title": "The Drake Hotel",
        "price": 3.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 7,
            "minute": 30,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 24,
            "minute": 0,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "1150 Queen St W, Toronto, ON M6J 1J3, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "restaurant, lodging, bar, food, point_of_interest, establishment",
        "rating": 4,
        "image": "",
        "description": "https://maps.google.com/?cid=5464217128495447518",
        "id": "ChIJ8b_aGgA1K4gR3n2TF4fM1Es"
    },
    {
        "type": "event",
        "title": "Mandarin Restaurant",
        "price": 2.0,
        "startTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 16,
            "minute": 30,
            "positive": true
        },
        "endTime": {
            "year": 2019,
            "month": 11,
            "day": 15,
            "hour": 22,
            "minute": 30,
            "positive": true
        },
        "expectedLength": {
            "year": 0,
            "month": 0,
            "day": 0,
            "hour": 2,
            "minute": 0,
            "positive": true
        },
        "location": "2206 Eglinton Ave E, Scarborough, ON M1L 4S7, Canada",
        "longitude": 47.2,
        "latitude": 47.2,
        "activity": "meal_takeaway, restaurant, food, point_of_interest, establishment",
        "rating": 4,
        "image": "",
        "description": "https://maps.google.com/?cid=8553543703437866050",
        "id": "ChIJQVYrBg7O1IkRQnQx_q1KtHY"
    }
]
```
