class Day15: DayInterface {
    public override fun part1Test(): Long? {
        return solvePart1(testInput)
    }
    public override fun part1Answer(): Long? {
        return solvePart1(puzzleInput)
    }
    public override fun part2Test(): Long? {
        return solvePart2(testInput)
    }
    public override fun part2Answer(): Long? {
        return solvePart2(puzzleInput)
    }

    private fun numberSpokenOnTurn(startingNumbers: List<Int>, whichTurn: Int): Long {
        val newestTurnSpoken = startingNumbers.mapIndexed { i, it -> it to (i+1) }.toMap().toMutableMap()
        var turn = newestTurnSpoken.count()
        var lastSpoken = 0

        while (++turn <= whichTurn) {
            val spoke = newestTurnSpoken[lastSpoken]
            if (spoke == null) {
                newestTurnSpoken[lastSpoken] = turn
                lastSpoken = 0
            } else {
                newestTurnSpoken[lastSpoken] = turn
                lastSpoken = turn - spoke
            }
        }

        return newestTurnSpoken.toList().sortedBy { (_, whichTurn) -> whichTurn }.last().first.toLong()
    }

    private fun solvePart1(input: List<Int>): Long? {
        return numberSpokenOnTurn(input, 2020)
    }

    private fun solvePart2(input: List<Int>): Long? {
        return numberSpokenOnTurn(input, 30000000)
    }

    private val testInput = listOf<Int>(
        0,
        3,
        6
    )
    private val puzzleInput = listOf<Int>(
        20,
        0,
        1,
        11,
        6,
        3
    )
}
