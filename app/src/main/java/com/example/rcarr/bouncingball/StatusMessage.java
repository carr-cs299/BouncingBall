package com.example.rcarr.bouncingball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.Formatter;

public class StatusMessage {
    private StringBuilder statusMsg = new StringBuilder();
    private Formatter formatter = new Formatter(statusMsg);
    private Paint paint;

    public StatusMessage(int color) {
        paint = new Paint();
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(48);
        paint.setColor(color);
    }

    public void update(Ball ball) {
        statusMsg.delete(0,statusMsg.length());
        formatter.format("Ball@(%3.0f,%3.0f),Speed=(%2.0f,%2.0f)", ball.ballX, ball.ballY, ball.ballSpeedX, ball.ballSpeedY);
    }

    public void draw(Canvas canvas) {
        canvas.drawText(statusMsg.toString(), 10, 60, paint);
    }
}
