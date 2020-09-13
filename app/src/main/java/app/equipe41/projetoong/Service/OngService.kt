package app.equipe41.projetoong.Service

import app.equipe41.projetoong.Models.Ong
import retrofit2.Call
import retrofit2.http.GET

interface OngService {

    @GET("/find")
    fun getOng(): Call<ArrayList<Ong>>
}