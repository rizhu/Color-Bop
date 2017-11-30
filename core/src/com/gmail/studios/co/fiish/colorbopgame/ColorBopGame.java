package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.Game;

public class ColorBopGame extends Game {
	GameScreen mGameScreen;
	LogoScreen mLogoScreen;
	TitleScreen mTitleScreen;
	ScreenSwitcher mSwitcher;

	@Override
	public void create() {
		mGameScreen = new GameScreen();
		mLogoScreen = new LogoScreen();
		mTitleScreen = new TitleScreen();
		mSwitcher = new ScreenSwitcher(this, mLogoScreen, mTitleScreen, mGameScreen);
		mLogoScreen.setScreenSwitcher(mSwitcher);
		mGameScreen.setScreenSwitcher(mSwitcher);
		mTitleScreen.setScreenSwitcher(mSwitcher);
		setScreen(mLogoScreen);
	}
}
