package mx.itesm.team4;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Inicio extends Game {
	public static final float ANCHO= 1280;
	public static final float ALTO= 720;
	public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

	public static final float PISO_X = 0;
	public static final float PISO_Y = 0;
	public static final float PISO_ANCHO = 50f;
	public static final float PISO_ALTO = 2f;
	public static final float PISO_DENSITY = 0f;

    public static final float RUNNER_DENSITY = 0.8f;
    public static final float RUNNER_ALTO = 2f;
    public static final float RUNNER_Y = PISO_Y+PISO_ALTO;
    public static final float RUNNER_X = 2;
    public static final float RUNNER_ANCHO = 1f;
    public static final Vector2 RUNNER_IMPULSO_SALTO = new Vector2(0, 13f);
	public static final float RUNNER_ESQUIVAR_X = 2f;
	public static final float RUNNER_ESQUIVAR_Y = 1.5f;
	public static final float ENEMY_X = 25f;
	public static final float RUNNING_SHORT_ENEMY_Y = 1.5f;
	public static final float ENEMY_DENSITY = RUNNER_DENSITY;
	public static final float RUNNING_LONG_ENEMY_Y = 2f;
	public static final float FLYING_ENEMY_Y = 3f;
	public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-10f, 0);
	public static final float RUNNER_HIT_ANGULAR_IMPULSE = 10f;

	public static final String BACKGROUND = "Fondo Largo.png";
	public static final String GROUND = "ground.png";
	public static final float WORLD_TO_SCREEN = 32f;

	public static final String CHARACTERS_ATLAS_PATH = "personajeSp.txt";
	public static final String[] RUNNER_RUNNING_REGION_NAMES = new String[] {"Personaje_run1", "Personaje_run2", "Personaje_run3", "Personaje_run4"};
	public static final String RUNNER_DODGING_REGION_NAME = "Personaje_run1";
	public static final String RUNNER_HIT_REGION_NAME = "Personaje_run3";
	public static final String RUNNER_JUMPING_REGION_NAME = "Personaje_run2";
	public static final String RUNNING_SMALL_ENEMY_REGION_NAMES = "Villano_1";
	public static final String RUNNING_LONG_ENEMY_REGION_NAMES ="Villano_2";
	public static final String RUNNING_BIG_ENEMY_REGION_NAMES ="Villano_1";
	public static final String RUNNING_WIDE_ENEMY_REGION_NAMES ="Villano_2";
	public static final String FLYING_SMALL_ENEMY_REGION_NAMES ="Villano_1";
	public static final String FLYING_WIDE_ENEMY_REGION_NAMES ="Villano_2";


	//manager
	private final AssetManager manager=new AssetManager();
	private Music audioFondo;
	@Override
	public void create () {

		setScreen(new PantallaTitulo(this));

		getManager().load("Canciones.mp3", Music.class);
		getManager().finishLoading();
		audioFondo=getManager().get("Canciones.mp3");
		audioFondo.setLooping(true);
		audioFondo.play();
	}


	public AssetManager getManager() {
		return manager;
	}

	public void playMusic(){
		audioFondo.play();
	}
	public void stopMusic(){
		audioFondo.stop();
	}

	public boolean isPlaying(){
		return audioFondo.isPlaying();
	}


}
/*
* 1. mensaje tap sobre pantalla titulo
* 2. Panta cargando
* 3. tamaño y distribución de botones en menu
* 4. intercambiar botones de info y ayuda
* 5. reubicar boton de regreso
* 6. cambiar boton de instrucciones
* 7. cambiar instrucciones
* 8. fotos nuestras en acerca de
* 9. terminar niveles
* 10. tamaño de botones
* 11. transparencia de enemigo y tamaño de ambos
* 12. agregar de pantalla de perdida y ganar en los niveles*/