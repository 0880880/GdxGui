package com.github.zeroeighteightzero.gdxgui;

import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

import com.badlogic.gdx.graphics.Color;

public class LinearGradientPaint extends Paint {

	private NVGColor nvgColor0 = Utils.createColor(Color.BLACK);
	private NVGColor nvgColor1 = Utils.createColor(Color.WHITE);
	
	private NVGPaint paint = NVGPaint.create();

	public float startX = 0;
	public float startY = .5f;
	public float endX = 1;
	public float endY = .5f;
	
	public LinearGradientPaint() {}
	
	public LinearGradientPaint(float r0, float g0, float b0, float a0, float r1, float g1, float b1, float a1) {
		Utils.setColor(nvgColor0, r0, g0, b0, a0);
		Utils.setColor(nvgColor1, r1, g1, b1, a1);
	}
	
	public LinearGradientPaint(float r0, float g0, float b0, float r1, float g1, float b1) {
		Utils.setColor(nvgColor0, r0, g0, b0);
		Utils.setColor(nvgColor1, r1, g1, b1);
	}
	
	public LinearGradientPaint(Color color0, Color color1) {
		Utils.setColor(nvgColor0, color0);
		Utils.setColor(nvgColor1, color1);
	}
	
	public LinearGradientPaint set(float r0, float g0, float b0, float a0, float r1, float g1, float b1, float a1) {
		Utils.setColor(nvgColor0, r0, g0, b0, a0);
		Utils.setColor(nvgColor1, r1, g1, b1, a1);
		return this;
	}
	
	public LinearGradientPaint set(float r0, float g0, float b0, float r1, float g1, float b1) {
		return set(r0, g0, b0, 1, r1, g1, b1, 1);
	}
	
	public LinearGradientPaint set(Color color0, Color color1) {
		return set(color0.r, color0.g, color0.b, color0.a, color1.r, color1.g, color1.b, color1.a);
	}
	
	public LinearGradientPaint setStart(float x, float y) {
		startX = x;
		startY = y;
		return this;
	}
	
	public LinearGradientPaint setEnd(float x, float y) {
		endX = x;
		endY = y;
		return this;
	}

	@Override
	void fill(long nvg, float x, float y, float width, float height) {
		NanoVG.nvgLinearGradient(nvg, x + startX * width, y + startY * height, x + endX * width, y + endY * height, nvgColor0, nvgColor1, paint);
		NanoVG.nvgFillPaint(nvg, paint);
	}

	@Override
	void stroke(long nvg, float x, float y, float width, float height) {
		NanoVG.nvgLinearGradient(nvg, x + startX * width, y + startY * height, x + endX * width, y + endY * height, nvgColor0, nvgColor1, paint);
		NanoVG.nvgStrokePaint(nvg, paint);
	}

}