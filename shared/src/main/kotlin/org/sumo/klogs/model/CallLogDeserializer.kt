package org.sumo.klogs.model

import java.io.IOException
import java.time.Instant

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode

import org.springframework.boot.jackson.JsonObjectDeserializer

class CallLogDeserializer : JsonObjectDeserializer<CallLog>() {

    @Throws(IOException::class)
    override fun deserializeObject(jsonParser: JsonParser,
                                   deserializationContext: DeserializationContext,
                                   objectCodec: ObjectCodec, jsonNode: JsonNode): CallLog {

        val commitNode = jsonNode.get("commit")
        val committerNode = jsonNode.get("committer")
        val sha = nullSafeValue(jsonNode.get("sha"), String::class.java)

        return CallLog(sha, sha, sha, sha, sha,
                sha, sha, sha, sha, sha, sha,
                sha, sha, sha, sha, sha, sha,
                sha, sha, sha, sha, sha, sha,
                sha, sha, sha, sha
        )
    }

}