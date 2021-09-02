package com.ahmety.nbeczzonguldak.Api;

import com.ahmety.nbeczzonguldak.Model.RequestStatus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {
    @Headers({"content-type: application/json","authorization: apikey 43VttHPvsNPZmOf53dAoSi:4DosqvjydBN312q0edTpr5"})
    @GET("health/dutyPharmacy?ilce=Merkez&il=Zonguldak")
    Call<RequestStatus> getPharmacyMerkez();

    @Headers({"content-type: application/json","authorization: apikey 43VttHPvsNPZmOf53dAoSi:4DosqvjydBN312q0edTpr5"})
    @GET("health/dutyPharmacy?ilce=Ereğli&il=Zonguldak")
    Call<RequestStatus> getPharmacyEregli();

    @Headers({"content-type: application/json","authorization: apikey 43VttHPvsNPZmOf53dAoSi:4DosqvjydBN312q0edTpr5"})
    @GET("health/dutyPharmacy?ilce=Alaplı&il=Zonguldak")
    Call<RequestStatus> getPharmacyAlapli();

    @Headers({"content-type: application/json","authorization: apikey 43VttHPvsNPZmOf53dAoSi:4DosqvjydBN312q0edTpr5"})
    @GET("health/dutyPharmacy?ilce=Çaycuma&il=Zonguldak")
    Call<RequestStatus> getPharmacyCaycuma();

    @Headers({"content-type: application/json","authorization: apikey 43VttHPvsNPZmOf53dAoSi:4DosqvjydBN312q0edTpr5"})
    @GET("health/dutyPharmacy?ilce=Devrek&il=Zonguldak")
    Call<RequestStatus> getPharmacyDevrek();

    @Headers({"content-type: application/json","authorization: apikey 43VttHPvsNPZmOf53dAoSi:4DosqvjydBN312q0edTpr5"})
    @GET("health/dutyPharmacy?ilce=Gökçebey&il=Zonguldak")
    Call<RequestStatus> getPharmacyGokcebey();

    @Headers({"content-type: application/json","authorization: apikey 43VttHPvsNPZmOf53dAoSi:4DosqvjydBN312q0edTpr5"})
    @GET("health/dutyPharmacy?ilce=Kilimli&il=Zonguldak")
    Call<RequestStatus> getPharmacyKilimli();

    @Headers({"content-type: application/json","authorization: apikey 43VttHPvsNPZmOf53dAoSi:4DosqvjydBN312q0edTpr5"})
    @GET("health/dutyPharmacy?ilce=Kozlu&il=Zonguldak")
    Call<RequestStatus> getPharmacyKozlu();
}
