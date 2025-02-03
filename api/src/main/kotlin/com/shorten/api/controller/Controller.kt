package com.shorten.api.controller

import com.shorten.api.feature.createlink.CreateLink
import com.shorten.api.feature.createlink.CreateLinkRequest
import com.shorten.api.feature.createlink.CreateLinkResponse
import com.shorten.api.feature.getlink.GetLink
import com.shorten.api.feature.getlink.GetLinkRequest
import com.shorten.api.feature.getlink.GetLinkResponse
import com.shorten.api.feature.ping.Ping
import com.shorten.api.shared.FeatureResult
import com.shorten.api.shared.UseCase
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val ping: Ping,
    private val createLink: CreateLink,
    private val getLink: GetLink
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/")
    fun ping(): FeatureResult<String> {
        return handle("", ping)
    }

    @PostMapping("/get")
    fun get(@RequestBody @Valid body: GetLinkRequest): FeatureResult<GetLinkResponse> {
        return handle(body, getLink)
    }

    @PostMapping("/create")
    fun create(@RequestBody @Valid body: CreateLinkRequest): FeatureResult<CreateLinkResponse> {
        return handle(body, createLink)
    }

    private fun <I, O> handle(input: I, useCase: UseCase<I, O>): FeatureResult<O> {
        return try {
            useCase.handle(input)
        } catch (e: Exception) {
            logger.error(e) { e }

            FeatureResult<O>()
                .withMessage("ServerError", "Server Error")
        }
    }
}