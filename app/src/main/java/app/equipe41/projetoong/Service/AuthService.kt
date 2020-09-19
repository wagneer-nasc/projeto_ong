package app.equipe41.projetoong.Service

import app.equipe41.projetoong.Models.Auth
import app.equipe41.projetoong.Models.Ong
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @POST("/signin")
    fun authOng(@Body auth: Auth): Call<Auth>

    @POST("/validateToken")
    fun authValidate(@Body auth: Auth): Call<Auth>

}

