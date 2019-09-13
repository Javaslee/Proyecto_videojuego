package mx.itesm.team4;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemigo {
    private Sprite sprite;
    private Texture textura;

    public Enemigo(Texture textura,float x, float y){
        this.textura=textura;
        this.sprite=new Sprite(this.textura);
        sprite.setPosition(x, y);
    }
    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

}
