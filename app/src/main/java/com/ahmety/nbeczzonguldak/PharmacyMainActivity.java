package com.ahmety.nbeczzonguldak;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmety.nbeczzonguldak.Model.RequestStatus;
import com.ahmety.nbeczzonguldak.Adapter.PharmacyListAdapter;
import com.ahmety.nbeczzonguldak.Adapter.PharmacyListJsoupAdapter;
import com.ahmety.nbeczzonguldak.Api.ApiClient;
import com.ahmety.nbeczzonguldak.Api.ApiErrorUtils;
import com.ahmety.nbeczzonguldak.Api.ApiInterface;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PharmacyMainActivity extends AppCompatActivity implements PharmacyListJsoupAdapter.LocationListener {
    TextView TextView_Today;
    String countyName, full_URL, day_inTurkish, month_inTurkish;
    RequestStatus ph_list;
    RecyclerView recyclerViewPharmacyList;
    protected RecyclerView.LayoutManager mLayoutManager;
    public ArrayList listPharmacyName= new ArrayList();
    public ArrayList listPharmacyAddress= new ArrayList();
    public ArrayList listPharmacyPhoneNumber= new ArrayList();
    private AdView bannerAd;
    // Jsoup ile veri çekmede kullanılan url
    private static String URL="http://www.zonguldak.nobetci-eczaneleri.net/";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        LocalDate currentDate = LocalDate.now(); // 2016-06-17
        setContentView(R.layout.activity_pharmacy_main);
        TextView_Today = findViewById(R.id.textViewToday);
        recyclerViewPharmacyList = findViewById(R.id.recyclerView_pharmacyList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPharmacyList.setLayoutManager(mLayoutManager);
        Bundle bundle = getIntent().getExtras();
        countyName = bundle.getString("city");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        bannerAd = findViewById(R.id.addView_ph);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerAd.loadAd(adRequest);

        setTitle("                                " + countyName);
        DayOfWeek dow = currentDate.getDayOfWeek(); // TUESDAY
        int dom = currentDate.getDayOfMonth(); // 17
        Month m = currentDate.getMonth(); // SEPTEMBER


        TextView_Today.setText(Integer.toString(dom)+" "+getMounthInTurkish(m)+" "+getDayInTurkish(dow));
        loadPharmacy();



       // new PharmacyMainActivity.dataFetch().execute();
        //Geri tuşu için actionbar aktif etmek
       // ActionBar ab = getActionBar();
       // ab.setDisplayHomeAsUpEnabled(true);
        bannerAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    private void loadPharmacy() {
        full_URL = "Merkez&il=Zonguldak";
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<RequestStatus> call;
        if (countyName.equals("Merkez")) {
           call = apiInterface.getPharmacyMerkez();
        }
        else if (countyName.equals("Ereğli")){
           call = apiInterface.getPharmacyEregli();
        }
        else if (countyName.equals("Alaplı")){
            call = apiInterface.getPharmacyAlapli();
        }
        else if (countyName.equals("Çaycuma")){
            call = apiInterface.getPharmacyCaycuma();
        }
        else if (countyName.equals("Devrek")){
            call = apiInterface.getPharmacyDevrek();
        }
        else if (countyName.equals("Gökçebey")){
            call = apiInterface.getPharmacyGokcebey();
        }
        else if (countyName.equals("Kilimli")){
            call = apiInterface.getPharmacyKilimli();
        }
        else if (countyName.equals("Kozlu")){
            call = apiInterface.getPharmacyKozlu();
        }
        else{
            call = apiInterface.getPharmacyMerkez();
        }

        call.enqueue(new Callback<RequestStatus>() {
            @Override
            public void onResponse(Call<RequestStatus> call, Response<RequestStatus> response) {
                if (response.isSuccessful()) {
                    ph_list = response.body();
                 /*   textProductName.setText(product_page_list.getProductName());
                    textDescription.setText(product_page_list.getProductDescription());*/

                    /*Glide.with(getApplicationContext()).load(product_page_list.getProductImages())
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_glide_img).error(R.drawable.ic_glide_warning)).
                            into(imgProduct);*/
                    PharmacyListAdapter myAdapter = new PharmacyListAdapter(getApplicationContext(), R.layout.item_pharmacy, ph_list.getResult());
                    DividerItemDecoration divider = new
                            DividerItemDecoration(recyclerViewPharmacyList.getContext(),
                            DividerItemDecoration.VERTICAL);
                    divider.setDrawable(
                            ContextCompat.getDrawable(getBaseContext(), R.drawable.line_divider)
                    );
                    recyclerViewPharmacyList.addItemDecoration(divider);
                    recyclerViewPharmacyList.setAdapter(myAdapter);


                } else {
                    ApiErrorUtils.parseError(response);
                    new dataFetch().execute();
                }

            }

            @Override
            public void onFailure(Call<RequestStatus> call, Throwable t) {
                Log.d("response", "apiError");
                new dataFetch().execute();
            }
        });
    }


    // copy paste  ////   copy paste////
    private class dataFetch extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog= new ProgressDialog(PharmacyMainActivity.this);
            progressDialog.setTitle(countyName);
            progressDialog.setMessage("Yükleniyor..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(URL).timeout(30 * 1000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .get();
                //Elements trsize = doc.select("tr[height=20] td:lt(4)");
                Elements elPharmacy= doc.getElementsByClass("panel panel-default");

                for (int i = 0; i < elPharmacy.size(); i++) {
                    if (elPharmacy.get(i).text().contains(countyName)) {
                        if(!elPharmacy.get(i).text().contains("Merkez/Zonguldak")&& elPharmacy.get(i).text().contains("Merkez")){

                        }
                        else {
                            listPharmacyName.add(elPharmacy.get(i).getElementsByTag("h4").text());
                            //   listPharmacyAddress.add(elPharmacy.get(i).getElementsByClass("list-group-item-text boldver").text());
                            String addressPhone = elPharmacy.get(i).getElementsByClass("list-group-item-text boldver").text();
                            String address = addressPhone.substring(0, addressPhone.indexOf("Tel"));
                            String phoneNo = addressPhone.substring(addressPhone.indexOf("Tel") + 3).replace(":", "").replace("0 (","").replace(")","");
                            listPharmacyAddress.add(address);
                            listPharmacyPhoneNumber.add(phoneNo);
                            //liste.add(elPharmacy.get(i).text().replaceAll("Haritada Gör",""));
                        }
                    }
                //pharmacy = doc.select("tr:nth-child(n)");
                 }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            PharmacyListJsoupAdapter adapter = new PharmacyListJsoupAdapter(listPharmacyName,listPharmacyAddress,listPharmacyPhoneNumber,getApplicationContext(), PharmacyMainActivity.this);
            recyclerViewPharmacyList.setAdapter(adapter);
           // lv.setAdapter(adapter);
            progressDialog.dismiss();

        }
    }

    // copy paste  ////

    public String getDayInTurkish(DayOfWeek day){
        if (day.equals(DayOfWeek.MONDAY)){
            day_inTurkish = "Pazartesi";
        }
        else if (day.equals(DayOfWeek.TUESDAY)){
            day_inTurkish = "Salı";
        }
        else if (day.equals(DayOfWeek.WEDNESDAY)){
            day_inTurkish = "Çarşamba";
        }
        else if (day.equals(DayOfWeek.THURSDAY)){
            day_inTurkish = "Perşembe";
        }
        else if (day.equals(DayOfWeek.FRIDAY)){
            day_inTurkish = "Cuma";
        }
        else if (day.equals(DayOfWeek.SATURDAY)){
            day_inTurkish = "Cumartesi";
        }
        else if (day.equals(DayOfWeek.SUNDAY)){
            day_inTurkish = "Pazar";
        }
        else{
            day_inTurkish = "";}
        return day_inTurkish;
    }

    public String getMounthInTurkish(Month month){
        if (month.equals(Month.JANUARY)){
            month_inTurkish = "Ocak";
        }
        else if (month.equals(Month.FEBRUARY)){
            month_inTurkish = "Şubat";
        }
        else if (month.equals(Month.MARCH)){
            month_inTurkish = "Mart";
        }
        else if (month.equals(Month.APRIL)){
            month_inTurkish = "Nisan";
        }
        else if (month.equals(Month.MAY)){
            month_inTurkish = "Mayıs";
        }
        else if (month.equals(Month.JUNE)){
            month_inTurkish = "Haziran";
        }
        else if (month.equals(Month.JULY)){
            month_inTurkish = "Temmuz";
        }
        else if (month.equals(Month.AUGUST)){
            month_inTurkish = "Ağustos";
        }
        else if (month.equals(Month.SEPTEMBER)){
            month_inTurkish = "Eylül";
        }
        else if (month.equals(Month.OCTOBER)){
            month_inTurkish = "Ekim";
        }
        else if (month.equals(Month.NOVEMBER)){
            month_inTurkish = "Kasım";
        }
        else if (month.equals(Month.DECEMBER)){
            month_inTurkish = "Aralık";
        }
        else{
            month_inTurkish = "";}
        return month_inTurkish;
    }
    // Decodes a URL encoded string using `UTF-8`
    public static String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    @Override
    public void showLocationOnMap(int p) {
           // String labelLocation = "Mithatpaşa Mah.Köprüaltı Cad. No:7/D   Merkez/Zonguldak";
            String labelLocation = listPharmacyName.get(p)+" "+listPharmacyAddress.get(p).toString();
            String urlAddress = "http://maps.google.com/maps?q=("+ labelLocation + ")&iwloc=A&hl=es";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
            startActivity(intent);
    }
    //Geri tuşunun fonksiyonunun yazıldığı yer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, List.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                // app icon in action bar clicked; go home
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
