package mail

import javax.mail.internet.MimeMessage
import javax.mail.{Message, Session, Transport}
import randomize.Participant

class ComposedMail(giver: Participant, receiver: Participant) {

  private val header = s"Secret Santa draw"
  private val body =
    s"""
       |Hi, ${giver.name}!
       |
       |You will buy a gift for ${receiver.name}!
    """.stripMargin

  def send(session: Session): Unit = {
   val message = new MimeMessage(session)
    message.setRecipients(Message.RecipientType.TO, receiver.emailAddress)
    message.setSubject(header)
    message.setText(body)
    Transport.send(message)
  }
}

object ComposedMail {
  def apply(giver: Participant, receiver: Participant): ComposedMail = {
    new ComposedMail(giver, receiver)
  }
}
