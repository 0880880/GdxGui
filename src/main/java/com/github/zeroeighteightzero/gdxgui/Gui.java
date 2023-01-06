package com.github.zeroeighteightzero.gdxgui;

import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.*;

import java.io.IOException;
import java.nio.IntBuffer;

import org.lwjgl.nanovg.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Clipboard;

public final class Gui {
	
	public static class WindowOptions {

		public static final int NO_TITLEBAR = 1;
		public static final int NO_BACKGROUND = 2;
		public static final int NO_SCROLL = 3;
		public static final int NO_SCROLL_X = 4;
		public static final int NO_SCROLL_Y = 5;
		public static final int NO_RESIZE = 6;
		public static final int NO_RESIZE_X = 7;
		public static final int NO_RESIZE_Y = 8;
		public static final int NO_RESIZE_XY = 9;
		
	}
	
	private static class Style {

		/////////////
		/////// BASE
		/////////////
		
		public float frameBorderWidth = .5f;
		public float indentWidth = 30;
		public Vector2 itemSpacing = new Vector2(10,10);
		
		public Padding framePadding = new Padding(6);
		public BorderRadius frameBorderRadius = new BorderRadius(0);

		///////////////
		/////// WINDOW
		///////////////

		public Float minWindowWidth = 200f;
		public Float minWindowHeight = 50f;
		public Float maxWindowWidth;
		public Float maxWindowHeight;

		public float windowTitleFontSize = 15;
		public float windowTitleBarBorderWidth = .5f;
		
		public Padding windowPadding = new Padding(7);
		public BorderRadius windowBorderRadius = new BorderRadius(0);
		
		/////////////
		/////// TEXT
		/////////////

		public float textFontSize = 13.5f;
		
		///////////////
		/////// Button
		///////////////

		public float buttonTextFontSize = 13.5f;
		
		///////////////
		/////// Slider
		///////////////

		public float sliderFontSize = 13.5f;
		public float sliderWidthPerc = .5f;
		public float sliderKnobWidth = 12f;
		public float sliderMinHeight = 20f;

		public BorderRadius sliderKnobBorderRadius = new BorderRadius(0);
		
		//////////////
		/////// Label
		//////////////

		public float labelWidthPerc = .5f;
		public float labelFontSize = 13.5f;
		
		//////////////
		/////// Input
		//////////////

		public float inputWidthPerc = .5f;
		public float inputFontSize = 13.5f;
		
		/////////////////
		/////// Checkbox
		/////////////////

		public float checkboxFontSize = 13f;
		public Padding checkboxPadding = new Padding(3);
		
		/////////////////////
		/////// Radio Button
		/////////////////////

		public float radioButtonFontSize = 13f;
		public Padding radioButtonPadding = new Padding(3);
		
		//////////////////
		/////// Separator
		//////////////////

		public float separatorHeight = .5f;
		
		//////////////
		/////// Group
		//////////////
		
		public float groupWidthPixel = 350; // 150
		public float groupHeightPixel = 230;
		public Padding groupPadding = new Padding(7);
		public BorderRadius groupBorderRadius = new BorderRadius(0);

		/////////////////
		/////// Collapse
		/////////////////
		
		public Padding collapsePadding = new Padding(7);
		public BorderRadius collapseBorderRadius = new BorderRadius(0);
		public float collapseTextFontSize = 13f;

		//////////////
		/////// Shape
		//////////////
		
		public float shapeStrokeWidth = 4;
		
		

		
		//////////////////
		/////// SCROLLBAR
		//////////////////

		public float scrollbarWidth = 8;
		public float scrollbarOffsetX = 5;
		public float scrollbarOffsetY = 5;
		public BorderRadius scrollbarBorderRadius = new BorderRadius(5);
		
		///////////////
		/////// PAINTS
		///////////////

		private Paints paints = new Paints();
		
		public Paints getPaints() { return paints; }

		static class Paints {
			
			public Paint titleBarBackgroundPaint = new ColorPaint(Color.valueOf("#294A7A"));
			public Paint titleBarTextPaint = new ColorPaint(Color.WHITE);
			public Paint titleBarBorderPaint = new ColorPaint(Color.WHITE);
			public Paint windowBackgroundPaint = new ColorPaint(Color.BLACK);
			public Paint resizeButtonPaint = new ColorPaint(Color.WHITE);
			public Paint textPaint = new ColorPaint(Color.WHITE);
			public Paint buttonTextPaint = new ColorPaint(Color.WHITE);
			public Paint frameBorderPaint = new ColorPaint(Color.WHITE);
			public Paint frameBackgroundPaint = new ColorPaint(Color.valueOf("#274972"));
			public Paint frameHoverBackgroundPaint = new ColorPaint(Color.valueOf("#20324D"));
			public Paint sliderKnobBackgroundPaint = new ColorPaint(Color.valueOf("#3D85E0"));
			public Paint sliderValueTextPaint = new ColorPaint(Color.WHITE);
			public Paint sliderLabelTextPaint = new ColorPaint(Color.WHITE);
			public Paint labelPaint = new ColorPaint(Color.WHITE);
			public Paint labelTextPaint = new ColorPaint(Color.WHITE);
			public Paint inputTextPaint = new ColorPaint(Color.WHITE);
			public Paint separatorPaint = new ColorPaint(Color.WHITE);
			public Paint checkboxActiveBackgroundPaint = new ColorPaint(Color.valueOf("#3D85E0"));
			public Paint checkboxTextPaint = new ColorPaint(Color.WHITE);
			public Paint radioButtonActiveBackgroundPaint = new ColorPaint(Color.valueOf("#3D85E0"));
			public Paint radioButtonTextPaint = new ColorPaint(Color.WHITE);
			public Paint collapseBackgroundPaint = new ColorPaint(Color.valueOf("#274972"));
			public Paint collapseHoverBackgroundPaint = new ColorPaint(Color.valueOf("#20324D"));
			public Paint collapseBorderPaint = new ColorPaint(Color.WHITE);
			public Paint collapseTextPaint = new ColorPaint(Color.WHITE);
			public Paint collapseIconPaint = new ColorPaint(Color.WHITE);
			public Paint shapePaint = new ColorPaint(Color.YELLOW);
			public Paint selectionPaint = new ColorPaint(Color.valueOf("#4287f5").add(.1f, .1f, .1f, -.3f));
			public Paint scrollbarPaint = new ColorPaint(Color.GRAY);
			public Paint debugPaint = new ColorPaint(Color.BLUE);
			
		}
		
		
	}
	
	static class WindowData {
		
		int windowId;
		int selectedItemId = -1;
		
		float x;
		float y;
		Float width;
		Float height;
		float mmox;
		float mmoy;
		float lrsx;
		float lrsw;
		float scroll;
		float maxItemY;
		
		boolean moving = false;
		boolean resizing = false;
		boolean resizingL = false;
		boolean resizingR = false;
		boolean resizingV = false;
		boolean resizingAll = false;
		
	}

	private static Array<WindowData> windowDatas = new Array<>();

	private static Float nextWindowX;
	private static Float nextWindowY;
	private static Float nextWindowWidth;
	private static Float nextWindowHeight;
	private static Float nextWindowMinWidth;
	private static Float nextWindowMinHeight;
	private static Float nextWindowMaxWidth;
	private static Float nextWindowMaxHeight;
	
	private static SystemCursor cursor;

	private static int rmwin = -1;
	
	// SLIDER
	
	private static boolean usingSlider;

	private static float lastItemWidth;
	private static float lastItemHeight;
	
	private static SpriteBatch batch;

	private static boolean dragging;
	private static float dragStartX;

	private static Group currentGroup;
	private static Collapse currentCollapse;
	private static double time;
	
	private static Rectangle rectangle = new Rectangle(0,0,0,0);

	private static boolean itemHovered;
	private static boolean anyItemHovered;
	private static boolean anyWindowHovered;

	private static float mouseX;
	private static float mouseY;

	private static class Group {

		public float x;
		public float y;
		public float width;
		public float height;

		private Group() {

			width = style.groupWidthPixel;
			height = style.groupHeightPixel;
			x = (int) currentWindow.getNextItemX(width, true);
			y = (int) currentWindow.getNextItemY(height, true);

			//NanoVG.nvgScissor();

		}

	}

	private static class Collapse {

		public Collapse oldCollapse;

		public float x;
		public float y;

		public boolean open = true;

		private Collapse(String text, boolean open) {

			this.open = open;

			if (currentCollapse != null)
				oldCollapse = currentCollapse;
			currentCollapse = this;
			MathUtils.clamp(1,1,1);
			float width = currentWindow.getWidth() * .5f + style.collapsePadding.getHorizontal();
			float height = style.collapseTextFontSize + style.collapsePadding.getVertical();

			int itemX = (int) currentWindow.getNextItemX(Integer.MAX_VALUE);
			int itemY = (int) currentWindow.getNextItemY(height);

			float mx = Gdx.input.getX();
			float my = Gdx.input.getY();

			boolean hovered = mx > itemX && my > itemY && mx < itemX + Math.min(width,currentWindow.getWidth() - (itemX - currentWindow.oldX)) && my < itemY + height;

			NanoVG.nvgBeginPath(nvg);

			NanoVG.nvgRoundedRectVarying(nvg, itemX, itemY, width, height, style.collapseBorderRadius.topLeft, style.collapseBorderRadius.topRight, style.collapseBorderRadius.bottomLeft, style.collapseBorderRadius.bottomRight);
			if (hovered)
				style.getPaints().collapseHoverBackgroundPaint.fill(nvg, itemX, itemY, width, height);
			else
				style.getPaints().collapseBackgroundPaint.fill(nvg, itemX, itemY, width, height);
			NanoVG.nvgFill(nvg);
			NanoVG.nvgStrokeWidth(nvg, style.frameBorderWidth);
			style.getPaints().collapseBorderPaint.stroke(nvg, itemX, itemY, width, height);
			NanoVG.nvgStroke(nvg);

			NanoVG.nvgClosePath(nvg);

			NanoVG.nvgBeginPath(nvg);

			NanoVG.nvgFontSize(nvg, style.collapseTextFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			style.getPaints().collapseTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX + style.collapsePadding.left + style.collapseTextFontSize + 2, itemY + style.collapsePadding.top + style.collapseTextFontSize / 2f, text);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);

