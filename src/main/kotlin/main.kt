fun main(args: Array<String>) {
  val day1 = Day1()
  val day2 = Day2()
  val day3 = Day3()
  val day4 = Day4()
  val day5 = Day5()
  val day6 = Day6()
  val day7 = Day7()
  val day8 = Day8()
  val day9 = Day9()
  val day10 = Day10()
  val day11 = Day11()
  val day12 = Day12()
  val day13 = Day13()
  val day14 = Day14()
  val day15 = Day15()
  val day16 = Day16()
  val day17 = Day17()
  val day18 = Day18()
  val day19 = Day19()
  val day20 = Day20()
  val day21 = Day21()
  val day22 = Day22()
  val day23 = Day23()
  val day24 = Day24()
  val day25 = Day25()
  val days: Array<DayInterface> = arrayOf<DayInterface>(
    day1,
    day2,
    day3,
    day4,
    day5,
    day6,
    day7,
    day8,
    day9,
    day10,
    day11,
    day12,
    day13,
    day14,
    day15,
    day16,
    day17,
    day18,
    day19,
    day20,
    day21,
    day22,
    day23,
    day24,
    day25
  )

  val CurrentDay = 18
  val DoAllDays = false

  if (DoAllDays) {
    for (i in 0 until CurrentDay) {
      println("Day ${i+1}:")
      println("Part 1 Test: ${days[i].part1Test()}")
      println("Part 1 Answer: ${days[i].part1Answer()}")
      println("Part 2 Test: ${days[i].part2Test()}")
      println("Part 2 Answer: ${days[i].part2Answer()}")
    }
  }
  else
  {
      println("Day ${CurrentDay}:")
      println("Part 1 Test: ${days[CurrentDay-1].part1Test()}")
      println("Part 1 Answer: ${days[CurrentDay-1].part1Answer()}")
      println("Part 2 Test: ${days[CurrentDay-1].part2Test()}")
      println("Part 2 Answer: ${days[CurrentDay-1].part2Answer()}")
  }
}
