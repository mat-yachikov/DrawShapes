package com.example.drawshapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Circle extends Shape{

    PointF center;
    float radius;

    public Circle(String color, PointF center, float radius)
    {
        super(color);
        this.center = center;
        this.radius = radius;
    }

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.parseColor("#"+this.color));
        canvas.drawCircle(center.x, center.y, radius, paint);
    }
}
