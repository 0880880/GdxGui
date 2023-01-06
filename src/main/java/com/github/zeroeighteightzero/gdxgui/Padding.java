package com.github.zeroeighteightzero.gdxgui;

public class Padding {

	float top;
	float left;
	float bottom;
	float right;
	
	public Padding() {}

	public Padding(float padding) {
		top = padding;
		left = padding;
		bottom = padding;
		right = padding;
	}

	public Padding(float horizontal, float vertical) {
		top = vertical;
		left = horizontal;
		bottom = vertical;
		right = horizontal;
	}

	public Padding(float top, float left, float bottom, float right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	public void set(float padding) {
		top = padding;
		left = padding;
		bottom = padding;
		right = padding;
	}

	public void set(float horizontal, float vertical) {
		top = vertical;
		left = horizontal;
		bottom = vertical;
		right = horizontal;
	}

	public void set(float top, float left, float bottom, float right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}
	
	public float getHorizontal() {
		return left + right;
	}
	
	public float getVertical() {
		return top + bottom;
	}

	public float getHorizontal2() {
		return getHorizontal() * 2;
	}
	
	public float getVertical2() {
		return getVertical() * 2;
	}
	
	public float getVertical3() {
		return getVertical2() * 2;
	}
	
}
