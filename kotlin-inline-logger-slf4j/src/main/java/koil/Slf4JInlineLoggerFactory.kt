package koil

import org.slf4j.ILoggerFactory

/**
 * Creates [InlineLogger]s implemented by [Slf4JInlineLogger].
 */
internal class Slf4JInlineLoggerFactory(
    private val factory: ILoggerFactory,
) : InlineLoggerFactory {
    override fun logger(tag: String, prefix: String?): InlineLogger {
        return Slf4JInlineLogger(factory.getLogger(tag), prefix)
    }
}

/**
 * Creates a [InlineLoggerFactory] backed by an [ILoggerFactory]
 */
fun InlineLoggerFactory.Companion.slf4j(
    factory: ILoggerFactory = org.slf4j.LoggerFactory.getILoggerFactory()
): InlineLoggerFactory {
    return Slf4JInlineLoggerFactory(factory)
}