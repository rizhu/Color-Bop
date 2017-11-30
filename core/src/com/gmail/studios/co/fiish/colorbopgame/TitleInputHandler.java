package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TitleInputHandler extends InputAdapter {
    int mPlayPointer;
    boolean mPlayTapping;
    Vector2 mTouchPos;
    PlayButton mPlayButton;
    Viewport mViewport;

    public TitleInputHandler(Viewport viewport, PlayButton playButton) {
        this.mViewport = viewport;
        this.mPlayButton = playButton;
        mTouchPos = new Vector2();
        mPlayTapping = false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (
                screenX >= mViewport.getScreenWidth() / 2 - mPlayButton.mWidth / 2 &&
                screenX <= mViewport.getScreenWidth() / 2 + mPlayButton.mWidth / 2 &&
                screenY >= 3 * mViewport.getScreenHeight() / 4 - mPlayButton.mHeight / 2 &&
                screenY <= 3 * mViewport.getScreenHeight() / 4 + mPlayButton.mHeight / 2) {
            mPlayPointer = pointer;
            mPlayTapping = true;
            mPlayButton.mTapping = true;
            return true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (mPlayButton.mTapping) {
            mPlayButton.mTapping = false;
        }
        if (
                screenX >= mViewport.getScreenWidth() / 2 - mPlayButton.mWidth / 2 &&
                screenX <= mViewport.getScreenWidth() / 2 + mPlayButton.mWidth / 2 &&
                screenY >= 3 * mViewport.getScreenHeight() / 4 - mPlayButton.mHeight / 2 &&
                screenY <= 3 * mViewport.getScreenHeight() / 4 + mPlayButton.mHeight / 2 &&
                mPlayTapping &&
                pointer == mPlayPointer) {
            mPlayTapping = false;
            mPlayButton.mTapped = true;
            return true;
        }
        return true;
    }
}

