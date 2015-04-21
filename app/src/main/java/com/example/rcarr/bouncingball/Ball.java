package com.example.rcarr.bouncingball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public class Ball {
    private float ballRadius = 80; // Ball's radius
    float ballX = ballRadius + 20;  // Ball's center (x,y)
    float ballY = ballRadius + 40;
    float ballSpeedX = 25;  // Ball's speed (x,y)
    float ballSpeedY = 15;
    private RectF ballBounds;      // Needed for Canvas.drawOval
    private Paint paint;           // The paint (e.g. style, color) used for drawing
    private Random rand;

    public Ball(int color) {
        ballBounds = new RectF();
        paint = new Paint();
        paint.setColor(color);
        rand = new Random();
    }

    public void moveWithCollisionDetection(Box box) {
        // Get new (x,y) position
        ballX += ballSpeedX;
        ballY += ballSpeedY;
        // Detect collision and react
        if (ballX + ballRadius > box.xMax) {
            ballSpeedX = -ballSpeedX;
            ballX = box.xMax-ballRadius;
            randomColor();
        } else if (ballX - ballRadius < box.xMin) {
            ballSpeedX = -ballSpeedX;
            ballX = box.xMin+ballRadius;
            randomColor();
        }
        if (ballY + ballRadius > box.yMax) {
            ballSpeedY = -ballSpeedY;
            ballY = box.yMax - ballRadius;
            randomColor();
        } else if (ballY - ballRadius < box.yMin) {
            ballSpeedY = -ballSpeedY;
            ballY = box.yMin + ballRadius;
            randomColor();
        }
    }
    public void randomColor() {
        paint.setColor(rand.nextInt(0xFFFFFF) + 0xFF000000);
    }

    public void draw(Canvas canvas) {
        ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
        canvas.drawOval(ballBounds,paint);
    }
}
