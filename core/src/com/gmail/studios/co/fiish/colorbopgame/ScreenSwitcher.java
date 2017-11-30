package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.Game;

public class ScreenSwitcher {
    LogoScreen mLogoScreen;
    GameScreen mGameScreen;
    TitleScreen mTitleScreen;
    Game mGame;

    public ScreenSwitcher(Game game, LogoScreen logoScreen, TitleScreen titleScreen, GameScreen gameScreen) {
        mGame = game;
        mLogoScreen = logoScreen;
        mTitleScreen = titleScreen;
        mGameScreen = gameScreen;
    }

    public void switchCheckLogo() {
        if (mLogoScreen.sElapsedTime >= 2) {
            mGame.setScreen(mTitleScreen);
        }
    }

    public void switchCheckTitle() {
        if (mTitleScreen.mPlayButton.mTapped) {
            mTitleScreen.mPlayButton.mTapped = false;
            mGame.setScreen(mGameScreen);
        }
    }

}
