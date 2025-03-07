import kotlin.math.abs

class Day14: DayInterface {
    public override fun part1Test(): Long? {
        return solvePart1(testInput)
    }
    public override fun part1Answer(): Long? {
        return solvePart1(puzzleInput)
    }
    public override fun part2Test(): Long? {
        return solvePart2(testInputPart2)
    }
    public override fun part2Answer(): Long? {
        return solvePart2(puzzleInput)
    }

    private fun solvePart1(input: List<String>): Long? {
        val bitmaskComputer = BitmaskComputer()

        for (command in input) {
            bitmaskComputer.executeCommand(command)
        }

        return bitmaskComputer.memory.map { it.value }.sum()
    }

    private fun solvePart2(input: List<String>): Long? {
        val computer = BitmaskComputerV2()

        for (command in input) {
            computer.executeCommand(command)
        }

        return computer.memory.map { it.value }.sum()
    }

    class BitmaskComputer {
        val memory = mutableMapOf<Int, Long>()

        val maskRegex = "mask = ([X01]{36})".toRegex()
        val memRegex = "mem\\[([0-9]+)\\] = ([0-9]+)".toRegex()

        val ALL36BITS: Long = 68719476735L

        var orMask = 0L
        var andMask = ALL36BITS

        fun reset() {
            memory.clear()
        }

        fun executeCommand(command: String) {
            val maskMatches = maskRegex.find(command)
            val memMatches = memRegex.find(command)

            if (maskMatches != null) {
                orMask = 0L
                andMask = ALL36BITS
                var currentBit: Long = (1L shl 35)

                for (c in maskMatches.groupValues[1]) {
                    when (c) {
                        '1' -> orMask = orMask or currentBit
                        '0' -> andMask = andMask xor currentBit
                    }
                    currentBit = currentBit shr 1
                }
                return
            }

            if (memMatches != null) {
                var toWrite = (memMatches.groupValues[2].toLong() and andMask) or orMask
                memory[memMatches.groupValues[1].toInt()] = toWrite

                return
            }
        }
    }

    class BitmaskComputerV2 {
        val memory = mutableMapOf<Long, Long>()

        val maskRegex = "mask = ([X01]{36})".toRegex()
        val memRegex = "mem\\[([0-9]+)\\] = ([0-9]+)".toRegex()

        val ALL36BITS: Long = 68719476735L

        var orBaseMask = 0L
        var floatMasks = mutableSetOf<Long>()
        var andBaseMask = ALL36BITS

        fun reset() {
            memory.clear()
        }

        fun executeCommand(command: String) {
            val maskMatches = maskRegex.find(command)
            val memMatches = memRegex.find(command)

            if (maskMatches != null) {
                orBaseMask = 0L
                floatMasks.clear()
                floatMasks.add(0L)
                var currentBit: Long = (1L shl 35)

                for (c in maskMatches.groupValues[1]) {
                    when (c) {
                        '1' -> orBaseMask = orBaseMask or currentBit
                        'X' -> {
                            val newFloatMasks = floatMasks.toMutableSet()
                            for (mask in floatMasks) {
                                newFloatMasks.add(mask or currentBit)
                            }
                            floatMasks = newFloatMasks
                        }
                    }
                    currentBit = currentBit shr 1
                }

                andBaseMask = floatMasks.sortedBy { abs(it.toDouble()) }.last().inv()

                return
            }

            if (memMatches != null) {
                val baseAddress = (memMatches.groupValues[1].toLong() or orBaseMask) and andBaseMask
                val toWrite = memMatches.groupValues[2].toLong()
                val addressesToWrite = mutableSetOf<Long>()

                for (mask in floatMasks) {
                    addressesToWrite.add(baseAddress or mask)
                }

                for (address in addressesToWrite) {
                    memory[address] = toWrite
                }

                return
            }
        }
    }

