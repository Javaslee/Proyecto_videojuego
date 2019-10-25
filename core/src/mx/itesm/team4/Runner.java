package mx.itesm.team4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Runner extends JuegoActor {

    private boolean esquivando;
    private boolean saltando;
    private boolean chocar;
    private Animation runningAnimation;
    private TextureRegion jumpingTexture;
    private TextureRegion dodgingTexture;
    private TextureRegion hitTexture;
    private float stateTime;


    public Runner(Body body) {
        super(body);
        TextureAtlas textureAtlas = new TextureAtlas(Inicio.CHARACTERS_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[Inicio.RUNNER_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Inicio.RUNNER_RUNNING_REGION_NAMES.length; i++) {
            String path = Inicio.RUNNER_RUNNING_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        runningAnimation = new Animation(0.1f, runningFrames);
        stateTime = 0f;
        jumpingTexture = textureAtlas.findRegion(Inicio.RUNNER_JUMPING_REGION_NAME);
        dodgingTexture = textureAtlas.findRegion(Inicio.RUNNER_DODGING_REGION_NAME);
        hitTexture = textureAtlas.findRegion(Inicio.RUNNER_HIT_REGION_NAME);
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) enums;
    }

    public void salta() {

        if (!saltando || esquivando) {
            body.applyLinearImpulse(getUserData().getImpulsoSalto(), body.getWorldCenter(), true);
            saltando = true;
        }

    }

    public void aterriza() {
        saltando = false;
    }

    public void esquivar() {
        if (!saltando) {
            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            esquivando = true;
        }
    }

    public void noEsquivar() {
        esquivando = false;
        body.setTransform(getUserData().getRunningPosition(), 0f);
    }

    public boolean estaEsquivando() {
        return esquivando;
    }

    public void hit() {
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        chocar = false;
    }

    public boolean isHit() {
        return chocar;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (esquivando) {
            batch.draw(dodgingTexture, screenRectangle.x, screenRectangle.y + screenRectangle.height / 4, screenRectangle.width,
                    screenRectangle.height * 3 / 4);
        } else {
            // Running
            // stateTime += Gdx.graphics.getDeltaTime();
            batch.draw((TextureRegion) runningAnimation.getKeyFrame(stateTime, true), screenRectangle.x, screenRectangle.y,
                    screenRectangle.getWidth(), screenRectangle.getHeight());
        }
    }
}
