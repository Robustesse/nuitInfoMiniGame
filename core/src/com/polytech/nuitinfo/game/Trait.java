package com.polytech.nuitinfo.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.polytech.nuitinfo.miniMain;

import java.util.ArrayList;

/**
 * Created by Barnabé on 12/7/2017.
 */

public class Trait {
    public static final int LONGUEUR = 760;
    private int positionX;
    private Texture texture;
    private Character bottom;

    public Trait(int positionX, Character c){
        this.positionX = positionX;
        texture = new Texture("trait.png");
        bottom = c;
    }

    public ArrayList<Intersection> getListeIntersectionsAPartirDe(Plateau plateau, int positionFrom){

        ArrayList<Intersection> fullList = plateau.getListeIntersections();
        ArrayList<Intersection> sortedList = new ArrayList<Intersection>();
        if(fullList.size() > 0){
            for(Intersection i: fullList){
                if(i.getPosition() < positionFrom && i.getTrait1().equals(this) || i.getTrait2().equals(this)){
                    sortedList.add(i);
                }
            }
        }
        return sortedList;
    }

    public Character getBottom(){
        return bottom;
    }

    public int getPositionX(){return positionX;}

    public boolean equals(Trait t){
        return this.positionX == t.getPositionX();
    }

    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(texture, positionX-5, miniMain.HEIGHT-LONGUEUR, 10, LONGUEUR);
        sb.end();
        bottom.render(sb);
    }


    public void setCharacter(Character character) {
        this.bottom = character;
    }
}
