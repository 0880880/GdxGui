package com.github.zeroeighteightzero.gdxgui;

public abstract class Paint {
	
	abstract void fill(long nvg, float x, float y, float width, float height);
	
	abstract void stroke(long nvg, float x, float y, float width, float height);
	
}