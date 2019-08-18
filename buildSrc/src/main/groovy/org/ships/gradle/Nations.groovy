package org.ships.gradle

import groovy.transform.Canonical

class Nations {
    static Nation[] getNations() {
        return [
            ['ahn', 'Austro-Hungarian Navy'],
            ['an', 'Argentinian Navy'],
            ['bn', 'Brazilian Navy'],
            ['cn', 'Chilean Navy'],
            ['fn', 'Finish Navy'],
            ['ijn', 'Imperial Japanese Navy'],
            ['km', 'German Navy'],
            ['mn', 'Marine Nationale'],
            ['rm', 'Regia Marina'],
            ['rn', 'Royal Navy'],
            ['rnn', 'Royal Netherlands Navy'],
            ['rus', 'Russian Navy'],
            ['sn', 'Spanish Navy'],
            ['swn', 'Swidish Navy'],
            ['tn', 'Turkish Navy'],
            ['usn', 'United States Navy'],
        ]
    }
}

@Canonical
class Nation {
    String id
    String name
}