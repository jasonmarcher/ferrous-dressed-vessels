package org.ships.gradle

import java.text.SimpleDateFormat
import org.apache.commons.csv.CSVPrinter

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

    String MainGun
    int MainBroadside
    String SecondaryGun
    int SecondaryBroadside

    int ArmorBelt
    int ArmorBeltAngle
    int ArmorDeck

    ShipClass(Object json) {
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

            MainGun = json.MainGun
            MainBroadside = json.MainBroadside
            SecondaryGun = json.SecondaryGun
            SecondaryBroadside = json.SecondaryBroadside

            ArmorBelt = json.ArmorBelt
            ArmorBeltAngle = json.ArmorBeltAngle

            if (json.ArmorDeck) {
                ArmorDeck = json.ArmorDeck
            } else {
                ArmorDeck = 0
            }
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
        printer.print(MainGun)
        printer.print(MainBroadside)
        printer.print(SecondaryGun)
        printer.print(SecondaryBroadside)

        printer.print(ArmorBelt)
        printer.print(ArmorBeltAngle)
        printer.print(ArmorDeck)

        printer.println()
    }
}