			NanoVG.nvgBeginPath(nvg);

			if (open) {
				NanoVG.nvgMoveTo(nvg,itemX + style.collapsePadding.left,itemY + style.collapsePadding.top);
				NanoVG.nvgLineTo(nvg,itemX + style.collapsePadding.left + style.collapseTextFontSize,itemY + style.collapsePadding.top);
				NanoVG.nvgLineTo(nvg,itemX + style.collapsePadding.left + style.collapseTextFontSize * .5f,itemY + style.collapseTextFontSize + style.collapsePadding.top);
				NanoVG.nvgLineTo(nvg,itemX + style.collapsePadding.left,itemY + style.collapsePadding.top);
			} else {
				NanoVG.nvgMoveTo(nvg,itemX + style.collapsePadding.left,itemY + style.collapsePadding.top + style.collapseTextFontSize);
				NanoVG.nvgLineTo(nvg,itemX + style.collapsePadding.left + style.collapseTextFontSize * .5f,itemY + style.collapsePadding.top);
				NanoVG.nvgLineTo(nvg,itemX + style.collapsePadding.left + style.collapseTextFontSize,itemY + style.collapsePadding.top + style.collapseTextFontSize);
				NanoVG.nvgLineTo(nvg,itemX + style.collapsePadding.left,itemY + style.collapsePadding.top + style.collapseTextFontSize);
			}
			style.getPaints().collapseIconPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);

