package ru.yandex.money.tools.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.json.JSONObject
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.json.set
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.metrics.functions.aliasByNode
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class MetricPanelBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create metric panel`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("EmptyMetricPanel.json")
    }

    @Test
    fun `should create metric panel with custom bounds`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            bounds = 12 to 6
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithCustomBounds.json")
    }

    @Test
    fun `should create metric panel with custom data source`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            datasource = Zabbix
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithCustomDataSource.json")
    }

    @Test
    fun `should create metric panel with additional properties`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            properties {
                it["legend"] = JSONObject()
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithAdditionalProperties.json")
    }

    @Test
    fun `should create metric panel with 2 metrics`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            metrics {
                metric("A") { "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasByNode 0 }
                metric("B") { "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasByNode 1 }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithMetrics.json")
    }
}
