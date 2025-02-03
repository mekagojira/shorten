package com.shorten.api.feature.getlink

import com.shorten.api.db.repo.LinkRepo
import com.shorten.api.shared.FeatureResult
import com.shorten.api.shared.UseCase
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class GetLink(
    private val linkRepo: LinkRepo
) : UseCase<GetLinkRequest, GetLinkResponse> {
    private val logger = KotlinLogging.logger {}


    override fun handle(input: GetLinkRequest): FeatureResult<GetLinkResponse> {
        logger.info { "Get link request: $input" }
        val res = FeatureResult<GetLinkResponse>()

        val link = linkRepo.findById(input.url).orElse(null) ?: return res.withMessage("LinkNotFound", "Link Not Found")

        return res.withData(GetLinkResponse(link = link.url.toString()))
    }
}