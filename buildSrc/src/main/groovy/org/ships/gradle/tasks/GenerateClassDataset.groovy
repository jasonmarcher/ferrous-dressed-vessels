package org.ships.gradle.tasks

import groovy.json.JsonSlurper
import java.io.FileWriter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.ships.gradle.Nation
import org.ships.gradle.Nations
import org.ships.gradle.ShipClass

class GenerateClassDataset extends DefaultTask {
    @TaskAction
    public void createDataset() {
        project.file(project.buildDir).mkdirs()

        def jsonSlurper = new JsonSlurper()
        def printer = new CSVPrinter(new FileWriter("$project.buildDir/classes.csv"), CSVFormat.EXCEL)
        printer.printRecord('ClassID','ClassName','Nation','Type','SubType','ClassLaidDown','StandardDisplacement','ShipHorsePower',
            'Speed','Range','Caliber','Broadside','APShellSize','ArmorBelt','ArmorBeltAngle')

        def classFiles = project.fileTree("res/classes")
        classFiles.forEach { classFile ->
            def shipClass = new ShipClass(jsonSlurper.parseText(classFile.text))
            shipClass.printRecord(printer)
        }
        printer.close(true)
    }
}