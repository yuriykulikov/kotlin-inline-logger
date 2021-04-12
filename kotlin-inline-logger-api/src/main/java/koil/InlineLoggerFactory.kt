package koil

/**
 * # LoggerFactory
 * A [InlineLoggerFactory] allows to decouple [InlineLogger] creation from its usage, thus making it possible
 * to decide which particular type of [InlineLogger] will be created during the configuration phase.
 *
 * ## Motivation
 * Virtually any system component uses logging. Decoupling the [InlineLogger] implementation allows to
 * to inject [InlineLogger] and [InlineLoggerFactory] into system components without them knowing which particular
 * [InlineLogger] is used. This allows code reuse between platforms, but most importantly it allows
 * [InlineLogger] **substitution** in unit tests. This is particularly important for Android libraries,
 * because using Logcat in unit tests is not possible. At also allows verification of log message
 * content, which is important for SDK libraries.
 */
interface InlineLoggerFactory {
    /**
     * Creates a [InlineLogger] which will use the given [tag] and [prefix] when writing messages.
     *
     * @param tag high level identifier for the message, a class or component name, see android.util.Log
     * @param prefix an optional precise identifier for the message, for example a subcomponent name
     */
    fun logger(tag: String, prefix: String? = null): InlineLogger

    companion object
}