class Day3: DayInterface {
    private val testInput = listOf<String>(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#"
    )
    private val puzzleInput = listOf<String>(
        ".##.....#....#....#..#.#...#.##",
        ".###........#.##....#......#..#",
        "#..#..#.....#...#....#.#.......",
        ".........#.................#...",
        "..#.......#.#.......#.......#.#",
        ".####........#.#..##.........#.",
        "........#.........#.........#..",
        "#..##...##....#.....##......#..",
        ".........#..............#......",
        "#.........#...##.........#.#...",
        "..............#........##.....#",
        "##....#...........#....#.#...#.",
        ".....#..#.....#...#.#..........",
        "#.......#...#..##........##..#.",
        ".#........#.......#............",
        ".......##.....#.#.#..#.#.......",
        "..#......#..#....##......#.#...",
        ".....##....#..#.....#..#.......",
        ".............#.......#.#....#..",
        ".................#.#......#....",
        ".#..#....#..........#.....#.##.",
        "#.#.#.#.....###.......#.....#..",
        "#...#..........#..#............",
        "...#...##.......#.##..#........",
        "..#...#.#.##...##.........#.#.#",
        ".....#...#.#....#.#.....#......",
        "...#...#.#..#...#.....#........",
        "...........###.#.......#.#.....",
        "..#..#.#........#.#.......#.#.#",
        ".#.......#...........#.........",
        ".#..#...##....#.......###..##..",
        "#....#.....#....##..#.........#",
        "#..#.......#...#......#.#....#.",
        "......##..#..#....#.#........##",
        "#.....#.........#......#..##..#",
        ".#..#.......#....#............#",
        "....#..........#.#...##....#.##",
        "..#...#.#...#.###.#..#......#..",
        "#.#...#..............#.......#.",
        "..##.......#......#....###.....",
        "......#.......#.#.##.#..##.#...",
        ".#......#......#.#....#..#.#..#",
        "....#....#..#...#.....#.#..#...",
        ".#.....#.#.#..#........#.#.###.",
        "#..#..#.#......#..#..#....#.#..",
        "..#.###....#....##...#.........",
        "...........#..#...........#....",
        ".................#..........#.#",
        ".#.#....#..#........#..#.......",
        "...........##..#...............",
        "...#.##.........#.........#.#.#",
        "........#..#....#.#............",
        "...##...##..................#.#",
        "...#..##..#...#......#.....#..#",
        ".##.#..#..#......#......#.....#",
        "....#.....#....#......##.#.....",
        ".....#.##....#...#.............",
        "......#...#.....##....#...#..##",
        "...#............#.###....##....",
        "............#.#.#...#.#........",
        "#.....#..#.#..##...........#.##",
        ".....#.#.#.#..##......##.#..#..",
        ".#.##..#.........#......#.....#",
        ".#.#.#.#.#..#..#........###....",
        "......##..........#.#.....##..#",
        "..#...#..#.....#.#.....#.......",
        "............#....#.............",
        "........#...#..#....#.#..###...",
        "#........##....##..............",
        ".........#.#.#..#..#...#.#.....",
        "....#............#....####...#.",
        "##.#.#......#.....#...#....###.",
        "...#..#..#..#.......#..#.#.#..#",
        "..#..#....#...#.##..#.........#",
        "#..#.#....#....#...#..##..#.#..",
        "...#....#.............#..#.#..#",
        "..#......#.##...#..............",
        "#....##.#.#...##......#.....##.",
        ".#...##...#...####.....##......",
        "...........#.###....#...#...#..",
        "##..#..##..#..#.#.#..###.......",
        ".#...##......#........##..#....",
        ".#...#...#.....#............#..",
        ".#.#.#...#.#..#.......#......#.",
        ".................#..#.#......#.",
        "#..#####......##.#....#...#....",
        "........#......#.....##......#.",
        "....#.#...#...#..#.......#####.",
        ".....##......#...#.......#....#",
        ".#....#...#..#...#.#...#..#...#",
        "....##.........#.#...####.#....",
        "...##..........#.#.......##....",
        ".........#......#.....#....#...",
        "#....##..#......#.....##....#..",
        "...#.#.............#...#.....#.",
        "...........#...#.#....#..#.#...",
        ".......#.#.#.....#..#........#.",
        "..##.....#..#.....##..#........",
        "...#.#..........#...#....#.#...",
        "..#....#......#...#.#...##..#.#",
        ".#...#..#..#..#.......#........",
        ".................#.#...........",
        "...............#......##.....#.",
        "..##.....###..#....#.........##",
        "....#.#........#.####.#...#....",
        ".....#.....##..####..##.......#",
        ".....####.#...#......#.........",
        "........#..#......#.....##.....",
        "...###..#.#..###.......#.......",
        "...#...##..#..#..#..#.##.......",
        "..#......##..#.....##..##......",
        "#.......#.#..#.................",
        "#.......#...#..###....#.#.#.#..",
        "#...#.#....##.##.#...........#.",
        ".#.........#..###..#.........#.",
        "#...#......#...#.#.........#...",
        ".#.##..............#.#....#...#",
        "........#.....#..#.#.#......#..",
        "............####.#......#......",
        "......#.#.#...#.#...#.#.....#..",
        "....#....#...#.....#.......#.#.",
        "..#....#....###..#....#.....##.",
        ".................#.....#.#....#",
        ".#.............#......#.##..#..",
        "#.....##.......#..#.....#....#.",
        "#.#......####...##.....#....##.",
        ".....#.#....#..................",
        "....#....#..#.#...........##...",
        "...#.............##......#..#..",
        "......#..........#...#...##.###",
        "...##......##.....#......#....#",
        "........#.#.#...#...#..##......",
        "......................###....##",
        ".#.....#..#..#.##.#.#.#....#.##",
        ".#..............#.....#......#.",
        ".#...#.##....#.....#.#.#..#..#.",
        "##...##.......#.....#..###.....",
        "...#..#.#....#........#........",
        "....#..............##...#......",
        "...........#..#.....##.#.#.#...",
        "#.#.....##..##.#.#........#....",
        ".........#....#.....#..##.#...#",
        "...#...#..#..#.####...#.......#",
        ".....##.....##.....#......#....",
        "#.##...#....#............#..##.",
        ".#.##..#...#....#.#......#.....",
        "..###................#.........",
        ".#..#..#................#......",
        "....#..#........#..#....#......",
        ".#..........###......#...###...",
        "...........##...#.#..#.........",
        "...#....#..........#.....#..##.",
        "..#..#.............#......###..",
        "#....#....##.....#....#.##.....",
        "......#.......#..#..........##.",
        "#..##.#...#.#.........#....#.#.",
        "...#...#..........#...#..#....#",
        "...###..#.#......#.##.#####...#",
        "..#.....#.#..............#..##.",
        "#..###......#.#..#........#....",
        ".#.......#.......#.....#.##....",
        ".#...##..#.......##.....#....##",
        "..........#.#..#.....#.........",
        ".......####...#...#.....##.....",
        "......#.......#.......#..#.#...",
        "...##....##.#.......#.##......#",
        ".#...#............#......##....",
        "#..#..#...#.#........#.........",
        ".......#.......#.....##.#......",
        ".#....##...#....#.........#...#",
        "#.#....#.....##...........#..#.",
        ".....#......#....#......#.#...#",
        ".#............#...#.#....#....#",
        "........##..#..##..##.##....#..",
        "........................#.#....",
        "#....#...#.....................",
        "##.#.............#.....#...##.#",
        "....##....###.......#..........",
        "..#.#..#.#...####.....#.....#..",
        "#.........#.......#......#..##.",
        ".#.#.............#..#...#...#..",
        "#..#....#....#..##.........#...",
        "#.#.....#.##.#...#.##..#.#..##.",
        "......#......#.###....#..###...",
        ".##...#.......#.........#.#...#",
        "..........#...#....#..#....#...",
        ".....#...#.....#....##....#.#..",
        "#....#...........#.#...#.......",
        ".###..#........##..........#...",
        "....###.##..#...#.#..##......##",
        ".#...#...........#...........#.",
        "#......#....#.##.........##..#.",
        ".#.......#........#......#.#.#.",
        ".......#..##.........#......#..",
        ".#..#.....##....##....#.....#..",
        "#.#.#.....#...#......#.........",
        "..............#.#.........#.#..",
        "....#...#.............#.#......",
        "..##.#............#.#.##....#..",
        ".....####..........#.#....##..#",
        "......#.#.........#.......###..",
        "#....##.#...#.#...........#...#",
        ".....#...#......#....###...#..#",
        "#....#..............#...#......",
        "...#..###...#..........#....#..",
        "#......#..#.#.#......#..#...#..",
        "................##......#..#...",
        "....#..#..#........##..#...#...",
        "...##.......#.##.#.....##...#.#",
        ".......#.##.#..#.....#...#.....",
        "......#........#..#......##.##.",
        "....................#.....#.#..",
        ".##....#...#...##...#.........#",
        "..#...#..#.##..#.#.#......#....",
        "#....###.#..#..#...#..#...##...",
        "#.......#.....#.#.......###.#.#",
        ".#.##...##..#......#....#...#..",
        "#.....#.......##..#....#.......",
        "...###...#............#....#..#",
        ".#....#.#...#..#..#.##.#.#.#...",
        "#......#.#..#.#.#......#.......",
        "..#..#....###.#........#..#.#..",
        ".......#......##.........#.....",
        "...#...###..#..#.##.#..##......",
        ".#.......##.......#..#..#.###.#",
        ".###.#..#.###...........#......",
        "...#................#.#...##..#",
        "....#.###....#.......#........#",
        ".##...#...#..#.....#...#.......",
        ".#...#..#...........#.#......##",
        "...##..#.#.#..#.#.#.......#....",
        ".#.#..#..#.#...........#.......",
        "..#....#.#.#.#.#..............#",
        "..##..............##....#.#..#.",
        "..#....#...##.....###.....#.#.#",
        "#....#......#..........##......",
        ".##.#.#......#...##..###..#....",
        ".#...........#.##.......##..##.",
        "###.....##...#.##..#...........",
        "...#.....#...........#..#.....#",
        "#.........#....#.......#.......",
        ".#.#...#.###....#..#...........",
        ".....#.......#.....#.##.#.#.#..",
        "..##.#.........##.........#..#.",
        ".......#....#......#.........##",
        "...##.....#..#.......#..#.#....",
        "..#...###.......#..#....###....",
        ".......#...###......#.#.....#.#",
        "#....#...#.#....#.#..........#.",
        "........#..#.....#.#.#.........",
        "......##.......###.......#...#.",
        ".........#..#..#.......#.......",
        "#.......#...#.....#.#..#....#..",
        ".##....#..###.............#....",
        "#.#...#.......#.....##.#.#....#",
        "....#....##.#........##........",
        "...##...#.#.............#...##.",
        "##....#.....#..#..#......#.....",
        "#...#.#........##....##......##",
        "..#...........#..#......###....",
        "..##..#.....#......#....##.....",
        "....###.#...#......##......#...",
        "....#....###...........###.#..#",
        "..#....#...#.##....#...#.......",
        "....##...........#............#",
        "..#.#......#......#.##.#...#..#",
        "#.###.............#.#.##.#.....",
        "#....##....#..#.#.#...........#",
        "...#...................#.......",
        ".#...#......#.......#.#....#..#",
        "....#...#..#..#..#.#.....#....#",
        "..#....#............#..###..##.",
        "...##...#...........#..#..#.#..",
        "..#..#..#.........#.........#.#",
        "...#.#.....#.#..##.........#...",
        "....#..........................",
        "....#.....#.#...#.###.........#",
        "....#.#.......#..#.#.#...#...#.",
        ".....#...#..#.....##....#.#.#..",
        "#....###......#..#..........#..",
        ".#.....#......##.......#...#.##",
        "...#..#.....#.#.....#.......##.",
        "............#..#....#...#..#.#.",
        "..........#.#..#..##...........",
        ".......#.......#..##...##.....#",
        "....#...##.#..#...#.#.......#..",
        "....#.#........#...####...#....",
        "#.#.............#.............#",
        ".#.#......#....#..#..#.....##.#",
        "#..#...........#........#.....#",
        "#....#....#.#..#.#....#.#...##.",
        "....##...##...#...#...........#",
        "...#.#..#....#..#..#..#........",
        "...#..##..#........#..........#",
        "#......#.##..##.......#..#.....",
        "..#...#......#...##.#..........",
        ".###.#..#..#........####...#...",
        "#..............#.#.#........#..",
        "..##....#.......#....##...#..##",
        ".##...#..#.#.....#..#.......##.",
        "..#.........##.......#....#..#.",
        ".#..#...#..##.#..#.....#.......",
        ".#....#.........#..#...#...##..",
        "..###..######..#.##.#....#.....",
        "....#..#.....#.............#..#",
        "...#....#.......#..#.#.......##",
        ".....#......#.......#..##...#..",
        ".##..#....##..##......#...#..#.",
        "......#......#...#...###.......",
        "....#.....#.###..##.....#.#.##.",
        ".......#....#...#..#..#...#.#..",
        "...####.#...#...#.#...##....#..",
        "......#.#....#....#.#....##....",
        "#..##...........####....##.#...",
        "...#...##.#.......#.#..........",
        "..#......#..#..#...#......#....",
        "..###..#.....#..#.#.......#...#",
        "#........#...##..#...#....#....",
        "...#.#...#.....#........#...#..",
        "...#....#.###...#..#...#..##.#.",
        ".....#..#..#...#...#..#........",
        "..#......##...............#.#.#",
        ".#...###.#....##..........#.#.."
    )

