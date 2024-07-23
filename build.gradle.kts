plugins {
    kotlin("jvm") version "1.9.23"
}

group = "cz.lukynka"
version = "0.1"

repositories {
    mavenCentral()
    maven {
        name = "devOS"
        url = uri("https://mvn.devos.one/releases")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jline:jline:3.26.3")
    implementation("com.varabyte.kotter:kotter-jvm:1.1.2")
    implementation("cz.lukynka:kotlin-bindables:1.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

val terminalProcessId = project.objects.property(Long::class.java)

tasks.register<Exec>("runCommandInExternalTerminal") {
    dependsOn
    group = "custom"
    description = "Run PowerShell commands from cmd.txt in a new terminal."

    dependsOn("build")

    doFirst {
        ProcessHandle.allProcesses().forEach { process ->
            val commandLine = process.info().command()
            if(commandLine.isPresent && commandLine.get().contains("Terminal")) {
                process.destroy()
            }
        }

        workingDir("${project.projectDir}/build/classes/kotlin/main")

        val javaHome = "C:\\Users\\LukynkaCZE\\.jdks\\corretto-21.0.3\\bin\\java.exe"
        val classpath = "\"E:\\Coding\\Kotlin\\lazy-kotlin-tui\\build\\classes\\kotlin\\main;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\com.varabyte.kotter\\kotter-jvm\\1.1.2\\4d6487e63ebb35e33e2880d7257161630438c67b\\kotter-jvm-1.1.2.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\org.jetbrains.kotlin\\kotlin-stdlib\\1.9.23\\dbaadea1f5e68f790d242a91a38355a83ec38747\\kotlin-stdlib-1.9.23.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\org.jline\\jline\\3.26.3\\65293c6a40adaff7b894d5bf1bae9a8fba27650f\\jline-3.26.3.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\org.jetbrains.kotlin\\kotlin-stdlib-jdk8\\1.8.0\\ed04f49e186a116753ad70d34f0ac2925d1d8020\\kotlin-stdlib-jdk8-1.8.0.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\org.jetbrains\\annotations\\13.0\\919f0dfe192fb4e063e7dacadee7f8bb9a2672a9\\annotations-13.0.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\org.jetbrains.kotlin\\kotlin-stdlib-jdk7\\1.8.0\\3c91271347f678c239607abb676d4032a7898427\\kotlin-stdlib-jdk7-1.8.0.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\org.jline\\jline-terminal-jansi\\3.21.0\\27086834f4d9951f9c2c3b299782e114b56fcf51\\jline-terminal-jansi-3.21.0.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\org.jline\\jline-terminal\\3.21.0\\47579f05a0002f9c561cb10dcf2f744976c49ac\\jline-terminal-3.21.0.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\net.jcip\\jcip-annotations\\1.0\\afba4942caaeaf46aab0b976afd57cc7c181467e\\jcip-annotations-1.0.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\org.jetbrains.kotlinx\\kotlinx-coroutines-core-jvm\\1.6.4\\2c997cd1c0ef33f3e751d3831929aeff1390cb30\\kotlinx-coroutines-core-jvm-1.6.4.jar;C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\org.fusesource.jansi\\jansi\\2.4.0\\321c614f85f1dea6bb08c1817c60d53b7f3552fd\\jansi-2.4.0.jar\";C:\\Users\\LukynkaCZE\\scoop\\apps\\gradle\\current\\.gradle\\caches\\modules-2\\files-2.1\\cz.lukynka\\kotlin-bindables\\1.1\\4da5d095cb331e43b5be7dce6241cf610ee09f5e\\kotlin-bindables-1.1.jar;"

        val command = "$javaHome -cp '$classpath' cz.lukynka.MainKt"

        commandLine("cmd.exe", "/c", "start", "wt.exe", "new-tab",
            "PowerShell", "-NoExit", "-Command", "\"${command.replace(";", "\\;")}\"")
    }
}