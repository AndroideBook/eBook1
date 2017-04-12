package com.amaker.ch14.app02;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	// �ŧiTextView
	private TextView tv;
	// �ŧiButton
	private Button b1,b2;
    // �w��A�Ⱥ޲z�����
    private LocationManager locationManager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// �]�w�ثeActivity�������G��
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      	// �w��A�ȱ`��
	    String locService = Context.LOCATION_SERVICE;
	    // �z�LgetSystemService��k���oLocationManager���
	    locationManager = (LocationManager)getSystemService(locService);
	    // �z�LfindViewById��k���oTextView���
	    tv = (TextView) findViewById(R.id.TextView01);
	    
	    // �z�LfindViewById��k���oButton���
	    b1 = (Button) findViewById(R.id.Button01);
	    // �z�LfindViewById��k���oButton���
	    b2 = (Button) findViewById(R.id.Button02);
	    // �����s�K�[�����ƥ��ť��
	    b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ���V�s�X
				forward();
			}
		});
	    
	    // �����s�K�[�����ƥ��ť��
	    b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// �ϦV�s�X
				reverse();
			}
		});
    }
    
    // ���V�s�X
    private void forward() {
    	// ��Ҥ�Geocoder
    	Geocoder gc = new Geocoder(this, Locale.getDefault());
    	// �a�}
    	String address = "�x�_101";
    	// ��m�C��
    	List<Address> locations = null;
    	try {
    		// ���o��m���O
    		locations = gc.getFromLocationName(address, 10);
    		// �p�GLocations������
    		if(locations.size()>0){
    			// ���oAddress���
    			Address a = locations.get(0);
    			// ���o�g�n��
    			double lat = a.getLatitude();
    			double lng = a.getLongitude();
    			// ��ܰT��
    			tv.setText(lat+":"+lng);
    		}
    	} catch (IOException e) {}
    }
    
    
    // �ϦV�s�X
    private void reverse() {
    	// �g��
    	//double lng = 116.46;
    	double lng = 121.564099;	//121.564427;
    	// �n��
    	//double lat = 39.92;
    	double lat = 25.033408;	//25.033671;
    	// ��Ҥ�Geocoder
    	Geocoder gc = new Geocoder(this, Locale.getDefault());
    	// �ŧi�a�}�C��
    	List<Address> addresses = null;
    	try {
    		// ���oAddress��ҦC��
    		addresses = gc.getFromLocation(lat, lng, 10);
    		// �ŧiStringBuilder�A�O�s��m�T��
    		StringBuilder sb = new StringBuilder();
    		// �P�_addresses�O�_����
    		if(addresses.size()>0){
    			// ���oAddress
    			Address a = addresses.get(0);
    			for (int i = 0; i < a.getMaxAddressLineIndex(); i++){
    				// �a�}
    				sb.append(a.getAddressLine(i)).append("\n");
    				// �a�I
    				sb.append(a.getLocality()).append("\n");
    				// �l���ϸ�
    				sb.append(a.getPostalCode()).append("\n");
    				// ��a
    				sb.append(a.getCountryName());
    			}
    			tv.setText(sb.toString());
    		}
    	} catch (IOException e) {}
    }
}