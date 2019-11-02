## Participants

Gnanaswarup Srinivasan

Hemant Bhanot

Jackie Tran

Jason Conte

Jason Hu

Logan Chester

XiangQian "Richard" Hong

## Sprint Backlog:

We decided to add an additional user story to our backlog as we did not have one for a log-in/register component up until this point.

---

`story 1`

- As Bob (a businessman), I want the app to be able to display my itinerary, so I can see my entire day laid out in front of me.

- Point Estimate: 21

`story 2`

- As Marie (young couple), I want to be able to modify the itinerary and add different events, in case an event is recommended that I do not want to attend.

- Point Estimate: 8

`story 3`

- As Karen (parent of two), I want to set the method of transportation so I can prepare and pack for the kids and the trip accordingly.

- Point Estimate: 2

`story 4`

- As Karen (parent of two), I want to set how far the trip might take us because kids get tired and bored very easily and quickly during long walks or trips.

- Point Estimate: 2

`story 5`

- As Joseph (a traveller), I would like to be able to view the amount of money that is required to make the trip so I can see if I could afford it.

- Point Estimate: 2

`story 6`

- As Bob (a businessman), I would like my itinerary to be saved to my private user account so that only I can view my itinerary, whenever I want, by using my account information.

- Point Estimate: 5

## Frontend Tasks:
---
**Richard Hong**:
  - `story 2` Search bar to search for events to be added to the itinerary
  - `story 2` Each event in itinerary has delete button

**Logan Chester**:
  - `story 3` Display method of transportation as multiple different selectable options
  - `story 4` A maximum distance can be set using a slider
  - `story 5` A maximum budget can be specified using a slider

**Gnanaswarup Srinivasan**:
  - `story 6` Create log-in page
  - `story 6` Create register page
  - `story 6` Submit register/log-in request to backend

**Unassigned**:
  - `story 2` User can select a time slot for events about to be added to itinerary
  
## Backend Tasks:
---

**Jason Hu**:
  - `story 1` Apply user event filters to fetched data
  - `story 2` Events can be deleted from the itinerary
  - `story 2` Events can be added to the itinerary
  - `stort 3` Method of transportation is stored
  - `stort 4` Maximum distance is stored
  - `story 5` Maximum budget is stored
  
**Hemant Bhanot**: 
  - `story 1` Events are only included if they satisfy the open time, and Types of activities filters specified previously by user
  - `story 1` Travel time between events calculated
  - `stort 1` Fetch data from Google Maps API

**Jason Conte**: 
  - `story 1` Total cost of events and travel does not exceed Budget specified previously by user
  - `story 1` Itinerary takes into account travel time, and will not include events that cannot be reached in time from the previous event
  - `story 1` Any events of the same type as an event rated poorly in the past by the user are given less preference than normal events

**Jackie Tran**: 
  - `story 2` Itinerary joins previous event to next event around a deleted event
  - `story 2` Itinerary resolves events that conflict with a newly added event

