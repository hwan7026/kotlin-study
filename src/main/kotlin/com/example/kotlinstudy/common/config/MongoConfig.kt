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

    /**
     * mongoDB 저장 시 _class 필드가 자동 생성 -> mongoConverter를 통해 _class 제거
     * setMapKeyDotReplacement 를 통해 "-DOT" 설정
     * @param mongoDatabaseFactory MongoDatabaseFactory?
     * @param mongoMappingContext MongoMappingContext?
     * @return MappingMongoConverter?
     */
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

