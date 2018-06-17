package com.tdm.mbtwitterdm.data.response

data class EventsList(val events: List<Event>?)

data class Entities(val hashtags: List<Any>?, val symbols: List<Any>?, val user_mentions: List<Any>?, val urls: List<Any>?)

data class Event(val type: String?, val id: String?, val created_timestamp: String?, val message_create: Message_create?)

data class Message_create(val target: MsgTarget?, val sender_id: String?, val source_app_id: String?, val message_data: Message_data?)

data class Message_data(val text: String?, val entities: Entities?)

data class MsgTarget(val recipient_id: String?)

data class SentMessage(val event: Event?)