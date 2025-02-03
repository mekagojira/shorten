package com.shorten.api.feature.createlink

import com.shorten.api.db.model.LinkModel
import com.shorten.api.db.repo.LinkRepo
import com.shorten.api.shared.FeatureResult
import com.shorten.api.shared.UseCase
import io.github.oshai.kotlinlogging.KotlinLogging
import io.viascom.nanoid.NanoId
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CreateLink(
    private val linkRepo: LinkRepo
) : UseCase<CreateLinkRequest, CreateLinkResponse> {
    private val logger = KotlinLogging.logger {}

    override fun handle(input: CreateLinkRequest): FeatureResult<CreateLinkResponse> {
        logger.info { "Create link request: $input" }

        val id = NanoId.generate(8, "0123456789abcdefghijklmnopqrstuvwxyz")

        val newLink = LinkModel(id = id, url = input.url, createdAt = Instant.now().toEpochMilli())
        linkRepo.save(newLink)

        return FeatureResult<CreateLinkResponse>().withData(CreateLinkResponse(link = id))
    }
}