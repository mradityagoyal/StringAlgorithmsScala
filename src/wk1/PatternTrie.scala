package wk1

import scala.collection.mutable.ListBuffer

trait PatternTrie {

  def root: PatternNode

  def addPattern(pattern: String): Unit

  def getPatterns(): List[String]

  def getLeaves(): List[PatternNode]

  def prefixTrieMatching(text: String): List[String]

}

class PatternTrieImpl extends PatternTrie {

  val root: PatternNode = PatternNodeImpl("", new ListBuffer())
  val END_SUFFIX: Char = '$'

  /**
   * adds a string pattern to the trie.
   */
  def addPattern(pattern: String): Unit = {
    var currentNode = root;
    //for each char in the pattern. 
    for (currentSymbol <- pattern + END_SUFFIX) {
      //if there is an outgoing edge from currentNode with label currentSymbol
      if (currentNode.outgoingEdges.exists(_.label == currentSymbol)) {
        //ending node of edge originating from currentNode ,with label = currentSymbol
        val newCurrent = currentNode.outgoingEdges.filter(_.label == currentSymbol).toList.head.end
        currentNode = newCurrent
      } else {
        //add a new node to Trie. 
        val patternToRoot = currentNode.patternToRoot + currentSymbol
        val newNode = PatternNodeImpl(patternToRoot, new ListBuffer())
        //add a new Edge from currentNode to newNode with label currentSymbol. 
        val newEdge = PatternEdgeImpl(currentSymbol, currentNode, newNode)
        currentNode.outgoingEdges += newEdge
        //currentNode <- newNode
        currentNode = newNode
      }
    }

  }

  /**
   * returns a list of patterns in trie.
   */
  @Override
  def getPatterns(): List[String] = {
    getLeaves().map(_.patternToRoot)
  }

  @Override
  def getLeaves(): List[PatternNode] = {
    def traversalHelper(current: PatternNode): List[PatternNode] = {
      if (current.isLeaf()) {
        List(current)
      } else {
        current.outgoingEdges.toList.map(_.end).flatMap { x => traversalHelper(x) }
      }
    }
    traversalHelper(root)
  }

  def prefixTrieMatching(text: String): List[String] = {

    def helper(subText: String, node: PatternNode): List[String] = {
      //if is connected to a $ edge. 
      if (isConnectedToLeafEdge(node)) {
        //connected to leaf and also has link to next match.
        if (node.outgoingEdges.exists(_.label == subText.head)) {
          node.patternToRoot :: helper(subText.tail, node.outgoingEdges.filter(_.label == subText.head).head.end)
        } else {
          List(node.patternToRoot)
        }
      } else if (node.outgoingEdges.exists(_.label == subText.head)) {
        helper(subText.tail, node.outgoingEdges.filter(_.label == subText.head).head.end)
      } else {
        List.empty
      }
    }
    helper(text, root)
  }

  def isConnectedToLeafEdge(node: PatternNode): Boolean = node.outgoingEdges.exists(_.label == END_SUFFIX)

}