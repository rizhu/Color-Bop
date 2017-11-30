package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {
    public final float mWORLD_SIZE = 500.0f;

    ShapeRenderer mRenderer;
    Viewport mViewport;
    Ball mBall;
    GameInputHandler mInputHandler;

    FreeTypeFontGenerator mGenerator;
    FreeTypeFontParameter mScoreParameter, mHighScoreParameter;

    ScoreCounter mScoreCounter;

    BitmapFont mScoreFont, mHighScoreFont;
    GlyphLayout mScoreLayout, mHighScoreLayout;
    SpriteBatch mBatch;

    Texture mBg;
    float mAspectRatio;

    ScreenSwitcher mSwitcher;

    @Override
    public void show() {
        mRenderer = new ShapeRenderer();
        mRenderer.setAutoShapeType(true);

        mViewport = new ExtendViewport(mWORLD_SIZE, mWORLD_SIZE);

        mGenerator = new FreeTypeFontGenerator(Gdx.files.internal("segoeuibold.ttf"));

        mHighScoreParameter = new FreeTypeFontParameter();
        mHighScoreParameter.size = 100;
        mHighScoreParameter.color = Color.BLACK;
        mHighScoreFont = mGenerator.generateFont(mHighScoreParameter);

        mScoreParameter = new FreeTypeFontParameter();
        mScoreParameter.size = 250;
        mScoreParameter.color = Color.BLACK;
        mScoreFont = mGenerator.generateFont(mScoreParameter);

        mScoreLayout = new GlyphLayout();
        mHighScoreLayout = new GlyphLayout();

        mBatch = new SpriteBatch();

        mScoreCounter = new ScoreCounter();

        mBall = new Ball(mViewport);

        mBg = new Texture(Gdx.files.internal("bg.png"));
        mAspectRatio = ((float) mBg.getWidth() / (float) mBg.getHeight());

        mInputHandler = new GameInputHandler();
        mInputHandler.setBall(mBall);
        mInputHandler.setViewport(mViewport);

        Gdx.input.setInputProcessor(mInputHandler);

        mScoreCounter.setBall(mBall);
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width, height, true);
        mBall.init();
    }

    @Override
    public void dispose() {
        mRenderer.dispose();
        mBatch.dispose();
        mScoreFont.dispose();
        mHighScoreFont.dispose();
        mGenerator.dispose();
        mBg.dispose();
        mBall.dispose();
        mInputHandler.dispose();
    }

    @Override
    public void render(float delta) {
        mViewport.apply(true);

        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mBatch.begin();
        mBatch.draw(mBg, 0, 0, mViewport.getScreenWidth(), mViewport.getScreenHeight());
        mBatch.end();

        mBatch.begin();
        mScoreLayout.setText(mScoreFont, "" + mScoreCounter.mScore);
        float scoreWidth = mScoreLayout.width;
        float scoreHeight = mScoreLayout.height;
        mScoreFont.draw(mBatch, mScoreLayout, (mViewport.getScreenWidth() / 2) - (scoreWidth / 2), (mViewport.getScreenHeight() / 2) + scoreHeight);
        mHighScoreLayout.setText(mHighScoreFont, "High Score: " + mScoreCounter.mHighScore);
        float highScoreWidth = mHighScoreLayout.width;
        float highScoreHeight = mHighScoreLayout.height;
        mHighScoreFont.draw(mBatch, mHighScoreLayout, (mViewport.getScreenWidth() / 2) - (highScoreWidth / 2), (mViewport.getScreenHeight() / 2) + scoreHeight + highScoreHeight + 250);
        mBatch.end();

        mBall.update(delta);
        mRenderer.begin(ShapeType.Filled);
        mRenderer.setProjectionMatrix(mViewport.getCamera().combined);
        mBall.render(mRenderer);
        mRenderer.end();

        mScoreCounter.update();
    }

    public void setScreenSwitcher(ScreenSwitcher switcher) {
        mSwitcher = switcher;
    }

}
