package com.shorten.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**") // Allow all paths
                    .allowedOrigins("*") // Allow all origins
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specified methods
                    .allowedHeaders("*")
            }
        }
    }
}