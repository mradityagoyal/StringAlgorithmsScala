package wk1

import scala.collection.mutable.ListBuffer

trait PatternNode {
  def patternToRoot: String
  def outgoingEdges: ListBuffer[PatternEdge]
  def isLeaf(): Boolean = outgoingEdges.isEmpty
}

case class PatternNodeImpl(patternToRoot: String, outgoingEdges : ListBuffer[PatternEdge]) extends PatternNode{
}