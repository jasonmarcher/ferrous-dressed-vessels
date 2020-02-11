package org.ships.gradle

import groovy.transform.Canonical

class Nations {
    static Nation[] getNations() {
        return [
            ['ahn', 'Austro-Hungarian Navy'],
            ['argentina', 'Argentinian Navy'],
            ['brazil', 'Brazilian Navy'],
            ['britain', 'Royal Navy'],
            ['chile', 'Chilean Navy'],
            ['china', 'Chinese Navy'],
            ['denmark', 'Danish Navy'],
            ['finland', 'Finish Navy'],
            ['france', 'Marine Nationale'],
            ['germany', 'German Navy'],
            ['greece', 'Greek Navy'],
            ['italy', 'Regia Marina'],
            ['japan', 'Imperial Japanese Navy'],
            ['netherlands', 'Royal Netherlands Navy'],
            ['norway', 'Norwegian Navy'],
            ['peru', 'Peruvian Navy'],
            ['poland', 'Polish Navy'],
            ['romania', 'Romanian Navy'],
            ['russia', 'Russian Navy'],
            ['spain', 'Spanish Navy'],
            ['sweden', 'Swidish Navy'],
            ['turkey', 'Turkish Navy'],
            ['usa', 'United States Navy'],
        ]
    }
}

@Canonical
class Nation {
    String id
    String name
}