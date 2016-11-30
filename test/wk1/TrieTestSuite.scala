package wk1

import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class TrieTestSuite extends FunSuite {

  /**
   * This class is a test suite for the methods in object FunSets. To run
   * the test suite, you can either:
   *  - run the "test" command in the SBT console
   *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
   */

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  //   test("string take") {
  //     val message = "hello, world"
  //     assert(message.take(5) == "hello")
  //   }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }

  test("Get leaves and patterns  ") {

    val trie: PatternTrie = new PatternTrieImpl
    trie.addPattern("banana")
    trie.addPattern("bandana")
    trie.addPattern("nananana")

    val patterns = trie.getPatterns();
    assert(patterns.size == 3)
    val leaves = trie.getLeaves()
    assert(leaves.size == 3)

    assert(leaves.size == patterns.size)
    trie.addPattern("ban")
    val matches = trie.prefixTrieMatching("bananana")
    
    assert(matches.exists(_.equals("ban")))
    assert(matches.exists(_.equals("banana")))
    assert(matches.size == 2)
  }
}