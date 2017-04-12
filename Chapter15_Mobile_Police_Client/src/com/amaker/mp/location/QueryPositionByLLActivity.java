package com.amaker.mp.location;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//added from here
import android.view.LayoutInflater;	
import android.view.Menu;
import android.view.MenuItem;

import com.amaker.mp.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class QueryPositionByLLActivity extends MapActivity {
	  private MapController mMapController01;
	  //private MapController controller;
	  //private MapView mapView;
		
	  private MapView mMapView01;
	  private Button mButton01,mButton02,mButton03; 
	  private EditText mEditText01;
	  private EditText mEditText02;
	  private int intZoomLevel=0;
	  /* Map�Ұʮɪ��w�]�y�СA�x�_101 */
	  private double dLat=121.564099;	//121.2526;
	  private double dLng=25.033408;	//24.8053;

	  @Override 
	  protected void onCreate(Bundle icicle) 
	  { 
	    super.onCreate(icicle); 
	    setContentView(R.layout.query_position_by_ll); 

	    /* �إ�MapView���� */ 
	    mMapView01 = (MapView)findViewById(R.id.myMapView1); 
	    mMapController01 = mMapView01.getController(); 
	    /* �]�wMapView����ܿﶵ(�ìP�B��D) */
	    mMapView01.setSatellite(false); 
	    mMapView01.setStreetView(true);
	    /* �w�]����j�h�� */
	    intZoomLevel = 17; 
	    mMapController01.setZoom(intZoomLevel); 
	    /* �]�wMap�����I���w�]���g�n��*/
	    refreshMapView();
	    
	    mEditText01 = (EditText)findViewById(R.id.myEdit1); 
	    mEditText02 = (EditText)findViewById(R.id.myEdit2);
	    
	    /* �e�X�d�ߪ�Button */ 
	    mButton01 = (Button)findViewById(R.id.myButton1); 
	    mButton01.setOnClickListener(new Button.OnClickListener() 
	    { 
	      //@Override 
	      public void onClick(View v) 
	      { 
	        /* �g�n�תť��ˬd */
	        if(mEditText01.getText().toString().equals("")||
	           mEditText02.getText().toString().equals(""))
	        {
	          showDialog("�g�שνn�׶�g�����T�I");
	        }
	        else
	        {
	          /* ���o��J���g�n�� */
	          dLng=Double.parseDouble(mEditText01.getText().toString());
	          dLat=Double.parseDouble(mEditText02.getText().toString());
	          /* �̿�J���g�n�׭���Map */
	          refreshMapView(); 
	        }
	      } 
	    }); 
	     
	    /* ��jMap��Button */
	    mButton02 = (Button)findViewById(R.id.myButton2); 
	    mButton02.setOnClickListener(new Button.OnClickListener() 
	    { 
	      //@Override 
	      public void onClick(View v) 
	      { 
	        intZoomLevel++; 
	        if(intZoomLevel>mMapView01.getMaxZoomLevel()) 
	        { 
	          intZoomLevel = mMapView01.getMaxZoomLevel(); 
	        } 
	        mMapController01.setZoom(intZoomLevel); 
	      } 
	    }); 
	     
	    /* �Y�pMap��Button */
	    mButton03 = (Button)findViewById(R.id.myButton3); 
	    mButton03.setOnClickListener(new Button.OnClickListener() 
	    { 
	      //@Override 
	      public void onClick(View v) 
	      { 
	        intZoomLevel--; 
	        if(intZoomLevel<1) 
	        { 
	          intZoomLevel = 1; 
	        } 
	        mMapController01.setZoom(intZoomLevel); 
	      } 
	    });
	  } 
	  
	  /* ����Map��method */
	  public void refreshMapView() 
	  { 
	    GeoPoint p = new GeoPoint((int)(dLat* 1E6), (int)(dLng* 1E6)); 
	    mMapView01.displayZoomControls(true);
	    /* �NMap�����I����GeoPoint */
	    mMapController01.animateTo(p); 
	    mMapController01.setZoom(intZoomLevel); 
	  } 
	   
	  @Override 
	  protected boolean isRouteDisplayed() 
	  { 
	    return false; 
	  }
	  
	  /* ���Dialog��method */
	  private void showDialog(String mess){
	    new AlertDialog.Builder(QueryPositionByLLActivity.this).setTitle("Message")
	    .setMessage(mess)
	    .setNegativeButton("�T�w", new DialogInterface.OnClickListener()
	    {
	      public void onClick(DialogInterface dialog, int which)
	      {
	      }
	    })
	    .show();
	  }

	  //***********************************************************************
	  // �۩w��ܤ��
	  /*
	  private void customDialog(){
		  try{  
			  LayoutInflater mInflater01 = LayoutInflater.from(QueryPositionByLLActivity.this); 
			  View myView = mInflater01.inflate(R.layout.custom_dialog, null); 
			  final EditText  lngEditText=(EditText)myView.findViewById(R.id.lngEditText);
			  final EditText  latEditText=(EditText)myView.findViewById(R.id.latEditText);
		      
			  new AlertDialog.Builder(this) 
		      	.setView(myView).setTitle("�п�J�g�n�׮y��") 
		      	.setPositiveButton("�T�w", 
		      new DialogInterface.OnClickListener(){ 
		        public void onClick(DialogInterface dialog, int whichButton){ 
		        	locate(Double.parseDouble(latEditText.getText().toString()),
		        			Double.parseDouble(lngEditText.getText().toString()));
		        }
		      }).show(); 
		    } 
		    catch(Exception e) 
		    { 
		      e.printStackTrace(); 
		    } 
	  }

	  //
	  // ��o��e�g�n�׸�T�A�q�LMapController�w�����I

	  private void locate(double lat,double lng) {
		    
			if (lat == 0.0 && lng == 0.0) {
				lat = 39.92;
				lng = 116.46;
			}

			controller = mapView.getController();
			GeoPoint point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
			controller.animateTo(point);
			
			drawable = this.getResources().getDrawable(R.drawable.androidmarker);
			mapOverlays = mapView.getOverlays();
			itemizedOverlay = new MyPositionItemizedOverlay(drawable);
			OverlayItem overlayitem = new OverlayItem(point, "", "");

			
		    itemizedOverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedOverlay);

	  }
	  //
	  
	  public boolean onCreateOptionsMenu(Menu menu) {
		    menu.add(0, 1, 0, "��ܿ�J�y�й�ܤ��");
		    return true;
	  }

	  public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
		    	case 1:
		    		customDialog();
		    		return true;
		    }
		    return false;
	  }
	  */
	  
}
