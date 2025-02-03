package com.shorten.api.shared

interface UseCase<I, O> {
    fun handle(input: I): FeatureResult<O>
}