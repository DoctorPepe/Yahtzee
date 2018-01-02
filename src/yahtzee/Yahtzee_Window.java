package yahtzee;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;
public class Yahtzee_Window implements ActionListener, MouseListener {
    //variables
    boolean p1Turn = true;
    boolean p2Turn = false;
    int turnCount = 3;

    JFrame mainFrame = new JFrame("Yahtzee! v0.63");
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel titlePanel = new JPanel();
    JPanel p1DicePanel = new JPanel(new BorderLayout());
    JPanel p2DicePanel = new JPanel(new BorderLayout());
    JPanel rollPanel = new JPanel();
    JPanel scorePanel = new JPanel(new GridLayout(3, 17, 1, 1));

    //title panel:
    JLabel titlePic = new JLabel(new ImageIcon(new ImageIcon("src\\images\\Yahtzee_logo.png").getImage().getScaledInstance(300, 114, Image.SCALE_DEFAULT)));

    //p1DicePanel
    JPanel p1DiceHand = new JPanel(new GridLayout(2, 5, 3, 3));
    JLabel p1Title = new JLabel("Player 1");
    JLabel[] p1DiceHandCurrent = new JLabel[5];
    JLabel[] p1HoldHand = new JLabel[5];
    boolean[] p1Holds = new boolean[5];
    int[] p1DiceHandCurrentValue = new int[5];
    int[] p1HoldHandValue = new int[5];

    //p2DicePanel
    JPanel p2DiceHand = new JPanel(new GridLayout(2, 5, 3, 3));
    JLabel p2Title = new JLabel("Player 2");
    JLabel[] p2DiceHandCurrent = new JLabel[5];
    JLabel[] p2HoldHand = new JLabel[5];
    boolean[] p2Holds = new boolean[5];
    int[] p2DiceHandCurrentValue = new int[5];
    int[] p2HoldHandValue = new int[5];

    //rollPanel
    JButton roll = new JButton("Roll!");
    JLabel turnCounter = new JLabel("Turns Left: " + turnCount);

    //scorePanel
    JLabel[] score = new JLabel[51];

