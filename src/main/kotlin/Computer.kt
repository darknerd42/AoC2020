class Computer {
    private var accumulator: Int = 0
    private var ip: Int = 0
    private var program: Program? = null

    public data class Instruction(val operation: String, val argument: Int) {}
    public data class RunResult(val result: Int, val terminatedNormally: Boolean) {}

    public fun loadProgram(newProgram: Program) {
        program = newProgram
    }

    public fun run(terminateOnLoop: Boolean = true): RunResult? {
        if (program == null) return null
        accumulator = 0
        ip = 0

        val visitedList = mutableListOf<Int>()
        val runningProgram = program!!

        while (ip < runningProgram.count()) {
            if (visitedList.contains(ip)) break
            visitedList.add(ip)
            when (runningProgram[ip].operation) {
                "nop" -> ip++
                "acc" -> accumulator += runningProgram[ip++].argument
                "jmp" -> ip += runningProgram[ip].argument
            }
        }

        return RunResult(accumulator, ip == runningProgram.count())
    }
}

typealias Program = List<Computer.Instruction>