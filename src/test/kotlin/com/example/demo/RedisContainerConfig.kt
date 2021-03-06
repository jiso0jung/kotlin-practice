@file:Suppress("UtilityClassWithPublicConstructor")

package com.example.demo

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource

open class RedisContainerConfig {
    companion object {
        @JvmStatic
        val REDIS_CONTAINER: KGenericContainer = KGenericContainer("redis:7-alpine").withExposedPorts(6379)

        @JvmStatic
        @DynamicPropertySource
        fun overrideProps(registry: DynamicPropertyRegistry) {
            registry.add("spring.redis.host") { REDIS_CONTAINER.host }
            registry.add("spring.redis.port") {
                "" + REDIS_CONTAINER.getMappedPort(6379)
            }
        }

        init {
            REDIS_CONTAINER.start()
        }
    }
}
