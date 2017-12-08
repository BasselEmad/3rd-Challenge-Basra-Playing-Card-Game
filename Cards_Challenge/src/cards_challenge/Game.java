/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards_challenge;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Bassel
 */
public class Game extends JFrame
{
    static final GraphicsEnvironment Graphics_Environment_Object = GraphicsEnvironment.getLocalGraphicsEnvironment();
    static final int Cards_Total_Number = 53;
    
    public static ArrayList <Playing_Cards> Cards = new ArrayList <Playing_Cards> (Cards_Total_Number);
    public static int Game_Height = Graphics_Environment_Object.getMaximumWindowBounds().height;
    public static int Game_Width = Graphics_Environment_Object.getMaximumWindowBounds().width;
    public static int Card_Height = 150;
    public static int Card_Width = 90;
    public static int Player_1_Score = 0;
    public static int Player_2_Score = 0;
    
    int Counter = 0;
    int Modulo = 0;
    
    String Numbers[] = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Cards Back"};
    
    String Cards_Path = "C:\\Users\\Bassel\\Documents\\NetBeansProjects\\Cards_Challenge\\Cards\\";
    
    String Cards_Images[] =
    {
        Cards_Path + "Ace Of Spade.png",   Cards_Path + "Ace Of Heart.png",   Cards_Path + "Ace Of Club.png",    Cards_Path + "Ace Of Diamond.png",
        Cards_Path + "2 Of Spade.png",     Cards_Path + "2 Of Heart.png",     Cards_Path + "2 Of Club.png",      Cards_Path + "2 Of Diamond.png",
        Cards_Path + "3 Of Spade.png",     Cards_Path + "3 Of Heart.png",     Cards_Path + "3 Of Club.png",      Cards_Path + "3 Of Diamond.png",
        Cards_Path + "4 Of Spade.png",     Cards_Path + "4 Of Heart.png",     Cards_Path + "4 Of Club.png",      Cards_Path + "4 Of Diamond.png",
        Cards_Path + "5 Of Spade.png",     Cards_Path + "5 Of Heart.png",     Cards_Path + "5 Of Club.png",      Cards_Path + "5 Of Diamond.png",
        Cards_Path + "6 Of Spade.png",     Cards_Path + "6 Of Heart.png",     Cards_Path + "6 Of Club.png",      Cards_Path + "6 Of Diamond.png",
        Cards_Path + "7 Of Spade.png",     Cards_Path + "7 Of Heart.png",     Cards_Path + "7 Of Club.png",      Cards_Path + "7 Of Diamond.png",
        Cards_Path + "8 Of Spade.png",     Cards_Path + "8 Of Heart.png",     Cards_Path + "8 Of Club.png",      Cards_Path + "8 Of Diamond.png",
        Cards_Path + "9 Of Spade.png",     Cards_Path + "9 Of Heart.png",     Cards_Path + "9 Of Club.png",      Cards_Path + "9 Of Diamond.png",
        Cards_Path + "10 Of Spade.png",    Cards_Path + "10 Of Heart.png",    Cards_Path + "10 Of Club.png",     Cards_Path + "10 Of Diamond.png",
        Cards_Path + "Jack Of Spade.png",  Cards_Path + "Jack Of Heart.png",  Cards_Path + "Jack Of Club.png",   Cards_Path + "Jack Of Diamond.png",
        Cards_Path + "Queen Of Spade.png", Cards_Path + "Queen Of Heart.png", Cards_Path + "Queen Of Club.png",  Cards_Path + "Queen Of Diamond.png",
        Cards_Path + "King Of Spade.png",  Cards_Path + "King Of Heart.png",  Cards_Path + "King Of Club.png",   Cards_Path + "King Of Diamond.png",
        Cards_Path + "Cards Back.png"
    };
    
    public static JLabel First_Player_Score = new JLabel("Score: " + Player_1_Score);
    public static JLabel Second_Player_Score = new JLabel("Score: " + Player_2_Score);
    public static JLabel Player_Turn_Arrow = new JLabel();
    public static JLabel Playing_Place = new JLabel();
    public static JLabel Background = new JLabel();
    public static JLabel Rounds = new JLabel();
    JLabel First_Player = new JLabel("First Player");
    JLabel Second_Player = new JLabel("Second Player");
    JLabel Card_Back_Label = new JLabel();
    
