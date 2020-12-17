class Day17: DayInterface {
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

    private fun solvePart1(input: List<String>): Long? {
        val grid = Grid3D()

        grid.setInitialState(input)

        grid.doTurns(6)

        return grid.state.map { it.value }.count { active -> active }.toLong()
    }

    private fun solvePart2(input: List<String>): Long? {
        val grid = Grid4D()

        grid.setInitialState(input)

        grid.doTurns(6)

        return grid.state.map { it.value }.count { active -> active }.toLong()
    }

    class Grid3D {
        val state = mutableMapOf<Triple<Int, Int, Int>, Boolean>()
        val nextState = mutableMapOf<Triple<Int, Int, Int>, Boolean>()

        private fun getNeighbors(of: Triple<Int, Int, Int>): List<Triple<Int, Int, Int>> {
            val neighbors = mutableListOf<Triple<Int, Int, Int>>()
            for (x in -1..1) {
                for (y in -1..1) {
                    for (z in -1..1) {
                        neighbors.add(Triple(of.first+x, of.second+y, of.third+z))
                    }
                }
            }

            neighbors.remove(of)

            return neighbors
        }

        private fun setNextState(of: Triple<Int, Int, Int>) {
            val neighbors = getNeighbors(of).filter { it != null }
            val activeNeighbors = neighbors.map { state[it] }.count {active -> active ?: false}
            val currentState = state[of] ?: false
            val toState = when (currentState) {
                true -> {
                    when (activeNeighbors) {
                        2,3 -> true
                        else -> false
                    }
                }
                false -> {
                    when (activeNeighbors) {
                        3 -> true
                        else -> false
                    }
                }
            }

            if (toState || state[of] != null) {
                nextState[of] = toState
            }
        }

        private fun takeTurn() {
            val setOfCubes = state.keys.toMutableSet()
            setOfCubes.addAll(state.keys.flatMap { getNeighbors(it) })

            for (cube in setOfCubes) {
                setNextState(cube)
            }

            for (toState in nextState) {
                state[toState.key] = toState.value
            }
        }

        fun doTurns(count: Int) {
            for (turn in 1..count) {
                println("Taking turn $turn...")
                takeTurn()
            }
        }

        fun setInitialState(strings: List<String>) {
            state.clear()
            nextState.clear()

            strings.forEachIndexed{y, row ->
                row.forEachIndexed {x, column ->
                   state[Triple(x,y,0)] = (column == '#')
                }
            }
        }
    }

    class Grid4D {
        data class Coordinate(val x: Int, val y: Int, val z: Int, val w: Int) {}

        val state = mutableMapOf<Coordinate, Boolean>()
        val nextState = mutableMapOf<Coordinate, Boolean>()

        private fun getNeighbors(of: Coordinate): List<Coordinate> {
            val neighbors = mutableListOf<Coordinate>()
            for (x in -1..1) {
                for (y in -1..1) {
                    for (z in -1..1) {
                        for (w in -1..1) {
                            neighbors.add(Coordinate(of.x + x, of.y + y, of.z + z, of.w + w))
                        }
                    }
                }
            }

            neighbors.remove(of)

            return neighbors
        }

        private fun setNextState(of: Coordinate) {
            val neighbors = getNeighbors(of).filter { it != null }
            val activeNeighbors = neighbors.map { state[it] }.count {active -> active ?: false}
            val currentState = state[of] ?: false
            val toState = when (currentState) {
                true -> {
                    when (activeNeighbors) {
                        2,3 -> true
                        else -> false
                    }
                }
                false -> {
                    when (activeNeighbors) {
                        3 -> true
                        else -> false
                    }
                }
            }

            if (toState || state[of] != null) {
                nextState[of] = toState
            }
        }

        private fun takeTurn() {
            val setOfCubes = state.keys.toMutableSet()
            setOfCubes.addAll(state.keys.flatMap { getNeighbors(it) })

            for (cube in setOfCubes) {
                setNextState(cube)
            }

            for (toState in nextState) {
                state[toState.key] = toState.value
            }
        }

        fun doTurns(count: Int) {
            for (turn in 1..count) {
                println("Taking turn $turn...")
                takeTurn()
            }
        }

        fun setInitialState(strings: List<String>) {
            state.clear()
            nextState.clear()

            strings.forEachIndexed{y, row ->
                row.forEachIndexed {x, column ->
                   state[Coordinate(x,y,0,0)] = (column == '#')
                }
            }
        }
    }

    private val testInput = listOf<String>(
        ".#.",
        "..#",
        "###",
    )
    private val puzzleInput = listOf<String>(
        "..##.##.",
        "#.#..###",
        "##.#.#.#",
        "#.#.##.#",
        "###..#..",
        ".#.#..##",
        "#.##.###",
        "#.#..##.",
    )
}
