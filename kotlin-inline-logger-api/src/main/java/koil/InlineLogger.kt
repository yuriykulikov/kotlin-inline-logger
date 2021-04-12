package koil

/**
 * Simple Kotlin Logger abstraction using inline functions for lazy log message evaluation.
 *
 * Use inline functions to log messages:
 * * [debug]
 * * [info]
 * * [warn]
 * * [error]
 *
 * ## Example
 * ```
 * logger.warn { "This is a lazy evaluated $message" }
 * logger.debug { "See the { and } ?" }
 * logger.error { "These are efficient inline functions which are not lambdas!  " }
 * ```
 */
abstract class InlineLogger {
    /**
     * Writes the log message and an optional [e] if logger is enabled.
     */
    inline fun error(e: Throwable? = null, supplier: () -> String) {
        if (isErrorEnabled) {
            writeError(supplier(), e)
        }
    }

    /**
     * Writes the log message and an optional [e]. The decision to perform the write must be done before.
     * Do not use this directly in client code, instead use [error]
     */
    abstract fun writeError(message: String, e: Throwable?)

    abstract val isErrorEnabled: Boolean

    /**
     * Writes the log message and an optional [e] if logger is enabled.
     */
    inline fun warn(e: Throwable? = null, supplier: () -> String) {
        if (isWarnEnabled) {
            writeWarn(supplier(), e)
        }
    }

    /**
     * Writes the log message and an optional [e]. The decision to perform the write must be done before.
     * Do not use this directly in client code, instead use [warn]
     */
    abstract fun writeWarn(message: String, e: Throwable?)

    abstract val isWarnEnabled: Boolean

    /**
     * Writes the log message and an optional [e] if logger is enabled.
     */
    inline fun info(e: Throwable? = null, supplier: () -> String) {
        if (isInfoEnabled) {
            writeInfo(supplier(), e)
        }
    }

    /**
     * Writes the log message and an optional [e]. The decision to perform the write must be done before.
     * Do not use this directly in client code, instead use [info]
     */
    abstract fun writeInfo(message: String, e: Throwable?)

    abstract val isInfoEnabled: Boolean

    /**
     * Writes the log message and an optional [e] if logger is enabled.
     */
    inline fun debug(e: Throwable? = null, supplier: () -> String) {
        if (isDebugEnabled) {
            writeDebug(supplier(), e)
        }
    }

    /**
     * Writes the log message and an optional [e]. The decision to perform the write must be done before.
     * Do not use this directly in client code, instead use [debug]
     */
    abstract fun writeDebug(message: String, e: Throwable?)

    abstract val isDebugEnabled: Boolean
}

