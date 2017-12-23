package org.sumo.klogs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.binder.kstream.annotations.KStreamProcessor

@SpringBootApplication
@EnableBinding(KStreamProcessor::class)
class KlogsApplication

fun main(args: Array<String>) {
    runApplication<KlogsApplication>(*args)
}


