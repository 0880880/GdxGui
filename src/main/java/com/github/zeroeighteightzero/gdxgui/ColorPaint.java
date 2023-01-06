package com.github.zeroeighteightzero.gdxgui;

import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;

import com.badlogic.gdx.graphics.Color;

public class ColorPaint extends Paint {
	
	private NVGColor nvgColor = Utils.createColor(Color.WHITE);
	
	public ColorPaint() {}
	
	public ColorPaint(float r, float g, float b, float a) {
		Utils.setColor(nvgColor, r, g, b, a);
	}
	
	public ColorPaint(float r, float g, float b) {
		Utils.setColor(nvgColor, r, g, b);
	}
	
	public ColorPaint(Color color) {
		Utils.setColor(nvgColor, color);
	}
	
	public ColorPaint set(float r, float g, float b, float a) {
		Utils.setColor(nvgColor, r, g, b, a);
		return this;
	}
	
	public ColorPaint set(float r, float g, float b) {
		Utils.setColor(nvgColor, r, g, b);
		return this;
	}
	
	public ColorPaint set(Color color) {
		Utils.setColor(nvgColor, color);
		return this;
	}

	@Override
	void fill(long nvg, float x, float y, float width, float height) {
		NanoVG.nvgFillColor(nvg, nvgColor);
	}

	@Override
	void stroke(long nvg, float x, float y, float width, float height) {
		NanoVG.nvgStrokeColor(nvg, nvgColor);
	}

}
