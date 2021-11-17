package com.company;

public class Frame {

    public int numberOfFrame =0;
    public Boolean isComplete = false;
    public Boolean isStrike = false;
    public Boolean isSpare = false;
    public int frameScore = 0;
    public int firstAttemptScore=0;
    public int secondAttemptScore=0;
    public int thirdAttemptScore=0;
    public int attempt =1;

    public Frame(int numberOfFrame) {
        this.numberOfFrame = numberOfFrame;
    }

    public Frame(Frame f) {
        this.numberOfFrame = f.numberOfFrame;
        this.isComplete = f.isComplete;
        this.isStrike = f.isStrike;
        this.isSpare = f.isSpare;
        this.frameScore = f.frameScore;
        this.firstAttemptScore = f.firstAttemptScore;
        this.secondAttemptScore = f.secondAttemptScore;
        this.thirdAttemptScore = f.thirdAttemptScore;
        this.attempt = f.attempt;
    }

    public void markAsComplete() {
        isComplete = true;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public void addScore(int newScore) {
        this.frameScore += newScore;
    }


    protected void initializeAttempts(int score) {

            if(numberOfFrame==9){ //10 RAMKA możliwe 3 rzuty
                if(attempt==1){
                    firstAttemptScore=score;
                    if(score==10)isStrike=true;
                    addScore(score);
                    attempt++;
                }
                else if(attempt==2){
                        secondAttemptScore=score;
                        addScore(score);

                        if (getFrameScore()==10){//Sprawdza czy jest spare
                            isSpare=true;
                        }
                        if(isStrike || isSpare){
                            //isComplete=true;
                        }
                        else isComplete=true;

                    attempt++;
                }
                else if(attempt==3 && isComplete == false){
                    thirdAttemptScore=score;
                    addScore(score);
                    attempt++;

                }
            }
            else { //NORMALNA RAMKA 1-9
                //Dwa rzuty albo jeden jeśli strike
                if (score==10){//Jeśli strike to jeden rzut
                    isStrike=true;
                    firstAttemptScore=score;
                    addScore(score);
                    isComplete =true;
                }
                else{ //nie ma strike
                    if (attempt==1){ //Pierwszy rzut
                        firstAttemptScore=score;
                        addScore(score);
                        attempt++;
                    }
                    else if (attempt==2){ //Drugi rzut
                        secondAttemptScore=score;
                        addScore(score);
                        if (getFrameScore()==10){//Sprawdza czy jest spare
                            isSpare=true;
                        }
                        isComplete=true;
                    }

                }
            }


    }


}
