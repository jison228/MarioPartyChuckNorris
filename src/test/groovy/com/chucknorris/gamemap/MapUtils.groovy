package com.chucknorris.gamemap

import com.chucknorris.gamemap.initiallizer.file.reader.MapFileReadable
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader
import com.chucknorris.player.Player

class MapUtils {
    static GameMap buildGameMapThroughCSVAndInitiallizePositions(String gameMapFileName, List<Player> playerList) {
        MapFileReadable mapFileReader = new MapFileCSVReader(gameMapFileName)

        GameMap gameMap = mapFileReader.buildGameMap()

        gameMap.initializePlayers(playerList)

        return gameMap
    }
}
