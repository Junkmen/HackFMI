package xtrordinary.games.enb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ENB extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
    OrthographicCamera mainCamera;
    AssetManager aManager;

    Object2DManager primiivesManager;
    private int circleIndexes[];

    public static int CAMERA_WIDTH = 480;
    public static int CAMERA_HEIGHT = 800;
	
	@Override
	public void create () {
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false,CAMERA_WIDTH,CAMERA_HEIGHT);
        mainCamera.update();

		batch = new SpriteBatch();
        defaultBlendDst = batch.getBlendDstFunc();
        defaultBlendSrc = batch.getBlendSrcFunc();
        mainCamera = new OrthographicCamera();
        aManager = new AssetManager();
        aManager.load("circle.png",Texture.class);

        aManager.finishLoading();

        circleIndexes = new int[5];
        primiivesManager = new Object2DManager();
        for (int i = 0; i < 5; i++) {
            Object2D circle = new Object2D() {
                @Override
                public void drawAndRotate(SpriteBatch batch) {
                    ENB.setBlending(ENB.BLEND_ADDITIVE,batch);
                    super.drawAndRotate(batch);
                    resetBlending(batch);
                }
            };
            circle.setTexture(aManager.get("circle.png", Texture.class));

            circle.updateDimensions();
            circle.setX(mainCamera.position.x);
            circle.setY(mainCamera.position.y);
            circleIndexes[0] = primiivesManager.addObject(circle);
       }
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainCamera.update();
      //  batch.setProjectionMatrix(mainCamera.combined);

        updateWorld(Gdx.graphics.getDeltaTime());
		batch.begin();
        renderWorld(batch);
		batch.end();
	}

    public void renderWorld(SpriteBatch batch) {
        primiivesManager.drawObjects(batch);
    }
    public void updateWorld(float deltaTime) {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    @Override
    public void dispose() {
        primiivesManager.disposeObjects();
        aManager.dispose();
        batch.dispose();
    }
    public int defaultBlendSrc,defaultBlendDst;
    public void resetBlending(SpriteBatch batch) {
        batch.setBlendFunction(defaultBlendSrc, defaultBlendDst);
    }
    public static void setBlending(int blending,SpriteBatch batch) {
        if (blending == ENB.BLEND_ADDITIVE) {
            //batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_CONSTANT_ALPHA);
            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        } else if (blending == ENB.BLEND_MULTIPLICATIVE) {
            batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
        }
    }
    public static int BLEND_ADDITIVE = 1,BLEND_MULTIPLICATIVE = 2;
}
