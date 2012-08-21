package com.indianic.polygon;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class RouteMapOverlayFill extends Overlay 
{
	public ArrayList<GeoPoint> points;

	public RouteMapOverlayFill() {
		points = new ArrayList<GeoPoint>();
	}

	public void addPoint(GeoPoint point) {
		points.add(point);
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapv, boolean shadow) 
	{
		super.draw(canvas, mapv, false);
		
		Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setDither(true);
		mPaint.setColor(0xFF5AA100);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeMiter((float)5.0);
		
		Paint nPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		nPaint.setDither(true);
		nPaint.setColor(0x40A5CC75);
		nPaint.setStyle(Paint.Style.FILL);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

		Path path = new Path();
		
		if(points.size() > 2) {
			for (int i = 0; i < points.size(); i++) 
			{
				GeoPoint gP1 = points.get(i);
				Point currentScreenPoint = new Point();

				Projection proj = mapv.getProjection();
				proj.toPixels(gP1, currentScreenPoint);

				if (i == 0) {
					path.moveTo(currentScreenPoint.x, currentScreenPoint.y);
				}
				else {
					path.lineTo(currentScreenPoint.x, currentScreenPoint.y);
				}
			}
		}
		canvas.drawPath(path, nPaint);
		canvas.drawPath(path, mPaint);
	}
}