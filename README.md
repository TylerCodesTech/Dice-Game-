# Dice-Game-
UNLV IS 380 Group Final

It's a simple dice game that allows two players to roll dice and compete against each other. The game interface is implemented using Swing components.

How to Play:
1.)Run the application by executing the main method in the DiceGames class.
2.)A window titled "Dice Games" will appear.
3.)The game starts with a coin toss to determine which player goes first. Click the "Roll" button to perform the coin toss.
4.)The player with the highest result of the coin toss will be displayed in the "Player X's turn" label.
5.)To roll the dice, click the "Roll" button. The application will simulate rolling two dice and calculate the total.
6.)The total for the current player will be displayed in the corresponding text field.
7.)After rolling the dice, you have the option to reroll. A dialog will appear asking if you want to reroll the dice. Click "Yes" to reroll or "No" to end your turn.
8.)If you choose to reroll, the new total will replace the previous total in the text field.
9.)The turn will automatically switch to the other player.
10.)Each time a turn ends, the player with the lowest total loses a life. A message dialog will indicate which player lost a life.
11.)If a player loses all their lives, they are eliminated from the game, and a message dialog will appear.
12.)The game continues until one player is eliminated. At that point, the application will exit.



Components:
The application consists of the following components:

Window: The main game window titled "Dice Games" with a fixed size.
Top Panel: A panel at the top of the window displaying the welcome message.
Turn Label: A label that indicates the current player's turn.
Player Labels: Labels that display the player numbers.
Player Fields: Text fields that display the player's total points.
Roll Button: A button to roll the dice.
Reroll Buttons: Buttons to reroll the dice for each player.



Class Structure:
The DiceGames class extends the JFrame class and implements the ActionListener interface. It contains the main game logic and event handling methods.

The class contains the following important methods:

Constructor: Sets up the game window, initializes the components, and sets their initial states.
buildTopPanel: Creates and configures the top panel.
actionPerformed: Handles the event when a user clicks the roll button or a reroll button.
rollDice: Simulates rolling the dice, calculates the total, and checks if the player wants to reroll.
rerollDice: Simulates rerolling the dice, calculates the new total, and ends the turn.
endTurn: Ends the player's turn, decreases the lives of the player with the lowest total, and checks for game over conditions.
main: The entry point of the program, creates an instance of DiceGames and makes the game window visible.
Feel free to modify this code to add additional features or customize the game as per your requirements. Enjoy playing Dice Games!
