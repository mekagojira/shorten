package com.shorten.api.shared

data class FeatureResult<T>(
    private var success: Boolean? = true,
    var code: String? = "OK",
    var data: T? = null,
    var message: String? = ""
) {
    fun withData(data: T?): FeatureResult<T> {
        this.success = true
        this.data = data
        return this
    }

    fun withMessage(code: String, message: String): FeatureResult<T> {
        this.code = code
        this.message = message
        return this
    }
}