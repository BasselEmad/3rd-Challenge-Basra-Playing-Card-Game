/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards_challenge;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Bassel
 */
public class Player_2
{
    boolean Card_Found = false;
    
    public Player_2()
    {
        Cards_Challenge.Is_Played = false;
        
        Game.Player_Turn_Arrow.setLocation((Game.Game_Width - Game.Card_Width) - 300, (Game.Game_Height - Game.Card_Height) - 180);
        
        Game.Cards.get(Cards_Challenge.i - 4).getCards_Holder().addMouseMotionListener(Player_2_Handler);
        Game.Cards.get(Cards_Challenge.i - 3).getCards_Holder().addMouseMotionListener(Player_2_Handler);
        Game.Cards.get(Cards_Challenge.i - 2).getCards_Holder().addMouseMotionListener(Player_2_Handler);
        Game.Cards.get(Cards_Challenge.i - 1).getCards_Holder().addMouseMotionListener(Player_2_Handler);
    }
    public void Delete_Mouse_Motion_Listener()
    {
        Game.Cards.get(Cards_Challenge.i - 4).getCards_Holder().removeMouseMotionListener(Player_2_Handler);
        Game.Cards.get(Cards_Challenge.i - 3).getCards_Holder().removeMouseMotionListener(Player_2_Handler);
        Game.Cards.get(Cards_Challenge.i - 2).getCards_Holder().removeMouseMotionListener(Player_2_Handler);
        Game.Cards.get(Cards_Challenge.i - 1).getCards_Holder().removeMouseMotionListener(Player_2_Handler);
    }
    public void If_Card_Placed()
    {
        Delete_Mouse_Motion_Listener();
        Cards_Challenge.Player_Turn = Cards_Challenge.Player_Turn + 1;
        Cards_Challenge.Player_2_Hand_Cards = Cards_Challenge.Player_2_Hand_Cards - 1;
        Game.Second_Player_Score.setText("Score: " + Game.Player_2_Score);
        Cards_Challenge.Is_Played = true;
        Cards_Challenge.Table_Object = null;
        Cards_Challenge.Table_Object = new Table();
        Cards_Challenge.Table_Object.revalidate();
        Cards_Challenge.Table_Object.repaint();
    }
    public MouseMotionListener Player_2_Handler = new MouseMotionListener()
    {
        @Override
        public void mouseDragged(MouseEvent e)
        {
            for(Playing_Cards P : Game.Cards)
            {
                if(P.getCards_Holder().equals(e.getSource()))
                {
                    P.getCards_Holder().setBounds(e.getXOnScreen() - 35,e.getYOnScreen() - 65, Game.Card_Width, Game.Card_Height);
                    
                    if((P.getCards_Holder().getBounds().getX() >= Game.Playing_Place.getX() &&
                        P.getCards_Holder().getBounds().getX() + P.getCards_Holder().getBounds().width <= Game.Playing_Place.getX() + Game.Playing_Place.getWidth()) &&
                       (P.getCards_Holder().getBounds().getY() >= Game.Playing_Place.getY() &&
                        P.getCards_Holder().getBounds().getY() + P.getCards_Holder().getBounds().height <= Game.Playing_Place.getY() + Game.Playing_Place.getHeight()))
                    {
                        try
                        {
                            Clip Clip_Object2 = AudioSystem.getClip();
        
                            AudioInputStream Audio_Input_Stream_Object2 = AudioSystem.getAudioInputStream(new File("C:\\Users\\Bassel\\Documents\\NetBeansProjects\\Cards_Challenge\\Card Sound Effect.wav"));
        
                            Clip_Object2.open(Audio_Input_Stream_Object2);
            
                            Clip_Object2.start();
                        }
                        catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex)
                        {}
                        if(P.getCard_Value() == 11 || (P.getCard_Value() == 7 && P.getCard_Shape().equals(Playing_Cards.Shapes_Names.Diamond)))
                        {
                            int Table_Cards = 0;
                            
                            for(Playing_Cards C : Cards_Challenge.Cards_On_Table)
                            {
                                if(C.getCard_Value() != 0)
                                {
                                    Table_Cards = Table_Cards + 1;
                                }
                            }
                            if(Table_Cards != 0)
                            {
                                P.getCards_Holder().setVisible(false);
                                
                                P.setCard_Value(0);
                                
                                Game.Player_2_Score = Game.Player_2_Score + 1;
                                
                                for(Playing_Cards C : Cards_Challenge.Cards_On_Table)
                                {
                                    C.getCards_Holder().setVisible(false);
                                    
                                    if(C.getCard_Value() != 0)
                                    {
                                        Game.Player_2_Score = Game.Player_2_Score + 1;
                                    }
                                    
                                    C.setCard_Value(0);
                                }
                                Cards_Challenge.Last_Player_To_Collect = 2;
                            }
                            else
                            {
                                Cards_Challenge.Cards_On_Table.add(P);
                            }
                        }
                        else
                        {
                            for(Playing_Cards Z : Cards_Challenge.Cards_On_Table)
                            {
                                for(Playing_Cards S : Cards_Challenge.Cards_On_Table)
                                {
                                    for(Playing_Cards M : Cards_Challenge.Cards_On_Table)
                                    {
                                        if(P.getCard_Value() == Z.getCard_Value() &&
                                           P.getCard_Value() == S.getCard_Value() &&
                                           P.getCard_Value() == M.getCard_Value() &&
                                           Z.getCard_Shape() != S.getCard_Shape() &&
                                           Z.getCard_Shape() != M.getCard_Shape() &&
                                           S.getCard_Shape() != M.getCard_Shape())
                                        {
                                            P.getCards_Holder().setVisible(false);
                                            P.setCard_Value(0);
                                            
                                            Z.getCards_Holder().setVisible(false);
                                            Z.setCard_Value(0);
                                            
                                            S.getCards_Holder().setVisible(false);
                                            S.setCard_Value(0);
                                            
                                            M.getCards_Holder().setVisible(false);
                                            M.setCard_Value(0);
                                            
                                            Game.Player_2_Score = Game.Player_2_Score + 4;
                                            
                                            Cards_Challenge.Last_Player_To_Collect = 2;
                                            
                                            Card_Found = true;
                                            
                                            break;
                                        }
                                    }
                                }
                            }
                            if(Card_Found == false)
                            {
                                for(Playing_Cards Z : Cards_Challenge.Cards_On_Table)
                                {
                                    for(Playing_Cards S : Cards_Challenge.Cards_On_Table)
                                    {
                                        if(P.getCard_Value() == Z.getCard_Value() && P.getCard_Value() == S.getCard_Value() && Z.getCard_Shape() != S.getCard_Shape())
                                        {
                                            P.getCards_Holder().setVisible(false);
                                            P.setCard_Value(0);
                                            
                                            Z.getCards_Holder().setVisible(false);
                                            Z.setCard_Value(0);
                                            
                                            S.getCards_Holder().setVisible(false);
                                            S.setCard_Value(0);
                                            
                                            Game.Player_2_Score = Game.Player_2_Score + 3;
                                            
                                            Cards_Challenge.Last_Player_To_Collect = 2;
                                            
                                            Card_Found = true;
                                            
                                            break;
                                        } 
                                    }
                                }
                            }
                            if(Card_Found == false)
                            {
                                for(Playing_Cards Z : Cards_Challenge.Cards_On_Table)
                                {
                                    if(P.getCard_Value() == Z.getCard_Value() && P.getCard_Shape() != Z.getCard_Shape())
                                    {
                                        P.getCards_Holder().setVisible(false);
                                        P.setCard_Value(0);
                                    
                                        Z.getCards_Holder().setVisible(false);
                                        Z.setCard_Value(0);
                                    
                                        Game.Player_2_Score = Game.Player_2_Score + 2;
                                        
                                        Cards_Challenge.Last_Player_To_Collect = 2;
                                        
                                        Card_Found = true;
                                        
                                        break;
                                    }
                                }
                            }
                            if(Card_Found == false)
                            {
                                Cards_Challenge.Cards_On_Table.add(P);
                                
                                Card_Found = true;
                            }
                        }
                        If_Card_Placed();
                        break;
                    }
                }
            }
        }
        @Override
        public void mouseMoved(MouseEvent e)
        {}
    };
}