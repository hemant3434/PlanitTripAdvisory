## Participants

Gnanaswarup Srinivasan

Hemant Bhanot

Jackie Tran

Jason Conte

Jason Hu

Logan Chester

XiangQian "Richard" Hong

## Sprint Backlog:
---
`story 1`
  - As Bob (a businessman), I want the app to be able to display my itinerary, so I can see my entire day laid out in front of me.
  - Point Estimate: 21
  - The size of this story and number of tasks prevented the story from being completed (although it is close). We have decided to complete the final 3 tasks in sprint 2.

`story 2`
  - As Bob (a businessman), I want to set when I would be travelling so I can make sure I don't have intervening business plans that will be affected by the trip.
  - Point Estimate: 2

`story 3`
  - As Joseph (a traveller), I want to set where I would be traveling to make sure I am not making the same type of trip or visiting the same place or area again because I want to explore as much as I possibly can.
  - Point Estimate: 2

## Frontend Tasks:
---
**Richard Hong**:
  - `story 1` Develop Event Card(s)
  - `story 1` Develop Transportation Card(s)
  - `story 1` Pull Data From Backend

**Logan Chester**:
  - `story 2` Set date of travel
  - `story 2` Set the time of day for travel
  
**Gnanaswarup Srinivasan**:
  - `story 3` Set location of travel
  - `story 3` Create map preview card with location marker
  
## Backend Tasks:
---

**Jason Hu**: 
  - `story 1` Event details are gathered from Google Maps API
  - `story 1` Events are only included if they satisfy the Time/date, Location, Max distance
  
**Hemant Bhanot**: 
  - `story 1` Events are only included if they satisfy the open time, and Types of activities filters specified previously by user
  - `story 1` Travel time between events are gathered from the Google Maps API </br>

**Jason Conte**: 
- `story 1` Total cost of events and travel does not exceed Budget specified previously by user
- `story 1` Events in itinerary are chosen to minimize the amount of empty time between events
- `story 1` Itinerary takes into account travel time, and will not include events that cannot be reached in time from the previous event
- `story 1` Any events of the same type as an event rated poorly in the past by the user are given less preference than normal events
- `story 1` Events with good global ratings given preference over events with poor global ratings

**Jackie Tran**: 
- `story 2` Store time and date
- `story 3` Store location


