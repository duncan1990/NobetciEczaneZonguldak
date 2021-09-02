package com.ahmety.nbeczzonguldak;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class List extends ListActivity{
	String city;
	//Admob TamBoyReklam Gecis Reklam
	private InterstitialAd interstitial;
	private static final String REKLAM_ID = "ca-app-pub-6942374915516583/3302739977";
	//End
      String Ilceler[]={"Merkez","Alapli","Çaycuma","Devrek","Ereğli","Gökçebey","Kilimli","Kozlu"};
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if(position==0)
		{
			 city = "Merkez";
			 Intent i = new Intent(List.this, PharmacyMainActivity.class);
			 i.putExtra("city", city);
			 startActivity(i);
		}
		else if(position==1)
		{
			city = "Alaplı";
			Intent i = new Intent(List.this, PharmacyMainActivity.class);
			i.putExtra("city", city);
			startActivity(i);
		}
		else if(position==2)
		{
			city = "Çaycuma";
			Intent i = new Intent(List.this, PharmacyMainActivity.class);
			i.putExtra("city", city);
			startActivity(i);
		}
		else if(position==3)
		{
			city = "Devrek";
			Intent i = new Intent(List.this, PharmacyMainActivity.class);
			i.putExtra("city", city);
			startActivity(i);
		}
		else if(position==4)
		{
			city = "Ereğli";
			Intent i = new Intent(List.this, PharmacyMainActivity.class);
			i.putExtra("city", city);
			startActivity(i);
		}
		else if(position==5)
		{
			city = "Gökçebey";
			Intent i = new Intent(List.this, PharmacyMainActivity.class);
			i.putExtra("city", city);
			startActivity(i);
		}
		else if(position==6)
		{
			city = "Kilimli";
			Intent i = new Intent(List.this, PharmacyMainActivity.class);
			i.putExtra("city", city);
			startActivity(i);
		}
		else if(position==7)
		{
			city = "Kozlu";
			Intent i = new Intent(List.this, PharmacyMainActivity.class);
			i.putExtra("city", city);
			startActivity(i);
		}
		super.onListItemClick(l, v, position, id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//getListView().setBackgroundResource(R.color.colorPrimaryRed);
		//getListView().setCacheColorHint(android.R.color.transparent);
		//Admob TamBoyReklam Gecis Reklam
		AdRequest adRequest = new AdRequest.Builder().build();
	//	interstitial.setAdUnitId(REKLAM_ID);
		InterstitialAd.load(this,REKLAM_ID, adRequest,
				new InterstitialAdLoadCallback() {
					@Override
					public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
						// The mInterstitialAd reference will be null until
						// an ad is loaded.
						interstitial = interstitialAd;
						//Log.i(TAG, "onAdLoaded");
					}

					@Override
					public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
						// Handle the error
						//Log.i(TAG, loadAdError.getMessage());
						interstitial = null;
					}
				});


		//Admob TamBoyReklam Gecis Reklam End
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Ilceler));
	}

}
