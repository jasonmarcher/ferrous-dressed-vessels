package org.ships.gradle

import groovy.json.JsonSlurper
import java.text.SimpleDateFormat
import org.apache.commons.csv.CSVPrinter
import org.gradle.api.file.FileTree

class Armory {
    def guns = [:]

    Armory(FileTree weaponFiles) {
        def jsonSlurper = new JsonSlurper()

        weaponFiles.forEach { weaponFile ->
            // TODO: Other weapon systems
            def gun = new Gun(jsonSlurper.parseText(weaponFile.text))
            guns[gun.GunID] = gun
        }
    }

    void printRecords(CSVPrinter printer) {
        printer.printRecord('GunID','DateOfDesign','GunCaliber','GunLength','GunWeight','RateOfFire','PrimaryShellWeight','PrimaryShellVelocity')

        guns.values().forEach { gun ->
            printer.print(gun.GunID)
            printer.print((new SimpleDateFormat('yyyy-mm-dd')).format(gun.DateOfDesign))
            printer.print(gun.GunCaliber)
            printer.print(gun.GunLength)
            printer.print(gun.GunWeight)
            printer.print(gun.RateOfFire)
            printer.print(gun.PrimaryShellWeight)
            printer.print(gun.PrimaryShellVelocity)
            printer.println()
        }
    }
}