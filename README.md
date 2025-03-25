q
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

| Barcelona |   5   |   7   |  10   |  14   |
|:---------:|:-----:|:-----:|:-----:|:-----:|
| team name | events played | gained points | goals scored | goals conceded |

### Advanced statistics:

**Barcelona WLD 4.33 6 8 11 15**

| Barcelona |   W   |   L   |   D   |  4.33  |   6   |   8   |  11   |  15   |
|:---------:|:-----:|:-----:|:-----:|:------:|:-----:|:-----:|:-----:|:-----:|
| team name | 3rd last event status | 2nd last event status | last event status | avg goals/event | events played | gained points | goals scored | goals conceded |