    private val testSlope = SlopeMap(testInput)
    private val puzzleSlope = SlopeMap(puzzleInput)

    private val slope1 = Pair(1,1)
    private val slope2 = Pair(3,1)
    private val slope3 = Pair(5,1)
    private val slope4 = Pair(7,1)
    private val slope5 = Pair(1,2)

    data class SlopeMap(val textInput: List<String>) {
        var map: MutableList<MutableList<Boolean>> = mutableListOf<MutableList<Boolean>>()
        var rows = 0
        var columns = 0

        init {
            for (row in 0..textInput.lastIndex) {
                map.add(mutableListOf<Boolean>())
                for (character in textInput[row]) {
                    map[row].add(character == '#')
                }
            }
            rows = map.count()
            columns = map[0].count()
        }

        public fun countTreesOnSlope(x: Int, y: Int): Int {
            var row = 0
            var column = 0
            var count = 0

            do {
                count += if (map[row][column]) 1 else 0
                row += y
                column = (column + x) % columns
            } while (row < rows)

            return count
        }
    }

    public override fun part1Test(): Long? {
        return testSlope.countTreesOnSlope(3,1).toLong()
    }
    public override fun part1Answer(): Long? {
        return puzzleSlope.countTreesOnSlope(3,1).toLong()
    }
    public override fun part2Test(): Long? {
        var answer = 1
        answer *= testSlope.countTreesOnSlope(slope1.first, slope1.second)
        answer *= testSlope.countTreesOnSlope(slope2.first, slope2.second)
        answer *= testSlope.countTreesOnSlope(slope3.first, slope3.second)
        answer *= testSlope.countTreesOnSlope(slope4.first, slope4.second)
        answer *= testSlope.countTreesOnSlope(slope5.first, slope5.second)

        return answer.toLong()
    }
    public override fun part2Answer(): Long? {
        var answer: Long = 1
        answer *= puzzleSlope.countTreesOnSlope(slope1.first, slope1.second)
        answer *= puzzleSlope.countTreesOnSlope(slope2.first, slope2.second)
        answer *= puzzleSlope.countTreesOnSlope(slope3.first, slope3.second)
        answer *= puzzleSlope.countTreesOnSlope(slope4.first, slope4.second)
        answer *= puzzleSlope.countTreesOnSlope(slope5.first, slope5.second)

        return answer
    }
}
