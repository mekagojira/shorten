package com.shorten.api.db.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "links")
data class LinkModel(
    @Id val id: String? = null,
    val url: String? = "",
    val createdAt: Long = System.currentTimeMillis(),
)