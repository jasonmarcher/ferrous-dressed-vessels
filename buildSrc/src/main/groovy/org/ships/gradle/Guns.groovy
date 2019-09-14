package org.ships.gradle

import org.apache.commons.csv.CSVPrinter
import java.text.SimpleDateFormat

class Gun {
    String GunID

    Date DateOfDesign

    int GunCaliber
    int GunLength
    int GunWeight
    float RateOfFire

    int PrimaryShellWeight
    int PrimaryShellVelocity

    Gun(Object json) {
        try {
            GunID = json.GunID

            DateOfDesign = (new SimpleDateFormat('yyyy-mm-dd')).parse(json.DateOfDesign)

            GunCaliber = json.GunCaliber
            GunLength = json.GunLength
            GunWeight = json.GunWeight
            RateOfFire = json.RateOfFire

            PrimaryShellWeight = json.PrimaryShellWeight
            PrimaryShellVelocity = json.PrimaryShellVelocity
        } catch (Exception ex) {
            throw ex
        }
    }

    Gun(String gunID, Date dateOfDesign, int caliber, BigDecimal primaryShellWeight) {
        GunID = gunID

        DateOfDesign = dateOfDesign

        GunCaliber = caliber
        PrimaryShellWeight = primaryShellWeight
    }
}