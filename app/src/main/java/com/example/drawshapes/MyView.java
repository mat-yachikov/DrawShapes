package com.example.drawshapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.PointF;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    final static int MAXPOINTS = 10;
    int width;
    int height;
    int sizeGrid = 48;

    String typeShape = "rect";
    String color = "000000";

    PointF[] points = new PointF[MAXPOINTS];
    Shape[] shapes = new Shape[100];

    int counterShapes = 0;
    int counterPoints = 0;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();

        drawGrid(canvas);
        drawPoints(canvas);
        drawShapes(canvas);
    }

    private void drawShapes(Canvas canvas) {
            Paint paint = new Paint();
            for (int i = 0; i < counterShapes; i++)
            {
                Shape shape = shapes[i];
                shape.draw(canvas,paint);
            }
    }

    private void drawGrid(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(Color.RED);
        int numBW = width / sizeGrid;
        int numBH = height / sizeGrid;
        for (int i = 1; i <= numBW; i++)
        {
            int x = i * sizeGrid;
            canvas.drawLine(x, 0, x, height, paint);
        }
        for (int i = 1; i <= numBH; i++)
        {
            int y = i * sizeGrid;
            canvas.drawLine(0, y, width, y, paint);
        }
    }

    void drawPoints(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        int maxPoints = Math.min(MAXPOINTS, counterPoints);
        for (int i =0; i < maxPoints; i++) {
            canvas.drawCircle(points[i].x, points[i].y, 20, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN)
    {
        float x = event.getX();
        float y = event.getY();
        if (counterPoints < MAXPOINTS) {
            points[counterPoints] = new PointF(x, y);
            counterPoints++;

            switch (this.typeShape)
            {
                case "rect": checkForCreateRect();
                case "circle": checkForCreateCircle();
                case "triangle": checkForCreateTriangle();
            }
            this.invalidate();
        }
    }
        return true;
    }

    private void checkForCreateTriangle() {
        if (counterPoints >= 3)
        {
            Triangle tri = new Triangle(this.color, points[0], points[1], points[2]);

            shapes[counterShapes] = tri;
            counterShapes++;
            counterPoints = 0;
            this.invalidate();
        }
    }

    private void checkForCreateCircle() {
        if (counterPoints >= 2)
        {
            float radius = (float) Math.sqrt(Math.pow((points[1].x-points[0].x),2)+Math.pow((points[1].y-points[0].y),2));
            Circle circle = new Circle(this.color, points[0], radius);

            shapes[counterShapes] = circle;
            counterShapes++;
            counterPoints = 0;
            this.invalidate();
        }
    }

    private void checkForCreateRect() {
        if (counterPoints >= 2)
        {
            Rect rect = new Rect(this.color, points[0], points[1]);

            shapes[counterShapes] = rect;
            counterShapes++;
            counterPoints = 0;
            this.invalidate();
        }
    }

    public void undo(){
        if (counterShapes > 0)
        {
            counterShapes--;
            this.invalidate();
        }
    }

    public void shapeChange(int checkedId) {
        if (checkedId == R.id.radioRect)
        {
            typeShape = "rect";
        }
        else if (checkedId == R.id.radioCircle)
        {
            typeShape = "circle";
        }
        else if (checkedId == R.id.radioTriangle)
        {
            typeShape = "triangle";
        }
    }

    public void colorChange(String nameColor) {
        switch (nameColor) {
            case "Black":
                color = "000000";
                break;
            case "Blue":
                color = "0000FF";
                break;
            case "Yellow":
                color = "FFFF00";
                break;
        }
    }
}
