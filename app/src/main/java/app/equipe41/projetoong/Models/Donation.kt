package app.equipe41.projetoong.Models

data class Donation (

    val _id: String,
    val nome_doador: String,
    val email_doador: String,
    val cpf: String,
    val valor_doacao: Double,
    val id_ong: String

)