package mx.itesm.team4;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Personaje {
    private Sprite sprite;
    private Texture texturaIzAd;
    private Texture texturaDeAd;


    public Personaje(Texture texturaIzAd, float x, float y){
        this.texturaDeAd=texturaDeAd;
        this.texturaIzAd=texturaIzAd;
        sprite=new Sprite(this.texturaIzAd);
        sprite.setPosition(Inicio.RUNNER_X, Inicio.RUNNER_Y);
    }



    public Personaje(Texture texturaIzAd, Texture texturaDeAd, float x, float y){
        this.texturaDeAd=texturaDeAd;
        this.texturaIzAd=texturaIzAd;
        sprite=new Sprite(this.texturaIzAd);
        sprite.setPosition(x, y);
    }



    public void mover(float dx){

        sprite.setX(sprite.getX()+dx);

    }

    public void saltar(float dy){ sprite.setY(sprite.getY()+dy); }



    public void draw(SpriteBatch batch){

        sprite.draw(batch);

    }



    public void cambiarImagen() {

        if(sprite.getTexture()==texturaIzAd) {

            sprite.setTexture(texturaDeAd);

        }

        else {

            sprite.setTexture(texturaIzAd);

        }

    }



    public Sprite getSprite() {

        return sprite;

    }

}