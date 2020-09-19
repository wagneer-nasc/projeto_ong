package app.equipe41.projetoong.Models

import java.math.BigDecimal

data class Donation(
    val _id: String,
    val nome_doador: String,
    val email_doador: String,
    val cpf: String,
    val valor_doacao: String
)