    public Yahtzee_Window() {
        //mainFrame
        mainFrame.add(titlePanel, BorderLayout.NORTH);
        mainFrame.add(p1DicePanel, BorderLayout.WEST);
        mainFrame.add(p2DicePanel, BorderLayout.EAST);
        mainFrame.add(rollPanel, BorderLayout.CENTER);
        mainFrame.add(scorePanel, BorderLayout.SOUTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1600, 800);
        mainFrame.setLocationRelativeTo(null);

        //titlePanel
        titlePanel.add(titlePic);

        //p1DicePanel
        p1Title.setBorder(BorderFactory.createLineBorder(Color.RED));
        p1DicePanel.add(p1DiceHand, BorderLayout.CENTER);
        p1DicePanel.add(p1Title, BorderLayout.NORTH);
        for (int a = 0; a <= 4; a++) {
            p1DiceHandCurrent[a] = new JLabel(new ImageIcon("src\\images\\Dice\\Dice0.png"));
            p1DiceHand.add(p1DiceHandCurrent[a]);
            p1DiceHandCurrent[a].addMouseListener(this);
        }
        for (int a = 0; a <= 4; a++) {
            p1HoldHand[a] = new JLabel(new ImageIcon("src\\images\\Dice\\DiceHold.png"));
            p1DiceHand.add(p1HoldHand[a]);
            p1HoldHand[a].addMouseListener(this);
        }

        //p2DicePanel
        p2DicePanel.add(p2DiceHand, BorderLayout.CENTER);
        p2DicePanel.add(p2Title, BorderLayout.NORTH);
        for (int a = 0; a <= 4; a++) {
            p2DiceHandCurrent[a] = new JLabel(new ImageIcon("src\\images\\Dice\\Dice0.png"));
            p2DiceHand.add(p2DiceHandCurrent[a]);
            p2DiceHandCurrent[a].addMouseListener(this);
        }
        for (int a = 0; a <= 4; a++) {
            p2HoldHand[a] = new JLabel(new ImageIcon("src\\images\\Dice\\DiceHold"
                    + ".png"));
            p2DiceHand.add(p2HoldHand[a]);
            p2HoldHand[a].addMouseListener(this);
        }

        //roll Panel
        rollPanel.add(roll);
        roll.addActionListener(this);
        rollPanel.add(turnCounter);

        //score panel
        for (int b = 0; b <= 50; b++) {
            score[b] = new JLabel();
            if (b != 0 && b != 1 && b != 2 && b != 3 && b != 4 && b != 5 && b != 6 && b != 7 && b != 8 && b != 9 && b != 10 && b != 11 && b != 12 && b != 13 && b != 14 && b != 15 && b != 16 && b != 17 && b != 34 && b != 24 && b != 25 && b != 33 && b != 40 && b != 41 && b != 50) {
                score[b].addMouseListener(this);
            }
            score[b].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            scorePanel.add(score[b]);
        }
        score[1].setText("Ones");
        score[2].setText("Twos");
        score[3].setText("Threes");
        score[4].setText("Fours");
        score[5].setText("Fives");
        score[6].setText("Sixes");
        score[7].setText("Sum");
        score[8].setText("Bonus");
        score[9].setText("Three of a Kind");
        score[10].setText("Four of a Kind");
        score[11].setText("Full House");
        score[12].setText("Small Straight");
        score[13].setText("Large Straight");
        score[14].setText("Chance");
        score[15].setText("Yahtzee");
        score[16].setText("Total");
        score[17].setText("Player 1");
        score[34].setText("Player 2");


        mainFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == roll && turnCount > 0) {
            turnCount--;
            turnCounter.setText("Turns Left: " + turnCount);

            Random rand = new Random();
            for (int b = 0; b <= 4; b++) {
                int temp = rand.nextInt(6) + 1;
                if (p1Turn && !p1Holds[b]) {
                    p1DiceHandCurrent[b].setIcon(new ImageIcon("src//images//Dice//Dice" + (temp) + ".png"));
                    p1DiceHandCurrentValue[b] = temp;
                } else if (p2Turn && !p2Holds[b]) {
                    p2DiceHandCurrent[b].setIcon(new ImageIcon("src//images//Dice//Dice" + (temp) + ".png"));
                    p2DiceHandCurrentValue[b] = temp;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int a = 0; a < 5; a++) { //code to hold dice
            if (e.getSource() == p1DiceHandCurrent[a] && !p1Holds[a] && p1Turn) {
                p1Holds[a] = !p1Holds[a];
                p1HoldHand[a].setIcon(new ImageIcon("src//images//Dice//Dice" + p1DiceHandCurrentValue[a] + ".png"));
                p1DiceHandCurrent[a].setIcon(new ImageIcon("src//images//Dice//Dice//Dice0.png"));
                p1HoldHandValue[a] = p1DiceHandCurrentValue[a];
                p1DiceHandCurrentValue[a] = 0;
            }
            if (e.getSource() == p2DiceHandCurrent[a] && !p2Holds[a] && p2Turn) {
                p2Holds[a] = !p2Holds[a];
                p2HoldHand[a].setIcon(new ImageIcon("src//images//Dice//Dice" + p2DiceHandCurrentValue[a] + ".png"));
                p2DiceHandCurrent[a].setIcon(new ImageIcon("src//images//Dice//Dice//Dice0.png"));
                p2HoldHandValue[a] = p2DiceHandCurrentValue[a];
                p2DiceHandCurrentValue[a] = 0;
            }
            if (e.getSource() == p1HoldHand[a] && p1Holds[a] && p1Turn) {
                p1Holds[a] = !p1Holds[a];
                p1DiceHandCurrent[a].setIcon(new ImageIcon("src//images//Dice//Dice" + p1HoldHandValue[a] + ".png"));
                p1HoldHand[a].setIcon(new ImageIcon("src//images//Dice//DiceHold.png"));
                p1DiceHandCurrentValue[a] = p1HoldHandValue[a];
                p1HoldHandValue[a] = 0;
            }
            if (e.getSource() == p2HoldHand[a] && p2Holds[a] && p2Turn) {
                p2Holds[a] = !p2Holds[a];
                p2DiceHandCurrent[a].setIcon(new ImageIcon("src//images//Dice//Dice" + p2HoldHandValue[a] + ".png"));
                p2HoldHand[a].setIcon(new ImageIcon("src//images//Dice//DiceHold.png"));
                p2DiceHandCurrentValue[a] = p2HoldHandValue[a];
                p2HoldHandValue[a] = 0;
            }
        }
        for (int b=0; b < 51; b++) {
            if (e.getSource() == score[b] && b > 18 && b <= 50 && score[b].getText() == "") {
                if (b == 31 || b == 48) { //for chance (this one will act as a sample for the rest.
                    int chanceTemp;
                    chanceTemp = chance(); //call the method for that particular score category
                    score[b].setText(chanceTemp + ""); //set text
                    endTurn(e); //end turn
                }
                if (b == 32 || b == 49) { //yahtzee
                	int yahtzeeTemp;
                	yahtzeeTemp = yahtzee();
                	score[b].setText(yahtzeeTemp + "");
                	endTurn(e);
				}
				if (b == 26 || b ==	43) { //three of a kind
                	int threeKindTemp;
                	threeKindTemp = threeKind();
                	score[b].setText(threeKindTemp + "");
                	endTurn(e);
				}
				if (b == 27 || b == 44) { //four of a kind
                    int fourKindTemp;
                    fourKindTemp = fourKind();
                    score[b].setText(fourKindTemp + "");
                    endTurn(e);
                }
                if (b == 28 || b == 45) {
                    int fullHouseTemp;
                    fullHouseTemp = fourKind();
                    score[b].setText(fullHouseTemp + "");
                    endTurn(e);
                }
            }
        }
	}

@Override
public void mouseExited(MouseEvent e) {
	
}
public int[] topScores() {
	int[] topScore = new int [6];
	int p1temp = 0;
	int p2temp = 0;
	for (int a = 1; a <= 6; a++) {
		for (int i = 0; i < 5; i++) {
			if (p1DiceHandCurrentValue[i] == a) {
				p1temp = p1temp + a;
			}
			if (p1HoldHandValue[i] == a) {
				p1temp = p1temp + a;
			}
			if (p2DiceHandCurrentValue[i] == a) {
				p2temp = p2temp + a;
			}
			if (p2HoldHandValue[i] == a) {
				p2temp = p2temp + a;
			}
		}
		if (p1Turn) {
			topScore[a-1] = p1temp;
		} else if (p2Turn) {
			topScore[a-1] = p2temp;
		}
	}
	return topScore;
}
public int threeKind () {
	int threeOfAKind = 0;
	int[] tempTotal = new int[6];
	if (p1Turn) {
		for (int a=0; a < 6; a++) {
			for (int i=0; i < 5; i++) {
				if (p1DiceHandCurrentValue[i] == (a + 1)) {
					tempTotal[a] = tempTotal[a] + 1;
				}
				if (p1HoldHandValue[i] == (a + 1)) {
					tempTotal[a] = tempTotal[a] + 1;
				}
			}
		}
		for (int b=0; b<5; b++) {
			if (tempTotal[b] >= 3) {
				for (int c=0; c<5; c++) {
					threeOfAKind = threeOfAKind + p1DiceHandCurrentValue[c] + p1HoldHandValue[c];
				}
			}
		}
	} else if (p2Turn) {
		for (int a=1; a < 6; a++) {
			for (int i=0; i < 5; i++) {
				if (p2DiceHandCurrentValue[i] == (a + 1)) {
					tempTotal[a] = tempTotal[a] + 1;
				}
				if (p2HoldHandValue[i] == (a + 1)) {
					tempTotal[a] = tempTotal[a] + 1;
				}
			}
		}
		for (int b=0; b<5; b++) {
			if (tempTotal[b] >= 3) {
				for (int c=0; c<5; c++) {
					threeOfAKind = threeOfAKind + p2DiceHandCurrentValue[c] + p2HoldHandValue[c];
				}
			}
		}
	}
	return threeOfAKind;
}
public int fourKind () {
    int fourOfAKind = 0;
    int[] tempTotal = new int[6];
    if (p1Turn) {
        for (int a=0; a < 6; a++) {
            for (int i=0; i < 5; i++) {
                if (p1DiceHandCurrentValue[i] == (a + 1)) {
                    tempTotal[a] = tempTotal[a] + 1;
                }
                if (p1HoldHandValue[i] == (a + 1)) {
                    tempTotal[a] = tempTotal[a] + 1;
                }
            }
        }
        for (int b=0; b<5; b++) {
            if (tempTotal[b] >= 3) {
                for (int c=0; c<5; c++) {
                    fourOfAKind = fourOfAKind + p1DiceHandCurrentValue[c] + p1HoldHandValue[c];
                }
            }
        }
    } else if (p2Turn) {
        for (int a=1; a < 6; a++) {
            for (int i=0; i < 5; i++) {
                if (p2DiceHandCurrentValue[i] == (a + 1)) {
                    tempTotal[a] = tempTotal[a] + 1;
                }
                if (p2HoldHandValue[i] == (a + 1)) {
                    tempTotal[a] = tempTotal[a] + 1;
                }
            }
        }
        for (int b=0; b<5; b++) {
            if (tempTotal[b] >= 3) {
                for (int c=0; c<5; c++) {
                    fourOfAKind = fourOfAKind + p2DiceHandCurrentValue[c] + p2HoldHandValue[c];
                }
            }
        }
    }
    return fourOfAKind;
}
public int fullHouse () {
	int fullHouse = 0;
	int[] tempTotal = new int[5];
	for (int i= 1; i <= 6; i++) {
		for (int a = 0; a <= 5; a++) {
			if (p1Turn) {
				if (p1DiceHandCurrentValue[i] == a || p1HoldHandValue[i] == a) {
					tempTotal[a] = tempTotal[a] + 1;
				}
			} else if (p2Turn) {
				if (p2DiceHandCurrentValue[i] == a || p2HoldHandValue[i] == a) {
					tempTotal[a] = tempTotal[a] + 1;
				}
			}
		}
	}
	for (int b=1; b <= 5; b++) {
		if (tempTotal[b] == 3) {
			int temp = b;
			for (int c=1; c <= 5; c++) {
				if (tempTotal[c] != temp && tempTotal[c] == 3) {
					fullHouse = 25;
				} else {
				    fullHouse = 0;
                }
			}
		}
	}
	return fullHouse;
}
public int smallStraight () {
	int smallStraight = 0;
	int[] tempTotal = new int[6]; //temptotal is the number of each type of dice (i.e 3 ones)
	for (int i= 1; i <= 6; i++) { //loop though all types of dice
		for (int a = 0; a <= 5; a++) { //lopp though the current hand and hold hand for each player
			if (p1Turn) {
				if (p1DiceHandCurrentValue[a] == i || p1HoldHandValue[a] == i) {
					tempTotal[i] = tempTotal[i] + 1;
				}
			} else if (p2Turn) {
				if (p2DiceHandCurrentValue[a] == i || p2HoldHandValue[a] == i) {
					tempTotal[i] = tempTotal[i] + 1;
				}
			}
		}
	}
	if (tempTotal[0] == 1 && tempTotal[1] == 1 && tempTotal[2] == 1 && tempTotal[3] == 1) { //check combinations to see is any of them work
		smallStraight = 30;
	} else if (tempTotal[1] == 1 && tempTotal[2] == 1 && tempTotal[3] == 1 && tempTotal[4] == 1) {
		smallStraight = 30;
	} else if (tempTotal[2] == 1 && tempTotal[3] == 1 && tempTotal[4] == 1 && tempTotal[5] == 1) {
		smallStraight = 30;
	} else {
		smallStraight = 0;
	}
	return smallStraight;
}
public int largeStraight () {
		int largeStraight = 0;
		int[] tempTotal = new int[6]; //temptotal is the number of each type of dice (i.e 3 ones)
		for (int i= 1; i <= 6; i++) { //loop though all types of dice
			for (int a = 0; a <= 5; a++) { //lopp though the current hand and hold hand for each player
				if (p1Turn) {
					if (p1DiceHandCurrentValue[a] == i || p1HoldHandValue[a] == i) {
						tempTotal[i] = tempTotal[i] + 1;
					}
				} else if (p2Turn) {
					if (p2DiceHandCurrentValue[a] == i || p2HoldHandValue[a] == i) {
						tempTotal[i] = tempTotal[i] + 1;
					}
				}
			}
		}
		if (tempTotal[0] == 1 && tempTotal[1] == 1 && tempTotal[2] == 1 && tempTotal[3] == 1 && tempTotal[4] ==1) {
			largeStraight = 40;
		} else if (tempTotal[1] == 1 && tempTotal[2] == 1 && tempTotal[3] ==1 && tempTotal[4] == 1 && tempTotal[5] ==1) {
			largeStraight = 40;
		} else {
			largeStraight = 0;
		}
		return largeStraight;
	}
public int chance () {
	int p1temp = 0;
	int p2temp = 0;
	int chance = 0;
	for (int i=0; i < 5; i++) {
		p1temp = p1temp + p1DiceHandCurrentValue[i] + p1HoldHandValue[i];
		p2temp = p2temp + p2DiceHandCurrentValue[i] + p2HoldHandValue[i];
	}
	if (p1Turn) {
		chance = p1temp;
	} else if (p2Turn) {
		chance = p2temp;
	}
	return chance;
}
public int yahtzee () {
	int yahtzeeTotal = 0;
	int[] tempTotal = new int[6];
	if (p1Turn) {
		for (int a=0; a < 6 ; a++) { //these are the possible values of the dice + 1
			for (int i=0; i < 5; i++) { //these are all of the dice currently in play
				if (p1DiceHandCurrentValue[i] == (a + 1)) {
					tempTotal[a] = tempTotal[a] + 1;
				}
				if (p1HoldHandValue[i] == (a + 1)) {
					tempTotal[a] = tempTotal[a] + 1;
				}
			}
		}
		for (int b=0; b < 6; b++) {
			if (tempTotal[b] == 5) {
				yahtzeeTotal = 50;
			} else {
				yahtzeeTotal = 0;
			}
		}
	} else if (p2Turn) { //this is the same section as before but for player 2
		for (int a=0; a < 6; a++) {
			for (int i=0; i < 5; i++) {
				if (p2DiceHandCurrentValue[i] == (a + 1)) {
					tempTotal[a] = tempTotal[a] + 1;
				}
				if (p2HoldHandValue[i] == (a + 1)) {
					tempTotal[a] = tempTotal[a] + 1;
				}
			}
		}
	}
	for (int b=0; b < 6; b++) {
		if (tempTotal[b] == 5) {
			yahtzeeTotal = 50;
		} else {
			yahtzeeTotal = 0;
		}
	}
	return yahtzeeTotal;
}
public void endTurn(MouseEvent e) {
    for (int a = 0; a < 51; a++) {
        if (e.getSource() == score[a] && p1Turn && a < 33) {
            //code to end turn for only p1
            turnCount = 3;
            turnCounter.setText("Turns Left: " + turnCount + "");
            p1Turn = !p1Turn;
            p2Turn = !p2Turn;
            if (p1Turn) {
                p1Title.setBorder(BorderFactory.createLineBorder(Color.RED));
                p2Title.setBorder(null);
            } else if (p2Turn) {
                p2Title.setBorder(BorderFactory.createLineBorder(Color.RED));
                p1Title.setBorder(null);
            }
            for (int b = 0; b < 5; b++) {
                p1DiceHandCurrent[b].setIcon(new ImageIcon("src//images//Dice//Dice0.png"));
                p1DiceHandCurrentValue[b] = 0;
                p1HoldHand[b].setIcon(new ImageIcon("src//images//Dice//DiceHold.png"));
                p1HoldHandValue[b] = 0;
                p1Holds[b] = false;

                p2DiceHandCurrent[b].setIcon(new ImageIcon("src//images//Dice//Dice0.png"));
                p2DiceHandCurrentValue[b] = 0;
                p2HoldHand[b].setIcon(new ImageIcon("src//images//Dice//DiceHold.png"));
                p2HoldHandValue[b] = 0;
                p2Holds[b] = false;
            }
        } else if (e.getSource() == score[a] && p2Turn && a > 33) {
            //code to end turn for only p2
            turnCount = 3;
            turnCounter.setText("Turns Left: " + turnCount + "");
            p1Turn = !p1Turn;
            p2Turn = !p2Turn;
            if (p1Turn) {
                p1Title.setBorder(BorderFactory.createLineBorder(Color.RED));
                p2Title.setBorder(null);
            } else if (p2Turn) {
                p2Title.setBorder(BorderFactory.createLineBorder(Color.RED));
                p1Title.setBorder(null);
            }
            for (int b = 0; b < 5; b++) {
                p1DiceHandCurrent[b].setIcon(new ImageIcon("src//images//Dice//Dice0.png"));
                p1DiceHandCurrentValue[b] = 0;
                p1HoldHand[b].setIcon(new ImageIcon("src//images//Dice//DiceHold.png"));
                p1HoldHandValue[b] = 0;
                p1Holds[b] = false;

                p2DiceHandCurrent[b].setIcon(new ImageIcon("src//images//Dice//Dice0.png"));
                p2DiceHandCurrentValue[b] = 0;
                p2HoldHand[b].setIcon(new ImageIcon("src//images//Dice//DiceHold.png"));
                p2HoldHandValue[b] = 0;
                p2Holds[b] = false;
            }
        }
    }
}
}
