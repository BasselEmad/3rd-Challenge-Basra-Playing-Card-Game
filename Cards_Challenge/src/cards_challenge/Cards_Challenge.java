/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards_challenge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Bassel
 */
public class Cards_Challenge
{

    /**
     */
    public static ArrayList <Playing_Cards> Cards_On_Table = new ArrayList <Playing_Cards>(4);
    public static Table Table_Object;
    public static Player_1 Player_1_Object;
    public static Player_2 Player_2_Object;
    public static int i = 0;
    public static int Distance_Between_Each_Card;
    public static Thread Thread_Object = new Thread();
    public static boolean Is_Played = false;
    public static boolean Game_Ended = false;
    public static long Start_Time;
    public static int Play_Time = 500;
    public static int Start_X;
    public static int Start_Y;
    public static int Target_X;
    public static int Target_Y;
    public static int Player_Turn = 0;
    public static int Temp_i = 4;
    public static int Player_2_Hand_Cards = 4;
    public static int Last_Player_To_Collect;
    public static int Round = 1;
    
    public static void main(String[] args) throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        // TODO code application logic here
        Game Game_Object = new Game();
        Game_Object.setVisible(true);
        Game_Object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Clip Clip_Object = AudioSystem.getClip();
        
        AudioInputStream Audio_Input_Stream_Object = AudioSystem.getAudioInputStream(new File("C:\\Users\\Bassel\\Documents\\NetBeansProjects\\Cards_Challenge\\Background Music.wav"));
        
        Clip_Object.open(Audio_Input_Stream_Object);
        
        FloatControl Gain_Control = (FloatControl) Clip_Object.getControl(FloatControl.Type.MASTER_GAIN);
        
        Gain_Control.setValue(-15.0f);
        
