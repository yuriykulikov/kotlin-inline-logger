package koil

/**
 * Implements [InlineLogger] and writes everything using [org.slf4j.Logger].
 *
 * Use [Slf4JInlineLoggerFactory] to create such loggers.
 */
internal class Slf4JInlineLogger(
    private val delegate: org.slf4j.Logger,
    private val prefix: String?,
) : InlineLogger() {
    override fun writeError(message: String, e: Throwable?) {
        when {
            e != null -> delegate.error(message.withPrefix(), e)
            else -> delegate.error(message.withPrefix())
        }
    }

    override val isErrorEnabled: Boolean
        get() = delegate.isErrorEnabled


    override fun writeWarn(message: String, e: Throwable?) {
        when {
            e != null -> delegate.warn(message.withPrefix(), e)
            else -> delegate.warn(message.withPrefix())
        }
    }

    override val isWarnEnabled: Boolean
        get() = delegate.isWarnEnabled

    override fun writeDebug(message: String, e: Throwable?) {
        when {
            e != null -> delegate.debug(message.withPrefix(), e)
            else -> delegate.debug(message.withPrefix())
        }
    }

    override val isDebugEnabled: Boolean
        get() = delegate.isDebugEnabled

    override val isInfoEnabled: Boolean
        get() = delegate.isInfoEnabled

    override fun writeInfo(message: String, e: Throwable?) {
        when {
            e != null -> delegate.info(message.withPrefix(), e)
            else -> delegate.info(message.withPrefix())
        }
    }

    private fun String.withPrefix(): String {
        return prefix?.let { "[$prefix] $this" } ?: this
    }
}
