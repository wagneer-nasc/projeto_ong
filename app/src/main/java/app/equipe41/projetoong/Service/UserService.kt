package app.equipe41.projetoong.Service

import app.equipe41.projetoong.Models.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/find")
    fun getUser(): Call<List<User>>
}

