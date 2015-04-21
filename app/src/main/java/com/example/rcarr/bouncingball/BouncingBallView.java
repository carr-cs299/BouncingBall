package com.example.rcarr.bouncingball;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class BouncingBallView extends View {
    private Ball ball;
    private Box box;
    private StatusMessage statusMsg;
    private float previousX;
    private float previousY;

    // Constructor
    public BouncingBallView(Context context) {
        super(context);

        box = new Box(0xFFFFFF00);
        ball = new Ball(0xFFFF8000);
        statusMsg = new StatusMessage(0xFFFF0000);

        this.setFocusableInTouchMode(true);
    }

    // Called back to draw the view. Also called by invalidate().
    @Override
    protected void onDraw(Canvas canvas) {
        box.draw(canvas);
        ball.draw(canvas);
        statusMsg.draw(canvas);

        // Update the position of the ball, including collision detection and reaction.
        ball.moveWithCollisionDetection(box);
        statusMsg.update(ball);

        // Delay
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) { }

        invalidate();  // Force a re-draw
    }

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        box.set(0, 0, w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        float deltaX, deltaY;
        float scalingFactor = 5.0f / ((box.xMax > box.yMax) ? box.yMax : box.xMax);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                deltaX = currentX - previousX;
                deltaY = currentY - previousY;
                ball.ballSpeedX += deltaX * scalingFactor;
                ball.ballSpeedY += deltaY * scalingFactor;
        }
        previousX = currentX;
        previousY = currentY;
        return true;
    }
}