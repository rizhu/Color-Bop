package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreCounter {
    Ball mBall;

    Preferences mPrefs = Gdx.app.getPreferences("highScore");

    public int mScore;
    public int mHighScore;

    public ScoreCounter() {
        mScore = 0;
        mHighScore = mPrefs.getInteger("highScore", 0);
    }

    public void setBall(Ball ball) {
        mBall = ball;
    }

    public void update() {
        mScore = mBall.sTapCount;
        if (mBall.mFailCalled) {
            if (mScore > mHighScore) {
                mHighScore = mScore;
                mPrefs.putInteger("highScore", mHighScore);
                mPrefs.flush();
                mScore = 0;
            }
        }
    }

}

