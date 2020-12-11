class XMASCypher {
    public data class DecodingResult(val invalidEntry: Long?)
    public fun decode(preamble: Int, message: List<Long>): DecodingResult {
        message.slice(preamble..message.lastIndex).forEach {
            if (!isValidEntry(it, preamble, message)) return DecodingResult(it)
        }

        return DecodingResult(null)
    }

    private fun isValidEntry(entry: Long, preamble: Int, message: List<Long>): Boolean {
        return generateValidEntries(getPreviousEntries(entry, preamble, message)).contains(entry)
    }

    private fun generateValidEntries(previousEntries: List<Long>): List<Long> {
        val validEntries = mutableListOf<Long>()

        previousEntries.forEachIndexed { index, entry ->
            previousEntries.slice(index+1..previousEntries.lastIndex).forEach {
                validEntries.add(entry + it)
            }
        }

        return validEntries
    }

    private fun getPreviousEntries(entry: Long, preamble: Int, message: List<Long>): List<Long> {
        val i = message.lastIndexOf(entry)
        return message.slice((i-preamble)..(i-1))
    }
}