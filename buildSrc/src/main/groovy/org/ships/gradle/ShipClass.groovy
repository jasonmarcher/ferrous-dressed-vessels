package org.ships.gradle

import org.apache.commons.csv.CSVPrinter
import java.text.SimpleDateFormat

class ShipClass {
    String ClassID
    String ClassName
    String Nation

    String Type
    String SubType
    String Existance
    Date ClassLaidDown
    int StandardDisplacement

    int ShipHorsePower
    float Speed
    int Range

    Gun MainGun
    int MainBroadside

    int ArmorBelt
    int ArmorBeltAngle

    ShipClass(Object json, Armory armory) {
        try {
            ClassID = json.ClassID
            ClassName = json.ClassName
            Nation = json.Nation

            Type = json.Type
            SubType = json.SubType
            Existance = json.Existance
            ClassLaidDown = (new SimpleDateFormat('yyyy-mm-dd')).parse(json.ClassLaidDown)
            StandardDisplacement = json.StandardDisplacement

            ShipHorsePower = json.ShipHorsePower
            Speed = json.Speed
            Range = json.Range

            if (json.MainGun) {
                MainGun = armory.guns[json.MainGun]
                if (MainGun == null) {println(ClassID)}
                MainBroadside = json.MainBroadside
            } else {
                MainGun = new Gun("$json.ClassID-$json.Caliber".toString(), (new SimpleDateFormat('yyyy-mm-dd')).parse(json.ClassLaidDown), json.Caliber, json.APShellSize)
                MainBroadside = json.Broadside
            }

            ArmorBelt = json.ArmorBelt
            ArmorBeltAngle = json.ArmorBeltAngle
        } catch (Exception ex) {
            println(ClassID)
            throw ex
        }
    }

    void printRecord(CSVPrinter printer) {
        printer.print(ClassID)
        printer.print(ClassName)
        printer.print(Nation)

        printer.print(Type)
        printer.print(SubType)
        printer.print(Existance)
        printer.print((new SimpleDateFormat('yyyy-mm-dd')).format(ClassLaidDown))
        printer.print(StandardDisplacement)

        printer.print(ShipHorsePower)
        printer.print(Speed)
        printer.print(Range)

        // TODO: Main gun
        printer.print(MainGun.gunCaliber)
        printer.print(MainBroadside)
        printer.print(MainGun.PrimaryShellWeight)

        // TODO: Secondary gun

        printer.print(ArmorBelt)
        printer.print(ArmorBeltAngle)

        printer.println()
    }
}