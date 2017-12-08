package com.polytech.nuitinfo.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.polytech.nuitinfo.miniMain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Barnabé on 12/7/2017.
 */

public class Plateau {
    private ArrayList<Trait> listeTraits;
    private ArrayList<Intersection> listeIntersections;

    private ArrayList<Character> listCharacter;
    private int difficulte;

    public Plateau(int difficulte){
        listCharacter = new ArrayList<Character>();
        listeIntersections = new ArrayList<Intersection>();
        listeTraits = new ArrayList<Trait>();
        ArrayList<String> formes = new ArrayList<String>();
        formes.add("carre");
        formes.add("cercle");
        formes.add("triangle");
        //formes.add("etoile");
        Random r = new Random();
        for(int i = 1; i < 4; i++){
            listeTraits.add(new Trait(i*100, null));
        }

        for(int i = 0; i < listeTraits.size(); i++){
            int k = r.nextInt(formes.size());
            String chosen = formes.get(k);
            listeTraits.get(i).setCharacter(new Character(listeTraits.get(i), chosen, 0, listeTraits.get(i).LONGUEUR));
        }

        listCharacter.add(new Character(this.getFirstTrait(), "Carre", 100, miniMain.HEIGHT-25));
    }

    public Character getSlowerCharacter(){
        Character slowerCharacter = listCharacter.get(0);
        for(int i = 1; i < listCharacter.size(); i++){
            if(listCharacter.get(i).getVitesse().y < slowerCharacter.getVitesse().y)
                slowerCharacter = listCharacter.get(i);
        }
        return slowerCharacter;
    }

    /**
     * Retourne la liste des intersections qui se trouvent en dessous de la position entrée en paramètre*
     * @param positionFrom
     * @return listeIntersectionsAPartirDe
     */
    public ArrayList<Intersection> getListeToutesIntersectionsAPartirDe(int positionFrom){
        ArrayList<Intersection> listeIntersectionsAPartirDe = listeIntersections;
        for(Intersection i: listeIntersections){
            if (i.getPosition() < positionFrom) {
                listeIntersectionsAPartirDe.remove(i);
            }
        }
        return listeIntersectionsAPartirDe;
    }

    public ArrayList<Intersection> getListeIntersections(){
        return this.listeIntersections;
    }

    public void checkIntersection(){
        for(Intersection i: listeIntersections){
            if(i.getPosition()-listCharacter.get(0).getPosition() < 1 && i.getPosition()-listCharacter.get(0).getPosition() > -1){
                if(i.contains(listCharacter.get(0).getTrait()) && !i.contains(listCharacter.get(0))){
                    listCharacter.get(0).setTrait(i.oppositeTrait(listCharacter.get(0).getTrait()));
                    i.addChar(listCharacter.get(0));
                }
            }
        }
    }

    public void render(SpriteBatch sb){
        //System.out.println(listeIntersections.size());
        for(Trait t: listeTraits){
            t.render(sb);
        }
        for(Intersection i: listeIntersections){
            i.render(sb);
        }
    }

    public Trait getFirstTrait(){
        return listeTraits.get(0);
    }

    public void addIntersection(int x, int y) {
        Trait[] temp = getCloserTraits(x);
        this.listeIntersections.add(new Intersection(temp[0], temp[1], y));
    }

    public Trait[] getCloserTraits(int positionX){
        ArrayList<Integer> distances = new ArrayList<Integer>();
        ArrayList<Trait> temp = new ArrayList<Trait>(listeTraits);
        for(int i = 0; i < temp.size(); i++){
            distances.add(Math.abs(listeTraits.get(i).getPositionX()-positionX));
        }
        int max = -100;
        int maxIndex = -10;
        for(int i = 0; i < distances.size(); i++){
            if(distances.get(i) > max){
                max = distances.get(i);
                maxIndex = i;
            }
        }
        System.out.println(listeIntersections.size());

        if(maxIndex >= 0){
            temp.remove(maxIndex);
        }
        Trait[] fin = {temp.get(0), temp.get(1)};
        return fin;
    }

}
