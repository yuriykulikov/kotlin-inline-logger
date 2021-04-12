package koil

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger

@RunWith(TestParameterInjector::class)
class Slf4JInlineLoggerTest {
    @Test
    fun `logs are written only if enabled`(
        @TestParameter error: Boolean,
        @TestParameter warn: Boolean,
        @TestParameter info: Boolean,
        @TestParameter debug: Boolean,
    ) {
        // given
        val slf4jLogger: Logger = mockk<Logger>(relaxed = true) {
            every { isErrorEnabled } returns error
            every { isWarnEnabled } returns warn
            every { isInfoEnabled } returns info
            every { isDebugEnabled } returns debug
        }

        val logger = InlineLoggerFactory.slf4j(factory = { slf4jLogger }).logger("Tag")

        // when
        logger.error { "error" }
        logger.warn { "warn" }
        logger.info { "info" }
        logger.debug { "debug" }

        // then
        verify(exactly = if (error) 1 else 0) { slf4jLogger.error("error") }
        verify(exactly = if (warn) 1 else 0) { slf4jLogger.warn("warn") }
        verify(exactly = if (info) 1 else 0) { slf4jLogger.info("info") }
        verify(exactly = if (debug) 1 else 0) { slf4jLogger.debug("debug") }
    }

    @Test
    fun `exceptions are written if present`() {
        // given
        val slf4jLogger: Logger = mockk<Logger>(relaxed = true) {
            every { isDebugEnabled } returns true
            every { isInfoEnabled } returns true
            every { isWarnEnabled } returns true
            every { isErrorEnabled } returns true
        }

        val logger = InlineLoggerFactory.slf4j(factory = { slf4jLogger }).logger("Tag")

        // when
        logger.error(e = RuntimeException()) { "error" }
        logger.warn(e = RuntimeException()) { "warn" }
        logger.info(e = RuntimeException()) { "info" }
        logger.debug(e = RuntimeException()) { "debug" }

        // then
        verify { slf4jLogger.error("error", any<RuntimeException>()) }
        verify { slf4jLogger.warn("warn", any<RuntimeException>()) }
        verify { slf4jLogger.info("info", any<RuntimeException>()) }
        verify { slf4jLogger.debug("debug", any<RuntimeException>()) }
    }

    @Test
    fun `prefixes are written if present`() {
        // given
        val slf4jLogger: Logger = mockk<Logger>(relaxed = true) {
            every { isDebugEnabled } returns true
            every { isInfoEnabled } returns true
            every { isWarnEnabled } returns true
            every { isErrorEnabled } returns true
        }

        val logger = InlineLoggerFactory.slf4j(factory = { slf4jLogger }).logger("Tag", "Prefix")

        // when
        logger.error(e = RuntimeException()) { "error" }
        logger.warn { "warn" }
        logger.info { "info" }
        logger.debug { "debug" }

        // then
        verify { slf4jLogger.error("[Prefix] error", any<RuntimeException>()) }
        verify { slf4jLogger.warn("[Prefix] warn") }
        verify { slf4jLogger.info("[Prefix] info") }
        verify { slf4jLogger.debug("[Prefix] debug") }
    }
}