			if (hovered) {
				cursor = SystemCursor.Hand;
				if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
					this.open = !this.open;
			}

		}

	}
	
	private static class Window {
		
		int id;
		float itemPosition = 0;
		int itemId = 0;
		
		float titleBarHeight;
		float windowHeight;
		float windowWidth;
		float oldX;
		float oldY;
		
		WindowData winData;

		boolean sameLineX = false;
		boolean sameLineY = false;
		
		private int options;
		
		boolean hovered;
		
		private Window(int options) {
			this.options = options;
		}
		
		private void render(String title, WindowData winData) {
			oldX = winData.x;
			oldY = winData.y;

			NanoVG.nvgFontSize(nvg, style.windowTitleFontSize);
			NanoVG.nvgTextAlign(nvg, NVG_ALIGN_BASELINE | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, title, bounds);

			float width = Math.abs(bounds[0]) + bounds[2];
			float height = Math.abs(bounds[1]) + bounds[3];
			float winHeight = 50f;
			
			if (winData.width != null)
				width = winData.width;
			if (winData.height != null)
				winHeight = winData.height;

			if (nextWindowMinWidth != null)
				width = Math.max(width, nextWindowMinWidth);
			else if (style.minWindowWidth != null)
				width = Math.max(width, style.minWindowWidth);
			if (nextWindowMinHeight != null)
				winHeight = Math.max(winHeight, nextWindowMinHeight);
			else if (style.minWindowHeight != null)
				winHeight = Math.max(winHeight, style.minWindowHeight);
			if (nextWindowMaxWidth != null)
				width = Math.min(width, nextWindowMaxWidth);
			else if (style.maxWindowWidth != null)
				width = Math.min(width, style.maxWindowWidth);
			if (nextWindowMaxHeight != null)
				winHeight = Math.min(winHeight, nextWindowMaxHeight);
			else if (style.maxWindowHeight != null)
				winHeight = Math.min(winHeight, style.maxWindowHeight);

			boolean enableBackground = (WindowOptions.NO_BACKGROUND & options) == 0;
			boolean enableTitleBar = (WindowOptions.NO_TITLEBAR & options) == 0;
			boolean enableResize = (WindowOptions.NO_RESIZE & options) == 0;
			boolean enableResizeX = (WindowOptions.NO_RESIZE_X & options) == 0;
			boolean enableResizeY = (WindowOptions.NO_RESIZE_Y & options) == 0;
			boolean enableResizeXY = (WindowOptions.NO_RESIZE_XY & options) == 0;
			boolean enableScroll = (WindowOptions.NO_SCROLL & options) == 0;
			
			if (!enableTitleBar) {
				winHeight += height + style.windowPadding.getVertical();
				height = 0;
			}
			
			hovered = mouseX > winData.x && mouseY > winData.y && mouseX < winData.x + width + style.windowPadding.getHorizontal() && mouseY < winData.y + winHeight + height + style.windowPadding.getVertical2();
			if (hovered)
				anyWindowHovered = hovered;

			windowHeight = winHeight;
			windowWidth = width;
			titleBarHeight = height;

			float wo = (enableScroll ? style.scrollbarOffsetX * 2 + style.scrollbarWidth : 0);
			float ho = (enableScroll && (enableResize && enableResizeXY) ? -20 : 0);

			if (enableBackground) {
				
				NanoVG.nvgBeginPath(nvg);
				
				if (enableTitleBar)
					if (style.windowBorderRadius.bottomLeft > 0 && style.windowBorderRadius.bottomRight > 0)
						NanoVG.nvgRoundedRectVarying(nvg, winData.x, winData.y + (enableTitleBar ?  height + style.windowPadding.getVertical() : 0), width + style.windowPadding.getHorizontal(), winHeight + style.windowPadding.getVertical(), 0, 0, style.windowBorderRadius.bottomLeft, style.windowBorderRadius.bottomRight);
					else
						NanoVG.nvgRect(nvg, winData.x, winData.y + (enableTitleBar ? height + style.windowPadding.getVertical() : 0), width + style.windowPadding.getHorizontal(), winHeight + style.windowPadding.getVertical());
				else 
					if (style.windowBorderRadius.bottomLeft > 0 && style.windowBorderRadius.bottomRight > 0 && style.windowBorderRadius.topLeft > 0 && style.windowBorderRadius.topRight > 0)
						NanoVG.nvgRoundedRectVarying(nvg, winData.x, winData.y + (enableTitleBar ?  height + style.windowPadding.getVertical() : 0), width + style.windowPadding.getHorizontal(), winHeight + style.windowPadding.getVertical(), style.windowBorderRadius.topLeft, style.windowBorderRadius.topRight, style.windowBorderRadius.bottomLeft, style.windowBorderRadius.bottomRight);
					else
						NanoVG.nvgRect(nvg, winData.x, winData.y + (enableTitleBar ?  height + style.windowPadding.getVertical() : 0), width + style.windowPadding.getHorizontal(), winHeight + style.windowPadding.getVertical());

				style.getPaints().windowBackgroundPaint.fill(nvg, winData.x, winData.y + (enableTitleBar ?  height + style.windowPadding.getVertical() : 0), width + style.windowPadding.getHorizontal(), winHeight + style.windowPadding.getVertical());
				NanoVG.nvgFill(nvg);
				
				NanoVG.nvgClosePath(nvg);
			}
			
			if (winData.maxItemY + lastItemHeight > winHeight && enableScroll) {
				
				float scl = (float) winHeight / (winData.maxItemY + lastItemHeight);
				float h = (winHeight + style.windowPadding.getVertical() - style.scrollbarOffsetY * 2 + ho);
				float a = (winData.scroll / 1f) * scl;
				
				//System.out.println("MAX : " + (winData.maxItemY + lastItemHeight) + " WINHEIGHT : " + winHeight + " scroll : " + winData.scroll);
				
				NanoVG.nvgBeginPath(nvg);
				//MathUtils.map(a, a, a, a, a)
				NanoVG.nvgRoundedRectVarying(nvg, winData.x + width + style.windowPadding.getHorizontal() + style.scrollbarOffsetX - wo, winData.y + height + style.windowPadding.getVertical() + style.scrollbarOffsetY + a, style.scrollbarWidth, Math.max(h * scl, style.sliderMinHeight), style.scrollbarBorderRadius.topLeft, style.scrollbarBorderRadius.topRight, style.scrollbarBorderRadius.bottomLeft, style.scrollbarBorderRadius.bottomRight);
				
				style.getPaints().scrollbarPaint.fill(nvg, winData.x + width + style.windowPadding.getHorizontal() + style.scrollbarOffsetX - wo, winData.y + height + style.windowPadding.getVertical() + style.scrollbarOffsetY + a, style.scrollbarWidth, Math.max(h * scl, style.sliderMinHeight));
				NanoVG.nvgFill(nvg);

				NanoVG.nvgClosePath(nvg);
				
			}
			
			NanoVG.nvgBeginPath(nvg);
			
			if (enableTitleBar) {
				if (style.windowBorderRadius.topLeft > 0 && style.windowBorderRadius.topRight > 0)
					NanoVG.nvgRoundedRectVarying(nvg, winData.x, winData.y, width + style.windowPadding.getHorizontal(), height + style.windowPadding.getVertical(), style.windowBorderRadius.topLeft, style.windowBorderRadius.topRight, 0, 0);
				else
					NanoVG.nvgRect(nvg, winData.x, winData.y, width + style.windowPadding.getHorizontal(), height + style.windowPadding.getVertical());
				style.getPaints().titleBarBackgroundPaint.fill(nvg, winData.x, winData.y, width + style.windowPadding.getHorizontal(), height + style.windowPadding.getVertical());
				NanoVG.nvgFill(nvg);
				NanoVG.nvgStrokeWidth(nvg, style.windowTitleBarBorderWidth);
				style.getPaints().titleBarBorderPaint.stroke(nvg, winData.x, winData.y, width + style.windowPadding.getHorizontal(), height + style.windowPadding.getVertical());
				NanoVG.nvgStroke(nvg);
			}
			
			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);

			if (enableTitleBar) {
				style.getPaints().titleBarTextPaint.fill(nvg,0,0, 0, 0);
				NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_TOP | NanoVG.NVG_ALIGN_LEFT);
				NanoVG.nvgFontSize(nvg, style.windowTitleFontSize);
				NanoVG.nvgText(nvg, winData.x + style.windowPadding.left, winData.y + style.windowPadding.top + 2, title);
				NanoVG.nvgFill(nvg);
			}
			
			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);
			
			if (enableTitleBar) {
				if (style.windowBorderRadius.topLeft > 0 && style.windowBorderRadius.topRight > 0)
					NanoVG.nvgRoundedRectVarying(nvg, winData.x, winData.y, width + style.windowPadding.getHorizontal(), height + style.windowPadding.getVertical(), style.windowBorderRadius.topLeft, style.windowBorderRadius.topRight, 0, 0);
				else
					NanoVG.nvgRect(nvg, winData.x, winData.y, width + style.windowPadding.getHorizontal(), height + style.windowPadding.getVertical());
				NanoVG.nvgStrokeWidth(nvg, style.windowTitleBarBorderWidth);
				style.getPaints().titleBarBorderPaint.stroke(nvg, winData.x, winData.y, width + style.windowPadding.getHorizontal(), height + style.windowPadding.getVertical());
			}
			
			NanoVG.nvgClosePath(nvg);

			float yp = height + (enableTitleBar ? style.windowPadding.getVertical2() : style.windowPadding.getVertical());
			
			if (enableResize && enableResizeXY) {
				
				NanoVG.nvgBeginPath(nvg);
				
				NanoVG.nvgMoveTo(nvg, winData.x + width + style.windowPadding.getHorizontal(), winData.y + winHeight + yp);
				NanoVG.nvgLineTo(nvg, winData.x + width + style.windowPadding.getHorizontal(), winData.y + winHeight + yp - 20);
				NanoVG.nvgLineTo(nvg, winData.x + width + style.windowPadding.getHorizontal() - 20, winData.y + winHeight + yp);
				NanoVG.nvgLineTo(nvg, winData.x + width + style.windowPadding.getHorizontal(), winData.y + winHeight + yp);

				style.getPaints().resizeButtonPaint.fill(nvg, winData.x + width + style.windowPadding.getHorizontal(), winData.y + winHeight + yp, 20, 20);
				NanoVG.nvgFill(nvg);
				
				NanoVG.nvgClosePath(nvg);
			}
			
			this.scissor();

			float mouseX = Gdx.input.getX();
			float mouseY = Gdx.input.getY();
			
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !winData.resizing && (rmwin == -1 || rmwin == id) && enableTitleBar && !usingSlider) {
				if (mouseX > winData.x && mouseY > winData.y && mouseX < winData.x + width + style.windowPadding.getHorizontal() && mouseY < winData.y + height + style.windowPadding.getVertical()) {
					if (!winData.moving) {
						winData.mmox = mouseX - winData.x;
						winData.mmoy = mouseY - winData.y;
					}
					winData.moving = true;
				}
			} else {
				winData.moving = false;
			}
			
			if (winData.moving) {
				winData.x = mouseX - winData.mmox;
				winData.y = mouseY -winData.mmoy;
			}

			float dstx = mouseX - winData.x;
			float dsty = mouseY - winData.y;
			
			if ((rmwin == -1 || rmwin == id) && enableResize && !usingSlider) {
				if (dstx > width + style.windowPadding.getHorizontal() - 20 && dstx <= width + style.windowPadding.getHorizontal() + 5 && dsty > winHeight + yp - 20 && dsty <= winHeight + yp + 2 && !winData.resizingR && !winData.resizingL && !winData.resizingV && enableResizeXY) {
					cursor = SystemCursor.NWSEResize;
				} else if (((dstx > width + style.windowPadding.getHorizontal() - 3 && dstx < width + style.windowPadding.getHorizontal() + 3) || (dstx > -3 && dstx < 3)) && mouseY > winData.y && mouseY < winData.y + winHeight + height + (enableTitleBar ? style.windowPadding.getVertical() : 0) + style.windowPadding.getVertical() && !winData.resizingAll && !winData.resizingV && enableResizeX) {
					cursor = SystemCursor.HorizontalResize;
				} else if (dstx >= 0 && dstx < width + style.windowPadding.getHorizontal() - (enableTitleBar ? 20 : 0) && dsty > winHeight + height + (enableTitleBar ? style.windowPadding.getVertical() : 0) + style.windowPadding.getVertical() - 3 && dsty < winHeight + height + (enableTitleBar ? style.windowPadding.getVertical() : 0) + style.windowPadding.getVertical() + 3 && !winData.resizingR && !winData.resizingL && !winData.resizingAll && enableResizeY) {
					cursor = SystemCursor.VerticalResize;
				}
			}
			
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !winData.moving && (rmwin == -1 || rmwin == id) && !usingSlider) {
				if (dstx > width + style.windowPadding.getHorizontal() - 20 && dstx <= width + style.windowPadding.getHorizontal() + 2 && dsty > winHeight + height + style.windowPadding.getVertical2() - 20 && dsty <= winHeight + height + style.windowPadding.getVertical2() + 5 && !winData.resizingR && !winData.resizingL && !winData.resizingV) {
					winData.resizing = true;
					winData.resizingAll = true;
				} else if (dstx > width + style.windowPadding.getHorizontal() - 3 && dstx < width + style.windowPadding.getHorizontal() + 3 && mouseY > winData.y && mouseY < winData.y + winHeight + height + (enableTitleBar ? style.windowPadding.getVertical() : 0) + style.windowPadding.getVertical() && !winData.resizingAll && !winData.resizingV && !winData.resizingL) {
					winData.resizing = true;
					winData.resizingR = true;
				} else if (dstx > -3 && dstx < 3 && mouseY > winData.y && mouseY < winData.y + winHeight + height + (enableTitleBar ? style.windowPadding.getVertical() : 0) + style.windowPadding.getVertical() && !winData.resizingAll && !winData.resizingV && !winData.resizingR) {
					if (!winData.resizingL) {
						winData.lrsx = winData.x;
						winData.lrsw = width + style.windowPadding.getHorizontal();
					}
					winData.resizing = true;
					winData.resizingL = true;
				} else if (dstx >= 0 && dstx < width + style.windowPadding.getHorizontal() - (enableTitleBar ? 20 : 0) && dsty > winHeight + height + (enableTitleBar ? style.windowPadding.getVertical() : 0) + style.windowPadding.getVertical() - 3 && dsty < winHeight + height + (enableTitleBar ? style.windowPadding.getVertical() : 0) + style.windowPadding.getVertical() + 3 && !winData.resizingR && !winData.resizingL && !winData.resizingAll) {
					winData.resizing = true;
					winData.resizingV = true;
				}
			} else {
				winData.resizing = false;
				winData.resizingL = false;
				winData.resizingR = false;
				winData.resizingV = false;
				winData.resizingAll = false;
			}
			
			if (!enableResize) {
				winData.resizing = false;
				winData.resizingL = false;
				winData.resizingR = false;
				winData.resizingV = false;
				winData.resizingAll = false;
			}
			if (!enableResizeX) {
				winData.resizingL = false;
				winData.resizingR = false;
			}
			if (!enableResizeY) {
				winData.resizingV = false;
			}
			if (!enableResizeXY) {
				winData.resizingAll = false;
			}

			if (winData.resizingL || winData.resizingR)
				cursor = SystemCursor.HorizontalResize;
			if (winData.resizingV)
				cursor = SystemCursor.VerticalResize;
			if (winData.resizingAll)
				cursor = SystemCursor.NWSEResize;
			
			float dstex = (winData.lrsx + winData.lrsw + style.windowPadding.getHorizontal()) - Gdx.input.getX();

			float minW = -1;
			float maxW = -1;

			if (style.minWindowWidth != null)
				minW = style.minWindowWidth;
			if (style.maxWindowWidth != null)
				maxW = style.maxWindowWidth;
			if (nextWindowMinWidth != null)
				minW = nextWindowMinWidth;
			if (nextWindowMaxWidth != null)
				maxW = nextWindowMaxWidth;
			
			if (winData.resizingL) {
				if (minW != -1) {
					if (dstex > minW + style.windowPadding.getHorizontal2()) {
						if (maxW != -1) {
							if (dstex < maxW + style.windowPadding.getHorizontal2()) {
								winData.width = dstex - style.windowPadding.getHorizontal2();
								winData.x += dstx;
							}
						} else {
							winData.width = dstex - style.windowPadding.getHorizontal2();
							winData.x += dstx;
						}
					}
				}
			} else if (winData.resizingR) {
				winData.width = dstx - style.windowPadding.getHorizontal();
				winData.resizing = true;
			} else if (winData.resizingV) {
				winData.height = dsty - style.windowPadding.getVertical3();
				winData.resizing = true;
			} else if (winData.resizingAll) {
				winData.width = dstx - style.windowPadding.getHorizontal();
				winData.height = dsty - style.windowPadding.getVertical3();
				winData.resizing = true;
			} else {
				winData.resizing = false;
			}
			
			if (winData.moving || winData.resizing) {
				if (Gui.rmwin == -1) {
					Gui.rmwin = id;
				}
			} else {
				if (Gui.rmwin == id) {
					Gui.rmwin = -1;
				}
			}
			
			this.winData = winData;
			
			boolean hovered = mouseX > oldX && mouseY > oldY && mouseX < oldX + width && mouseY < oldY + winHeight || false;
			if (hovered)
				System.out.println("asd");
			if (hovered && enableScroll) {
				winData.scroll += GuiInputProcessor.scroll * 20;
				GuiInputProcessor.scroll = 0;
			}

			winData.scroll = Math.max(Math.min(winData.scroll, winData.maxItemY - winHeight - style.windowPadding.bottom), 0);

			if (winData.moving) winData.maxItemY = 0;

		}
		
		float offsetX = 0;

		float getNextItemX(float width, boolean d) {
			float res = oldX + style.windowPadding.left + (sameLineX ? lastItemWidth + style.itemSpacing.x : 0) + offsetX;
			lastItemWidth = width + (sameLineX ? lastItemWidth + style.itemSpacing.x : 0);
			if (sameLineX)
				sameLineX = false;
			if (currentGroup != null)
				return res + style.groupPadding.left;
			
			itemId++;
			
			return res;
		}

		float getY() {
			return oldY;
		}
		
		public void indent() {
			offsetX += style.indentWidth;
		}
		
		public void unindent() {
			offsetX -= style.indentWidth;
		}

		boolean group;
		float groupBottom;

		@Deprecated
		float getNextItemYOld(float height, boolean d) {
			float val = itemPosition - (sameLineY ? lastItemHeight + style.itemSpacing.y : 0);
			float res = oldY + val + style.windowPadding.top + style.windowPadding.getVertical() + titleBarHeight - winData.scroll;
			if (!d) {
				itemPosition += (sameLineY ? 0 : height + style.itemSpacing.y);
				lastItemHeight = height;
			}
			
			/*
			itemPosition -= (style.windowItemSpacing.y + style.groupPadding.getVertical());
			lastItemHeight = height;
			*/

			if (currentGroup != null) {
				groupBottom = (int) (currentGroup.height + (sameLineY ? 0 : style.itemSpacing.y) + style.groupPadding.getVertical());
				group = true;
			} else {
				if (group) {
					res -= itemPosition;
					res += groupBottom + height + style.itemSpacing.y;
					itemPosition -= itemPosition;
					itemPosition += groupBottom+ height + style.itemSpacing.y;
					groupBottom = 0;
					group = false;
				}
				winData.maxItemY = Math.max(winData.maxItemY, itemPosition);
			}

			itemId++;

			sameLineY = false;
			if (currentGroup != null)
				res = oldY + val + style.windowPadding.top + style.windowPadding.getVertical() + style.groupPadding.top + titleBarHeight;
			return res;
		}

		float getNextItemY(float height, boolean d) {
			float off = oldY + titleBarHeight + style.windowPadding.getVertical() + style.windowPadding.top;

			float out = itemPosition;

			if (d) {
				System.out.println(itemPosition);
				groupBottom = height + style.itemSpacing.y + style.groupPadding.getVertical();
			}

			if (!sameLineY) {
				itemPosition += height + style.itemSpacing.y;
			} else {
				out -= lastItemHeight + style.itemSpacing.y;
			}

			if (group && currentGroup == null) {
				itemPosition = groupBottom;
				out = itemPosition;
				if (!sameLineY) {
					itemPosition += height + style.itemSpacing.y;
				} else {
					out -= lastItemHeight + style.itemSpacing.y;
				}
				group = false;
			}

			if (currentGroup != null && !group) {
				itemPosition -= style.groupHeightPixel;
				out -= style.groupHeightPixel;
				group = true;
			} else if (currentGroup != null) {
				//itemPosition -= style.groupHeightPixel;
				//out -= style.groupHeightPixel;
				group = true;
			};

			itemId++;

			winData.maxItemY = Math.max(winData.maxItemY, itemPosition);
			sameLineY = false;
			lastItemHeight = height;
			return off + out - winData.scroll;
		}
		
		float getNextItemX(float width) {
			return getNextItemX(width, false);
		}
		
		float getNextItemY(float height) {
			return getNextItemY(height, false);
		}
		
		float getWidth() {
			boolean enableScroll = (WindowOptions.NO_SCROLL & options) == 0;
			float wo = (enableScroll ? style.scrollbarOffsetX * 2 + style.scrollbarWidth : 0);
			return windowWidth + style.windowPadding.getHorizontal() - wo;
		}
		
		float getHeight() {
			return windowHeight + titleBarHeight + style.windowPadding.getVertical2();
		}
		
		int getId() {
			return this.id;
		}
		
		int getItemId() {
			return itemId;
		}
		
		int getSelectedItemId() {
			return winData.selectedItemId;
		}
		
		void setSelectedItemId(int selectedItemId) {
			winData.selectedItemId = selectedItemId;
		}
		
		void scissor() {
			boolean enableScroll = (WindowOptions.NO_SCROLL & options) == 0;
			float wo = (enableScroll ? style.scrollbarOffsetX * 2 + style.scrollbarWidth : 0);
			NanoVG.nvgScissor(nvg, oldX, oldY + titleBarHeight + style.windowPadding.getVertical(), windowWidth + style.windowPadding.getHorizontal() - wo, windowHeight + style.windowPadding.getVertical());
		}
		
		void sameLine() {
			sameLineX = true;
			sameLineY = true;
		}
		
	}
	
	private static long nvg;
	
	private static Style style = new Style();
	
	private static boolean infrm = false;
	
	private static Window currentWindow;

	private static IntBuffer fbo = BufferUtils.newIntBuffer(1);
	
	private static Clipboard clipboard;
	
	private Gui() {}
	
	public static void init() {
		init(Gdx.files.internal("RobotoMono-Regular.ttf"), "RobotoMono-Regular");
	}
	
	public static void init(FileHandle fontFile, String fontName) {
		nvg = nvgCreate(NVG_STENCIL_STROKES);

		try {
			nvgCreateFontMem(nvg, fontName, Utils.ioResourceToByteBuffer(fontFile, 150 * 1024), 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Gdx.gl30.glGenFramebuffers(1, fbo);
		Gdx.gl30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, fbo.get(0));
		
		clipboard = Gdx.app.getClipboard();
		
		batch = new SpriteBatch();
		
	}
	
	public static void addFont(FileHandle fontFile, String fontName) {
		if (NanoVG.nvgFindFont(nvg, fontName) == -1) {
			try {
				nvgCreateFontMem(nvg, fontName, Utils.ioResourceToByteBuffer(fontFile, 150 * 1024), 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("A font with this name already exists.");
		}
	}
	
	public static void setFont(String fontName) {
		NanoVG.nvgFontFace(nvg, fontName);
	}
	
	public static Style getStyle() {
		return style;
	}
	
	public static void setNextWindowX(float x) {
		nextWindowX = x;
	}
	public static void setNextWindowY(float y) {
		nextWindowY = y;
	}
	public static void setNextWindowWidth(float width) {
		nextWindowWidth = width;
	}
	public static void setNextWindowHeight(float height) {
		nextWindowHeight = height;
	}
	public static void setNextWindowMinWidth(float width) {
		nextWindowMinWidth = width;
	}
	public static void setNextWindowMinHeight(float height) {
		nextWindowMinHeight = height;
	}
	public static void setNextWindowMaxWidth(float width) {
		nextWindowMaxWidth = width;
	}
	public static void setNextWindowMaxHeight(float height) {
		nextWindowMaxHeight = height;
	}
	
	public static void setNextWindowPosition(float x, float y) {
		nextWindowX = x;
		nextWindowY = y;
	}
	public static void setNextWindowSize(float width, float height) {
		nextWindowWidth = width;
		nextWindowHeight = height;
	}
	public static void setNextWindowMinSize(float width, float height) {
		nextWindowMinWidth = width;
		nextWindowMinHeight = height;
	}
	public static void setNextWindowMaxSize(float width, float height) {
		nextWindowMaxWidth = width;
		nextWindowMaxHeight = height;
	}
	
	public static void setNextWindowPosition(Vector2 position) {
		nextWindowX = position.x;
		nextWindowY = position.y;
	}
	public static void setNextWindowSize(Vector2 size) {
		nextWindowWidth = size.x;
		nextWindowHeight = size.y;
	}
	public static void setNextWindowMinSize(Vector2 size) {
		nextWindowMinWidth = size.x;
		nextWindowMinHeight = size.y;
	}
	public static void setNextWindowMaxSize(Vector2 size) {
		nextWindowMaxWidth = size.x;
		nextWindowMaxHeight = size.y;
	}
	
	private static int windowDataId = 0;
	
	public static void beginFrame() {
		if (infrm)
			throw new IllegalStateException("Gui.endFrame must be called before beginFrane.");

		cursor = SystemCursor.Arrow;
		
		time += Gdx.graphics.getDeltaTime();
		
		NanoVG.nvgBeginFrame(nvg, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1);
		infrm = true;

		mouseX = Gdx.input.getX();
		mouseY = Gdx.input.getY();
		
		anyWindowHovered = false;
		anyItemHovered = false;
	}
	
	public static void endFrame() {
		if (!infrm)
			throw new IllegalStateException("Gui.beginFrame must be called before endFrame.");
		
		NanoVG.nvgEndFrame(nvg);
		
		windowDataId = 0;
		infrm = false;

		currentWindow = null;
		nextWindowX = null;
		nextWindowY = null;
		nextWindowWidth = null;
		nextWindowHeight = null;
		nextWindowMinWidth = null;
		nextWindowMinHeight = null;
		nextWindowMaxWidth = null;
		nextWindowMaxHeight = null;
		
		Gdx.graphics.setSystemCursor(cursor);
		
	}
	
	public static void begin(String title, int options) {
		if (currentWindow == null) {
			
			currentWindow = new Window(options);
			currentWindow.id = windowDataId;
			
			WindowData winData = null;
			if (windowDatas.size > windowDataId) {
				winData = windowDatas.get(windowDataId);
				if (winData.windowId != currentWindow.id) {
					winData = new WindowData();
					winData.windowId = windowDataId;
					winData.x = MathUtils.random(0, Gdx.graphics.getWidth());
					winData.y = MathUtils.random(0, Gdx.graphics.getHeight());
					if (nextWindowX != null)
						winData.x = nextWindowX;
					if (nextWindowY != null)
						winData.y = nextWindowY;
					winData.width = nextWindowWidth;
					winData.height = nextWindowHeight;
					
					windowDatas.insert(windowDataId, winData);
				}
				if (nextWindowWidth != null)
					winData.width = nextWindowWidth;
				if (nextWindowHeight != null)
					winData.height = nextWindowHeight;
			} else {
				winData = new WindowData();
				winData.windowId = windowDataId;
				winData.x = MathUtils.random(0, Gdx.graphics.getWidth());
				winData.y = MathUtils.random(0, Gdx.graphics.getHeight());
				if (nextWindowX != null)
					winData.x = nextWindowX;
				if (nextWindowY != null)
					winData.y = nextWindowY;
				winData.width = nextWindowWidth;
				winData.height = nextWindowHeight;
				
				windowDatas.add(winData);
			}

			currentWindow.render(title, winData);
			windowDataId++;
		}
	}
	
	public static void begin(String title) {
		begin(title, 0);
	}
	
	public static void end() {
		if (currentWindow != null) {
			currentWindow = null;
			nextWindowX = null;
			nextWindowY = null;
			nextWindowWidth = null;
			nextWindowHeight = null;
			nextWindowMinWidth = null;
			nextWindowMinHeight = null;
			nextWindowMaxWidth = null;
			nextWindowMaxHeight = null;
			NanoVG.nvgResetScissor(nvg);
		}
	}
	
	private static float[] bounds = new float[4];
	
	public static void text(String text) {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			NanoVG.nvgFontSize(nvg, style.textFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_TOP | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, text, bounds);
			
			float itemX = currentWindow.getNextItemX(bounds[2]);
			float itemY = currentWindow.getNextItemY(style.textFontSize);
			
			if (itemY - currentWindow.getY() <= currentWindow.getHeight() || itemX - currentWindow.oldX > currentWindow.getWidth()) {
				style.getPaints().textPaint.fill(nvg,0,0, 0, 0);

				NanoVG.nvgBeginPath(nvg);
				
				NanoVG.nvgFontSize(nvg, style.textFontSize);
				NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_TOP | NanoVG.NVG_ALIGN_LEFT);
				NanoVG.nvgText(nvg, itemX, itemY, text);
				NanoVG.nvgFill(nvg);
	
				NanoVG.nvgClosePath(nvg);
			}
		}
	}
	
	private static boolean baseButton(String text) {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			NanoVG.nvgFontSize(nvg, style.buttonTextFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_TOP | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, text, bounds);

			float mx = Gdx.input.getX();
			float my = Gdx.input.getY();
			
			float itemX = currentWindow.getNextItemX(bounds[2] + style.framePadding.getHorizontal());
			float itemY = currentWindow.getNextItemY(style.buttonTextFontSize + style.framePadding.getVertical());

			if (itemY - currentWindow.getY() > currentWindow.getHeight() || itemX - currentWindow.oldX > currentWindow.getWidth())
				return false;
			
			boolean hovered = mx >= itemX && my >= itemY && mx <= itemX + Math.min(bounds[2] + style.framePadding.getHorizontal(),currentWindow.getWidth() - (itemX - currentWindow.oldX)) && my <= itemY + bounds[3] + style.framePadding.getVertical();

			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRoundedRectVarying(nvg, itemX, itemY, bounds[2] + style.framePadding.getHorizontal(), bounds[3] + style.framePadding.getVertical(), style.frameBorderRadius.topLeft, style.frameBorderRadius.topRight, style.frameBorderRadius.bottomLeft, style.frameBorderRadius.bottomRight);
			if (hovered)
				style.getPaints().frameHoverBackgroundPaint.fill(nvg, itemX, itemY, bounds[2] + style.framePadding.getHorizontal(), bounds[3] + style.framePadding.getVertical());
			else
				style.getPaints().frameBackgroundPaint.fill(nvg, itemX, itemY, bounds[2] + style.framePadding.getHorizontal(), bounds[3] + style.framePadding.getVertical());
			NanoVG.nvgFill(nvg);
			NanoVG.nvgStrokeWidth(nvg, style.frameBorderWidth);
			style.getPaints().frameBorderPaint.stroke(nvg, itemX, itemY, bounds[2] + style.framePadding.getHorizontal(), bounds[3] + style.framePadding.getVertical());
			NanoVG.nvgStroke(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);
			
			style.getPaints().buttonTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX + style.framePadding.left, itemY + style.framePadding.top + 2, text);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
			if (hovered)
				cursor = SystemCursor.Hand;
			
			return hovered;
		}
		
		return false;
	}

	public static boolean button(String text) {
		return baseButton(text) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
	}
	
	public static boolean button(String text, boolean hold) {
		return baseButton(text) && (hold ? Gdx.input.isButtonPressed(Input.Buttons.LEFT) : Gdx.input.isButtonJustPressed(Input.Buttons.LEFT));
	}
	
	private static float baseSlider(String label, float pos, String value) {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			int itemId = currentWindow.getItemId();

			float width = style.sliderWidthPerc * currentWindow.getWidth();

			float mx = Gdx.input.getX();
			float my = Gdx.input.getY();
			
			NanoVG.nvgFontSize(nvg, style.sliderFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, label, bounds);
			
			float itemX = currentWindow.getNextItemX(width + style.framePadding.getHorizontal() + (bounds[2] - bounds[0]) + style.framePadding.left);
			float itemY = currentWindow.getNextItemY(style.sliderFontSize + style.framePadding.getVertical());
			
			boolean hovered = mx >= itemX & my >= itemY & mx <= itemX + Math.min(width + style.framePadding.getHorizontal(),currentWindow.getWidth() - (itemX - currentWindow.oldX)) & my <= itemY + style.sliderFontSize + style.framePadding.getVertical();
			
			if (itemY - currentWindow.getY() > currentWindow.getHeight() || itemX - currentWindow.oldX > currentWindow.getWidth())
				return pos;
			
			NanoVG.nvgFontSize(nvg, style.sliderFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_TOP | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, value, bounds);
			
			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRoundedRectVarying(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.sliderFontSize + style.framePadding.getVertical(), style.frameBorderRadius.topLeft, style.frameBorderRadius.topRight, style.frameBorderRadius.bottomLeft, style.frameBorderRadius.bottomRight);

			if (hovered || currentWindow.getSelectedItemId() == itemId)
				style.getPaints().frameHoverBackgroundPaint.fill(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.sliderFontSize + style.framePadding.getVertical());
			else
				style.getPaints().frameBackgroundPaint.fill(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.sliderFontSize + style.framePadding.getVertical());
			NanoVG.nvgFill(nvg);
			NanoVG.nvgStrokeWidth(nvg, style.frameBorderWidth);
			style.getPaints().frameBorderPaint.stroke(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.sliderFontSize + style.framePadding.getVertical());
			NanoVG.nvgStroke(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);

			NanoVG.nvgRoundedRectVarying(nvg, itemX + 1 + (pos * (width - style.sliderKnobWidth*2*(1-pos) - 2.5f*(pos))), itemY + 1 + style.frameBorderWidth, style.sliderKnobWidth, style.sliderFontSize + style.framePadding.getVertical() - 2 - style.frameBorderWidth * 2, style.sliderKnobBorderRadius.topLeft, style.sliderKnobBorderRadius.topRight, style.sliderKnobBorderRadius.bottomLeft, style.sliderKnobBorderRadius.bottomRight);

			style.getPaints().sliderKnobBackgroundPaint.fill(nvg, itemX + 1 + (pos * (width - style.sliderKnobWidth*2*(1-pos) - 2.5f*(pos))), itemY + 1 + style.frameBorderWidth, style.sliderKnobWidth, style.sliderFontSize + style.framePadding.getVertical() - 2 - style.frameBorderWidth * 2);
			NanoVG.nvgFill(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);

			style.getPaints().sliderValueTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX + width/2f - bounds[2] / 2f, itemY + style.framePadding.top + 2, value);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);

			style.getPaints().sliderLabelTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgFontSize(nvg, style.sliderFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_MIDDLE);
			NanoVG.nvgText(nvg, itemX + width + style.framePadding.getHorizontal() + style.framePadding.left, itemY + style.sliderFontSize/2f + style.framePadding.top + 2, label);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
			float knobDst = mx - itemX + 1 - style.sliderKnobWidth/2f;
			float val = Math.max(Math.min(knobDst / (width + style.framePadding.getHorizontal() - style.sliderKnobWidth), 1f), 0f);
			
			if (hovered && currentWindow.getSelectedItemId() == -1 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				currentWindow.setSelectedItemId(itemId);
				System.out.println(itemId);
			}

			if (currentWindow.getSelectedItemId() == itemId)
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && rmwin == -1) {
					usingSlider = true;
					return val;
				} else {
					currentWindow.setSelectedItemId(-1);
					usingSlider = false;
				}
			
		}
		
		return pos;
	}
	
	public static boolean sliderBoolean(String label, boolean b) {
		return (baseSlider(label, (b ? 1f : 0f), String.valueOf(b)) > .5f ? true : false);
	}
	
	public static int sliderInt(String label, int i, int min, int max, int step) {
		float mn = min;
		float mx = max;
		float it = i;
		return (int) (MathUtils.floor(((baseSlider(label, (it - mn) / (mx - mn), String.valueOf(i)) + mn) * (mx+mn)) / step) * step);
	}
	
	public static int sliderInt(String label, int i, int min, int max) {
		return sliderInt(label, i, min, max, 1);
	}
	
	public static float sliderFloat(String label, float f, float min, float max, float step) {
		return (float) (Math.floor((baseSlider(label, (f - min) / (max - min), Float.toString(Math.round(f * 100f) / 100f)) + min) * (max+min) / step) * step);
	}
	
	public static float sliderFloat(String label, float f, float min, float max) {
		return sliderFloat(label, f, min, max, .01f);
	}
	
	public static void label(String label, String text) {
		
		float itemX = currentWindow.getNextItemX(0);
		float itemY = currentWindow.getNextItemY(style.labelFontSize);
		
		if (currentWindow != null && (itemY - currentWindow.getY() > currentWindow.getHeight() || itemX - currentWindow.oldX > currentWindow.getWidth()) && !(insideCollapse && !currentCollapse.open)) {

			float textPos = style.labelWidthPerc * currentWindow.getWidth();
			
			NanoVG.nvgFontSize(nvg, style.labelFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, text, bounds);
			
			NanoVG.nvgBeginPath(nvg);

			style.getPaints().labelPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX, itemY, label);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);

			style.getPaints().labelTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX + textPos, itemY, text);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
		}
	}

	private static int cursorPosition;
	private static int selStart;
	private static int selEnd;
	
	public static String inputText(String label, String text) {
		
		String newText = text;
		
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			NanoVG.nvgFontSize(nvg, style.inputFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, label, bounds);
			
			float width = style.inputWidthPerc * currentWindow.getWidth();
			
			int itemId = currentWindow.getItemId();
			int selItemId = currentWindow.getSelectedItemId();
			
			float itemX = currentWindow.getNextItemX(width + style.framePadding.getHorizontal() + (bounds[2] - bounds[0]) + style.framePadding.left);
			float itemY = currentWindow.getNextItemY(style.inputFontSize + style.framePadding.getVertical());
			
			if (itemY - currentWindow.getY() > currentWindow.getHeight() || itemX - currentWindow.oldX > currentWindow.getWidth())
				return newText;

			float mx = Gdx.input.getX();
			float my = Gdx.input.getY();
			
			boolean hovered = mx >= itemX & my >= itemY & mx <= itemX + Math.min(width + style.framePadding.getHorizontal(),currentWindow.getWidth() - (itemX - currentWindow.oldX)) & my <= itemY + style.inputFontSize + style.framePadding.getVertical();
			
			NanoVG.nvgBeginPath(nvg);

			NanoVG.nvgRoundedRectVarying(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.inputFontSize + style.framePadding.getVertical(), style.frameBorderRadius.topLeft, style.frameBorderRadius.topRight, style.frameBorderRadius.bottomLeft, style.frameBorderRadius.bottomRight);
			if (hovered || selItemId == itemId)
				style.getPaints().frameHoverBackgroundPaint.fill(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.inputFontSize + style.framePadding.getVertical());
			else
				style.getPaints().frameBackgroundPaint.fill(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.inputFontSize + style.framePadding.getVertical());
			NanoVG.nvgFill(nvg);
			NanoVG.nvgStrokeWidth(nvg, style.frameBorderWidth);
			style.getPaints().frameBorderPaint.stroke(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.inputFontSize + style.framePadding.getVertical());
			NanoVG.nvgStroke(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);

			NanoVG.nvgFontSize(nvg, style.inputFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			style.getPaints().inputTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX + width + style.framePadding.getHorizontal() + style.framePadding.left, itemY + style.framePadding.top + style.inputFontSize / 2f, label);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgScissor(nvg, itemX, currentWindow.oldY + style.windowTitleFontSize + style.windowPadding.getVertical(), Math.min(width + style.framePadding.getHorizontal(), currentWindow.getWidth() - (itemX - currentWindow.oldX)), currentWindow.getHeight() - style.windowTitleFontSize - style.windowPadding.getVertical());
			
			NanoVG.nvgBeginPath(nvg);

			NanoVG.nvgFontSize(nvg, style.inputFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			style.getPaints().inputTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX + style.framePadding.left, itemY + style.framePadding.top + style.inputFontSize / 2f, newText);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
			if (hovered)
				cursor = SystemCursor.Ibeam;
			
			int textLength = newText.length();
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				if (hovered) {
					currentWindow.setSelectedItemId(itemId);
					cursorPosition = textLength;
					float dst = 1000000;
					try ( NVGGlyphPosition.Buffer positions = NVGGlyphPosition.calloc(textLength); ) {
						NanoVG.nvgTextGlyphPositions(nvg, 0, 0, newText, positions);
						for (int i = 0; i < textLength; i++) {
							float mp = mx - itemX;
							float d = Math.abs((i == textLength ? positions.get(i).maxx() : positions.get(i).x()) - positions.get(0).x() - mp);
							if (d < dst) {
								cursorPosition = i;
								dst = d;
							}
						}
					} catch (Exception e) {}
					selStart = cursorPosition;
					selEnd = cursorPosition;
				} else {
					if (selItemId == itemId)
						currentWindow.setSelectedItemId(-1);
				}
			}
			
			if (selItemId == itemId) {
				if (newText.isEmpty()) {
					NanoVG.nvgBeginPath(nvg);
					
					NanoVG.nvgRect(nvg, itemX + style.framePadding.left, itemY + style.framePadding.top, 1, style.inputFontSize);
					style.getPaints().inputTextPaint.fill(nvg,0,0, 0, 0);
					NanoVG.nvgFill(nvg);
					
					NanoVG.nvgClosePath(nvg);
				} else {
					try ( NVGGlyphPosition.Buffer positions = NVGGlyphPosition.calloc(textLength); ) {
						NanoVG.nvgTextGlyphPositions(nvg, 0, 0, newText, positions);
						
						NanoVG.nvgBeginPath(nvg);
						
						NanoVG.nvgRect(nvg, itemX + style.framePadding.left + (cursorPosition == textLength ? positions.get(cursorPosition - 1).maxx() : positions.get(cursorPosition).minx()), itemY + style.framePadding.top, 1, style.inputFontSize);
						style.getPaints().inputTextPaint.fill(nvg,0,0, 0, 0);
						NanoVG.nvgFill(nvg);
						
						NanoVG.nvgClosePath(nvg);

						selEnd = Math.min(selEnd, newText.length());
						
						float start = (selStart == textLength ? positions.get(selStart - 1).maxx() : positions.get(selStart).minx());
						float end = (selEnd == textLength ? positions.get(selEnd - 1).maxx() : positions.get(selEnd).minx());
						
						NanoVG.nvgBeginPath(nvg);
						
						NanoVG.nvgRect(nvg, itemX + style.framePadding.left + start, itemY + style.framePadding.top, end - start, style.inputFontSize);
						style.getPaints().selectionPaint.fill(nvg,0,0, 0, 0);
						NanoVG.nvgFill(nvg);
						
						NanoVG.nvgClosePath(nvg);
					} catch (Exception e) {}
				}
				
				boolean shift = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT);

				if (GuiInputProcessor.rArrow) {
					cursorPosition++;
					if (!shift) selStart = cursorPosition;
					selEnd = cursorPosition;
					GuiInputProcessor.rArrow = false;
				}
				if (GuiInputProcessor.lArrow) {
					cursorPosition--;
					if (!shift) selStart = cursorPosition;
					selEnd = cursorPosition;
					GuiInputProcessor.lArrow = false;
				}
					
				cursorPosition = Math.max(cursorPosition, 0);

				if ((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) | Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) & Gdx.input.isKeyJustPressed(Input.Keys.C)) {
					System.out.println("COPY");
				}
				
				if ((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) | Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) & Gdx.input.isKeyJustPressed(Input.Keys.V)) {
					if (clipboard.hasContents()) {
						String content = clipboard.getContents();
						newText += content;
						cursorPosition += content.length();
					}
				}
				
				if (Character.getName(GuiInputProcessor.chr).equals("BACKSPACE")) {
					if (textLength > 0) {
						if (selStart != selEnd) {
							String t0 = newText.substring(0, selStart);
							String t1 = newText.substring(selEnd, newText.length());
							newText = t0 + t1;
							GuiInputProcessor.chr = (int) 0;
							cursorPosition = selStart;
							selStart = cursorPosition;
							selEnd = cursorPosition;
							
							selStart = Math.max(Math.min(selStart, textLength - 1), 0);
							selEnd = Math.max(Math.min(selEnd, textLength - 1), 0);
						} else {
							if (cursorPosition == textLength) {
								newText = newText.substring(0, newText.length() - 1);
								GuiInputProcessor.chr = (int) 0;
								cursorPosition--;
							} else {
								newText = newText.substring(0, cursorPosition) + newText.substring(cursorPosition, newText.length() - 1);
								GuiInputProcessor.chr = (int) 0;
								cursorPosition--;
							}
							cursorPosition = Math.max(Math.min(cursorPosition, textLength - 1), 0);
						}
					}
				} else if (GuiInputProcessor.chr != 0 & !(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) | Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))) {
					if (selStart != selEnd) {
						String t0 = newText.substring(0, selStart);
						String t1 = newText.substring(selEnd, newText.length());
						newText = t0 + t1;
						
						cursorPosition = selStart;
						selStart = cursorPosition;
						selEnd = cursorPosition;
						
						t0 = newText.substring(0, cursorPosition);
						t1 = newText.substring(cursorPosition, newText.length());
						newText = t0 + GuiInputProcessor.chr + t1;
						cursorPosition++;
						GuiInputProcessor.chr = (int) 0;
					} else {
						String t0 = newText.substring(0, cursorPosition);
						String t1 = newText.substring(cursorPosition, newText.length());
						newText = t0 + GuiInputProcessor.chr + t1;
						GuiInputProcessor.chr = (int) 0;
						cursorPosition = selStart;
						selStart = cursorPosition;
						selEnd = cursorPosition;
						cursorPosition++;
					}
				}
			}
			
			currentWindow.scissor();
			
		}
		
		return newText;
	}
	
	public static void separator() {
		
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {

			float itemX = currentWindow.getNextItemX(0);
			float itemY = currentWindow.getNextItemY(style.separatorHeight);

			NanoVG.nvgBeginPath(nvg);

			NanoVG.nvgRect(nvg, itemX, itemY, currentWindow.getWidth() - style.windowPadding.getHorizontal(), style.separatorHeight);
			style.getPaints().separatorPaint.fill(nvg, itemX, itemY, currentWindow.getWidth() - style.windowPadding.getHorizontal(), style.separatorHeight);
			NanoVG.nvgFill(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
		}
		
	}
	
	public static void sameLine() {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open))
			currentWindow.sameLine();
	}
	
	public static void spacing() {
		
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {

			currentWindow.getNextItemX(0);
			currentWindow.getNextItemY(style.itemSpacing.y);
			
		}
		
	}
	
	public static boolean checkbox(String label, boolean active) {
		
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			NanoVG.nvgFontSize(nvg, style.checkboxFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, label, bounds);
			
			float size = style.checkboxFontSize;

			float itemX = currentWindow.getNextItemX(0);
			float itemY = currentWindow.getNextItemY(style.itemSpacing.y + style.framePadding.getVertical());

			float mx = Gdx.input.getX();
			float my = Gdx.input.getY();
			
			boolean hovered = mx > itemX && my > itemY && mx < itemX + Math.min(size + style.checkboxPadding.getHorizontal(),currentWindow.getWidth() - (itemX - currentWindow.oldX)) && my < itemY + size + style.checkboxPadding.getVertical();

			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRoundedRectVarying(nvg, itemX, itemY, size + style.checkboxPadding.getHorizontal(), size + style.checkboxPadding.getVertical(), style.frameBorderRadius.topLeft, style.frameBorderRadius.topRight, style.frameBorderRadius.bottomLeft, style.frameBorderRadius.bottomRight);
			if (hovered)
				style.getPaints().frameHoverBackgroundPaint.fill(nvg, itemX, itemY, size + style.checkboxPadding.getHorizontal(), size + style.checkboxPadding.getVertical());
			else
				style.getPaints().frameBackgroundPaint.fill(nvg, itemX, itemY, size + style.checkboxPadding.getHorizontal(), size + style.checkboxPadding.getVertical());
			NanoVG.nvgFill(nvg);
			NanoVG.nvgStrokeWidth(nvg, style.frameBorderWidth);
			style.getPaints().frameBorderPaint.stroke(nvg, itemX, itemY, size + style.checkboxPadding.getHorizontal(), size + style.checkboxPadding.getVertical());
			NanoVG.nvgStroke(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
			float f = 6;
			float h = 3;

			if (active) {
				NanoVG.nvgBeginPath(nvg);
				
				NanoVG.nvgRoundedRectVarying(nvg, itemX + h - .5f, itemY + h, size + style.checkboxPadding.getHorizontal() - f + 1, size + style.checkboxPadding.getVertical() - f + 1, style.frameBorderRadius.topLeft, style.frameBorderRadius.topRight, style.frameBorderRadius.bottomLeft, style.frameBorderRadius.bottomRight);
				if (active)
					style.getPaints().checkboxActiveBackgroundPaint.fill(nvg, itemX + h - .5f, itemY + h, size + style.checkboxPadding.getHorizontal() - f + 1, size + style.checkboxPadding.getVertical() - f + 1);
				NanoVG.nvgFill(nvg);
				
				NanoVG.nvgClosePath(nvg);
			}
			
			NanoVG.nvgBeginPath(nvg);

			NanoVG.nvgFontSize(nvg, style.checkboxFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			style.getPaints().checkboxTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX + size + style.checkboxPadding.getHorizontal() + style.checkboxPadding.left, itemY + style.checkboxPadding.top + style.checkboxFontSize / 2f, label);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
			if (hovered && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
				return !active;
			
		}
		return active;
	}
	
	public static boolean radioButton(String label, boolean active) {
		
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			NanoVG.nvgFontSize(nvg, style.radioButtonFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, label, bounds);
			
			float size = style.radioButtonFontSize;

			float itemX = currentWindow.getNextItemX(style.radioButtonPadding.getHorizontal());
			float itemY = currentWindow.getNextItemY(style.itemSpacing.y + style.radioButtonPadding.getVertical());

			float mx = Gdx.input.getX();
			float my = Gdx.input.getY();
			
			boolean hovered = mx > itemX && my > itemY && mx < itemX + Math.min(size + style.radioButtonPadding.getHorizontal(),currentWindow.getWidth() - (itemX - currentWindow.oldX)) && my < itemY + size + style.radioButtonPadding.getVertical();

			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRoundedRect(nvg, itemX, itemY, size + style.radioButtonPadding.getHorizontal(), size + style.radioButtonPadding.getVertical(), 1000);
			if (hovered)
				style.getPaints().frameHoverBackgroundPaint.fill(nvg, itemX, itemY, size + style.radioButtonPadding.getHorizontal(), size + style.radioButtonPadding.getVertical());
			else
				style.getPaints().frameBackgroundPaint.fill(nvg, itemX, itemY, size + style.radioButtonPadding.getHorizontal(), size + style.radioButtonPadding.getVertical());
			NanoVG.nvgFill(nvg);
			NanoVG.nvgStrokeWidth(nvg, style.frameBorderWidth);
			style.getPaints().frameBorderPaint.stroke(nvg, itemX, itemY, size + style.radioButtonPadding.getHorizontal(), size + style.radioButtonPadding.getVertical());
			NanoVG.nvgStroke(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
			float f = 6;
			float h = 3;

			if (active) {
				NanoVG.nvgBeginPath(nvg);
				
				NanoVG.nvgRoundedRect(nvg, itemX + h, itemY + h, size + style.radioButtonPadding.getHorizontal() - f, size + style.radioButtonPadding.getVertical() - f, 1000);
				if (active)
					style.getPaints().radioButtonActiveBackgroundPaint.fill(nvg, itemX, itemY, size + style.radioButtonPadding.getHorizontal() - f, size + style.radioButtonPadding.getVertical() - f);
				NanoVG.nvgFill(nvg);
				
				NanoVG.nvgClosePath(nvg);
			}
			
			NanoVG.nvgBeginPath(nvg);

			NanoVG.nvgFontSize(nvg, style.radioButtonFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			style.getPaints().radioButtonTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX + size + style.radioButtonPadding.getHorizontal() + style.radioButtonPadding.left, itemY + style.radioButtonPadding.top + style.radioButtonFontSize / 2f, label);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
			if (hovered && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
				return !active;
			
		}
		return active;
	}
	
	public static void image(Texture texture, int width, int height) {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			int itemX = (int) currentWindow.getNextItemX(width);
			int itemY = Gdx.graphics.getHeight() - (int) currentWindow.getNextItemY(height);

			NanoVG.nvgSave(nvg);
			NanoVG.nvgEndFrame(nvg);
			
			batch.begin();
			
		    Gdx.gl.glEnable(GL30.GL_SCISSOR_TEST);
			
		    Gdx.gl.glScissor((int) currentWindow.oldX, (int) (Gdx.graphics.getHeight() - currentWindow.getHeight() - currentWindow.oldY), (int) currentWindow.getWidth(), (int) (currentWindow.getHeight() - style.windowPadding.getVertical() - style.windowTitleFontSize));
		    
			batch.draw(texture, itemX, itemY - height, width, height);
			
			batch.flush();
			
		    Gdx.gl.glDisable(GL30.GL_SCISSOR_TEST);
		    
			batch.end();
			
			NanoVG.nvgBeginFrame(nvg, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1);
			NanoVG.nvgRestore(nvg);
			
			currentWindow.scissor();
			
		}
	}
	
	public static void image(TextureRegion textureRegion, int width, int height) {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			int itemX = (int) currentWindow.getNextItemX(width);
			int itemY = Gdx.graphics.getHeight() - (int) currentWindow.getNextItemY(height);

			NanoVG.nvgSave(nvg);
			NanoVG.nvgEndFrame(nvg);
			
			batch.begin();
			
		    Gdx.gl.glEnable(GL30.GL_SCISSOR_TEST);
			
		    Gdx.gl.glScissor((int) currentWindow.oldX, (int) (Gdx.graphics.getHeight() - currentWindow.getHeight() - currentWindow.oldY), (int) currentWindow.getWidth(), (int) (currentWindow.getHeight() - style.windowPadding.getVertical() - style.windowTitleFontSize));
		    
			batch.draw(textureRegion, itemX, itemY - height, width, height);
			
			batch.flush();
			
		    Gdx.gl.glDisable(GL30.GL_SCISSOR_TEST);
		    
			batch.end();
			
			NanoVG.nvgBeginFrame(nvg, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1);
			NanoVG.nvgRestore(nvg);
			
			currentWindow.scissor();
			
		}
	}
	
	public static void image(Texture texture) {
		image(texture, texture.getWidth(), texture.getHeight());
	}
	
	public static void beginGroup() {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			currentGroup = new Group();
			
			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRoundedRectVarying(nvg, currentGroup.x, currentGroup.y, currentGroup.width + style.groupPadding.getHorizontal(), currentGroup.height + style.groupPadding.getVertical(), style.groupBorderRadius.topLeft, style.groupBorderRadius.topRight, style.groupBorderRadius.bottomLeft, style.groupBorderRadius.bottomRight);
			NanoVG.nvgStrokeWidth(nvg, style.frameBorderWidth);
			style.getPaints().frameBorderPaint.stroke(nvg, currentGroup.x, currentGroup.y, currentGroup.width + style.groupPadding.getHorizontal(), currentGroup.height + style.groupPadding.getVertical());
			NanoVG.nvgStroke(nvg);
			
			NanoVG.nvgClosePath(nvg);

			//NanoVG.nvgScissor(nvg, currentGroup.x, currentGroup.y, Math.min(currentGroup.width + style.groupPadding.getHorizontal(), currentWindow.getWidth() - style.windowPadding.left), Math.min(currentGroup.y + currentGroup.height - currentWindow.oldY + style.groupPadding.getVertical(), currentWindow.getHeight()));
			NanoVG.nvgScissor(nvg, currentGroup.x, Math.max(currentGroup.y, currentWindow.oldY + currentWindow.titleBarHeight + style.windowPadding.getVertical()), Math.min(currentGroup.width + style.groupPadding.getHorizontal(), currentWindow.getWidth() - style.windowPadding.left), Math.min(currentGroup.height + style.groupPadding.getVertical(), currentWindow.windowHeight + style.windowPadding.bottom));

		}
	}
	
	public static void endGroup() {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			currentGroup = null;
			NanoVG.nvgResetScissor(nvg);
			currentWindow.scissor();
		}
	}
	
	public static void indent(int amount) {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open))
			for (int i = 0; i < amount; i++)
				currentWindow.indent();
	}
	
	public static void indent() {
		indent(1);
	}
	
	public static void unindent(int amount) {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open))
			for (int i = 0; i < amount; i++)
				currentWindow.unindent();
	}
	
	public static void unindent() {
		unindent(1);
	}

	private static boolean insideCollapse;
	
	public static boolean beginCollapse(String text, boolean open) {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			currentCollapse = new Collapse(text, open);
			
			insideCollapse = true;
			if (currentCollapse.open)
				currentWindow.indent();

			return currentCollapse.open;

		}

		return open;
	}
	
	public static void endCollapse() {
		if (currentWindow != null && currentCollapse != null) {
			/*if (currentCollapse.oldCollapse == null)*/ insideCollapse = false;
			if (currentCollapse.open)
				currentWindow.unindent();
			if (currentCollapse.oldCollapse != null) {
				Collapse old = currentCollapse.oldCollapse;
				currentCollapse = old;
			}
		}
	}
	
	public static void rectangle(float width, float height) {
		if (currentWindow != null) {
			
			int itemX = (int) currentWindow.getNextItemX(width);
			int itemY = (int) currentWindow.getNextItemY(height);
			
			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRect(nvg, itemX, itemY, width, height);
			NanoVG.nvgStrokeWidth(nvg, style.shapeStrokeWidth);
			style.paints.shapePaint.stroke(nvg, itemX, itemY, width, height);
			NanoVG.nvgStroke(nvg);

			NanoVG.nvgClosePath(nvg);
			
		}
	}
	
	public static void filledRectangle(float width, float height) {
		if (currentWindow != null) {
			
			int itemX = (int) currentWindow.getNextItemX(width);
			int itemY = (int) currentWindow.getNextItemY(height);
			
			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRect(nvg, itemX, itemY, width, height);
			style.paints.shapePaint.fill(nvg, itemX, itemY, width, height);
			NanoVG.nvgFill(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
		}
	}
	
	public static void circle(float radius) {
		if (currentWindow != null) {
			
			int itemX = (int) currentWindow.getNextItemX(radius * 2);
			int itemY = (int) currentWindow.getNextItemY(radius * 2);
			
			NanoVG.nvgBeginPath(nvg);
		
			NanoVG.nvgCircle(nvg, itemX + radius, itemY + radius, radius);
			NanoVG.nvgStrokeWidth(nvg, style.shapeStrokeWidth);
			style.paints.shapePaint.stroke(nvg, itemX + radius, itemY + radius, radius * 2, radius * 2);
			NanoVG.nvgStroke(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
		}
	}
	
	public static void filledCircle(float radius) {
		if (currentWindow != null) {
			

			int itemX = (int) currentWindow.getNextItemX(radius * 2);
			int itemY = (int) currentWindow.getNextItemY(radius * 2);
			
			NanoVG.nvgBeginPath(nvg);
		
			NanoVG.nvgCircle(nvg, itemX + radius, itemY + radius, radius);
			style.paints.shapePaint.fill(nvg, itemX, itemY, radius * 2, radius * 2);
			NanoVG.nvgFill(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
		}
	}
	
	public static void roundedRectangle(float width, float height, float borderRadius) {
		if (currentWindow != null) {
			
			int itemX = (int) currentWindow.getNextItemX(width);
			int itemY = (int) currentWindow.getNextItemY(height);
			
			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRoundedRect(nvg, itemX, itemY, width, height, borderRadius);
			NanoVG.nvgStrokeWidth(nvg, style.shapeStrokeWidth);
			style.paints.shapePaint.stroke(nvg, itemX, itemY, width, height);
			NanoVG.nvgStroke(nvg);

			NanoVG.nvgClosePath(nvg);
			
		}
	}
	
	public static void filledRoundedRectangle(float width, float height, float borderRadius) {
		if (currentWindow != null) {
			
			int itemX = (int) currentWindow.getNextItemX(width);
			int itemY = (int) currentWindow.getNextItemY(height);
			
			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRoundedRect(nvg, itemX, itemY, width, height, borderRadius);
			style.paints.shapePaint.fill(nvg, itemX, itemY, width, height);
			NanoVG.nvgFill(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
		}
	}
	
	private static float baseDrag(String label, float pos, String value, float speed) {
		if (currentWindow != null && !(insideCollapse && !currentCollapse.open)) {
			
			int itemId = currentWindow.getItemId();

			float width = style.sliderWidthPerc * currentWindow.getWidth();

			float mx = Gdx.input.getX();
			float my = Gdx.input.getY();
			
			NanoVG.nvgFontSize(nvg, style.sliderFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, label, bounds);
			
			float itemX = currentWindow.getNextItemX(width + style.framePadding.getHorizontal() + (bounds[2] - bounds[0]) + style.framePadding.left);
			float itemY = currentWindow.getNextItemY(style.sliderFontSize + style.framePadding.getVertical());
			
			boolean hovered = mx >= itemX & my >= itemY & mx <= itemX + Math.min(width + style.framePadding.getHorizontal(),currentWindow.getWidth() - (itemX - currentWindow.oldX)) & my <= itemY + style.sliderFontSize + style.framePadding.getVertical();
			
			if (itemY - currentWindow.getY() > currentWindow.getHeight() || itemX - currentWindow.oldX > currentWindow.getWidth())
				return pos;
			
			NanoVG.nvgFontSize(nvg, style.sliderFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_TOP | NanoVG.NVG_ALIGN_LEFT);
			NanoVG.nvgTextBounds(nvg, 0, 0, value, bounds);
			
			NanoVG.nvgBeginPath(nvg);
			
			NanoVG.nvgRoundedRectVarying(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.sliderFontSize + style.framePadding.getVertical(), style.frameBorderRadius.topLeft, style.frameBorderRadius.topRight, style.frameBorderRadius.bottomLeft, style.frameBorderRadius.bottomRight);

			if (hovered || currentWindow.getSelectedItemId() == itemId)
				style.getPaints().frameHoverBackgroundPaint.fill(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.sliderFontSize + style.framePadding.getVertical());
			else
				style.getPaints().frameBackgroundPaint.fill(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.sliderFontSize + style.framePadding.getVertical());
			NanoVG.nvgFill(nvg);
			NanoVG.nvgStrokeWidth(nvg, style.frameBorderWidth);
			style.getPaints().frameBorderPaint.stroke(nvg, itemX, itemY, width + style.framePadding.getHorizontal(), style.sliderFontSize + style.framePadding.getVertical());
			NanoVG.nvgStroke(nvg);
			
			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);

			style.getPaints().sliderValueTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgText(nvg, itemX + width/2f - bounds[2] / 2f, itemY + style.framePadding.top + 2, value);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);
			
			NanoVG.nvgBeginPath(nvg);

			style.getPaints().sliderLabelTextPaint.fill(nvg,0,0, 0, 0);
			NanoVG.nvgFontSize(nvg, style.sliderFontSize);
			NanoVG.nvgTextAlign(nvg, NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_MIDDLE);
			NanoVG.nvgText(nvg, itemX + width + style.framePadding.getHorizontal() + style.framePadding.left, itemY + style.sliderFontSize/2f + style.framePadding.top + 2, label);
			NanoVG.nvgFill(nvg);

			NanoVG.nvgClosePath(nvg);

			float knobDst = mx - dragStartX;
			float val = Math.max(Math.min(knobDst / (width * speed + style.framePadding.getHorizontal() - style.sliderKnobWidth), 1f), 0f);
			
			if (hovered && currentWindow.getSelectedItemId() == -1 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				currentWindow.setSelectedItemId(itemId);
			}

			if (currentWindow.getSelectedItemId() == itemId)
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && rmwin == -1) {
					if (!dragging)
						dragStartX = mx;
					dragging = true;
					return val;
				} else {
					currentWindow.setSelectedItemId(-1);
					dragging = false;
				}
			
		}
		
		return pos;
	}
	
	public static float dragFloat(String label, float f, float min, float max, float step, float speed) {
		return (float) (Math.floor((baseDrag(label, (f - min) / (max - min), Float.toString(Math.round(f * 100f) / 100f), speed) + min) * (max+min) / step) * step);
	}
	
	public static float dragFloat(String label, float f, float min, float max, float step) {
		return (float) (Math.floor((baseDrag(label, (f - min) / (max - min), Float.toString(Math.round(f * 100f) / 100f), 1f) + min) * (max+min) / step) * step);
	}
	
	public static float dragFloat(String label, float f, float min, float max) {
		return (float) (Math.floor((baseDrag(label, (f - min) / (max - min), Float.toString(Math.round(f * 100f) / 100f), 1f) + min) * (max+min) / .01f) * .01f);
	}
	
	public static int dragInt(String label, int i, int min, int max, int step, float speed) {
		float mn = min;
		float mx = max;
		float it = i;
		return (int) (MathUtils.floor(((baseDrag(label, (it - mn) / (mx - mn), String.valueOf(i), speed) + mn) * (mx+mn)) / step) * step);
	}
	
	public static int dragInt(String label, int i, int min, int max, int step) {
		float mn = min;
		float mx = max;
		float it = i;
		return (int) (MathUtils.floor(((baseDrag(label, (it - mn) / (mx - mn), String.valueOf(i), 1) + mn) * (mx+mn)) / step) * step);
	}
	
	public static int dragInt(String label, int i, int min, int max) {
		float mn = min;
		float mx = max;
		float it = i;
		return (int) (MathUtils.floor(((baseDrag(label, (it - mn) / (mx - mn), String.valueOf(i), 1) + mn) * (mx+mn)) / 1) * 1);
	}
	
	public static boolean isWindowHovered() {
		return currentWindow.hovered;
	}
	
	public static boolean isAnyWindowHovered() {
		return anyWindowHovered;
	}
	
	public static boolean isItemHovered() {
		return itemHovered;
	}
	
	public static boolean isAnyItemHovered() {
		return anyItemHovered;
	}
	
	public static float getWindowX() {
		if (currentWindow != null)
			return currentWindow.oldX;
		return 0;
	}
	
	public static float getWindowY() {
		if (currentWindow != null)
			return currentWindow.oldY;
		return 0;
	}
	
	public static float getWindowWidth() {
		if (currentWindow != null)
			return currentWindow.getWidth();
		return 0;
	}
	
	public static float getWindowHeight() {
		if (currentWindow != null)
			return currentWindow.getHeight();
		return 0;
	}
	
	/**
	 * windowTitleFontSize+windowPadding.getVertical()=</br>windowTitleBarHeight
	 */
	public static float getWindowTitleBarHeight() {
		return style.windowTitleFontSize + style.windowPadding.getVertical();
	}
		
}
