package ru.yandex.money.tools.grafana.dsl.dashboard

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.json.set
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.panels.idGenerator
import ru.yandex.money.tools.grafana.dsl.panels.panel
import ru.yandex.money.tools.grafana.dsl.panels.x
import ru.yandex.money.tools.grafana.dsl.panels.y
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.time.now
import ru.yandex.money.tools.grafana.dsl.time.off

class DashboardBuilderTest {

    @BeforeMethod
    fun beforeMethod() {
        idGenerator = 1
        x = 0
        y = 0
    }

    @Test
    fun `should correct create minimal example`() {
        // expect
        val minimalDashboard = dashboard("TestAutogenerated") {}

        // that
        minimalDashboard shouldEqualToJson jsonFile("MinimalDashboard.json")
    }

    @Test
    fun `should correct change time range`() {
        // expect
        val dashboard = dashboard("TestAutogenerated") {
            timeRange = now - 3.h..now
        }

        // that
        dashboard shouldEqualToJson jsonFile("DashboardWithTimeRange.json")
    }

    @Test
    fun `should set false to refresh property when refresh is off`() {
        // expect
        val dashboard = dashboard("TestAutogenerated") {
            refresh = off
        }

        // that
        dashboard shouldEqualToJson jsonFile("DashboardWithOffRefresh.json")
    }

    @Test
    fun `should contains 2 tags when 1 tag added`() {
        // expect
        val dashboard = dashboard("TestAutogenerated") {
            tags += "test"
        }

        // that
        dashboard shouldEqualToJson jsonFile("DashboardWithTags.json")
    }

    @Test
    fun `should add panel to board when panels is called`() {
        // expect
        val dashboard = dashboard("TestAutogenerated") {
            panels {
                panel(title = "Test Panel") {
                    properties {
                        it["type"] = "graph"
                    }
                }
            }
        }

        // that
        dashboard shouldEqualToJson jsonFile("DashboardWithEmptyPanel.json")
    }

    @Test
    fun `should add variable when variable is created`() {
        // expect
        val dashboard = dashboard("TestAutogenerated") {
            @Suppress("UNUSED_VARIABLE")
            val variable by variable {
                interval(1.m, 10.m)
            }
        }

        // that
        dashboard shouldEqualToJson jsonFile("DashboardWithVariable.json")
    }
}