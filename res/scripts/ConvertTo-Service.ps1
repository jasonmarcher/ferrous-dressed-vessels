$ships = Import-CSV "~/ships - ships-import.csv"
$classes = Import-CSV $PSScriptRoot/../prebuilt/classes.csv

$ClassTable = @{}
foreach ($class in $classes) {
    $ClassTable[$class.ClassID] = $class.Nation
}

$service = foreach ($ship in $ships) {
    if (-NOT $ship.LaidDown) { throw "Ship $($ship.HullID) is missing a LaidDown date" }

    # Construction
    $End, $Fate = if ($ship.Launched) {
            $ship.Launched
            "Fitting"
        } elseif ($ship.Commissioned) {
            $ship.Commissioned
            "Active"
        } elseif ($ship.Stricken) {
            $ship.Stricken
            $ship.Fate
        } else {
            ""
            ""
        }
    if ($End -and (([DateTime]$End) -lt ([DateTime]$ship.LaidDown))) { throw "Ship $($ship.HullID) construction end date is not after LaidDown" }
    if ($ship.LaidDown -ne $End) {
        New-Object PSObject -Property ([ordered]@{
            HullID = $ship.HullID
            ClassID = $ship.ClassID
            FittingID = ""
            ShipName = $ship.ShipName
            Nation = $ClassTable[$ship.ClassID]
            State = "Construction"
            Fate = $Fate
            Start = $ship.LaidDown
            End = $End
        })
    }

    # Fitting
    if ($ship.Launched) {
        $End, $Fate = if ($ship.Commissioned) {
                $ship.Commissioned
                "Active"
            } elseif ($ship.Stricken) {
                $ship.Stricken
                $ship.Fate
            } else {
                ""
                ""
            }
        if ($End -and (([DateTime]$End) -lt ([DateTime]$ship.Launched))) { throw "Ship $($ship.HullID) fitting end date is not after Launch" }
        if ($ship.LaidDown -ne $End) {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                State = "Fitting"
                Fate = $Fate
                Start = $ship.Launched
                End = $End
            })
        }
    }

    # Active
    if ($ship.Commissioned) {
        $End, $Fate = if ($ship.Stricken) {
                $ship.Stricken
                $ship.Fate
            } else {
                ""
                ""
            }
        if ($End -and (([DateTime]$End) -lt ([DateTime]$ship.Commissioned))) { throw "Ship $($ship.HullID) active end date is not after Commissioned" }
        if ($ship.LaidDown -ne $End) {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                State = "Active"
                Fate = $Fate
                Start = $ship.Commissioned
                End = $End
            })
        }
    }
}

$service | Export-Csv $PSScriptRoot/../prebuilt/new-service.csv -NoTypeInformation

$events = foreach ($ship in $ships) {
    switch ($ship.Existance) {
        "Built" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Laid Down"
                Date = $ship.LaidDown
            })
            break
        }
        "Bought" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Bought"
                Date = $ship.LaidDown
            })
            break
        }
        "Cancelled" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Laid Down"
                Date = $ship.LaidDown
            })
            break
        }
        "Captured" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Captured"
                Date = $ship.LaidDown
            })
            break
        }
        "Converted" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Converted"
                Date = $ship.LaidDown
            })
            break
        }
        "Modernized" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Modernized"
                Date = $ship.LaidDown
            })
            break
        }
    }

    # Fitting
    if ($ship.Launched) {
        if ($ship.LaidDown -ne $ship.Launched) {
            switch ($ship.Existance) {
                "Bought" {
                    New-Object PSObject -Property ([ordered]@{
                        HullID = $ship.HullID
                        ClassID = $ship.ClassID
                        FittingID = ""
                        ShipName = $ship.ShipName
                        Nation = $ClassTable[$ship.ClassID]
                        Event = "Launched"
                        Date = $ship.Launched
                    })
                    break
                }
                "Built" {
                    New-Object PSObject -Property ([ordered]@{
                        HullID = $ship.HullID
                        ClassID = $ship.ClassID
                        FittingID = ""
                        ShipName = $ship.ShipName
                        Nation = $ClassTable[$ship.ClassID]
                        Event = "Launched"
                        Date = $ship.Launched
                    })
                    break
                }
                "Cancelled" {
                    New-Object PSObject -Property ([ordered]@{
                        HullID = $ship.HullID
                        ClassID = $ship.ClassID
                        FittingID = ""
                        ShipName = $ship.ShipName
                        Nation = $ClassTable[$ship.ClassID]
                        Event = "Launched"
                        Date = $ship.Launched
                    })
                    break
                }
                "Captured" {
                    New-Object PSObject -Property ([ordered]@{
                        HullID = $ship.HullID
                        ClassID = $ship.ClassID
                        FittingID = ""
                        ShipName = $ship.ShipName
                        Nation = $ClassTable[$ship.ClassID]
                        Event = "Launched"
                        Date = $ship.Launched
                    })
                    break
                }
            }
        }
    }

    # Active
    if ($ship.Commissioned -and ($ship.LaidDown -ne $ship.Commissioned)) {
        New-Object PSObject -Property ([ordered]@{
            HullID = $ship.HullID
            ClassID = $ship.ClassID
            FittingID = ""
            ShipName = $ship.ShipName
            Nation = $ClassTable[$ship.ClassID]
            Event = "Completed"
            Date = $ship.Commissioned
        })
    }

    $End, $Fate = if ($ship.Stricken) {
            $ship.Stricken
            $ship.Fate
        } else {
            ""
            ""
        }

    switch ($Fate) {
        "Captured" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Captured"
                Date = $End
            })
            break
        }
        "Scrapped" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Scrapped"
                Date = $End
            })
            break
        }
        "Sold" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Sold"
                Date = $End
            })
            break
        }
        "Sunk" {
            New-Object PSObject -Property ([ordered]@{
                HullID = $ship.HullID
                ClassID = $ship.ClassID
                FittingID = ""
                ShipName = $ship.ShipName
                Nation = $ClassTable[$ship.ClassID]
                Event = "Sunk"
                Date = $End
            })
            break
        }
    }
}

$events | Export-Csv $PSScriptRoot/../prebuilt/new-events.csv -NoTypeInformation