    public Game()
    {
        setTitle("Cards Challenge");
        setSize(Game_Width, Game_Height);
        setLayout(null);
        
        for(int i = 0; i < Cards_Total_Number; i++)
        {
            Playing_Cards Card = new Playing_Cards();
            JLabel Card_Label = new JLabel();
            
            Modulo = i % 4;
            
            switch (Modulo)
            {
                case 0:
                {
                    Counter = Counter + 1;
                    Card.setCard_Shape(Playing_Cards.Shapes_Names.Spade);
                    break;
                }
                case 1:
                {
                    Card.setCard_Shape(Playing_Cards.Shapes_Names.Heart);
                    break;
                }
                case 2:
                {
                    Card.setCard_Shape(Playing_Cards.Shapes_Names.Club);
                    break;
                }
                case 3:
                {
                    Card.setCard_Shape(Playing_Cards.Shapes_Names.Diamond);
                    break;
                }
            }
            Card.setCard_Value(Counter);
            
            Card.setCard_Name(Numbers[Counter - 1]);
            
            Card.setCard_Image(Cards_Images[i]);
            
            Card_Label.setIcon(new ImageIcon(new ImageIcon(Card.getCard_Image()).getImage().getScaledInstance(Card_Width, Card_Height, Image.SCALE_DEFAULT)));
            
            Card_Label.setBounds(20, 20, Card_Width, Card_Height);
            
            Card.setCards_Holder(Card_Label);
            
            if(i < Cards_Total_Number - 1)
            {
                Cards.add(Card);
            }
            else
            {
                Card_Back_Label = Card_Label;
            }
        }
        First_Player_Score.setBounds(((Game_Width - Game.Card_Width) / 32) + (Game.Card_Width * 2) - 40, (Game_Height - Game.Card_Height) - 50, 200, 40);
        First_Player_Score.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 30));
        First_Player_Score.setForeground(Color.BLACK);
        
        Second_Player_Score.setBounds((Game_Width - Game.Card_Width) - 300, (Game_Height - Game.Card_Height) - 50, 250, 40);
        Second_Player_Score.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 30));
        Second_Player_Score.setForeground(Color.BLACK);
        
        Player_Turn_Arrow.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Bassel\\Documents\\NetBeansProjects\\Cards_Challenge\\Player Turn Arrow.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        Player_Turn_Arrow.setSize(100, 100);
        Player_Turn_Arrow.setVisible(false);
        
        Playing_Place.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Bassel\\Documents\\NetBeansProjects\\Cards_Challenge\\Playing Place.png").getImage().getScaledInstance((int) (Card_Width * 1.5), (int) (Card_Height * 1.5), Image.SCALE_DEFAULT)));
        Playing_Place.setBounds(((Game_Width - Playing_Place.getSize().width) / 2) - 80, ((Game_Height - Playing_Place.getSize().height) / 2) - 50, (int) (Card_Width * 1.5), (int) (Card_Height * 1.5));
        
        Background.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Bassel\\Documents\\NetBeansProjects\\Cards_Challenge\\Background.png").getImage().getScaledInstance(Game_Width, Game_Height, Image.SCALE_DEFAULT)));
        Background.setBounds(0, 0, Game_Width, Game_Height);
        
        Rounds.setBounds((Game_Width - 250) / 2, Game_Height / 16, 200, 40);
        Rounds.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 30));
        Rounds.setForeground(Color.BLACK);
        
        First_Player.setBounds(((Game_Width - Game.Card_Width) / 32) + (Game.Card_Width * 2) - 40, (Game_Height - Game.Card_Height) - 80, 200, 40);
        First_Player.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 30));
        First_Player.setForeground(Color.BLACK);
        
        Second_Player.setBounds(Game_Width - 390, (Game_Height - Game.Card_Height) - 80, 250, 40);
        Second_Player.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 30));
        Second_Player.setForeground(Color.BLACK);
        
        for(int i = 0; i < 2; i++)
        {
            Collections.shuffle(Cards);
        }
        
        add(Card_Back_Label);
        
        Cards.forEach((C) ->
        {
            add(C.getCards_Holder());
        });
        
        add(First_Player_Score);
        add(Second_Player_Score);
        add(Player_Turn_Arrow);
        add(Playing_Place);
        add(Rounds);
        add(First_Player);
        add(Second_Player);
        add(Background);
    }
}