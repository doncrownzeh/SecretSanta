package randomize

import scala.annotation.tailrec
import scala.util.Random

object SecretSanta {
  def randomizePairings(participants: List[Participant]): List[ParticipantsPairing] = {
    val givers = participants
    val receivers = Random.shuffle(participants)

    @tailrec
    def randomize(givers: List[Participant], receivers: List[Participant], pairings: List[ParticipantsPairing]): List[ParticipantsPairing] = {
      givers match {
        case Nil | _ :: Nil => pairings
        case firstGiver :: secondGiver :: Nil =>
          if (firstGiver != receivers.head && secondGiver != receivers.tail.head)
            pairings :+ ParticipantsPairing(firstGiver, receivers.head) :+ ParticipantsPairing(secondGiver, receivers.tail.head)
          else pairings :+ ParticipantsPairing(firstGiver, receivers.tail.head) :+ ParticipantsPairing(secondGiver, receivers.head)
        case head :: tail =>
          val receiver = receivers.filter(_ != head).head
          val newPairings = pairings :+ ParticipantsPairing(head, receiver)
          randomize(tail, receivers.filter(_ != receiver), newPairings)
      }
    }

    randomize(givers, receivers, List.empty)
  }
}
