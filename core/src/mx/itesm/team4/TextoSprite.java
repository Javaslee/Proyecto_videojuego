package mx.itesm.team4;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextoSprite {
    private Sprite textoSp;
    public TextoSprite(Texture textura, float x, float y){
        textoSp = new Sprite(textura);
        textoSp.setPosition(x,y);
    }
    public void render(SpriteBatch batch){
        textoSp.draw(batch);
    }
}
