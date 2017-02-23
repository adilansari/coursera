
import scala.collection.mutable

case class User(name: String, id: Int)

class InvertedIndex(val userMap: mutable.Map[String, User]) {

  def tokenizeName(name: String): Seq[String] = {
    name.split(" ").map(_.toLowerCase)
  }

  def add(term: String, user: User): Unit = {
    userMap += term -> user
  }

  def add(user: User): Unit = {
    tokenizeName(user.name).foreach {
      term => add(term, user)
    }
  }
}
