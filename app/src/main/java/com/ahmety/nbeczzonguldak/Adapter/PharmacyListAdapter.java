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

import com.ahmety.nbeczzonguldak.Model.Result;
import com.ahmety.nbeczzonguldak.R;

import java.util.List;

public class PharmacyListAdapter extends RecyclerView.Adapter<PharmacyListAdapter.ViewHolder> {
    private Context mContext;
    int rawLayout;
    private List<Result> pharmacyList;

    public PharmacyListAdapter(Context mContext, int rawLayout, List<Result> pharmacyList) {
        this.mContext = mContext;
        this.rawLayout = rawLayout;
        this.pharmacyList = pharmacyList;
    }


    @NonNull
    @Override
    public PharmacyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(rawLayout, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.textPharmacyName.setText(pharmacyList.get(position).getName()+" Ecz.");
        viewHolder.textPharmacyAddress.setText(pharmacyList.get(position).getAddress());
        viewHolder.textPharmacyTelNo.setText(pharmacyList.get(position).getPhone());
        if(position %2 != 0){
            viewHolder.cardViewPharmacyItem.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorBgRecyclerview));
        }
        viewHolder.imgLocationPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAddress = "http://maps.google.com/maps?q=("+pharmacyList.get(position).getName()+" Eczanesi "+ pharmacyList.get(position).getAddress() + ")&iwloc=A&hl=es";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        viewHolder.textPharmacyTelNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telNumber = pharmacyList.get(position).getPhone();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+telNumber));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(callIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pharmacyList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgLocationPin;
        private TextView textPharmacyName, textPharmacyAddress, textPharmacyTelNo;
        private CardView cardViewPharmacyItem;
        ViewHolder(View itemView){
            super(itemView);
            imgLocationPin = itemView.findViewById(R.id.imageView_locationPin);
            textPharmacyName = itemView.findViewById(R.id.textView_pharmacyName);
            textPharmacyAddress = itemView.findViewById(R.id.textView_pharmacyAddress);
            textPharmacyTelNo = itemView.findViewById(R.id.textView_pharmacyTelNo);
            cardViewPharmacyItem = itemView.findViewById(R.id.cardView_pharmacyItem);
        }
    }
}


  /*


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        Glide.with(mContext).load(productPriceList.get(position).getShopImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_glide_img).error(R.drawable.ic_glide_warning)).
                into(viewHolder.imgProductDetail);
        viewHolder.textProductDetailCost.setText(productPriceList.get(position).getShopProductPrice()+" TL");
        //viewHolder.textProductDetailMarket.setText(productPriceList.get(position).getShopName());
        viewHolder.cardViewProductDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.imgProductDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(mContext,ProductActivity.class);
                i.putExtra("Id", productPageList.get(position).getProductID());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);*/
    /*        }
        });

        viewHolder.btnProductDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartListener.onProductSelect(productPriceList.get(position));

            }
        });
    }



    @Override
    public int getItemCount() {
        return productPriceList.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProductDetail;
        private TextView textProductDetailCost, textProductDetailMarket;
        private Button btnProductDetail;
        private CardView cardViewProductDetail;
        ViewHolder(View itemView){
            super(itemView);
            imgProductDetail = itemView.findViewById(R.id.imageView_product_detail);
            textProductDetailCost = itemView.findViewById(R.id.text_product_detail_cost);
            //textProductDetailMarket = itemView.findViewById(R.id.textViewProductShopName);
            btnProductDetail = itemView.findViewById(R.id.buttonProductDetail);
            cardViewProductDetail = itemView.findViewById(R.id.cardViewProductDetail);
        }
    }
    public interface CartListener {
        void onProductSelect(ProductShop productPrice);

    }
}*/
