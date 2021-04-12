# kotlin-inline-logger
Simple Kotlin Logger abstraction using inline functions for lazy log message evaluation.

## Motivation
Using Kotlin inline functions is allowing truly lazy and inexpensive idiomatic Kotlin logging.

## Usage
```kotlin
// configure loggerFactory
val loggerFactory = InlineLoggerFactory.slf4j()

// create a logger
val logger = loggerFactory.logger(tag = "Tag")

// use the logger
logger.debug { "Nice debug $message" }
logger.warn { "Alarming warning $message" }
logger.error(e = exception) { "Critical error: $fail" }
```

## How does it work
When you write this code:
```kotlin
logger.debug { "Nice debug $message" }
```

Kotlin compiler will replace this call with the following code block in the bytecode:
```kotlin
if (logger.isDebugEnabled) {
    logger.writeDebug("Nice debug $message")
}
```

Resulting bytecode will evaluate string template if debug is enabled for this logger.
However, there will be no lambda, because this is an inline function:

```kotlin
inline fun debug(e: Throwable? = null, supplier: () -> String) {
    if (isDebugEnabled) {
        writeDebug(supplier(), e)
    }
}
```

## Alternatives
[MicroUtils/kotlin-logging](https://github.com/MicroUtils/kotlin-logging)
