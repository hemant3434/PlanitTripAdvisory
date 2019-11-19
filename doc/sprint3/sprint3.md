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
We have decided to remove story 14 from our sprint due to its limited value and effort associated with it.

`story 14`

- As Bob (a businessman), I want my itinerary to sync with my calendar so that I only have to look at one place for information about my events and meetings.

- Point Estimate: 2
---

`story 6`

- As Karen (parent of two), I would like to view the variety of activities available on the trip so I can discuss with the kids because they are very picky and may not enjoy the trip.

- Point Estimate: 2

`story 7`

- As Marie (young couple), I want to see where I am in a map so I can go to nearby places to explore the surrounding environment and have a nice leisure time/walk with my boyfriend.

- Point Estimate: 3

`story 8`

- As Karen (parent of two), I want to see the travel time on the map so I can see if I am comfortable with the amount of time traveling and see if the trip is worth the travel time it is going to take to get there.

- Point Estimate: 1

`story 9`

- As Karen (parent of two), I would like to see the fastest route between activities as the kids get tired and bored very quickly.

- Point Estimate: 1

`story 10`

- As Joseph (a traveller), I want my itinerary to display the cost of each event per person, so that I can more easily compare whether the event is worth the price.

- Point Estimate: 1

`story 11`

- As Bob (a businessman), I want my itinerary to display the travel time between events, so that I can more easily determine which events do not fit into my busy schedule.

- Point Estimate: 3

`story 12`

- As Karen (a mom of two), I want my itinerary to display the typical amount of time spent at events so that I can more easily determine which events take last long for my children to fully enjoy.

- Point Estimate: 1

`story 13`

- As Marie (young couple), I want to be able to modify the itinerary and add different events, in case an event is recommended that I do not want to attend.

- Point Estimate: 8

`story 15`

- As Bob (a businessman), I want the app to be able to display my itinerary, so I can see my entire day laid out in front of me.

- Point Estimate: 21

`story 17`

- As Joseph (a traveller), I want to be able to rate the events that I attend in the app so that other travellers know what events they should visit.

- Point Estimate: 3

`story 6`

- As Bob (a businessman), I would like my itinerary to be saved to my private user account so that only I can view my itinerary, whenever I want, by using my account information.

- Point Estimate: 5

## Frontend Tasks:
---

We have decided to remove the following task: "A map is displayed that includes nearby relevant locations".
We made this decision because it is a redundant task that does not add value to our product.

---

**Richard Hong**:
- `Story 13` User can select time slot for events about to be added to itinerary

**Logan Chester**:
- `story 6` User can select from multiple activities
- `story 9` Once itinerary has been set, the map tab will display the fastest route from one event to the next (keeping preferred mode of transportation in mind)
- `story 9` Map zooms out appropriately to show entirety of the next travel segment of the itinerary
- `story 9` Route displayed is easily distinguishable from map background

**Gnanaswarup Srinivasan**:
- `story 7` The map displayed supports zooming in/out
- `story 7` The map displayed shows the user's current location
- `story 6` The login/register screen sends/receives data from the backend for validation

**Unassigned**
- `story 8` Time estimates are retrieved from the backend
- `story 8` Beside each segment of the route on the map is a time estimate of how long it will take to travel between events
- `story 10` Each event displays cost per person
- `story 10` Each travel step displays cost per person
- `story 10` Total cost per person shown at the bottom of the itinerary
- `story 11` Each travel step displays estimated travel time
- `story 12` Each event displays typical amount of time spent at the event
  
## Backend Tasks: ##

**Jason Hu**:
- `story 10` Total cost per person is calculated
- `story 13` Add an event to the itinerary

**Hemant Bhanot**: 
- `Story 15` Apply user event filters to fetched data
- `story 10` Cost per person per each event is calculated
- `story 10` Cost per person per each travel segment is calculated

**Jason Conte**: 

**Jackie Tran**: 

**Unassigned**
- `story 6` The activities that the user selects are stored
- `story 8` Time estimates are calculated and available to be retrieved
- `story 17` Events of the same type as events rated poorly by the user are rarely included
- `story 17` When itinerary is being created, events of the same type as events rated highly by the user are given preference
