package randomize

import scala.annotation.tailrec
import scala.util.Random

object SecretSanta {
  def randomizeParticipants(participants: List[Participant]): List[(Participant, Participant)] = {
    val availableGivers = participants
    val availableReceivers = Random.shuffle(participants)

    @tailrec
    def randomize(givers: List[Participant], receivers: List[Participant], results: List[(Participant, Participant)]): List[(Participant, Participant)] = {
      if (givers.isEmpty) results
      else givers match {
        case firstGiver :: secondGiver :: Nil =>
          if (firstGiver != receivers.head && secondGiver != receivers(1)) results :+ (firstGiver, receivers.head) :+ (secondGiver, receivers.tail.head)
          else results :+ (firstGiver, receivers.tail.head) :+ (secondGiver, receivers.head)
        case head :: tail =>
            val receiver = receivers.filter(_ != head).head
            val res = results :+ (head, receiver)
            randomize(tail, receivers.filter(_ != receiver) , res)
      }
    }
    randomize(availableGivers, availableReceivers, List.empty)
  }
}
