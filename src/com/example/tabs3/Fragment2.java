package com.example.tabs3;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.threetabs.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment2 extends SupportMapFragment implements LocationListener {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		init();
		super.onActivityCreated(savedInstanceState);
	}

	private void init() {
		UiSettings settings = getMap().getUiSettings();
		settings.setAllGesturesEnabled(true);
		settings.setMyLocationButtonEnabled(true);

		/*
		 * Commented lines to hide radius (this will automatically turns off GPS mode)
		 * You also should replace LocationManager.GPS_PROVIDER with provider. 
		*/
		//getMap().setMyLocationEnabled(true);

		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(getActivity().LOCATION_SERVICE);
//		Criteria criteria = new Criteria();
//		String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		getMap().moveCamera(CameraUpdateFactory.newLatLng(latLng));
		if (location != null) {
			onLocationChanged(location);
		}
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20000, 0, this);
	}

	@Override
	public void onLocationChanged(Location arg0) {
		getMap().clear();
		double latitude = arg0.getLatitude();
		double longitude = arg0.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		getMap().moveCamera(CameraUpdateFactory.newLatLng(latLng));
		getMap().animateCamera(CameraUpdateFactory.zoomTo(15));
		getMap().addMarker(
				new MarkerOptions().position(latLng).icon(
						BitmapDescriptorFactory
								.fromResource(R.drawable.ic_launcher)));
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}