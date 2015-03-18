# The game of Connect Four
* Birkbeck DCS MSc Software Design and Programming Assignment Two
* Assignment by  Daryl Smith *BBK username: `jsmit37`* and Oliver Smart  *BBK username: `osmart01`*


## Testing
* Development Testing was accomplished by JUnit tests that including printing information to Console 
so that results could be checked by "eye-ball"
* As the code provided was in the default package :disappointed: JUnit testing had to be done from default. This leads to the comment
in provided `Game.java`  *Testing is difficult using a JUnit testing class because it is difficult to get at many of the fields and methods from that class. So you may want to put some testing methods in this class.*
* This seems to be a rather bad idea so instead JUnit tests were written in the default class but in a separate source directory
[src-testing](src-testing)
* Once the development work had been completed testing was continued by playing a number of games against the AI with a depth of 6. The conclusion was that the AI plays a much better game of connect4 than either of us.
* To test a bit further a program was developed to play a tournament of 300 games pitching the "dummy" random piece placing player vs the AI: [Tournament](src-testing/Tournament.java).
  * the depth of the minimax tree was introduced as a variable and results plotted:
  * ![Image](test-results/Tournament_dummy_vs_ai.png?raw=true)
  * [png version](test-results/Tournament_dummy_vs_ai.png)
  * conclusion: the AI plays very much better than the dummy. The depth of 1 or 2 is enough to win > 90% of the time. At a depth of 4
    the AI wins 100% of the time. CPU requires start to rise at a depth of 4.
  * If there was more time would do tournament of AI depth=1 vs AI varying depth


        
