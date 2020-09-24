package app.equipe41.projetoong.SharedPreference

import android.content.Context
import app.equipe41.projetoong.Models.Auth
import app.equipe41.projetoong.Util.Constants

object MyPreference {

    fun setToken(context: Context, auth: Auth) {
        val preference =
            context.getSharedPreferences(Constants.MY_PREFERENCE, Context.MODE_PRIVATE)
                .edit()
        preference.apply {
            putString(Constants.TOKEN_KEY, auth.token)
            putString(Constants.ID_ONG, auth.id)
        }.apply()
    }

    fun getToken(context: Context): String {
        return context.getSharedPreferences(
            Constants.MY_PREFERENCE,
            Context.MODE_PRIVATE
        ).getString(Constants.TOKEN_KEY, null).toString()
    }

    fun getIdOngLegate(context: Context): String {
        return context.getSharedPreferences(Constants.MY_PREFERENCE, Context.MODE_PRIVATE)
            .getString(Constants.ID_ONG, null).toString()

    }

    fun deleteToken (context: Context) {
            context.getSharedPreferences(Constants.MY_PREFERENCE, Context.MODE_PRIVATE)
                .edit().clear().apply()
    }

    fun setLogged(context: Context, legato: String) {
        val preference =
            context.getSharedPreferences(Constants.MY_PREFERENCE, Context.MODE_PRIVATE)
                .edit()
        preference.apply {
            putString(Constants.LOGGED, legato)
        }.apply()
    }

    fun getLogged(context: Context): String {
        return context.getSharedPreferences(
            Constants.MY_PREFERENCE,
            Context.MODE_PRIVATE
        ).getString(Constants.LOGGED, null).toString()
    }

}