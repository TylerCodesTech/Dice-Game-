import java.awt.*;				// Needed for BorderLayout and FlowLayout classes.
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;		// Needed for listener interface.
import javax.swing.*;			// Needed for Swing classes.
 
public class DiceGames extends JFrame implements ActionListener	// Dice Games inherits from JFrame.
{
    private final int WIDTH = 700;			// Frame width.
    private final int HEIGHT = 300;			// Frame height.
	
	private JLabel turnLabel;				// To display the player's turn.
	private JLabel welcomeLabel;			// To display the welcome message.
	private JPanel topPanel;				// To display the top panel.
    private JLabel[] playerLabels;			// To display the player's labels.
    private JTextField[] playerFields;		// To display the player's totals.
    private JButton rollButton;				// The roll button.
    private JButton[] rerollButtons;		// The re-roll buttons.
    private int[] playerTotals;				// To hold the player's totals.
    private int[] playerLives;				// To hold the player's lives.
    private int playerTurn;					// To hold which player's turn it is.
    private boolean[] rerolled;				// To hold if the player has re-rolled.
 
    /*
     * Constructor
     */
    
    public DiceGames()
    {
    	super("Dice Games");
    	
    	// Set the window's title.
    	setTitle("Dice Games");
    	
    	// Set the size of the window.
    	setSize(WIDTH, HEIGHT);
    	
    	// Specify what happens when the close button is clicked.
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Get the content pane.
    	Container contentPane = getContentPane();
    	
    	// Add a BorderLayout manager to the content pane.
    	contentPane.setLayout(new BorderLayout());
    	
    	// Set up the top panel.
    	JPanel topPanel = buildTopPanel();
    	contentPane.add(topPanel, BorderLayout.NORTH);
 
        // Set up the turn label.
        turnLabel = new JLabel("A coin toss will determine which player goes " +
        					"first. Click the 'Roll' button.");
        contentPane.add(turnLabel, BorderLayout.NORTH);
 
        // Set up the player labels and fields.
        playerLabels = new JLabel[2];
        playerFields = new JTextField[2];
        for (int i = 0; i < 2; i++)
        {
            playerLabels[i] = new JLabel("Player " + (i + 1) + " total: ");
            playerFields[i] = new JTextField(5);
            playerFields[i].setEditable(false);
        }
 
        // Set up the roll button.
        rollButton = new JButton("Roll");
        rollButton.addActionListener(this);
 
        // Set up the re-roll buttons.
        rerollButtons = new JButton[2];
        for (int i = 0; i < 2; i++)
        {
            rerollButtons[i] = new JButton("Reroll");
            rerollButtons[i].addActionListener(this);
            rerollButtons[i].setEnabled(false);
        }
 
        // Set up the player panels.
        JPanel[] playerPanels = new JPanel[2];
        for (int i = 0; i < 2; i++)
        {
            playerPanels[i] = new JPanel();
            playerPanels[i].add(playerLabels[i]);
            playerPanels[i].add(playerFields[i]);
            playerPanels[i].add(rerollButtons[i]);
        }
 
        // Set up the roll panel.
        JPanel rollPanel = new JPanel();
        rollPanel.add(rollButton);
 
        // Add the panels to the frame.
        add(playerPanels[0], BorderLayout.WEST);
        add(playerPanels[1], BorderLayout.EAST);
        add(rollPanel, BorderLayout.SOUTH);
 
        // Game start variables.
        playerTotals = new int[2];
        playerLives = new int[]{6, 6};
        playerTurn = -1;
        rerolled = new boolean[2];
    }
 
    /**
     * Private method to create the top panel.
     */
    
    private JPanel buildTopPanel()
    {
    	// Create a panel.
    	JPanel topPanel = new JPanel();
    	
    	// Set up the welcomeLabel.
    	welcomeLabel = new JLabel("Welcome to Dice Games!");
    	topPanel.add(welcomeLabel, BorderLayout.NORTH);
    	topPanel.setLayout(new FlowLayout());
    	topPanel.setBackground(Color.CYAN);
    	
    	// Return the top panel.
    	return topPanel;
    }
    
    /**
     * Private inner class that handles the event when a user
     * clicks the roll button.
     */
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == rollButton)
        {
            rollDice();
        }
        else
        {
            for (int i = 0; i < 2; i++)
            {
                if (e.getSource() == rerollButtons[i])
                {
                    rerollDice(i);
                    break;
                }
            }
        }
    }
 
    /**
     * Private inner class that plays the main game: allows the user
     * to flip the coin using the roll button, rolls the dice, displays
     * the total amount from the dice, and checks for a re-roll click.
     */
   
    private void rollDice()
    {
        // Determines which player goes first.
        if (playerTurn == -1)
        {
            playerTurn = (int) (Math.random() * 2);
            turnLabel.setText("Player " + (playerTurn + 1) + "'s turn");
        }
 
        // Rolls the dice.
        int roll = (int) (Math.random() * 6) + 1;
        int roll2 = (int) (Math.random() * 6) + 1;
        playerTotals[playerTurn] = roll + roll2;
        
        // Display the roll's total in it's designated field.
        playerFields[playerTurn].setText("" + playerTotals[playerTurn]);
        
        // Checks if the user chose to re-roll the dice.
        if (!rerolled[playerTurn] && JOptionPane.showConfirmDialog(this, 
        		"Reroll?", "Reroll?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
        	rerollButtons[playerTurn].setEnabled(true);
        	rerolled[playerTurn] = true;
        }
        
        else
        {
        	// Ends turn.
        	endTurn();
        }
    }
    
    /**
     * Private class that handles the event of re-rolling the dice.
     * player is sent in.
     * @param player
     */
 
    private void rerollDice(int player)
    {
    	// Rolls the dice.
    	int roll = (int) (Math.random() * 6) + 1;
    	int roll2 = (int) (Math.random() * 6) + 1;
    	playerTotals[player] = roll + roll2;
 
    	// Displays the roll.
    	playerFields[player].setText("" + playerTotals[player]);
 
    	// Disables the re-roll button.
    	rerollButtons[player].setEnabled(false);
 
    	// Ends turn.
    	endTurn();
    }
 
    /**
     * Private class that ends the player's turn and
     * decreases the lives of the low total player.
     */
    
    private void endTurn()
    {
    	// Check for lowest roll amount.
    	int lowest = Integer.MAX_VALUE;
    	int lowestPlayer = -1;
    	for (int i = 0; i < 2; i++)
    	{
    		if (playerTotals[i] < lowest)
    		{
    			lowest = playerTotals[i];
    			lowestPlayer = i;
    		}
    	}
 
    	// Decrease the life of the low total player.
    	playerLives[lowestPlayer]--;
    	JOptionPane.showMessageDialog(this, "Player " +
    						(lowestPlayer + 1) + " loses a life");
    	
    	// Checks to see if any players have lost all of their lives.
    	if (playerLives[lowestPlayer] == 0)
    	{
    		JOptionPane.showMessageDialog(this, "Player " +
    						(lowestPlayer + 1) + " is out of the game");
    		System.exit(0);
    	}
 
    	// Updates the turnLabel to specify the correct player's turn.
    	playerTurn = (playerTurn + 1) % 2;
    	turnLabel.setText("Player " + (playerTurn + 1) + "'s turn");
 
    	// Resets re-roll flag.
    	rerolled[playerTurn] = false;
    }
    
    /**
     * Main program.
     */
 
    public static void main(String[] args)
    {
    	// Create an instance of DiceGames.
    	DiceGames dicey = new DiceGames();
	
    	// Make the frame visible.
    	dicey.setVisible(true);
    }
 }
