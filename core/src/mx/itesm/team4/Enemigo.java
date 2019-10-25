package mx.itesm.team4;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Enemigo extends JuegoActor{

    private TextureRegion enemigo1;
    private TextureRegion enemigo2;
    private TextureRegion enemigo3;
    private TextureRegion enemigo4;
    private TextureRegion enemigo5;
    private TextureRegion enemigo6;


    public Enemigo(Body body) {
        super(body);
        TextureAtlas textureAtlas = new TextureAtlas(Inicio.CHARACTERS_ATLAS_PATH);
        enemigo1 = textureAtlas.findRegion(Inicio.RUNNING_SMALL_ENEMY_REGION_NAMES);
        enemigo2 = textureAtlas.findRegion(Inicio.RUNNING_LONG_ENEMY_REGION_NAMES);
        enemigo3 = textureAtlas.findRegion(Inicio.RUNNING_BIG_ENEMY_REGION_NAMES);
        enemigo4 = textureAtlas.findRegion(Inicio.RUNNING_WIDE_ENEMY_REGION_NAMES);
        enemigo5 = textureAtlas.findRegion(Inicio.RUNNING_SMALL_ENEMY_REGION_NAMES);
        enemigo6 = textureAtlas.findRegion(Inicio.RUNNING_WIDE_ENEMY_REGION_NAMES);


    }

    @Override
    public EnemigoUserData getUserData() {
        return (EnemigoUserData) enums;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(enemigo1, (screenRectangle.x - (screenRectangle.width * 0.1f)),
                screenRectangle.y, screenRectangle.width * 1.2f, screenRectangle.height * 1.1f);
    }

}
