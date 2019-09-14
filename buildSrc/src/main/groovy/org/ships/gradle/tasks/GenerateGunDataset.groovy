package org.ships.gradle.tasks

import java.io.FileWriter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.ships.gradle.Armory

class GenerateGunDataset extends DefaultTask {
    @TaskAction
    public void createDataset() {
        project.file(project.buildDir).mkdirs()

        def printer = new CSVPrinter(new FileWriter("$project.buildDir/guns.csv"), CSVFormat.EXCEL)

        Armory armory = new Armory(project.fileTree("res/weapons"))
        armory.printRecords(printer)

        printer.close(true)
    }
}