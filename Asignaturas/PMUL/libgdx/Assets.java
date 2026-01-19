package com.cdm.pescador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static Texture fondo;
	public static BitmapFont fuente;
	public static TextureAtlas atlas;
	public static TextureRegion pescador,anzuelo,sedal,pezAzul;

	public static Sound captura, finDelJuego;
	public static Music musicaDeFondo;
	
	public static void cargarTexturas(){
		fondo = new Texture(Gdx.files.internal("graficos/fondo.jpg"));
		
		captura=Gdx.audio.newSound(Gdx.files.internal("sonidos/captura.mp3"));
		finDelJuego=Gdx.audio.newSound(Gdx.files.internal("sonidos/finDelJuego.mp3"));
		musicaDeFondo=Gdx.audio.newMusic(Gdx.files.internal("sonidos/fondo.mp3"));

		//fuente = new BitmapFont(Gdx.files.internal("fuentes/fuente.fnt"));
		fuente = new BitmapFont();
		fuente.getData().setScale(.5f);
		fuente.setColor(Color.RED);

		atlas = new TextureAtlas(Gdx.files.internal("graficos/atlas.atlas"));
		pescador = atlas.findRegion("pescador");
		anzuelo = atlas.findRegion("anzuelo");
		sedal = atlas.findRegion("punto");

		pezAzul=atlas.findRegion("pezAzul");

		
	}
	
	public static void liberarTexturas(){
		captura.dispose();
		musicaDeFondo.dispose();
		finDelJuego.dispose();
		fondo.dispose();
		fuente.dispose();
		atlas.dispose();
	}
}
