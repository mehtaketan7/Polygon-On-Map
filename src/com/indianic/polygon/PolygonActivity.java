package com.indianic.polygon;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Projection;

public class PolygonActivity extends MapActivity {

	private MapView mapView = null;
	private ArrayList<Latlong> list = null;
	private final String[] latitude = { "22.593726", "26.194877", "25.363882",
			"23.443089", "20.673905", "22.593726" };
	private final String[] longitude = { "75.798339", "78.171386", "81.950683",
			"83.620605", "78.654784", "75.798339" };
	private Latlong latlong;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);

		list = new ArrayList<Latlong>();
		list.clear();

		for (int i = 0; i < latitude.length; i++) {
			latlong = new Latlong();
			latlong.setLatitude(latitude[i]);
			latlong.setLongitude(longitude[i]);
			list.add(latlong);
		}

		RouteMapOverlayFill overlay = new RouteMapOverlayFill();

		for (int i = 0; i < list.size(); i++) {

			final Latlong model = list.get(i);
			GeoPoint pt = new GeoPoint((int) (Double.valueOf(model
					.getLatitude()) * 1E6), (int) (Double.valueOf(model
					.getLongitude()) * 1E6));
			overlay.addPoint(pt);
			mapView.getController().animateTo(pt);
			mapView.getController().setZoom(6);
		}
		mapView.getOverlays().add(overlay);
		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}