package com.example.foodkart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Enhanced Radial View for "Glass Box" visualization.
 * Optimized for readability and aesthetics.
 */
public class RadialResultView extends View {

    private Paint circlePaint;
    private Paint textPaint;
    private Paint linePaint;
    private Paint ringPaint;

    private List<ScoredRestaurant> topRestaurants = new ArrayList<>();
    private final float maxRadiusFactor = 0.75f;

    public RadialResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextSize(32f);
        textPaint.setFakeBoldText(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.parseColor("#BDBDBD"));
        linePaint.setStrokeWidth(3f);
        linePaint.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));

        ringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setColor(Color.parseColor("#E0E0E0"));
        ringPaint.setStrokeWidth(2f);
    }

    public void setData(List<Restaurant> restaurants, double[] scores) {
        this.topRestaurants.clear();
        int count = Math.min(restaurants.size(), 5);
        for (int i = 0; i < count; i++) {
            topRestaurants.add(new ScoredRestaurant(restaurants.get(i), scores[i]));
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int maxRadius = (int) (Math.min(centerX, centerY) * maxRadiusFactor);

        // Draw concentric rings for reference
        canvas.drawCircle(centerX, centerY, maxRadius * 0.33f, ringPaint);
        canvas.drawCircle(centerX, centerY, maxRadius * 0.66f, ringPaint);
        canvas.drawCircle(centerX, centerY, maxRadius, ringPaint);

        // Draw central anchor (The "Goal")
        circlePaint.setColor(Color.parseColor("#FF3D00"));
        canvas.drawCircle(centerX, centerY, 25f, circlePaint);
        
        if (topRestaurants.isEmpty()) return;

        float angleStep = 360f / topRestaurants.size();

        for (int i = 0; i < topRestaurants.size(); i++) {
            ScoredRestaurant sr = topRestaurants.get(i);
            // Distribute items around the circle
            float angleDegrees = i * angleStep - 90;
            float angleRadians = (float) Math.toRadians(angleDegrees);

            // Distance calculation: Better CCi -> Closer to Center
            // Closeness Coefficient is usually 0 to 1. 
            // We'll map it so CCi=1 is at radius 50 (near center) and CCi=0 is at maxRadius.
            float minAllowedRadius = 60f;
            float radius = minAllowedRadius + (maxRadius - minAllowedRadius) * (1.0f - (float) sr.score);
            
            float x = centerX + radius * (float) Math.cos(angleRadians);
            float y = centerY + radius * (float) Math.sin(angleRadians);

            // Draw connection line
            canvas.drawLine(centerX, centerY, x, y, linePaint);

            // Draw restaurant node (Size proportional to rank/score)
            float nodeSize = 35f + (float) sr.score * 35f;
            
            // Subtle Shadow
            circlePaint.setShadowLayer(8f, 0, 4f, Color.argb(60, 0, 0, 0));
            circlePaint.setColor(Color.parseColor("#4CAF50")); // Recommended Green
            canvas.drawCircle(x, y, nodeSize, circlePaint);
            circlePaint.clearShadowLayer();

            // Label placement (avoiding the node)
            float labelOffset = nodeSize + 35f;
            // Adjust label based on quadrant to avoid overlap with line
            float labelX = x;
            float labelY = y + labelOffset;
            
            if (angleDegrees > 0 && angleDegrees < 180) {
                 // Bottom half, label goes further down
            } else {
                 // Top half, label could go above if needed, but keeping it below for consistency
            }

            // Draw Label background for readability
            String name = sr.restaurant.getName();
            float textWidth = textPaint.measureText(name);
            Paint bgPaint = new Paint();
            bgPaint.setColor(Color.argb(180, 255, 255, 255));
            canvas.drawRect(labelX - textWidth/2 - 10, labelY - 25, labelX + textWidth/2 + 10, labelY + 10, bgPaint);
            
            canvas.drawText(name, labelX, labelY, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            performClick();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private static class ScoredRestaurant {
        Restaurant restaurant;
        double score;

        ScoredRestaurant(Restaurant restaurant, double score) {
            this.restaurant = restaurant;
            this.score = score;
        }
    }
}