package ru.yandex.money.tools.grafana.dsl.dashboard

import org.json.JSONArray
import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.panels.Panels
import ru.yandex.money.tools.grafana.dsl.time.Refresh
import ru.yandex.money.tools.grafana.dsl.time.TimeRange
import ru.yandex.money.tools.grafana.dsl.time.d
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.time.s
import ru.yandex.money.tools.grafana.dsl.variables.Variables

/**
 * Dashboard. Represents root of JSON-document, used for importing into Grafana.
 *
 * @property title Display title
 * @property timeRange Metrics time range
 * @property refresh Metrics refresh rate
 * @property tags Tags
 * @property variables Variables that should be reused for querying metrics
 * @property panels Panels
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Dashboard(
    private val title: String,
    private val timeRange: TimeRange,
    private val refresh: Refresh,
    private val tags: Tags,
    private val variables: Variables,
    private val panels: Panels
) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "title" to title
        "time" to timeRange
        "refresh" to refresh.asRefreshPeriod()
        "tags" to tags
        "panels" to panels
        "templating" to jsonObject {
            "list" to variables
        }
        "annotations" to jsonObject {
            "list" to JSONArray()
        }
        "editable" to false
        "graphTooltip" to 0
        "links" to JSONArray()
        "schemaVersion" to 16
        "style" to "dark"
        "timepicker" to jsonObject {
            "refresh_intervals" to jsonArray[5.s, 10.s, 30.s, 1.m, 5.m, 15.m, 30.m, 1.h, 2.h, 1.d]
            "time_options" to jsonArray[5.m, 15.m, 1.h, 6.h, 12.h, 24.h, 2.d, 7.d, 30.d]
        }
        "timezone" to "browser"
    }
}
