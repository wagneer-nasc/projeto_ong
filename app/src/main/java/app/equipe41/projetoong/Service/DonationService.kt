package app.equipe41.projetoong.Service

import app.equipe41.projetoong.Models.Donation
import app.equipe41.projetoong.Models.Ong
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DonationService {

    @POST("/doacoes/{id}")
    fun postDonation(@Path("id")id:String, @Body donation: Donation) : Call<Donation>

    @GET("/doacoes/{id}")
    fun getDonation(@Path("id")id: String) : Call<ArrayList<Donation>>

}