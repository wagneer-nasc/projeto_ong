package app.equipe41.projetoong.Retrofit

import app.equipe41.projetoong.Util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance : Retrofit?=null
    val getInstance: Retrofit
    get () {
        if(instance == null) {
            instance =  Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }

}