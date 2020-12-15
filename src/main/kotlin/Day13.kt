import kotlin.math.roundToInt

class Day13: DayInterface {
    public override fun part1Test(): Long? {
        return solvePart1(testInput, testEarliest)
    }
    public override fun part1Answer(): Long? {
        return solvePart1(puzzleInput, puzzleEarliest)
    }
    public override fun part2Test(): Long? {
        return solvePart2(testInput, testEarliest)
    }
    public override fun part2Answer(): Long? {
        return solvePart2(puzzleInput, puzzleEarliest)
    }

    private fun solvePart1(schedule: String, earliest: Int): Long? {
        val busList = schedule.split(',').filter { it != "x" }.map { it.toInt() }

        val waits = busList
            .mapIndexed { i, it -> Pair(i, calcWait(earliest.toLong(), it.toLong())) }

        val first = waits.sortedBy { it.second }.first()
        return (first.second * busList[first.first]).toLong()
    }

    private fun calcWait(earliest: Long, busNumber: Long): Long {
        val x = earliest.toDouble() / busNumber
        val y = Math.ceil(x).toLong()
        return y * busNumber - earliest
    }

    private fun solvePart2(schedule: String, earliest: Int): Long? {
        val unfilteredBusList = schedule.split(',')
        val busList = unfilteredBusList.filter { it != "x" }.map { it.toLong() }
        val expectedMods = busList.map { it - unfilteredBusList.indexOf( it.toString() ) }

        return chineseRemainder(busList.toLongArray(), expectedMods.toLongArray())
    }

    // Found code for Chinese Remainder Theorem from Rosetta Code:
    fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1L
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1L) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0) x1 += b
        return x1
    }

    fun chineseRemainder(n: LongArray, a: LongArray): Long {
        val prod = n.fold(1L) { acc, i -> acc * i }
        var sum = 0L
        for (i in 0 until n.size) {
            val p = prod / n[i]
            sum += a[i] * multInv(p, n[i]) * p
        }
        return sum % prod
    }

    private val testInput = "7,13,x,x,59,x,31,19"
    private val testEarliest = 939
    private val puzzleInput = "19,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,821,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,463,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,23"
    private val puzzleEarliest = 1001612
}
