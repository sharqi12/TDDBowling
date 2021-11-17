package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
     int numOfFrame=0;
     int finalScore=0;
     ArrayList<Frame> frames = new ArrayList<Frame>(); //out of minimum 10
     List<Frame> currentResultFrame = new ArrayList<Frame>();

    public void addScore(int score){
        this.finalScore += score;
    }

    //RZUT
    public void roll(int score){

        if (frames.size()==0){
            frames.add(new Frame(numOfFrame));
        }
        else if (frames.get(numOfFrame).isComplete){
            numOfFrame++;
            frames.add(new Frame(numOfFrame));
        }
        frames.get(numOfFrame).initializeAttempts(score);


        currentResultFrame = currentResultUpdate(frames);
        finalScore = getFinalScore(currentResultFrame);


    }

    public boolean isPreviousCompleted(int numOfFrame){
        if(frames.size()==0)return true;
        else if(frames.size()==1)return true;
        else if(frames.get(numOfFrame-1).isComplete) return true;
        else return false;
    }

    public List<Frame> currentResultUpdate(ArrayList<Frame> frames){
        List<Frame> currentResult = new ArrayList<Frame>();
        for(Frame f: frames){
            currentResult.add(new Frame(f));
        }
            if(currentResult.size()>1){ // liczy tylko jesli wykonano wiecej niz jeden ruch
                if(currentResult.size()!=10) { //Jeśli nie jest ostatnia ramka/tura
                    for (int i=1; i<currentResult.size();i++){ //liczy od drugiej ramki
                        if(currentResult.get(i-1).isStrike) { //Jesli jest Strike w poprzedniej rundzie
                            if (currentResult.get(i).isStrike){//czy aktualna runda zawiera strike
                                currentResult.get(i - 1).frameScore += frames.get(i).firstAttemptScore;
                                if(currentResult.size() >= i+2){ //sprawdza czy aktualnie wykonany jest juz nastepny rzut
                                    currentResult.get(i - 1).frameScore += frames.get(i+1).firstAttemptScore;
                                }
                            }else {
                                if(currentResult.get(i).isComplete)
                                    currentResult.get(i - 1).frameScore += frames.get(i).getFrameScore();
                            }
                        }
                        else if(currentResult.get(i-1).isSpare){ //Jesli jest Spare w poprzedniej rundzie
                            currentResult.get(i - 1).frameScore += frames.get(i).firstAttemptScore;
                        }
                        //}
                    }
                }
                else if(currentResult.size()==10){ // jeśli jest ostatnia ramka/tura
                    for (int i=1; i<currentResult.size()-1;i++){ //liczy od drugiej ramki
                        if(currentResult.get(i-1).isStrike) { //Jesli jest Strike w poprzedniej rundzie
                            if (currentResult.get(i).isStrike){//czy aktualna runda zawiera strike
                                currentResult.get(i - 1).frameScore += frames.get(i).firstAttemptScore;
                                if(currentResult.size() >= i+1){ //sprawdza czy aktualnie wykonany jest juz nastepny rzut
                                    currentResult.get(i - 1).frameScore += frames.get(i+1).firstAttemptScore;
                                }
                            }else {
                                if(currentResult.get(i).isComplete)
                                    currentResult.get(i - 1).frameScore += frames.get(i).getFrameScore();
                            }
                        }
                        else if(currentResult.get(i-1).isSpare){ //Jesli jest Spare w poprzedniej rundzie
                            currentResult.get(i - 1).frameScore += frames.get(i).firstAttemptScore;
                        }
                        //}
                    }
                    if(currentResult.get(9).attempt==2){//Jeśli jest pierwszy rzut ostatniej ramki
                        if(currentResult.get(8).isStrike) {//Jeśli był strike w przedostatniej ramce
                            currentResult.get(8).frameScore += currentResult.get(9).firstAttemptScore;
                        }
                        if(currentResult.get(8).isSpare){//Jeśli był Spare w przedostatniej ramce/turze
                            currentResult.get(8).frameScore += currentResult.get(9).firstAttemptScore;
                        }

                    }else if(currentResult.get(9).attempt==3){//Jeśli jest drugi rzut ostatniej ramki
                        if(currentResult.get(8).isStrike) {//Jeśli był strike w przedostatniej ramce
                            currentResult.get(8).frameScore += currentResult.get(9).firstAttemptScore;
                            currentResult.get(8).frameScore += currentResult.get(9).secondAttemptScore;
                        }
                        if(currentResult.get(9).firstAttemptScore==10){ //Strike w pierwszym rzucie ostatniej rundy
                            currentResult.get(9).firstAttemptScore += currentResult.get(9).secondAttemptScore;
                        }

                    }else if (currentResult.get(9).attempt==4){//Jeśli jest trzeci rzut ostatniej ramki
                        if(currentResult.get(8).isStrike) {//Jeśli był strike w przedostatniej ramce
                            currentResult.get(8).frameScore += currentResult.get(9).firstAttemptScore;
                            currentResult.get(8).frameScore += currentResult.get(9).secondAttemptScore;
                        }
                        if(currentResult.get(8).isSpare){//Jeśli był Spare w przedostatniej ramce/turze
                            currentResult.get(8).frameScore += currentResult.get(9).firstAttemptScore;
                        }
                        if(currentResult.get(9).firstAttemptScore==10){ //Strike w pierwszym rzucie ostatniej rundy
                            currentResult.get(9).firstAttemptScore += currentResult.get(9).secondAttemptScore;
                            currentResult.get(9).firstAttemptScore += currentResult.get(9).thirdAttemptScore;
                        }
                        if(currentResult.get(9).firstAttemptScore + currentResult.get(9).secondAttemptScore == 10){ //Spare w ostatniej rundzie
                            currentResult.get(9).secondAttemptScore += currentResult.get(9).thirdAttemptScore;
                        }

                    }

                }

            }

        return currentResult;
    }

    public int getFinalScore(List<Frame> listOfFinalResults){
        int score = 0;
        for(Frame f: listOfFinalResults){
            score += f.getFrameScore();
        }
        return score;
    }

}
