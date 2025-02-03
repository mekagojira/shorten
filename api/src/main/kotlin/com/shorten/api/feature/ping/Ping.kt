package com.shorten.api.feature.ping

import com.shorten.api.shared.FeatureResult
import com.shorten.api.shared.UseCase
import org.springframework.stereotype.Service

@Service
class Ping : UseCase<String, String> {
    override fun handle(input: String): FeatureResult<String> {
        return FeatureResult<String>().withData("OK")
    }
}