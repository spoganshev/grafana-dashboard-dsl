package ru.yandex.money.tools.grafana.dsl.json

import org.json.JSONArray
import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.time.Duration

operator fun JSONObject.set(key: String, value: Any?) {
    this.put(key, value)
}

class JsonBuilder(private val properties: MutableMap<String, Any?> = mutableMapOf<String, Any?>()) {

    infix fun String.to(value: String?) {
        properties[this] = value
    }

    infix fun String.to(value: Boolean) {
        properties[this] = value
    }

    infix fun String.to(value: Int) {
        properties[this] = value
    }

    infix fun String.to(value: Long) {
        properties[this] = value
    }

    infix fun String.to(duration: Duration) {
        properties[this] = duration.toString()
    }

    infix fun String.to(value: Json<*>?) {
        if (value != null) {
            properties[this] = value.toJson()
        }
    }

    infix fun String.to(obj: JSONObject) {
        properties[this] = obj
    }

    infix fun String.to(array: JSONArray) {
        properties[this] = array
    }

    infix fun String.to(value: Any?) {
        properties[this] = value
    }

    internal fun toJson() = JSONObject(properties)
}

fun jsonObject(build: JsonBuilder.() -> Unit): JSONObject {
    val builder = JsonBuilder()
    builder.build()
    return builder.toJson()
}

fun jsonObject(basic: JSONObject, build: JsonBuilder.() -> Unit): JSONObject {
    val builder = JsonBuilder(basic.toMap())
    builder.build()
    return builder.toJson()
}

fun emptyJsonArray() = JSONArray()

class JsonArrayBuilder {
    operator fun get(vararg values: Any?) = JSONArray(values.map {
        when (it) {
            is Boolean -> it
            is Int -> it
            is Long -> it
            is Json<*> -> it.toJson()
            is JSONObject -> it
            is JSONArray -> it
            else -> it?.toString()
        }
    })
}

val jsonArray = JsonArrayBuilder()