        Clip_Object.loop(Clip.LOOP_CONTINUOUSLY);
        
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {}
        });
        
        Put_On_Table();
        Give_Player1();
        Give_Player2();
        
        Game.Rounds.setText("Round: " + Round + " / 6");
        
        Table_Object.setVisible(true);
        
        Game.Player_Turn_Arrow.setVisible(true);
        
        while(i < Game.Cards_Total_Number && Game_Ended == false)
        {
            int Calculate_Player_Turn = Player_Turn % 2;
            
            if(Calculate_Player_Turn == 0)
            {
                Player_1_Object = new Player_1();
                
                for(Playing_Cards T : Cards_On_Table)
                {
                    try
                    {
                        T.getCards_Holder().removeMouseMotionListener(Cards_Challenge.Player_1_Object.Player_1_Handler);
                        
                        T.getCards_Holder().removeMouseMotionListener(Cards_Challenge.Player_2_Object.Player_2_Handler);
                    }
                    catch(NullPointerException e)
                    {}
                }
                while(Is_Played == false)
                {
                    System.out.print("");
                }
            }
            else 
            {
                Player_2_Object = new Player_2();
                
                for(Playing_Cards T : Cards_On_Table)
                {
                    try
                    {
                        T.getCards_Holder().removeMouseMotionListener(Cards_Challenge.Player_1_Object.Player_1_Handler);
                        T.getCards_Holder().removeMouseMotionListener(Cards_Challenge.Player_2_Object.Player_2_Handler);
                    }
                    catch(NullPointerException e)
                    {}
                }
                
                while(Is_Played == false)
                {
                    System.out.print("");
                }
            }
            if(Player_2_Hand_Cards == 0)
            {
                if(i >= Game.Cards_Total_Number - 1)
                {
                    Game_Ended = true;
                    break;
                }
                Give_Player1();
                Give_Player2();
                
                Round = Round + 1;
                
                Game.Rounds.setText("Round: " + Round + " / 6");
                
                Player_2_Hand_Cards = 4;
            }
        }
        
        Clip_Object.stop();
        
        Clip Clip_Object3 = AudioSystem.getClip();
        
        AudioInputStream Audio_Input_Stream_Object3 = AudioSystem.getAudioInputStream(new File("C:\\Users\\Bassel\\Documents\\NetBeansProjects\\Cards_Challenge\\Game Over.wav"));
        
        Clip_Object3.open(Audio_Input_Stream_Object3);
        
        Clip_Object3.start();
        
        for(Playing_Cards Q : Cards_Challenge.Cards_On_Table)
        {
            if(Q.getCard_Value() != 0)
            {
                Q.getCards_Holder().setVisible(false);
                
                if(Last_Player_To_Collect == 1)
                {
                    Game.Player_1_Score = Game.Player_1_Score + 1;
                }
                else
                {
                    Game.Player_2_Score = Game.Player_2_Score + 1;
                }
            }
        }
        
        Game.First_Player_Score.setText("Score: " + Game.Player_1_Score);
        Game.Second_Player_Score.setText("Score: " + Game.Player_2_Score);
        
        int Result = 0;
        
        if(Game.Player_1_Score > Game.Player_2_Score)
        {
            Result = JOptionPane.showConfirmDialog(null, "Game Over, Player 1 Wins With Score: " + Game.Player_1_Score + ".", "Game Over", JOptionPane.DEFAULT_OPTION);
        }
        else
        {
            Result = JOptionPane.showConfirmDialog(null, "Game Over, Player 2 Wins With Score: " + Game.Player_2_Score + ".", "Game Over", JOptionPane.DEFAULT_OPTION);
        }
        
        if (Result == JOptionPane.OK_OPTION)
        {
            exit(0);
        }
    }
    public static void Put_On_Table()
    {
        while(i < Temp_i)
        {
            Cards_On_Table.add(Game.Cards.get(i));
            i = i + 1;
        }
        Table_Object = new Table();
        Table_Object.setVisible(false);
        Temp_i = Temp_i + 4;
    }
    public static void Give_Player1() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException
    {
        Distance_Between_Each_Card = 0;
        
        while(i < Temp_i)
        {
            Start_X = 20;
            Start_Y = 20;

            Target_X = ((Game.Game_Width - Game.Card_Width) / 32) + Distance_Between_Each_Card;
            Target_Y = Game.Game_Height - Game.Card_Height;
            
            Start_Time = System.currentTimeMillis();
            
            Timer timer = new Timer(0, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    long Duration = System.currentTimeMillis() - Start_Time;
                    float Progress = (float)Duration / (float)Play_Time;
                    if(Progress > 1f)
                    {
                        Progress = 1f;
                        ((Timer)(e.getSource())).stop();
                    }

                    int x = Start_X + (int)Math.round((Target_X - Start_X) * Progress);
                    int y = Start_Y + (int)Math.round((Target_Y - Start_Y) * Progress);

                    Game.Cards.get(i).getCards_Holder().setLocation(x, y);
                }
            });
            timer.start();
            Distance_Between_Each_Card = Distance_Between_Each_Card + Game.Card_Width + 20;
            
            Clip Clip_Object2 = AudioSystem.getClip();
        
            AudioInputStream Audio_Input_Stream_Object2 = AudioSystem.getAudioInputStream(new File("C:\\Users\\Bassel\\Documents\\NetBeansProjects\\Cards_Challenge\\Card Sound Effect.wav"));
        
            Clip_Object2.open(Audio_Input_Stream_Object2);
            
            Clip_Object2.start();
            
            Thread_Object.sleep(1000);
            i = i + 1;
        }
        Temp_i = Temp_i + 4;
    }
    public static void Give_Player2() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException
    {
        Distance_Between_Each_Card = 0;
        
        while(i < Temp_i)
        {
            Start_X = 20;
            Start_Y = 20;

            Target_X = ((Game.Game_Width - Game.Card_Width) - 50) - Distance_Between_Each_Card;
            Target_Y = (Game.Game_Height - Game.Card_Height);
            
            Start_Time = System.currentTimeMillis();
            
            Timer timer = new Timer(0, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    long Duration = System.currentTimeMillis() - Start_Time;
                    float Progress = (float)Duration / (float)Play_Time;
                    if(Progress > 1f)
                    {
                        Progress = 1f;
                        ((Timer)(e.getSource())).stop();
                    }

                    int x = Start_X + (int)Math.round((Target_X - Start_X) * Progress);
                    int y = Start_Y + (int)Math.round((Target_Y - Start_Y) * Progress);

                    Game.Cards.get(i).getCards_Holder().setLocation(x, y);
                }
            });
            timer.start();
            Distance_Between_Each_Card = Distance_Between_Each_Card + Game.Card_Width + 20;
            
            Clip Clip_Object2 = AudioSystem.getClip();
        
            AudioInputStream Audio_Input_Stream_Object2 = AudioSystem.getAudioInputStream(new File("C:\\Users\\Bassel\\Documents\\NetBeansProjects\\Cards_Challenge\\Card Sound Effect.wav"));
        
            Clip_Object2.open(Audio_Input_Stream_Object2);
            
            Clip_Object2.start();
            
            Thread_Object.sleep(1000);
            i = i + 1;
        }
        Temp_i = Temp_i + 4;
    }
}