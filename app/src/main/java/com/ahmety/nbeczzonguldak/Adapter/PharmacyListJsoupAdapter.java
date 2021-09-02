package com.ahmety.nbeczzonguldak.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmety.nbeczzonguldak.R;

import java.util.List;

public class PharmacyListJsoupAdapter extends RecyclerView.Adapter<PharmacyListJsoupAdapter.JViewHolder> {

    private java.util.List<String> mpharmacyName;
    private java.util.List<String> mpharmacyAddress;
    private List<String> mpharmacyPhoneNo;
    private LayoutInflater mInflater;
    Context mContext;
    private LocationListener locationListener;

    public PharmacyListJsoupAdapter(java.util.List<String> mpharmacyName, java.util.List<String> mpharmacyAddress, java.util.List<String> mpharmacyPhoneNo, Context mContext, LocationListener locationListener) {
        this.mpharmacyName = mpharmacyName;
        this.mpharmacyAddress = mpharmacyAddress;
        this.mpharmacyPhoneNo = mpharmacyPhoneNo;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.locationListener = locationListener;
    }

    @NonNull
    @Override
    public PharmacyListJsoupAdapter.JViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_pharmacy_jsoup, parent,false);
        return new JViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyListJsoupAdapter.JViewHolder jViewHolder, final int position) {
        jViewHolder.textPharmacyNameJ.setText(mpharmacyName.get(position));
        jViewHolder.textPharmacyAddressJ.setText(mpharmacyAddress.get(position));
        jViewHolder.textPharmacyTelNoJ.setText(mpharmacyPhoneNo.get(position));
        if(position %2 != 0){
            jViewHolder.cardViewPharmacyItemJ.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorBgRecyclerview));
        }
        jViewHolder.textLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationListener.showLocationOnMap(position);
      /*          String labelLocation = "Mithatpaşa Mah.Köprüaltı Cad. No:7/D   Merkez/Zonguldak";
                String urlAddress = "http://maps.google.com/maps?q=("+ labelLocation + ")&iwloc=A&hl=es";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
                mContext.startActivity(intent);*/
            }
        });

        jViewHolder.textPharmacyTelNoJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telNumber = mpharmacyPhoneNo.get(position);
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+telNumber));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(callIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mpharmacyName.size();
    }

   public class JViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgLocationPinJ;
        private TextView textPharmacyNameJ, textPharmacyAddressJ, textPharmacyTelNoJ, textLocation;
        private CardView cardViewPharmacyItemJ;
        JViewHolder(View itemView){
            super(itemView);
            textLocation = itemView.findViewById(R.id.textViewLocationPin);
          //  imgLocationPinJ = itemView.findViewById(R.id.imageView_locationPinJ);
            textPharmacyNameJ = itemView.findViewById(R.id.textView_pharmacyNameJ);
            textPharmacyAddressJ = itemView.findViewById(R.id.textView_pharmacyAddressJ);
            textPharmacyTelNoJ = itemView.findViewById(R.id.textView_pharmacyTelNoJ);
            cardViewPharmacyItemJ = itemView.findViewById(R.id.cardView_pharmacyItemJ);
        }
    }

    public interface LocationListener {
        void showLocationOnMap(int position );
    }
}
