package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * Project: A number guessing game created with swing
 * Author: Lisa-Maria Lamprecht
 * --using new class because we are implementing actionlistener, so this frame can listen for events like a button click
 */

public class MyFrame extends JFrame implements ActionListener { 
    //global variables
    JButton button;
    JButton playAgainButton;
    JTextField textField;
    JLabel outputMessage;
    JLabel tally;
    JLabel bestRound;
    Random rand = new Random();
    int randomNumber = rand.nextInt(101); //get a rand number between 0-100
    int tallyValue = 0;
    int bestRoundValue = 0;

    //create a constructor
    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of app 
        this.setPreferredSize(new Dimension(900,500)); //set frame size
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(); //c will be used for positioning elements in the gridbaglayout

        this.setSize(900, 500);

        //submit button
        button = new JButton("Submit");
        c.fill = GridBagConstraints.HORIZONTAL; //natural height, max width
        c.weightx = 5; //the space between components
        c.weighty = 5; 
        c.gridx = 3; //x and y specifiy the component row and col
        c.gridy = 4;
        button.addActionListener((e) -> submit()); //use a lamba so buttons don't have to share action listener - separates functionality
        this.add(button, c);

        //instruction label
        JLabel instruction = new JLabel();
        instruction.setText("Input Your Guess (must be between 1 and 100");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;
        c.weighty = 3; 
        c.gridx = 2;
        c.gridy = 3;
        this.add(instruction, c);

        //text field input
        textField = new JTextField(10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;
        c.weighty = 3; 
        c.gridx = 2;
        c.gridy = 4;
        textField.setPreferredSize(new Dimension(250,40));
        this.add(textField, c);

        //number of guesses tally
        tally = new JLabel();
        tally.setText("Number of guesses: " + tallyValue);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;
        c.weighty = 3; 
        c.gridx = 1;
        c.gridy = 1;
        this.add(tally, c);

        //best round
        bestRound = new JLabel();
        bestRound.setText("Best Round: " + bestRoundValue);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;
        c.weighty = 3; 
        c.gridx = 1;
        c.gridy = 2;
        this.add(bestRound, c);

        //output message label
        outputMessage = new JLabel();
        outputMessage.setText("Take a guess!");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;
        c.weighty = 3; 
        c.gridx = 2;
        c.gridy = 5;
        this.add(outputMessage, c);

        //play again button
        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener((e) -> playAgain());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5;
        c.weighty = 5; 
        c.gridx = 3;
        c.gridy = 5;
        this.add(playAgainButton, c);
        
        this.setTitle("Number Guessing Game");
        this.setVisible(true); //make frame visible
        this.pack(); //frame size adjusts to components

        ImageIcon icon = new ImageIcon("res/game-icon.png"); //creates an image icon
        this.setIconImage(icon.getImage());//changes icon of frame

        System.out.println(randomNumber); //for testing purposes
    }

    //add any unimplemented methods because we are using an interface
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private void submit() {
        int textFieldValue;
        try {
            textFieldValue = Integer.parseInt(textField.getText());
            tallyValue++;
            // if guess is correct
            if (textFieldValue == randomNumber) {
                outputMessage.setText("Congratulations you guessed correctly!");
                tally.setText("Number of guesses:" + tallyValue);

                if(bestRoundValue==0) { //set best round, if first round
                    bestRoundValue = tallyValue;
                    bestRound.setText("Best Round:" + bestRoundValue);
                }
                else if (tallyValue < bestRoundValue) { //if the current tally is better than the set best round, set new best round
                    bestRoundValue = tallyValue;
                    bestRound.setText("Best Round:" + bestRoundValue);
                }
                textFieldValue = 0; // reset text field val
            }
            // if guess is less than randomNumber
            else if (textFieldValue < randomNumber) {
                outputMessage.setText("Incorrect - the number I am thinking of is MORE than that");

                tally.setText("Number of guesses:" + tallyValue);
                System.out.println(randomNumber + "A"); //for testing purpose
                textFieldValue = 0; // reset text field val
            }
            // if guess is more than randomNumber
            else if (textFieldValue > randomNumber) {
                outputMessage.setText("Incorrect - the number I am thinking of is LESS than that");

                System.out.println(tallyValue);
                tally.setText("Number of guesses:" + tallyValue);

                textFieldValue = 0; // reset text field val
            }
        } catch (NumberFormatException ex) {
            outputMessage.setText("You must insert a valid number");
        }
    }

    private void playAgain() {
        randomNumber = rand.nextInt(101);
        outputMessage.setText("Take a guess!");
        tallyValue = 0;
        tally.setText("Number of guesses:" + tallyValue);
    }
   
}
