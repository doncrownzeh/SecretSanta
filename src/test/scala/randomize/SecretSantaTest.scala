package randomize

import org.scalatest.{FeatureSpec, GivenWhenThen}

class SecretSantaTest extends FeatureSpec with GivenWhenThen {

  feature("Randomizing participants for secret santa") {

    scenario("Participant can't buy a gift for himself") {
      Given("a list of participants")
      val participants = List(Participant("K", "K"), Participant("G", "G"), Participant("A", "A"), Participant("F", "F"))
      for (1 <- 1 to 100000) {
        When("Randomizing secret santa")
        val randomizedPairings = SecretSanta.randomizePairings(participants)
        Then("each of participants has different person randomized")
        randomizedPairings.foreach(pairing => assert(pairing.giver != pairing.receiver))
      }
    }

    scenario("Each person gives exactly one gift") {
      Given("a list of participants")
      val participants = List(Participant("K", "K"), Participant("G", "G"), Participant("A", "A"), Participant("F", "F"))
      for (1 <- 1 to 100000) {
        When("Randomizing secret santa")
        val randomizedPairings = SecretSanta.randomizePairings(participants)
        Then("each of participants is randomized exactly once as giver")
        val givers = randomizedPairings.map(pairing => pairing.giver)
        assert(givers.size == givers.distinct.size)
      }
    }

    scenario("Each person receives exactly one gift") {
      Given("a list of participants")
      val participants = List(Participant("K", "K"), Participant("G", "G"), Participant("A", "A"), Participant("F", "F"))
      for (1 <- 1 to 100000) {
        When("Randomizing secret santa")
        val randomizedPairings = SecretSanta.randomizePairings(participants)
        Then("each of participants is randomized exactly once as receiver")
        val receivers = randomizedPairings.map(pairing => pairing.receiver)
        assert(receivers.size == receivers.distinct.size)
      }
    }
  }
}
