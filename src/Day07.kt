fun main() {
    fun part1(input: List<String>): Int {

        val root = DirectoryNode(value = "/")

        var currentDirectory = root

        outerLoop@ for ((index, line) in input.withIndex()) {
//            println("index:$index $line")
            if (line.startsWith("$ cd ..")) {
                currentDirectory = currentDirectory.parent!!
            } else if (line.startsWith("$ cd")) {
                val destinationDirectory = line.substring(5)
                if (destinationDirectory == currentDirectory.value) {
                    continue
                } else {
                    for (subdirectory in currentDirectory.directories) {
                        if (subdirectory.value == destinationDirectory) {
                            currentDirectory = subdirectory
                            continue@outerLoop
                        }
                    }
                    throw IllegalStateException("Directory $destinationDirectory does not exist in location ${currentDirectory.value}")
                }
            } else if (line.startsWith("$ ls")) {
                continue
            } else if (line.startsWith("dir")) {
                currentDirectory.directories.add(
                    DirectoryNode(
                    parent = currentDirectory,
                    value = line.substring(4)
                    )
                )
            } else {
                val spaceIndex = line.indexOf(" ")
                currentDirectory.files.add(
                    MyFile(
                        size = line.substring(0, spaceIndex).toInt(),
                        name = line.substring(spaceIndex + 1)
                    )
                )
            }
        }

        val fileSystemSize = root.totalSize()
        val limitedFileSystemSize = root.sumOfDirectoriesUnderLimit(100000)

        return limitedFileSystemSize
    }

    fun part2(input: List<String>): Int {
        val root = DirectoryNode(value = "/")

        var currentDirectory = root

        outerLoop@ for ((index, line) in input.withIndex()) {
//            println("index:$index $line")
            if (line.startsWith("$ cd ..")) {
                currentDirectory = currentDirectory.parent!!
            } else if (line.startsWith("$ cd")) {
                val destinationDirectory = line.substring(5)
                if (destinationDirectory == currentDirectory.value) {
                    continue
                } else {
                    for (subdirectory in currentDirectory.directories) {
                        if (subdirectory.value == destinationDirectory) {
                            currentDirectory = subdirectory
                            continue@outerLoop
                        }
                    }
                    throw IllegalStateException("Directory $destinationDirectory does not exist in location ${currentDirectory.value}")
                }
            } else if (line.startsWith("$ ls")) {
                continue
            } else if (line.startsWith("dir")) {
                currentDirectory.directories.add(
                    DirectoryNode(
                        parent = currentDirectory,
                        value = line.substring(4)
                    )
                )
            } else {
                val spaceIndex = line.indexOf(" ")
                currentDirectory.files.add(
                    MyFile(
                        size = line.substring(0, spaceIndex).toInt(),
                        name = line.substring(spaceIndex + 1)
                    )
                )
            }
        }

        val availableSpace = 70000000 - root.totalSize()
        val spaceToFree = 30000000 - availableSpace

        val listOfDirectorySizes = mutableListOf<Int>()
        root.listDirectorySizes(listOfDirectorySizes)

        return listOfDirectorySizes.sorted().first { it > spaceToFree }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}

data class DirectoryNode(
    val value: String,
    val parent: DirectoryNode? = null,
    var files: MutableList<MyFile> = mutableListOf(),
    ) {
    var directories: MutableList<DirectoryNode> = mutableListOf()
}

data class MyFile(
    val size: Int,
    val name: String,
)

fun DirectoryNode.totalSize(): Int {
    return this.files.sumOf { it.size } + this.directories.sumOf { it.totalSize() }
}

fun DirectoryNode.sumOfDirectoriesUnderLimit(limit: Int): Int {
    val size = this.totalSize()
    return (if (size <= limit) size else 0) + this.directories.sumOf { it.sumOfDirectoriesUnderLimit(limit) }
}

fun DirectoryNode.listDirectorySizes(list: MutableList<Int>, includeThis: Boolean = true) {
    if (includeThis) {
        list.add(this.totalSize())
    }
    this.directories.forEach {
        list.add(it.totalSize())
        it.listDirectorySizes(list, false)
    }
}