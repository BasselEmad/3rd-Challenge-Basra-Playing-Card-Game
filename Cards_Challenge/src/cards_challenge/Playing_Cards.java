/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards_challenge;

import javax.swing.JLabel;

/**
 *
 * @author Bassel
 */
public class Playing_Cards {
    public enum Shapes_Names{Spade, Heart, Club, Diamond};
    private Shapes_Names Card_Shape;
    private int Card_Value;
    private String Card_Name;
    private String Card_Image;
    private JLabel Cards_Holder;

    public Shapes_Names getCard_Shape() {
        return Card_Shape;
    }

    public void setCard_Shape(Shapes_Names Card_Shape) {
        this.Card_Shape = Card_Shape;
    }

    public int getCard_Value() {
        return Card_Value;
    }

    public void setCard_Value(int Card_Value) {
        this.Card_Value = Card_Value;
    }

    public String getCard_Name() {
        return Card_Name;
    }

    public void setCard_Name(String Card_Name) {
        this.Card_Name = Card_Name;
    }

    public String getCard_Image() {
        return Card_Image;
    }

    public void setCard_Image(String Card_Image) {
        this.Card_Image = Card_Image;
    }

    public JLabel getCards_Holder() {
        return Cards_Holder;
    }

    public void setCards_Holder(JLabel Cards_Holder) {
        this.Cards_Holder = Cards_Holder;
    }
}