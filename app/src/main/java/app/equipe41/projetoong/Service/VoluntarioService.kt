package app.equipe41.projetoong.Service

import app.equipe41.projetoong.Models.Donation
import app.equipe41.projetoong.Models.Voluntario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VoluntarioService {

    @POST("/voluntarios/{id}")
    fun postVoluntario(@Path("id")id:String, @Body voluntario: Voluntario) : Call<Voluntario>

    @GET("/voluntarios/{id}")
    fun getVoluntary(@Path("id")id: String) : Call<ArrayList<Voluntario>>
}