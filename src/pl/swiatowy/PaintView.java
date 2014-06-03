package pl.swiatowy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import pl.swiatowy.assets.ColorDialog;
import pl.swiatowy.assets.SizeDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created: 2014-06-0216:34
 *
 * @author swiatek25
 */
public class PaintView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = PaintView.class.getSimpleName();

    private int color;
    private int size = SizeDialog.DEFAULT_SIZE;
    private List<Drawing> rects;

    public PaintView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundColor(Color.WHITE);
        rects = new ArrayList<>();
        color = ColorDialog.DEFAULT_COLOR;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "Surface created");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        rects.clear();
        rects = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Drawing drawing = new Drawing(event.getX() - size, event.getY() - size, event.getX() + size, event.getY() + size, color);
        rects.add(drawing);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint drawingColor = new Paint();
        for (Drawing drawing : rects) {
            drawingColor.setColor(drawing.getColor());
            canvas.drawOval(drawing, drawingColor);
        }
    }

    public void changeColor(int color) {
        this.color = color;
        invalidate();
    }

    public void changeSize(int size) {
        this.size = size;
        invalidate();
    }

    public int getSize() {
        return size;
    }

    class Drawing extends RectF {

        private final int color;

        public Drawing(float left, float top, float right, float bottom, int color) {
            super(left, top, right, bottom);
            this.color = color;
        }

        public int getColor() {
            return color;
        }
    }
}
