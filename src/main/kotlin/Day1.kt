class Day1: DayInterface {
    public override fun part1Test(): Long? {
        return getAnswer(testInput)?.toLong()
    }

    public override fun part1Answer(): Long? {
        return getAnswer(input)?.toLong()
    }

    public override fun part2Test(): Long? {
        return getPart2Answer(testInput)?.toLong()
    }

    public override fun part2Answer(): Long? {
        return getPart2Answer(input)?.toLong()
    }

    private fun getAnswer(entryList: List<Int>): Int? {
        val matches = getMatchesForSum(entryList, 2020)
        if (matches != null) {
            return matches.first * matches.second
        }

        return null
    }

    private fun getMatchesForSum(entryList: List<Int>, sum: Int): Pair<Int, Int>? {
        entryList.forEachIndexed { index, entry ->
            val match = sum - entry
            if (entryList.contains(match)) {
                return Pair(entry, match)
            }
        }
        return null
    }

    private fun getPart2Answer(entryList: List<Int>): Int? {
        entryList.forEachIndexed { index, entry ->
            val matches = getMatchesForSum(entryList.slice((index + 1)..entryList.lastIndex), 2020 - entry)
            if (matches != null) {
                val entry2 = matches.first
                val entry3 = matches.second
                return entry * entry2 * entry3
            }
        }
        return null
    }

    private val testInput = listOf<Int>(1721, 979, 366, 299, 675, 1456)
    private val input = listOf<Int>(1749,
        1897,
        881,
        1736,
        1161,
        1720,
        1676,
        305,
        264,
        1904,
        1880,
        1173,
        483,
        1978,
        1428,
        1635,
        1386,
        1858,
        1602,
        1916,
        1906,
        1212,
        1730,
        1777,
        1698,
        1845,
        1812,
        1922,
        1729,
        1803,
        1761,
        1901,
        1748,
        1188,
        1964,
        1935,
        1919,
        1810,
        1567,
        1849,
        1417,
        1452,
        54,
        1722,
        1784,
        1261,
        1744,
        1594,
        1526,
        1771,
        1762,
        1894,
        1717,
        1716,
        51,
        1955,
        1143,
        1741,
        1999,
        1775,
        1944,
        1983,
        1962,
        1198,
        1553,
        1835,
        1867,
        1662,
        1461,
        1811,
        1764,
        1726,
        1927,
        1179,
        1468,
        1948,
        1813,
        1213,
        1905,
        1371,
        1751,
        1215,
        1392,
        1798,
        1823,
        1815,
        1923,
        1942,
        1987,
        1887,
        1838,
        1395,
        2007,
        1479,
        1752,
        1945,
        1621,
        1538,
        1937,
        565,
        1969,
        1493,
        1291,
        1438,
        1578,
        1770,
        2005,
        1703,
        1712,
        1943,
        2003,
        1499,
        1903,
        1760,
        1950,
        1990,
        1185,
        1809,
        1337,
        1358,
        1743,
        1707,
        1671,
        1788,
        1785,
        1972,
        1863,
        1690,
        1512,
        1963,
        1825,
        1460,
        1828,
        1902,
        1874,
        1755,
        1951,
        1830,
        1767,
        1787,
        1373,
        1709,
        1514,
        1807,
        1791,
        1724,
        1859,
        1590,
        1976,
        1572,
        1947,
        1913,
        1995,
        1728,
        1624,
        1731,
        1706,
        1782,
        1994,
        1851,
        1843,
        1773,
        1982,
        1685,
        2001,
        1346,
        1200,
        1746,
        1520,
        972,
        1834,
        1909,
        2008,
        1733,
        1960,
        1280,
        1879,
        1203,
        1979,
        1133,
        1647,
        1282,
        1684,
        860,
        1444,
        1780,
        1989,
        1795,
        1819,
        1797,
        1842,
        1796,
        1457,
        1839,
        1853,
        1711,
        1883,
        1146,
        1734,
        1389)
}
