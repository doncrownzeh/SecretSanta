package mail

import java.util.Properties

import javax.mail.{Authenticator, PasswordAuthentication}

object GmailProperties {
  private val HOST = "smtp.gmail.com"
  private val PORT = "587"
  private val USERNAME = "USERNAME"
  private val PASSWORD = "PASSWORD"

  val authenticator: Authenticator = new Authenticator {
    override def getPasswordAuthentication: PasswordAuthentication = {
      new PasswordAuthentication(USERNAME, PASSWORD)
    }
  }
  val properties: Properties =  {
    val properties: Properties = new Properties()
    properties.put("mail.smtp.auth", "true")
    properties.put("mail.smtp.starttls.enable", "true")
    properties.put("mail.smtp.host", HOST)
    properties.put("mail.smtp.port", PORT)
    properties
  }

}
