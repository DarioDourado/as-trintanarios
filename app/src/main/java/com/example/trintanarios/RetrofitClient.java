package com.example.trintanarios;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // 10.0.2.2 aponta para o localhost da máquina host no emulador Android
    // Para dispositivo físico, substitua pelo IP da sua máquina na rede local (ex: 192.168.1.X)
    private static final String BASE_URL = "http://10.0.2.2:8000/";
    private static Retrofit retrofit = null;

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
