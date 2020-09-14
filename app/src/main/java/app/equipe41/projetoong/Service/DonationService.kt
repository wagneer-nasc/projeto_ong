package app.equipe41.projetoong.Service

import app.equipe41.projetoong.Models.Donation
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DonationService {

    @POST("/doacoes")
    fun postDonation(@Body donation: Donation) : Call<Donation>

}