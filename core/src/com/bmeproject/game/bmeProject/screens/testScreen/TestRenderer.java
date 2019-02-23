package com.bmeproject.game.bmeProject.screens.testScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.bmeproject.game.bmeProject.util.Constants;

public class TestRenderer implements Disposable {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private TestController testController;

    public TestRenderer(TestController testController){
        this.testController = testController;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0,0,0);
        camera.update();
    }

    public void render(){
        renderTestObjects();
    }

    private void renderTestObjects(){
        testController.cameraHelper.applyTo(camera);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for(Sprite sprite : testController.testSprites){
            sprite.draw(batch);
        }
        batch.end();
    }

    public void resize(int width, int height){
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
