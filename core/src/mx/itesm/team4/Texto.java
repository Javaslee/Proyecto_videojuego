//Creada por:Natalya Lopez
package mx.itesm.team4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Texto {
    private BitmapFont fuente;

    public Texto(){
        fuente = new BitmapFont(Gdx.files.internal("fuenteCuadrado.fnt"));
    }

    public void mostrarTexto(SpriteBatch batch, String texto, float x, float y){
        GlyphLayout glyph = new GlyphLayout();
        glyph.setText(fuente, texto);
        float anchoTexto = glyph.width;
        fuente.draw(batch, glyph, x-anchoTexto/2,y);
    }
}
