package randomize

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers

class SecretSantaTest extends AnyFeatureSpec with GivenWhenThen with Matchers {

  Feature("Randomizing participants for secret santa") {

    Scenario("Participant can't buy a gift for himself") {
      Given("a list of participants")
      val participants = List(Participant("K", "K"), Participant("G", "G"), Participant("A", "A"), Participant("F", "F"))
      for (1 <- 1 to 100000) {
        When("Randomizing secret santa")
        val randomizedPairings = SecretSanta(participants)
        Then("each of participants has different person randomized")
        randomizedPairings.foreach(pairing => pairing.giver should not equal pairing.receiver)
      }
    }

    Scenario("Each person gives exactly one gift") {
      Given("a list of participants")
      val participants = List(Participant("K", "K"), Participant("G", "G"), Participant("A", "A"), Participant("F", "F"))
      for (1 <- 1 to 100000) {
        When("Randomizing secret santa")
        val randomizedPairings = SecretSanta(participants)
        Then("each of participants is randomized exactly once as giver")
        val givers = randomizedPairings.map(pairing => pairing.giver)
        givers.size shouldBe givers.distinct.size
      }
    }

    Scenario("Each person receives exactly one gift") {
      Given("a list of participants")
      val participants = List(Participant("K", "K"), Participant("G", "G"), Participant("A", "A"), Participant("F", "F"))
      for (1 <- 1 to 100000) {
        When("Randomizing secret santa")
        val randomizedPairings = SecretSanta(participants)
        Then("each of participants is randomized exactly once as receiver")
        val receivers = randomizedPairings.map(pairing => pairing.receiver)
        receivers.size shouldBe receivers.distinct.size
      }
    }
  }
}
