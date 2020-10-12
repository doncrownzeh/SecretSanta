package randomize

import org.scalatest.{BeforeAndAfter, GivenWhenThen, FeatureSpec}

class SecretSantaTest extends FeatureSpec with GivenWhenThen with BeforeAndAfter {

  feature("Randomizing participants for secret santa") {

    scenario("Participant can't buy a gift for himself") {
      Given("a list of participants")
      val participants = List(Participant("K", "K"), Participant("G", "G"), Participant("A", "A"), Participant("F", "F"))
      for (1 <- 1 to 100000) {
        When("Randomizing secret santa")
        val randomizedParticipants = SecretSanta.randomizeParticipants(participants)
        Then("each of participants has different person randomized")
        randomizedParticipants.foreach(randomized => assert(randomized._1 != randomized._2))
      }
    }

    scenario("Each person gives exactly one gift") {
      Given("a list of participants")
      val participants = List(Participant("K", "K"), Participant("G", "G"), Participant("A", "A"), Participant("F", "F"))
      for (1 <- 1 to 100000) {
        When("Randomizing secret santa")
        val randomizedParticipants = SecretSanta.randomizeParticipants(participants)
        Then("each of participants is randomized exactly once as giver")
        val givers = randomizedParticipants.map(randomized => randomized._1)
        assert(givers.size == givers.distinct.size)
      }
    }

    scenario("Each person receives exactly one gift") {
      Given("a list of participants")
      val participants = List(Participant("K", "K"), Participant("G", "G"), Participant("A", "A"), Participant("F", "F"))
      for (1 <- 1 to 100000) {
        When("Randomizing secret santa")
        val randomizedParticipants = SecretSanta.randomizeParticipants(participants)
        Then("each of participants is randomized exactly once as receiver")
        val receivers = randomizedParticipants.map(randomized => randomized._2)
        assert(receivers.size == receivers.distinct.size)
      }
    }
  }
}
