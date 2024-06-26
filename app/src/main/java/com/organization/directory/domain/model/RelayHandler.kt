package com.organization.directory.domain.model

import com.jakewharton.rxrelay3.PublishRelay
import com.jakewharton.rxrelay3.Relay

object RelayHandler {

    val payLoadHandler: Relay<Pair<String, Any?>> = PublishRelay.create()
}