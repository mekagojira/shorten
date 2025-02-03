package com.shorten.api.feature.getlink

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class GetLinkRequest(
    @field:NotBlank
    @field:Pattern(
        regexp = "^[0-9a-z]{8}\$"
    )
    var url: String
)

data class GetLinkResponse(
    var link: String
)