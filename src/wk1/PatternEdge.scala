package wk1

trait PatternEdge {
  
  def label: Char
  def start: PatternNode
  def end: PatternNode
}

case class PatternEdgeImpl(label: Char, start: PatternNode, end: PatternNode) extends PatternEdge{
  
}