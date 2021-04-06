package br.com.firstsoft.historiconotificacoes.domain.src.model

data class AppNotification(
    val id: Int,
    val appName: String,
    val title: String,
    val message: String,
    val packageName: String,
    val createdTime: Long,
    val extras: Map<String, String>
)
