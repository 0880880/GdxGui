package com.github.zeroeighteightzero.gdxgui;

import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

import com.badlogic.gdx.graphics.Color;

public class RadialGradientPaint extends Paint {

	private NVGColor nvgColor0 = Utils.createColor(Color.BLACK);
	private NVGColor nvgColor1 = Utils.createColor(Color.WHITE);
	
	private NVGPaint paint = NVGPaint.create();

	public float centerX = .5f;
	public float centerY = .5f;
	
	public float innerRadius = 1f;
	public float outerRadius = .5f;
	
	public RadialGradientPaint() {}
	
	public RadialGradientPaint(float r0, float g0, float b0, float a0, float r1, float g1, float b1, float a1) {
		Utils.setColor(nvgColor0, r0, g0, b0, a0);
		Utils.setColor(nvgColor1, r1, g1, b1, a1);
	}
	
	public RadialGradientPaint(float r0, float g0, float b0, float r1, float g1, float b1) {
		Utils.setColor(nvgColor0, r0, g0, b0);
		Utils.setColor(nvgColor1, r1, g1, b1);
	}
	
	public RadialGradientPaint(Color color0, Color color1) {
		Utils.setColor(nvgColor0, color0);
		Utils.setColor(nvgColor1, color1);
	}
	
	public RadialGradientPaint set(float r0, float g0, float b0, float a0, float r1, float g1, float b1, float a1) {
		Utils.setColor(nvgColor0, r0, g0, b0, a0);
		Utils.setColor(nvgColor1, r1, g1, b1, a1);
		return this;
	}
	
	public RadialGradientPaint set(float r0, float g0, float b0, float r1, float g1, float b1) {
		return set(r0, g0, b0, 1, r1, g1, b1, 1);
	}
	
	public RadialGradientPaint set(Color color0, Color color1) {
		return set(color0.r, color0.g, color0.b, color0.a, color1.r, color1.g, color1.b, color1.a);
	}
	
	public RadialGradientPaint setCenter(float x, float y) {
		centerX = x;
		centerY = y;
		return this;
	}

	@Override
	void fill(long nvg, float x, float y, float width, float height) {
		NanoVG.nvgRadialGradient(nvg, centerX * width, centerY * height, innerRadius, outerRadius, nvgColor0, nvgColor1, paint);
		NanoVG.nvgFillPaint(nvg, paint);
	}

	@Override
	void stroke(long nvg, float x, float y, float width, float height) {
		NanoVG.nvgRadialGradient(nvg, centerX * width, centerY * height, innerRadius, outerRadius, nvgColor0, nvgColor1, paint);
		NanoVG.nvgStrokePaint(nvg, paint);
	}

}