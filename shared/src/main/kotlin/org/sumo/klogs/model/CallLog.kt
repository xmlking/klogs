package org.sumo.klogs.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = CallLogDeserializer::class)
data class CallLog(
        val CallUUID: String,
        val ConnID: String,
        val GVPSessionID: String,
        val StartDTM: String,
        val EndDTM: String,
        val ANI: String,
        val DNIS: String,
        val TFN: String,
        val LogicalAppName: String,
        val ContainerAppName: String,
        val ContainerAppVersion: String,
        val Server: String,
        val XferReason: String,
        val XferStatus: String,
        val XferPeg: String,
        val XferApp: String,
        val OnDutyFlag: String,
        val BusinessData1: String,
        val BusinessData2: String,
        val BusinessData3: String,
        val BusinessData4: String,
        val BusinessData5: String,
        val CallerData1: String,
        val CallerData2: String,
        val CallerData3: String,
        val CallerData4: String,
        val CallerData5: String
)