
import scala.collection.mutable

case class User(name: String, id: Int)

class InvertedIndex(val userMap: mutable.Map[String, User]) {

}
