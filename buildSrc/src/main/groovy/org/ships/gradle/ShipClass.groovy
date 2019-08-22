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

    int Caliber
    int Broadside
    float APShellSize

    int ArmorBelt
    int ArmorBeltAngle

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

            Caliber = json.Caliber
            Broadside = json.Broadside
            APShellSize = json.APShellSize

            ArmorBelt = json.ArmorBelt
            ArmorBeltAngle = json.ArmorBeltAngle
        } catch (Exception ex) {
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

        printer.print(Caliber)
        printer.print(Broadside)
        printer.print(APShellSize)

        printer.print(ArmorBelt)
        printer.print(ArmorBeltAngle)

        printer.println()
    }
}