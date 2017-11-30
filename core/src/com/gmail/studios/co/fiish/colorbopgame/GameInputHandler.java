package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameInputHandler extends InputAdapter {
    int mBallPointer;
    Ball mBall;
    Viewport mViewport;
    Vector2 mTouchPos;

    boolean mBallTouched;

    Sound mTap = Gdx.audio.newSound(Gdx.files.internal("buttonTap.wav"));

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mTouchPos = mViewport.unproject(new Vector2(screenX, screenY));
        if ((Math.pow(mTouchPos.x - mBall.mPosition.x, 2) + Math.pow(mTouchPos.y - mBall.mPosition.y, 2)) <= Math.pow(mBall.sRADIUS, 2)) {
            if (mTouchPos.y <= (mBall.mPosition).y - 36) {
                mBallPointer = pointer;
                mBallTouched = true;
                return true;
            } else {
                mBallTouched = false;
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer == mBallPointer && mBallTouched) {
            mBall.setInRestingPhase(false);
            mBall.kick(mTouchPos.x, mTouchPos.y);
            mBallTouched = false;
            return true;
        }
        return true;
    }

    public void setBall(Ball ball) {
        this.mBall = ball;
    }

    public void setViewport(Viewport viewport) {
        this.mViewport = viewport;
    }

    public void dispose() {
        mTap.dispose();
    }
}

