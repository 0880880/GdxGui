package com.github.zeroeighteightzero.gdxgui;

import static org.lwjgl.BufferUtils.createByteBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.BufferUtils;

public class Utils {
	
	static float getASPX(Align align, float width) {
		if (align == Align.TOP)
			return width/2;
		else if (align == Align.TOP_RIGHT)
			return width;
		else if (align == Align.TOP_LEFT)
			return 0;
		
		else if (align == Align.BOTTOM)
			return width/2;
		else if (align == Align.BOTTOM_RIGHT)
			return width;
		else if (align == Align.BOTTOM_LEFT)
			return 0;
		
		else if (align == Align.CENTER)
			return width/2;
		else if (align == Align.RIGHT)
			return width;
		else if (align == Align.LEFT)
			return 0;
		return 0;
	}
	
	static float getASPY(Align align, float height) {
		if (align == Align.TOP)
			return 0;
		else if (align == Align.TOP_RIGHT)
			return 0;
		else if (align == Align.TOP_LEFT)
			return 0;
		
		else if (align == Align.BOTTOM)
			return height;
		else if (align == Align.BOTTOM_RIGHT)
			return height;
		else if (align == Align.BOTTOM_LEFT)
			return height;
		
		else if (align == Align.CENTER)
			return height/2;
		else if (align == Align.RIGHT)
			return height/2;
		else if (align == Align.LEFT)
			return height/2;
		return 0;
	}
	
	public static int getNVGAlign(Align align) {
		if (align == Align.TOP)
			return NanoVG.NVG_ALIGN_TOP | NanoVG.NVG_ALIGN_CENTER;
		else if (align == Align.TOP_RIGHT)
			return NanoVG.NVG_ALIGN_TOP | NanoVG.NVG_ALIGN_RIGHT;
		else if (align == Align.TOP_LEFT)
			return NanoVG.NVG_ALIGN_TOP | NanoVG.NVG_ALIGN_LEFT;
		
		else if (align == Align.BOTTOM)
			return NanoVG.NVG_ALIGN_BOTTOM | NanoVG.NVG_ALIGN_CENTER;
		else if (align == Align.BOTTOM_RIGHT)
			return NanoVG.NVG_ALIGN_BOTTOM | NanoVG.NVG_ALIGN_RIGHT;
		else if (align == Align.BOTTOM_LEFT)
			return NanoVG.NVG_ALIGN_BOTTOM | NanoVG.NVG_ALIGN_LEFT;
		
		else if (align == Align.CENTER) {
			return NanoVG.NVG_ALIGN_CENTER;
		} else if (align == Align.RIGHT)
			return NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_RIGHT;
		else if (align == Align.LEFT)
			return NanoVG.NVG_ALIGN_MIDDLE | NanoVG.NVG_ALIGN_LEFT;
		return -1;
	}
	
	private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.newByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }
	
	static ByteBuffer ioResourceToByteBuffer(FileHandle resource, int bufferSize) throws IOException {
	    ByteBuffer buffer;
        InputStream source = resource.read();
	
	    Path path = Paths.get(resource.file().getAbsolutePath());
	    if (Files.isReadable(path)) {
	        try (SeekableByteChannel fc = Files.newByteChannel(path)) {
	            buffer = BufferUtils.newByteBuffer((int) fc.size() + 1);
	            while (fc.read(buffer) != -1) ;
	        }
	    } else {
	        try (
	            ReadableByteChannel rbc = Channels.newChannel(source)) {
	            buffer = createByteBuffer(bufferSize);
	
	            while (true) {
	                int bytes = rbc.read(buffer);
	                if (bytes == -1) {
	                    break;
	                }
	                if (buffer.remaining() == 0) {
	                    buffer = resizeBuffer(buffer, buffer.capacity() * 2);
	                }
	            }
	        }
	    }
	
	    buffer.flip();
	    source.close();
	    return buffer;
	}
	
	public static NVGColor createColor(Color color) {
		return setColor(NVGColor.create(), color);
	}
	
	public static NVGColor createColor(float r, float g, float b, float a) {
		return setColor(NVGColor.create(), r, g, b, a);
	}
	
	public static NVGColor createColor(float r, float g, float b) {
		return setColor(NVGColor.create(), r, g, b);
	}
	
	public static NVGColor setColor(NVGColor color, float r, float g, float b, float a) {
		color.r(r);
		color.g(g);
		color.b(b);
		color.a(a);
		return color;
	}

	public static NVGColor setColor(NVGColor color, float r, float g, float b) {
		color.r(r);
		color.g(g);
		color.b(b);
		color.a(1f);
		return color;
	}
	
	public static NVGColor setColor(NVGColor fromColor, Color toColor) {
		return setColor(fromColor, toColor.r, toColor.g, toColor.b, toColor.a);
	}

}
