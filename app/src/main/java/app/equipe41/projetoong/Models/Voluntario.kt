package app.equipe41.projetoong.Models

data class Voluntario (
    val nome_voluntario: String,
    val telefone_voluntario: String,
    val cnpj_ong: String,
    val email: String,
    val senha: String,
    val endereco: String,
    val numero: Int,
    val id_ong: String
)