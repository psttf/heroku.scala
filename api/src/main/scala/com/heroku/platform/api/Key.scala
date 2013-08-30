package com.heroku.platform.api

import com.heroku.platform.api.Request._
import com.heroku.platform.api.Key.CreateKeyBody

object Key {
  case class CreateKeyBody(public_key: String)

  case class Create(publicKey: String, headers: Map[String, String] = Map.empty) extends RequestWithBody[CreateKeyBody, Key] {
    val expect: Set[Int] = expect201
    val endpoint: String = "/account/keys"
    val method: String = POST
    val body = CreateKeyBody(publicKey)
  }

  case class List(range: Option[String] = None, headers: Map[String, String] = Map.empty) extends ListRequest[Key] {
    val endpoint: String = "/account/keys"
    val method: String = GET

    def nextRequest(nextRange: String): ListRequest[Key] = this.copy(range = Some(nextRange))
  }

  case class Info(keyId: String, headers: Map[String, String] = Map.empty) extends Request[Key] {
    val endpoint: String = s"/account/keys/$keyId"
    val method: String = GET
    val expect = expect200
  }

  case class Delete(keyId: String, headers: Map[String, String] = Map.empty) extends Request[Key] {
    val endpoint: String = s"/account/keys/$keyId"
    val method: String = DELETE
    val expect = expect200
  }
}

case class Key(created_at: String, email: String, fingerprint: String, id: String, public_key: String)

trait KeyResponseJson {
  implicit def keyFromJson: FromJson[Key]
}

trait KeyRequestJson {
  implicit def createKeyBodyToJson: ToJson[CreateKeyBody]
}