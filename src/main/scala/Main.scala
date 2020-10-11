import javax.mail.Session
import mail.preparation.ComposedMail
import mail.send.GmailProperties
import randomize.{Participant, SecretSanta}

object Main extends App {

  import GmailProperties._
  val session = Session.getInstance(properties, authenticator)

  val participants = List(
    Participant("Participant1", "participant1@gmail.com"),
    Participant("Participant2", "participant2@gmail.com"),
    Participant("Participant3", "participant3@gmail.com"),
    Participant("Participant4", "participant4@gmail.com"))

  SecretSanta.randomizeParticipants(participants)
    .map(result => ComposedMail(result._1, result._2))
    .foreach(mail => mail.send(session))
}