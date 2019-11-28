$classes = Import-CSV ~/dates.csv

function convertDate {
    param (
        $DateText
    )

    switch -regex ($DateText) {
        '^\d+\/\d+\/\d+' { [datetime]::parseexact($DateText, 'd/M/yyyy', $null).toString('yyyy-MM-dd'); break }
        '^\d+\/\d+' { [datetime]::parseexact($DateText, 'M/yyyy', $null).toString('yyyy-MM-dd'); break }
        Default { return "" }
    }
}

$ships = foreach ($class in $classes) {
    # Write-Host $class.ClassID
    $page = Invoke-WebRequest $class.Source
    $header,$rows = $page.ParsedHtml.getElementsByTagName("table") | Where-Object innerText -like "*laid down*" | % children | % children
    $columns = [ordered]@{}
    for ($i = 0; $i -lt @($header.children).count; $i++) {
        switch ($header.children[$i].innerText) {
            'Name' {$columns['Name'] = $i}
            'Laid down' {$columns['Laid down'] = $i}
            'Launched' {$columns['Launched'] = $i}
            'Comp' {$columns['Comm'] = $i}
            'Comm' {$columns['Comm'] = $i}
            'Fate' {$columns['Fate'] = $i}
        }
    }
    foreach ($row in $rows) {
        $ship = New-Object PSObject -Property ([ordered]@{
            ClassID = $class.ClassID
            ShipName = @($row.children)[$columns['Name']].innerText -replace '.+<(.+)>','$1'
            Existance = ""
            Fate = ""
            LaidDown = convertDate @($row.children)[$columns['Laid down']].innerText
            Launched = convertDate @($row.children)[$columns['Launched']].innerText
            Commissioned = convertDate @($row.children)[$columns['Comm']].innerText
            Stricken = @($row.children)[$columns['Fate']].innerText
        })

        if ($ship.Commissioned) {
            $ship.Existance = "Built"
        } else {
            $ship.Existance = "Cancelled"
            $ship.Fate = "Scrapped"
        }
        $ship
    }
}

$ships | Export-CSV ~/ships.csv -NoTypeInformation