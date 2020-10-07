package app.equipe41.projetoong.Service


import app.equipe41.projetoong.Models.Ong
import retrofit2.Call
import retrofit2.http.*

interface OngService {

    @GET("/ongs")
    fun getOng(): Call<ArrayList<Ong>>

    @POST("/ongs")
    fun postOng(@Body ong: Ong): Call<Ong>

    @GET("/ongs/{id}")
    fun getOneOng(@Path("id")id: String) : Call<Ong>

    @PUT("/ongs/{id}")
    fun updateOng(@Path("id")id: String, @Body ong : Ong) : Call<Ong>

}