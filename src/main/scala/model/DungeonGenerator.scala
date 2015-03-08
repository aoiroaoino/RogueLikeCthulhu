package model

import scala.io.Source
import model.param.Panel

trait Generatable {
  type Field

  def make: Field
}

trait GeneratorFromFile extends Generatable {
  type Field = Array[Array[Panel]]

  val fileName: String

  private val replaceTable: Map[String, Panel] = Map(
    "1" -> Panel.Floor,
    "2" -> Panel.Wall,
    "3" -> Panel.Door,
    "4" -> Panel.Way
  )

  private def replaceChar2Panel(str: String): Array[Panel] =
    str.map{(c: Char) => replaceTable.getOrElse(c.toString, Panel.NothingPanel)}.toArray

  def make: Field = {
    val file = getClass.getResourceAsStream(fileName)
    Source.fromInputStream(file).getLines.map(replaceChar2Panel).toArray
  }
}

trait GeneratorRandom extends Generatable {
  type Field = Array[Array[Panel]]

  def make: Field = Array.ofDim[Panel](1, 0)
}

class TestDungeon extends GeneratorFromFile {
  val fileName = "/test_dungeon.dun"
}

class RandomDungeon extends GeneratorRandom {
}


// If you create dungeon, use this object.
object DungeonGenerator {

  def makeTestDungeon = new TestDungeon make

  def makeRandomDungeon = new RandomDungeon make

  implicit class dungeon2String(dungeon: Array[Array[Panel]]) {
    def toAppearance: String = dungeon.map {_.map(_.appearance).mkString}.mkString("\n")
  }
}

