package com.example.kotlinstudy.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.convert.DbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
@EnableMongoAuditing
class MongoConfig {

    @Bean
    fun mongoConverter(
        mongoDatabaseFactory: MongoDatabaseFactory?,
        mongoMappingContext: MongoMappingContext?
    ) : MappingMongoConverter? {
        val dbRefResolver : DbRefResolver = DefaultDbRefResolver(mongoDatabaseFactory!!)
        val mongoConverter = MappingMongoConverter(dbRefResolver, mongoMappingContext!!)
        mongoConverter.setMapKeyDotReplacement("-DOT")
        return mongoConverter
    }
}

