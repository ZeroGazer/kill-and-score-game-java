package hk.ust.isom3320.project;

import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ranking{
	private static final String DATA_PATH = new String("ranking.txt");
	private final int MAX_NUM_OF_PLAYER = 10;
	private String[]data = new String[MAX_NUM_OF_PLAYER];
	private File file = new File(DATA_PATH);
	private Scanner dataInput;
	private Image rankingImage;
	private ImageIcon rankingIcon;
	private JLabel rankingLabel;
	public JPanel rankingPanel;

	Ranking(){
		//input data
		try{
		dataInput = new Scanner(file);
			for(int i = 0; i < MAX_NUM_OF_PLAYER; i++){
					data[i] = dataInput.next();
			}
		}
		catch (Exception ex){
		}
		finally{
		//dataInput.close();
		}
		rankingIcon  = new ImageIcon(getClass().getResource("images/menu/Ranking.gif"));
		rankingLabel = new JLabel(rankingIcon);
		rankingLabel.setSize(rankingIcon.getIconWidth(), rankingIcon.getIconHeight());
		rankingLabel.setVisible(true);
		
		initializeRankingPanel();
	}

	//check the input score is in top 10
	public boolean isTop10(int score){
		if(score>Integer.parseInt(data[9]))
			return true;
		else
			return false;
	}

	//save the player name and score
	public void addPlayer(int score){
		data[9]= Integer.toString(score);
		//sort the data
		for(int i = MAX_NUM_OF_PLAYER-1; i > 0; i--){
			for(int j = i; j > 0; j--){
				if((Integer.parseInt(data[i]))>(Integer.parseInt(data[j-1]))){
				String temp;
				temp = data[j];
				data[j]= data[j-1];
				data[j-1] = temp;
				}
			}
		}
		//save data
		try{
			PrintWriter dataOutput = new PrintWriter(file);
			for(int i = 0; i < MAX_NUM_OF_PLAYER; i++){
				dataOutput.print(data[i] + " ");
			}
		}
		catch(Exception ex){
		}
		finally{
			//dataOutput.close();
		}
		initializeRankingPanel();
		rankingPanel.setVisible(true);
	}
	
	public void initializeRankingPanel(){
		rankingPanel = new JPanel();
		rankingPanel.setLayout(new GridLayout(11,2));
		rankingPanel.add(rankingLabel);
		JLabel rank1, rank2, rank3, rank4, rank5, rank6, rank7, rank8, rank9, rank10;
		JLabel score, score1, score2, score3, score4, score5, score6, score7, score8, score9, score10;
		Font font = new Font("Times New Roman", 1, 35);
		rank1 = new JLabel("1", SwingConstants.CENTER);
		rank2 = new JLabel("2", SwingConstants.CENTER);
		rank3 = new JLabel("3", SwingConstants.CENTER);
		rank4 = new JLabel("4", SwingConstants.CENTER);
		rank5 = new JLabel("5", SwingConstants.CENTER);
		rank6 = new JLabel("6", SwingConstants.CENTER);
		rank7 = new JLabel("7", SwingConstants.CENTER);
		rank8 = new JLabel("8", SwingConstants.CENTER);
		rank9 = new JLabel("9", SwingConstants.CENTER);
		rank10 = new JLabel("10", SwingConstants.CENTER);
		score = new JLabel("Score", SwingConstants.CENTER);
		score1 = new JLabel(data[0], SwingConstants.CENTER);
		score2 = new JLabel(data[1], SwingConstants.CENTER);
		score3 = new JLabel(data[2], SwingConstants.CENTER);
		score4 = new JLabel(data[3], SwingConstants.CENTER);
		score5 = new JLabel(data[4], SwingConstants.CENTER);
		score6 = new JLabel(data[5], SwingConstants.CENTER);
		score7 = new JLabel(data[6], SwingConstants.CENTER);
		score8 = new JLabel(data[7], SwingConstants.CENTER);
		score9 = new JLabel(data[8], SwingConstants.CENTER);
		score10 = new JLabel(data[9], SwingConstants.CENTER);
		rank1.setFont(font);
		rank2.setFont(font);
		rank3.setFont(font);
		rank4.setFont(font);
		rank5.setFont(font);
		rank6.setFont(font);
		rank7.setFont(font);
		rank8.setFont(font);
		rank9.setFont(font);
		rank10.setFont(font);
		score.setFont(font);
		score1.setFont(font);
		score2.setFont(font);
		score3.setFont(font);
		score4.setFont(font);
		score5.setFont(font);
		score6.setFont(font);
		score7.setFont(font);
		score8.setFont(font);
		score9.setFont(font);
		score10.setFont(font);
		rank1.setForeground(Color.RED);
		rank2.setForeground(Color.RED);
		rank3.setForeground(Color.RED);
		rank4.setForeground(Color.RED);
		rank5.setForeground(Color.RED);
		rank6.setForeground(Color.RED);
		rank7.setForeground(Color.RED);
		rank8.setForeground(Color.RED);
		rank9.setForeground(Color.RED);
		rank10.setForeground(Color.RED);
		score.setForeground(Color.RED);
		score1.setForeground(Color.RED);
		score2.setForeground(Color.RED);
		score3.setForeground(Color.RED);
		score4.setForeground(Color.RED);
		score5.setForeground(Color.RED);
		score6.setForeground(Color.RED);
		score7.setForeground(Color.RED);
		score8.setForeground(Color.RED);
		score9.setForeground(Color.RED);
		score10.setForeground(Color.RED);
		rankingPanel.add(score);
		rankingPanel.add(rank1);
		rankingPanel.add(score1);
		rankingPanel.add(rank2);
		rankingPanel.add(score2);
		rankingPanel.add(rank3);
		rankingPanel.add(score3);
		rankingPanel.add(rank4);
		rankingPanel.add(score4);
		rankingPanel.add(rank5);
		rankingPanel.add(score5);
		rankingPanel.add(rank6);
		rankingPanel.add(score6);
		rankingPanel.add(rank7);
		rankingPanel.add(score7);
		rankingPanel.add(rank8);
		rankingPanel.add(score8);
		rankingPanel.add(rank9);
		rankingPanel.add(score9);
		rankingPanel.add(rank10);
		rankingPanel.add(score10);
		rankingPanel.setVisible(false);
	}
}