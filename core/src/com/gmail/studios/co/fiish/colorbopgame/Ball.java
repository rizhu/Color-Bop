package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.audio.Sound;

public class Ball {

    public static Color sColor = Color.BLACK;
    public static final float sRADIUS = 100.0f;
    public static final float sGRAVITY = 1000.0f;
    public static final float sKICK_VELOCITY = 800.0f;
    public static int sTapCount;
    public boolean mFailCalled = false;

    public Vector2 mPosition;
    public Vector2 mVelocity;

    Viewport mViewport;
    Sound mBop;

    boolean inRestingPhase;


    public Ball(Viewport mViewport) {
        this.mViewport = mViewport;
        mBop = Gdx.audio.newSound(Gdx.files.internal("bop.wav"));
        init();
    }

    public void init() {
        sTapCount = 0;
        float r, g, b;
        r = (float) Math.random();
        g = (float) Math.random();
        b = (float) Math.random();
        sColor = new Color(r, g, b, 1);
        inRestingPhase = true;
        mPosition = new Vector2(mViewport.getWorldWidth() / 2, sRADIUS + 10);
        mVelocity = new Vector2();
        mVelocity.x = 0;
        mVelocity.y = 0;
    }

    public void update(float delta) {
        boolean restingPhase = isInRestingPhase();

        if (restingPhase) {
        } else {
            mVelocity.y = mVelocity.y - (sGRAVITY * delta);

            mPosition.x += mVelocity.x * delta;
            mPosition.y += mVelocity.y * delta;

            collisionCheck(mViewport.getWorldWidth());

            failCheck();

        }
    }

    public void render(ShapeRenderer mRenderer) {
        mRenderer.set(ShapeType.Filled);
        mRenderer.setColor(sColor);
        mRenderer.circle(mPosition.x, mPosition.y, sRADIUS, 500);
    }

    public void collisionCheck(float mViewportWidth) {
        if (mPosition.x - sRADIUS < 0) {
            mPosition.x = sRADIUS;
            mVelocity.x = -mVelocity.x;
        }
        if (mPosition.x + sRADIUS > mViewportWidth) {
            mPosition.x = mViewportWidth - sRADIUS;
            mVelocity.x = -mVelocity.x;
        }
    }

    public void kick(float screenX, float screenY) {
        if (screenY <= mPosition.y - 36) {
            mVelocity.x = 0.0f;
            mVelocity.y = 0.0f;
            float mRelativeX = screenX - mPosition.x;
            float mRelativeY = screenY - mPosition.y;
            float mAngle = 180 + (MathUtils.atan2(mRelativeY, mRelativeX) * MathUtils.radiansToDegrees);
            mVelocity.x = sKICK_VELOCITY * MathUtils.cosDeg(mAngle);
            mVelocity.y = sKICK_VELOCITY * MathUtils.sinDeg(mAngle);
            sTapCount++;
            mBop.play();
            float r, g, b;
            r = (float) Math.random();
            g = (float) Math.random();
            b = (float) Math.random();
            sColor = new Color(r, g, b, 1);
        } else {
            return;
        }
    }

    public void failCheck() {
        if ((mPosition.y + sRADIUS) < 0) {
            mFailCalled = true;
            init();
        }
    }

    public void dispose() {
        mBop.dispose();
    }

    public void setInRestingPhase(boolean inRestingPhase) {
        this.inRestingPhase = inRestingPhase;
    }
    public boolean isInRestingPhase() {
        return inRestingPhase;
    }

}

