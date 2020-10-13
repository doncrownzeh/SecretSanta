package mail

import javax.mail.internet.MimeMessage
import javax.mail.{Message, Session, Transport}
import randomize.ParticipantsPairing

class ComposedMail(pairing: ParticipantsPairing) {

  private val header = s"Secret Santa draw"
  private val body =
    s"""
       |Hi, ${pairing.giver.name}!
       |
       |You will buy a gift for ${pairing.receiver.name}!
    """.stripMargin

  def send(session: Session): Unit = {
   val message = new MimeMessage(session)
    message.setRecipients(Message.RecipientType.TO, pairing.giver.emailAddress)
    message.setSubject(header)
    message.setText(body)
    Transport.send(message)
  }
}

object ComposedMail {
  def apply(pairing: ParticipantsPairing): ComposedMail = {
    new ComposedMail(pairing)
  }
}
