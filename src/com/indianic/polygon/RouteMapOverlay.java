package com.indianic.polygon;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class RouteMapOverlay extends Overlay {
	public ArrayList<GeoPoint> points;

	public RouteMapOverlay() {
		points = new ArrayList<GeoPoint>();
	}

	public void addPoint(GeoPoint point) {
		points.add(point);
	}

	 public void draw(Canvas canvas, MapView mapv, boolean shadow) {
	 super.draw(canvas, mapv, shadow);
	
	 Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	 mPaint.setDither(true);
	 mPaint.setColor(Color.BLUE);
	 mPaint.setAlpha(200);
	 mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	 mPaint.setStrokeJoin(Paint.Join.ROUND);
	 mPaint.setStrokeCap(Paint.Cap.ROUND);
	 mPaint.setStrokeWidth(2);
	
	 Path path = new Path();
	 
	 Projection projection = mapv.getProjection();
	
	 for (int i = 0; i < points.size() - 1; i++) {
	 Point p1 = new Point();
	 Point p2 = new Point();
	
	 projection.toPixels((GeoPoint) points.get(i), p1);
	 projection.toPixels((GeoPoint) points.get(i + 1), p2);
	
	 path.moveTo(p2.x, p2.y);
	 path.lineTo(p1.x, p1.y);
	
	 }
	
	 canvas.drawPath(path, mPaint);
	 }

}