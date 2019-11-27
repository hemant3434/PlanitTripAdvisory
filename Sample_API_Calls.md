# POST: addEvent
## endpoint: http://localhost:8080/api/v1/addEvent
### Sample API request body:
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
# PUT: deleteEvent
## endpoint: http://localhost:8080/api/v1/deleteEvent
### Sample API request body:
```
{
  "eventId": 12345
}
```
(no response)
