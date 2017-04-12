package com.amaker.mp.location;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

import com.amaker.mp.R;
import com.amaker.mp.R.drawable;
import com.amaker.mp.R.id;
import com.amaker.mp.R.layout;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MyPositionActivity extends MapActivity {

	LinearLayout linearLayout;
	MapView mapView;
	ZoomControls mZoom;
	private LocationManager locationManager;
	
	List<Overlay> mapOverlays;
	Drawable drawable;
	MyPositionItemizedOverlay itemizedOverlay;

	//@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_position);

		linearLayout = (LinearLayout) findViewById(R.id.zoomview);
		mapView = (MapView) findViewById(R.id.mapview);
	    /* �]�wMapView����ܿﶵ(�ìP�B��D) */
		mapView.setSatellite(false); 
		mapView.setStreetView(true);
		
		mZoom = (ZoomControls) mapView.getZoomControls();
		linearLayout.addView(mZoom);

		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedOverlay = new MyPositionItemizedOverlay(drawable);
		
		MapController controller = mapView.getController();
		
		GeoPoint point = locate(controller);
		
		OverlayItem overlayitem = new OverlayItem(point, "", "");

		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);

	}

	private GeoPoint locate(MapController controller) {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		String provider = LocationManager.GPS_PROVIDER;
		Location location = locationManager.getLastKnownLocation(provider);
		
		double lat = 0.0;
		double lng = 0.0;
		if (location != null) {
			 lat = location.getLatitude();
			 lng = location.getLongitude();
		}else{
			lat = 25.033408;	//39.92;
			lng = 121.564099;	//116.46;
		}
		
		GeoPoint point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		controller.animateTo(point);
		return point;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
