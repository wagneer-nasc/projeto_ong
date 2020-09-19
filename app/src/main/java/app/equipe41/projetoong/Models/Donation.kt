package app.equipe41.projetoong.Models

import java.math.BigDecimal
import java.util.*

data class Donation(
    val _id: String,
    val nome_doador: String,
    val email_doador: String,
    val cpf: String,
    val valor_doacao: String,
    val createdAT: Date
)

