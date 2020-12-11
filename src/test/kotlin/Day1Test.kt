import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.expect

internal class Day1Test {
    private val day1 = Day1()
    private val entries = listOf<Int>(1721, 979, 366, 299, 675, 1456)

    @Test
    fun getAnswer() {
        assertEquals(day1.getAnswer(entries), 514579)
    }
}