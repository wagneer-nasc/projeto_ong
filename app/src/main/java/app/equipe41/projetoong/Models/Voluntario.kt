package app.equipe41.projetoong.Models

data class Voluntario (
    val _id: String,
    val nome_voluntario: String,
    val telefone_voluntario: String,
    val cpf_voluntario: String,
    val email: String,
    val endereco: String,
    val numero: Int,
    val id_ong: String
)