    private val testInput = listOf<String>(
        "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
        "mem[8] = 11",
        "mem[7] = 101",
        "mem[8] = 0",
    )
    private val testInputPart2 = listOf<String>(
        "mask = 000000000000000000000000000000X1001X",
        "mem[42] = 100",
        "mask = 00000000000000000000000000000000X0XX",
        "mem[26] = 1",
    )
    private val puzzleInput = listOf<String>(
        "mask = 111101X010011110100100110100101X0X0X",
        "mem[37049] = 1010356",
        "mem[5632] = 28913",
        "mem[9384] = 7522",
        "mask = 00X1011X11X0000010100X011X10X10X10XX",
        "mem[25379] = 7906399",
        "mem[16059] = 904",
        "mem[2132] = 163108832",
        "mask = 001X00000X011X1X100X000001X001X11XX1",
        "mem[2469] = 46318",
        "mem[24674] = 15229",
        "mem[30896] = 743",
        "mem[55599] = 1710",
        "mask = 111X01X0100X111X1X010XX01X1111101100",
        "mem[2201] = 2380",
        "mem[47171] = 21857",
        "mem[6021] = 7287595",
        "mem[49580] = 3837",
        "mem[65412] = 28041507",
        "mem[32992] = 99064028",
        "mask = 011111100X0101111XXX1101X0101X01X010",
        "mem[2629] = 12036738",
        "mem[60110] = 3985",
        "mem[41436] = 2216",
        "mem[19863] = 17395296",
        "mask = 00100001100XXX101001X11010101X011100",
        "mem[16044] = 7248570",
        "mem[330] = 230",
        "mem[48382] = 48086230",
        "mem[40678] = 108001",
        "mem[62875] = 406660",
        "mask = XXX00XX000X111110X010000100X01001101",
        "mem[12613] = 32311025",
        "mem[39377] = 34791",
        "mem[65154] = 966",
        "mem[10459] = 430",
        "mask = 1XX00100000111XX0X01001X00000X000100",
        "mem[6418] = 116508",
        "mem[14345] = 12616",
        "mem[7328] = 187562",
        "mask = X010X1X010011111X0010101X1X0X0100101",
        "mem[28854] = 69124",
        "mem[10581] = 2102343",
        "mem[5064] = 8954",
        "mem[53938] = 9972323",
        "mask = 11X10110X0X111111X110001X11001011111",
        "mem[53175] = 41406445",
        "mem[44587] = 824360210",
        "mem[15218] = 2192587",
        "mem[27476] = 146",
        "mem[47148] = 195",
        "mem[30041] = 7439",
        "mask = 011X1110X0XXX11110X00000100000X101X1",
        "mem[62141] = 301264",
        "mem[2797] = 113383",
        "mem[56055] = 10421",
        "mem[25190] = 22658",
        "mem[48346] = 774379",
        "mask = 1X10011100011X1X1001101000X01X110XX0",
        "mem[53351] = 7500",
        "mem[32711] = 35862",
        "mem[49953] = 136939",
        "mem[54272] = 653",
        "mem[10725] = 14756",
        "mask = 001X0001X001X1XX100101X00010X10X111X",
        "mem[5178] = 215121044",
        "mem[7485] = 2612",
        "mem[46367] = 3136721",
        "mem[60928] = 952320",
        "mem[35848] = 30664825",
        "mem[18833] = 35880",
        "mem[5251] = 31816",
        "mask = 00110110110000X01010001X01XX00110011",
        "mem[65480] = 33015758",
        "mem[11882] = 418279079",
        "mem[12794] = 993098",
        "mem[14684] = 23634",
        "mem[5284] = 325131",
        "mem[53421] = 813",
        "mem[28834] = 14981",
        "mask = 0X11001X100101111X00010XXX0110X11101",
        "mem[32442] = 4146416",
        "mem[19331] = 885",
        "mem[25467] = 2204",
        "mem[2871] = 7670",
        "mask = 0X1001X0010110X1100101010X1111011000",
        "mem[14801] = 2579830",
        "mem[45126] = 1714",
        "mem[2338] = 209130",
        "mask = 1110011X1XX111101001101X10XXX10100X0",
        "mem[22942] = 859315",
        "mem[36322] = 1574906",
        "mem[10928] = 7421",
        "mem[37807] = 51884453",
        "mem[9354] = 39143194",
        "mem[62973] = 11860",
        "mask = 11X101101001X111101101001010011X1X10",
        "mem[31502] = 56496763",
        "mem[21129] = 2962",
        "mem[62973] = 3663259",
        "mem[10709] = 6403",
        "mem[38977] = 15315",
        "mask = 01X01X10X001111X100000X0X00011X01XX1",
        "mem[62133] = 498160429",
        "mem[531] = 553487",
        "mem[411] = 777030385",
        "mask = X1100100X0X11111010X10X110XX1001X001",
        "mem[6228] = 1713",
        "mem[51367] = 396473",
        "mem[62453] = 96391",
        "mask = 001XX1101100X00010100100111100001XX1",
        "mem[33445] = 2761",
        "mem[52666] = 39423328",
        "mem[14758] = 622201665",
        "mem[14945] = 33891",
        "mem[52088] = 94",
        "mem[51131] = 22350",
        "mask = X010001000011X1111011XX000010000110X",
        "mem[57553] = 88950985",
        "mem[57903] = 440",
        "mem[23955] = 6318441",
        "mem[30448] = 946292536",
        "mem[7028] = 4478449",
        "mem[31497] = 493251886",
        "mem[33016] = 275462",
        "mask = 0XX00010100XX1110000000X111001XX01X0",
        "mem[3595] = 796519126",
        "mem[905] = 217493",
        "mask = 00100X100X1110111000000X111X00001X0X",
        "mem[50727] = 817675",
        "mem[2615] = 1140",
        "mem[31960] = 80747",
        "mem[19071] = 167215900",
        "mem[3408] = 540853",
        "mem[16589] = 5161",
        "mask = 0X0001X0001111110101001X10000X001001",
        "mem[14467] = 115424408",
        "mem[4421] = 203554",
        "mem[39690] = 43419",
        "mem[60124] = 20364545",
        "mem[4045] = 592",
        "mask = 00110X1X10X010X0101X0X0X1110010X01X1",
        "mem[33919] = 3440852",
        "mem[4168] = 5247",
        "mem[62598] = 3006098",
        "mem[985] = 803",
        "mem[53351] = 31335",
        "mem[6231] = 934222",
        "mask = 011000XX10X11111X00X00000001X001000X",
        "mem[27828] = 24343",
        "mem[2504] = 1003",
        "mem[18671] = 4434215",
        "mem[58493] = 59534182",
        "mask = 00100XXXX0011X11X001000010X11100X1X1",
        "mem[3324] = 1293",
        "mem[10581] = 136295",
        "mask = 001X0110101X10001XXXX001101100000X10",
        "mem[49440] = 53178",
        "mem[42866] = 824856",
        "mem[29940] = 24261823",
        "mem[34839] = 24643",
        "mem[15218] = 25062",
        "mem[16615] = 532684240",
        "mask = 100X01011001101110011010100111X11X1X",
        "mem[37973] = 398383827",
        "mem[9995] = 60944",
        "mem[50087] = 213",
        "mem[10877] = 24617462",
        "mem[22319] = 98329242",
        "mem[18804] = 8442",
        "mem[15218] = 6567",
        "mask = 011X00101000X1110001X111X00001010000",
        "mem[1144] = 191",
        "mem[4977] = 25877834",
        "mem[61839] = 891",
        "mem[28455] = 1803393",
        "mem[20126] = 304512",
        "mem[5157] = 53364453",
        "mask = 0X10X11010011111X00XX000XX1X10110000",
        "mem[56583] = 121318560",
        "mem[2527] = 4351729",
        "mem[35848] = 107737120",
        "mask = 1010011100X10111100X10X01111110001X0",
        "mem[27109] = 567808",
        "mem[33424] = 1664",
        "mem[2527] = 34412",
        "mask = 11XX0100X0011111010011010X10X0001111",
        "mem[51781] = 85436",
        "mem[55487] = 140238989",
        "mem[43566] = 3597793",
        "mem[18841] = 1770027",
        "mem[353] = 39838355",
        "mem[46209] = 75205509",
        "mask = 0X010010X0X1011110X0110011001011XX00",
        "mem[333] = 584",
        "mem[37351] = 35049323",
        "mem[50567] = 8503452",
        "mem[50009] = 1037",
        "mem[41067] = 558449",
        "mem[48631] = 124",
        "mem[40369] = 113478590",
        "mask = 0101XX11X0010111100001X11101001X1001",
        "mem[28490] = 181563760",
        "mem[18571] = 8337",
        "mem[44808] = 1314",
        "mask = 1010011100X111X0XX01000X00X1XXX10101",
        "mem[43089] = 1900272",
        "mem[6977] = 22862",
        "mem[49214] = 2431545",
        "mask = 01X000X010X01111000100X1XX0011X11101",
        "mem[14804] = 26046",
        "mem[9366] = 60909483",
        "mem[16097] = 238936",
        "mask = 01001010100X1111X000X00010011001X010",
        "mem[2523] = 6760572",
        "mem[12107] = 433898",
        "mem[57031] = 77090",
        "mem[33537] = 362161",
        "mask = 1X10001000011111110X0100X1X10000000X",
        "mem[39070] = 303156",
        "mem[41188] = 1047577",
        "mem[41056] = 3574711",
        "mem[11162] = 1738300",
        "mask = 0X100X1X0X0110111001000000X111010001",
        "mem[3493] = 2425241",
        "mem[31960] = 96",
        "mem[34845] = 221",
        "mem[64844] = 233",
        "mask = 0X100X0100X1X11X1001X1000110X010101X",
        "mem[39377] = 104653",
        "mem[65436] = 149841506",
        "mem[22237] = 3616149",
        "mem[61480] = 7187",
        "mem[27012] = 7017895",
        "mask = 00XX0101000111X11001X010100X10XX1XX1",
        "mem[50003] = 2355",
        "mem[469] = 88842",
        "mem[25093] = 1179395",
        "mask = 0110010X1001111101000X11100101X0X001",
        "mem[50334] = 50100973",
        "mem[35316] = 21836",
        "mem[61908] = 22783",
        "mem[21656] = 197177",
        "mask = 0010001010X111110X0X10001010X1010111",
        "mem[29845] = 143666",
        "mem[32060] = 561672787",
        "mem[36707] = 405059",
        "mem[19863] = 162",
        "mem[25227] = 188028832",
        "mem[2066] = 162548071",
        "mem[45165] = 259779",
        "mask = 1010010000011X1X0X010X10100011X00000",
        "mem[5284] = 8659870",
        "mem[5336] = 164190934",
        "mem[48215] = 20381190",
        "mem[9554] = 17192",
        "mem[47703] = 4580",
        "mask = 111X0110100111111001X000100X1X0XXX0X",
        "mem[17439] = 219185",
        "mem[34316] = 7642851",
        "mem[16097] = 1322995",
        "mem[63489] = 26333",
        "mem[8564] = 239317",
        "mask = 001X0X1110X11011XX010000111X00111110",
        "mem[2549] = 48026",
        "mem[49893] = 529734",
        "mem[12282] = 10740",
        "mask = X01X0X0XX0X111X11X01001010100101111X",
        "mem[2527] = 3463",
        "mem[30720] = 760291",
        "mem[15059] = 83546",
        "mem[19085] = 37777469",
        "mem[53803] = 552",
        "mem[9990] = 880",
        "mem[40536] = 2290",
        "mask = X110001010111111XX000000X00X0100X010",
        "mem[15218] = 12935",
        "mem[18551] = 450",
        "mem[30680] = 520194451",
        "mem[36464] = 6697282",
        "mem[36217] = 51281648",
        "mask = X0100101X001X0110001000000X010000101",
        "mem[65308] = 11452431",
        "mem[32308] = 578640",
        "mem[33016] = 395020",
        "mem[146] = 4075063",
        "mem[10581] = 6323",
        "mask = 111X011010X1X1111001101101000X000100",
        "mem[1124] = 939",
        "mem[59082] = 1101187",
        "mem[771] = 3031",
        "mem[40567] = 304221164",
        "mem[30736] = 723012",
        "mask = 001000010X0111XXX0X1001000X11100X000",
        "mem[37497] = 12765",
        "mem[42349] = 345",
        "mem[38653] = 22973",
        "mem[23706] = 32907",
        "mem[2325] = 57317553",
        "mem[14023] = 2586828",
        "mask = 0X00111X000111111000000000010X0X1100",
        "mem[8589] = 417",
        "mem[10771] = 261822994",
        "mem[39783] = 2745013",
        "mem[49893] = 449844",
        "mem[4972] = 822896",
        "mask = 00X100011001010XX0X10001XX100100111X",
        "mem[49414] = 834562",
        "mem[17225] = 1582894",
        "mem[23955] = 3148561",
        "mem[12247] = 56753",
        "mask = X010000001X11XX010X100X0X10010100010",
        "mem[3449] = 152127",
        "mem[12501] = 17379211",
        "mask = 0X1000X010011XX11001011001010010X100",
        "mem[49214] = 3490251",
        "mem[41052] = 328973",
        "mask = 001X0X0000X1X01XXX010XX1110010010001",
        "mem[28455] = 29564233",
        "mem[26490] = 414988",
        "mem[19657] = 13471121",
        "mem[45429] = 79102559",
        "mask = 01100100100111X1XX0XX010X00X1X1101X0",
        "mem[2501] = 227077",
        "mem[4763] = 339106",
        "mem[16589] = 43005",
        "mem[2325] = 1301",
        "mem[61707] = 110061832",
        "mask = 0010001000X110X110XX000011010XX10110",
        "mem[19243] = 31993",
        "mem[5497] = 16163",
        "mem[49219] = 376",
        "mem[2293] = 70350",
        "mem[6805] = 2436214",
        "mask = 0100X101X0011111100100X0001XX1011X01",
        "mem[11655] = 5152",
        "mem[31460] = 11602",
        "mem[4977] = 145091169",
        "mem[42202] = 833458",
        "mem[29542] = 711",
        "mem[12217] = 5625",
        "mask = XX1001X01001111110010XX01X0111X10101",
        "mem[31497] = 253099",
        "mem[20926] = 12754500",
        "mem[46425] = 39605909",
        "mem[16087] = 71713",
        "mem[29845] = 25701",
        "mem[1168] = 234",
        "mask = 0111001X1011X11110000X11000100X001X0",
        "mem[62012] = 1540164",
        "mem[22679] = 534776422",
        "mem[17479] = 137590180",
        "mem[10339] = 38375",
        "mem[37358] = 447",
        "mem[56428] = 23904960",
        "mask = 001000X000011011110X0X1000X11001010X",
        "mem[8739] = 33671897",
        "mem[24788] = 95773",
        "mem[45589] = 44176",
        "mem[1168] = 79291",
        "mem[16933] = 53672410",
        "mem[45502] = 106610",
        "mem[54973] = 17925",
        "mask = 0X0X1X101X01101110001100000X010X00X1",
        "mem[37609] = 71849",
        "mem[45642] = 2382124",
        "mem[47190] = 50948518",
        "mem[15016] = 972332",
        "mem[44808] = 11636",
        "mask = 000100100X11X1111010X10011X000111001",
        "mem[28997] = 1558",
        "mem[2528] = 234583",
        "mem[51337] = 1525",
        "mem[64900] = 38121855",
        "mem[53607] = 21522241",
        "mem[8665] = 16698630",
        "mem[2825] = 4655465",
        "mask = 1X11011010X1111110X10XX11X00000XX100",
        "mem[47190] = 353685875",
        "mem[60172] = 923",
        "mem[28868] = 112106",
        "mem[36463] = 36720",
        "mem[26769] = 2479472",
        "mem[38188] = 28414",
        "mask = 1X10011XX001X11X100110001XX011X1000X",
        "mem[37152] = 3921062",
        "mem[63714] = 30760",
        "mem[42997] = 955114",
        "mem[41168] = 1065869",
        "mem[61522] = 3462489",
        "mask = XX1X010000111111X100XX01X0X011011X01",
        "mem[42280] = 139618",
        "mem[36909] = 189834",
        "mem[20969] = 33843",
        "mem[16059] = 18609129",
        "mem[41693] = 2838",
        "mem[2417] = 501526",
        "mem[37152] = 70388201",
        "mask = X01001000X011111000XX0000000X1010100",
        "mem[808] = 2969",
        "mem[35836] = 1345",
        "mem[6157] = 596830",
        "mem[12107] = 119",
        "mem[20072] = 25414",
        "mask = 001X0010000110111X01XX00X0001X010X01",
        "mem[26904] = 185000628",
        "mem[2031] = 18885539",
        "mem[11204] = 6826362",
        "mask = 01110010X011011XX000111X000X110001X1",
        "mem[31647] = 22585",
        "mem[38116] = 33020167",
        "mem[32992] = 217342345",
        "mem[41167] = 3050",
        "mem[61980] = 4001756",
        "mem[62005] = 19633",
        "mask = 11110110100111X110010011X00X0110100X",
        "mem[11108] = 1045",
        "mem[16463] = 730",
        "mem[44526] = 3005934",
        "mem[16120] = 14440473",
        "mask = 00100X101X01101X1X0X00XX111X011001X1",
        "mem[29849] = 1224",
        "mem[496] = 14957",
        "mem[55083] = 194047",
        "mem[42431] = 11237807",
        "mask = 001101011001X1X11X01X0XX000X110X101X",
        "mem[3845] = 21873557",
        "mem[48657] = 334901",
        "mem[13919] = 29628952",
        "mem[42349] = 6148",
        "mask = 111X011010X1111X1001X011X1X0001XX00X",
        "mem[48724] = 70703519",
        "mem[11408] = 4581",
        "mem[57553] = 85089",
        "mask = 01X00010X0X0X1110X01000X000111010111",
        "mem[2143] = 531",
        "mem[59659] = 2027",
        "mask = 0010XXX1X00111111001001X101111010110",
        "mem[2293] = 5556",
        "mem[13572] = 489821002",
        "mem[328] = 346",
        "mem[51240] = 56336112",
        "mem[11162] = 655",
        "mem[41419] = 3227",
        "mem[9636] = 1286",
        "mask = 111001001X01X11X100100X1100X010011X1",
        "mem[31352] = 15612",
        "mem[61439] = 349664457",
        "mem[985] = 3131435",
        "mask = 001101101XX0XX0010100X01101X0X0X011X",
        "mem[3595] = 903157",
        "mem[18722] = 2090814",
        "mem[44603] = 7639414",
        "mem[40369] = 3205",
        "mem[52725] = 783",
        "mem[48724] = 705041",
        "mask = 0011001010101XX010X110X010100X110X01",
        "mem[14902] = 25442",
        "mem[59213] = 659231",
        "mem[1584] = 3041350",
        "mask = X10X1XX0100X1X1X10X00000100010001001",
        "mem[63266] = 16366",
        "mem[44587] = 1548",
        "mem[5271] = 17272",
        "mem[2683] = 130438219",
        "mask = X01X0110101X1XXX101110011X010X100X00",
        "mem[23078] = 26703985",
        "mem[65154] = 1358",
        "mem[36912] = 299",
        "mask = 001X001010011X1100001X00100000X10X01",
        "mem[8920] = 313883810",
        "mem[2506] = 3273",
        "mem[25221] = 9461384",
        "mem[33362] = 736",
        "mask = 001000X00X011X11000X00001000X0110010",
        "mem[2031] = 1010",
        "mem[61542] = 13692",
        "mem[5497] = 3407774",
        "mask = 00100000X10XX011100XX1110X001X011001",
        "mem[15608] = 515444",
        "mem[56525] = 185613",
        "mem[32303] = 16754",
        "mask = 0001X11111X0XX00101000100011X1101000",
        "mem[30591] = 25695",
        "mem[37807] = 116328",
        "mem[6242] = 234258",
        "mask = 111001XX100111X11001X0101000X0100100",
        "mem[32442] = 2767797",
        "mem[4593] = 1693",
        "mask = 0X10001010XX1111000XX0XX100111011XX1",
        "mem[6033] = 8168",
        "mem[8528] = 388750736",
        "mask = 010X11101X111111X0000010X00100101X01",
        "mem[7469] = 45",
        "mem[61980] = 27055",
        "mem[65447] = 194171",
        "mask = 101X011010X1111X100101X01X0110010100",
        "mem[39746] = 1296",
        "mem[353] = 48147876",
        "mem[26904] = 1727",
        "mask = 0000X11X00111111010X0X010000X0000X11",
        "mem[61522] = 1753",
        "mem[23985] = 23215529",
        "mem[11204] = 72089150",
        "mem[61128] = 804774",
        "mem[36744] = 27992562",
        "mask = XXXX0X0110011X1110010XX01010X1011100",
        "mem[3953] = 37147",
        "mem[11231] = 10994",
        "mem[58707] = 39480",
        "mask = 101001X0001111X1110X0X11000000XX1001",
        "mem[39732] = 4528",
        "mem[11954] = 14445",
        "mem[28017] = 43025334",
        "mem[2269] = 394695349",
        "mem[31909] = 11219",
        "mem[28113] = 63433",
        "mask = 01XX001X1001X1111000X10XX1X110110X01",
        "mem[11408] = 332041",
        "mem[24495] = 10080269",
        "mem[56525] = 12577593",
        "mask = 00X0X1101X0X101110000X101X1011101X10",
        "mem[33357] = 4390",
        "mem[53607] = 951",
        "mem[24287] = 266786",
        "mem[13131] = 18212",
        "mem[603] = 79598",
        "mem[35836] = 26511461",
        "mask = X0100111001111001XX1X1XXX011000110X0",
        "mem[52978] = 1985754",
        "mem[39100] = 1279",
        "mem[34231] = 116905",
        "mem[16439] = 896",
        "mask = 010011101XX1111X100000000000XX10110X",
        "mem[699] = 8153",
        "mem[15621] = 3406",
        "mem[3084] = 60170404",
        "mask = 01XX00101011X1X1100000100X0XX11XX000",
        "mem[6391] = 812",
        "mem[4690] = 7757",
        "mem[36912] = 1021",
        "mem[50287] = 633592703",
        "mask = 0110XX10XX100111101011X1X010110X00X1",
        "mem[55972] = 5779",
        "mem[32157] = 293228",
        "mem[56428] = 1966",
        "mem[18030] = 3580797",
        "mem[38977] = 489",
        "mem[34180] = 8866609",
        )
}
