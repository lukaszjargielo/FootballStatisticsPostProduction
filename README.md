# The Football Events Statistics

A statistics company reads information about football events and produces statistics data. 
The goal is to write an application that can correctly handle a stream of messages from two services: 
**Events Result Service** and **Statistics Service**.

## Events Result Service Message

The **Events Result Service** sends a message whenever a football event is finished. The message is sent as JSON and contains the following fields:

- **type**: `"RESULT"`
- **result**: value of the result message
	- **home_team**: name of the home team (e.g., Bayern)
	- **away_team**: name of the away team (e.g., Barcelona)
	- **home_score**: number of goals scored by the home team
	- **away_score**: number of goals scored by the away team

### Example:

```json
{
	"type": "RESULT",
	"result": {
		"home_team": "Bayern",
		"away_team": "Barcelona",
		"home_score": 2,
		"away_score": 1
	}
}
```


It means the result of Bayern - Barcelona event is 2:0.

## Statistics Service Message

**Statistics Service** sends an request for retrieving a specified team statistics:

- **type** = "GET_STATISTICS"
- **get_statistics** - value of the get_statistics message
	- **teams** - teams list, for which the request is sent e.g. ["Bayern", "Barcelona"]

### Example:

```
{
	"type":"GET_STATISTICS",
	"get_statistics": {
		"teams": ["Bayern", "Barcelona"]
	}
}
```

## The goal of application:
The application should produce the output after each received message. 

On a **RESULT** message it should produce the simplified statistics information for both teams: 
- <u>number of played events</u>
- <u>sum of gained points</u>
- <u>sum of goals scored</u>
- <u>sum of goals conceded</u>

On a **GET_STATISTICS** message, it should produce statistics information for the team.
The statistics should contain: 

- <u>information about the latest 3 team's results</u>: form (text
with letters **W** - win, **D** - draw, **L** - lose) e.g. **WDL**
- <u>average amount of goals in the team events</u>
(sum of scored and conceded)
- <u>number of played events</u>
- <u>sum of gained points</u>
- <u>sum of goals scored</u>
- <u>sum of goals conceded</u>

The input for the program is in the attached messages.txt file. Every line contains one
JSON message.
Order of the incoming messages is important.

## Sample messages and expected output:
incoming message

**expected output**

{ "type": "RESULT", "result": { "home_team": "Bayern", "away_team": "Barcelona",
"home_score": 3, "away_score": 0 } }

**Bayern 1 3 3 0**

**Barcelona 1 0 0 3**

{ "type": "RESULT", "result": { "home_team": "PSG", "away_team": "Bayern", "home_score": 3,
"away_score": 3 } }

**PSG 1 1 3 3**

**Bayern 2 4 6 3**

{ "type": "GET_STATISTICS", "get_statistics": { "teams": ["Bayern"] } }

**Bayern DW 4.5 2 4 6 3**

{ "type": "RESULT", "result": { "home_team": "Bayern", "away_team": "Real", "home_score": 0,
"away_score": 1 } }

**Bayern 3 4 6 4**

**Real 1 3 1 0**

{ "type": "RESULT", "result": { "home_team": "Milan", "away_team": "Bayern", "home_score": 1,
"away_score": 3 } }

**Milan 1 0 1 3**

**Bayern 4 7 9 5**

{ "type": "GET_STATISTICS", "get_statistics": { "teams": ["Bayern", "Milan"] } }

**Bayern WLD 3.67 3 4 6 5**

**Milan L 4.0 1 0 1 3**

## Enum `EventStatuses` symbols explanation:
- **W** - win
- **L** - lose
- **D** - draw
- **UNDEFINED** - default value for an event whose status has not yet been determined
## Explaining the meaning of individual values in statistics output:

### Event scoring rules:

- **W** - 3 points
- **L** - 0 points
- **D** - 1 point 

### Simplified statistics:

**Barcelona 5 7 10 14**

| Barcelona |       5       |       7       |      10      |       14       |
|:---------:|:-------------:|:-------------:|:------------:|:--------------:|
| team name | events played | gained points | goals scored | goals conceded |

### Advanced statistics:

**Barcelona WLD 4.33 6 8 11 15**

| Barcelona |           W           |           L           |         D         |      4.33       |       6       |       8       |      11      |       15       |
|:---------:|:---------------------:|:---------------------:|:-----------------:|:---------------:|:-------------:|:-------------:|:------------:|:--------------:|
| team name | 3rd last event status | 2nd last event status | last event status | avg goals/event | events played | gained points | goals scored | goals conceded |