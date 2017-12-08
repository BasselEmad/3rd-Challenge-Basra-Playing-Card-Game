/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards_challenge;

import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Bassel
 */
public class Table extends JPanel
{
    public Table()
    {
        setLayout(new FlowLayout());
        setOpaque(false);
        setBounds((Game.Game_Width / 2) - 700, (Game.Game_Height / 2) - 220, Game.Game_Width, Game.Card_Height * 3);
        
        for(Playing_Cards C : Cards_Challenge.Cards_On_Table)
        {
            add(C.getCards_Holder());
        }
        Game.Background.add(this);
    }
}