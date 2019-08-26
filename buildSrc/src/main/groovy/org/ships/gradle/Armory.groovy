package org.ships.gradle

import groovy.json.JsonSlurper
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
}