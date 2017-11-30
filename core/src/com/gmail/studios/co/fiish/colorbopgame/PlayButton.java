package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PlayButton {
    public boolean mTapped, mTapping;
    public Vector2 mPos;
    public float mWidth, mHeight, mAspectRatio;
    public Texture mButtonTexture;

    Viewport mViewport;

    public PlayButton(Viewport viewport) {
        this.mViewport = viewport;
        init();
    }

    public void init() {
        mButtonTexture = new Texture(Gdx.files.internal("playButton.png"));
        mPos = new Vector2(mViewport.getWorldWidth() / 2, mViewport.getWorldHeight() / 4);
        mTapped = false;
        mTapping = false;
    }

    public void render(SpriteBatch batch) {
        if (mTapping)
            mPos = new Vector2(mViewport.getScreenWidth() / 2, mViewport.getScreenHeight() / 4 - 10);
        else
            mPos = new Vector2(mViewport.getScreenWidth() / 2, mViewport.getScreenHeight() / 4);
        mAspectRatio = ((float) mButtonTexture.getHeight()) / ((float) mButtonTexture.getWidth());
        mWidth = (float) (mViewport.getScreenWidth() / 2);
        mHeight = mAspectRatio * mWidth;
        batch.draw(mButtonTexture, mPos.x - (mWidth / 2), mPos.y - (mHeight / 2), mWidth, mHeight);
    }
}
