package com.github.zeroeighteightzero.gdxgui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GuiInputProcessor implements InputProcessor {

	public static boolean lArrow;
	public static boolean rArrow;
	
	public static float scroll;
	
	public static char chr = 0;

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.LEFT)
			lArrow = true;
		if (keycode == Input.Keys.RIGHT)
			rArrow = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT)
			lArrow = false;
		if (keycode == Input.Keys.RIGHT)
			rArrow = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		chr = character;
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
	public boolean scrolled(float amountX, float amountY) {
		scroll = amountY;
		return false;
	}

}
