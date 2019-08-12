package org.ships.gradle.tasks

import groovy.json.JsonSlurper
import java.io.FileWriter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.ships.gradle.Nation
import org.ships.gradle.Nations

class GenerateClassDataset extends DefaultTask {
    @TaskAction
    public void createDataset() {
        project.file(project.buildDir).mkdirs()

        def jsonSlurper = new JsonSlurper()
        def printer = new CSVPrinter(new FileWriter("$project.buildDir/classes.csv"), CSVFormat.EXCEL)
        printer.printRecord('ClassID','ClassName','Type','SubType','ClassLaidDown','StandardDisplacement','ShipHorsePower',
            'Speed','Range','Caliber','Broadside','APShellSize','ArmorBelt','ArmorBeltAngle','Nation')

        Nations.nations.each { Nation nation ->
            // println nation
            def classFiles = project.fileTree("res/classes/$nation.id")
            classFiles.forEach { classFile ->
                def object = jsonSlurper.parseText(classFile.text)
                // println object.getClass()
                object.put('Nation', nation.id)
                printer.printRecord(object.values())
            }
        }
        printer.close(true)
    }
}