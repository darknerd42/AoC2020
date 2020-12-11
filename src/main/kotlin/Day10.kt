import java.lang.Integer.min

class Day10: DayInterface {
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
//        return null
    }

    private fun solvePart1(input: List<Int>): Long? {
        val differences = input.mapIndexed {i, it ->
            if (i == 0) it
            else it - input[i-1]
        }

        return (differences.count { it == 1 } * (differences.count { it == 3 } + 1)).toLong()
    }

    private fun solvePart2(input: List<Int>): Long? {
        val counts = mutableMapOf<Int, Long>()
        return countValidPermutations(input, counts)
    }

    private fun countValidPermutations(input: List<Int>, counts: MutableMap<Int, Long>): Long {
        val cachedCount = counts[input[0]]
        if (cachedCount != null) return cachedCount

        val newCount = when (input.count()) {
            1,2 -> 1
            3 -> if (input[2]-input[0] <= 3) 2 else 1
            else -> input.subList(1, 4).mapIndexed { i, it ->
                if ((it-input[0]) <= 3) {
                    countValidPermutations(input.subList(i+1, input.count()), counts)
                } else {
                    0
                }
            }.sum()
        }

        counts[input[0]] = newCount

        return newCount
    }

//    private val testInput = listOf<Int> (
//        1,
//        4,
//        5,
//        6,
//        7,
//        10,
//        11,
//        12,
//        15,
//        16,
//        19
//    )
    private val testInput = listOf<Int>(
        0,
        1,
        2,
        3,
        4,
        7,
        8,
        9,
        10,
        11,
        14,
        17,
        18,
        19,
        20,
        23,
        24,
        25,
        28,
        31,
        32,
        33,
        34,
        35,
        38,
        39,
        42,
        45,
        46,
        47,
        48,
        49
    )
    private val puzzleInput = listOf<Int>(
        0,
        1,
        2,
        3,
        6,
        7,
        8,
        11,
        12,
        13,
        14,
        17,
        18,
        19,
        20,
        21,
        24,
        25,
        26,
        27,
        30,
        31,
        34,
        35,
        36,
        37,
        38,
        41,
        42,
        43,
        46,
        49,
        50,
        53,
        54,
        55,
        56,
        57,
        60,
        63,
        64,
        65,
        66,
        69,
        70,
        71,
        72,
        73,
        76,
        77,
        78,
        79,
        82,
        83,
        84,
        85,
        88,
        91,
        92,
        93,
        94,
        97,
        98,
        99,
        100,
        103,
        106,
        109,
        110,
        111,
        112,
        113,
        116,
        117,
        120,
        121,
        122,
        123,
        124,
        127,
        128,
        129,
        130,
        131,
        134,
        135,
        136,
        137,
        140,
        143,
        144,
        145,
        146
    )
}
