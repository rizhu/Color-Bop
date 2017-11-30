package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class TitleScreen extends ScreenAdapter {
    public final float mWORLD_SIZE = 500.0f;

    Viewport mViewport;
    TitleInputHandler mInputHandler;
    PlayButton mPlayButton;
    Texture mBg, mTitle;

    SpriteBatch mBatch;

    ScreenSwitcher mSwitcher;

    float mAspectRatioTitle, mWidth, mHeight;

    @Override
    public void show() {
        mBatch = new SpriteBatch();

        mViewport = new ExtendViewport(mWORLD_SIZE, mWORLD_SIZE);

        mBg = new Texture(Gdx.files.internal("bg.png"));
        mTitle = new Texture(Gdx.files.internal("colorBop.png"));
        mAspectRatioTitle = ((float) mTitle.getHeight()) / ((float) mTitle.getWidth());

        mPlayButton = new PlayButton(mViewport);

        mInputHandler = new TitleInputHandler(mViewport, mPlayButton);

        Gdx.input.setInputProcessor(mInputHandler);
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        mBatch.dispose();
        mTitle.dispose();
        mBg.dispose();
        mPlayButton.mButtonTexture.dispose();
    }

    @Override
    public void render(float delta) {
        mViewport.apply(true);

        mSwitcher.switchCheckTitle();

        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //mBatch.setProjectionMatrix(mViewport.getCamera().combined);

        mBatch.begin();
        mBatch.draw(mBg, 0, 0, mViewport.getScreenWidth(), mViewport.getScreenHeight());
        mBatch.end();

        mBatch.begin();
        mWidth = mViewport.getScreenWidth() / 2;
        mHeight = mAspectRatioTitle * mWidth;
        mBatch.draw(mTitle, mViewport.getScreenWidth() / 2 - mWidth / 2, mViewport.getScreenHeight() /  2, mWidth, mHeight);
        mBatch.end();

        mBatch.begin();
        mPlayButton.render(mBatch);
        mBatch.end();

        mSwitcher.switchCheckTitle();

    }

    public void setScreenSwitcher(ScreenSwitcher switcher) {
        mSwitcher = switcher;
    }
}
