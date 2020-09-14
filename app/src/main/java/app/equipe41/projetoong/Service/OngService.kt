package app.equipe41.projetoong.Service

import app.equipe41.projetoong.Models.Ong
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OngService {

    @GET("/ongs")
    fun getOng(): Call<ArrayList<Ong>>

    @POST("/ongs")
    fun postOng(@Body ong: Ong): Call<Ong>

}