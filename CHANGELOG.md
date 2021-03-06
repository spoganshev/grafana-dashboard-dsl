# grafana-dashboard-dsl

## NEXT_VERSION

## [1.2.1]() (29-01-2019)

* Translate README, CHANGELOG and comments to english

## [1.2.0]() (10-01-2019)

* Block `Legend` for `GraphPanel` can now be customised. There are two default implementations:
     * `DEFAULT` - shows `min`, `max`, `avg`, `current`, `total` for each metric
     * `EMPTY` -  shows only names/aliases for metrics
* `GraphPanel` can now be configured to show `points` with the specified `pointradius`
* Can now configure null point mode in `GraphPanel` via `NullPointMode`
* Fill ratio can now be configured for `fill` graphic in `GraphPanel`
* Added some common colors for Grafana to `Color`
* Block `aliasColors` can now be configured for `GraphPanel`
* Parameter `averageSeries` can now be specified in `Metric`
* `Dashboard` can now accept time interval from start of a day - `now/d`

## [1.1.1]() (05-12-2018)

* Fix link to examples in build-script

## [1.1.0]() (04-12-2018)

* Examples of DSL usage
* Function `movingMedian` can now accept `Variable`

## [1.0.5]() (29-11-2018)

* Improved README.md
* Publish javadoc

## [1.0.2]() (26-11-2018)

* Fix public build

## [1.0.1]() (26-11-2018)

* Gradle build for github

## [1.0.0]() (22-11-2018)

* First release
