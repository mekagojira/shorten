package com.shorten.api.feature.createlink

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class CreateLinkRequest(
    @field:NotBlank(message = "URL is required")
    @field:Pattern(
        regexp = "^(http|https)://\\S+$",
        message = "Invalid URL format"
    )
    val url: String
)

data class CreateLinkResponse(
    val link: String
)