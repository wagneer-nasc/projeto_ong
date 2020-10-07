package app.equipe41.projetoong.Models

import java.util.*

data class Voluntario (
    val _id: String,
    val nome_voluntario: String,
    val telefone_voluntario: String,
    val cpf_voluntario: String,
    val email: String,
    val endereco: String,
    val numero: String,
    val createdAT: Date
)