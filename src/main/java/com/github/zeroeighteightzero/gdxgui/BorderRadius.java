package com.github.zeroeighteightzero.gdxgui;

public class BorderRadius {

	float topLeft;
	float topRight;
	float bottomLeft;
	float bottomRight;
	
	public BorderRadius() {}
	
	public BorderRadius(float borderRadius) {
		topLeft = borderRadius;
		topRight = borderRadius;
		bottomLeft = borderRadius;
		bottomRight = borderRadius;
	}
	
	public BorderRadius(float topLeft, float topRight, float bottomLeft, float bottomRight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}
	
	public BorderRadius(float top, float bottom) {
		this.topLeft = top;
		this.topRight = top;
		this.bottomLeft = bottom;
		this.bottomRight = bottom;
	}
	
	public void set(float borderRadius) {
		topLeft = borderRadius;
		topRight = borderRadius;
		bottomLeft = borderRadius;
		bottomRight = borderRadius;
	}
	
	public void set(float topLeft, float topRight, float bottomLeft, float bottomRight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}
	
	public void set(float top, float bottom) {
		this.topLeft = top;
		this.topRight = top;
		this.bottomLeft = bottom;
		this.bottomRight = bottom;
	}
	
}
