package model

import dispatch._
import play.api.libs.json.{Json, JsValue}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by rew3 on 3/9/16.
 */

object IronMQ {

  val project_id  = "56dfe32eac66fa000600013d"
  val token = "OAuth " + "smVmR3bdNdBom3svq1wL"
  val host = "https://mq-aws-eu-west-1-1.iron.io" + s"/3/projects/$project_id"+"/queues/"

  def createQueue(queueName: String,json: JsValue): Future[Either[Throwable,String]] = {
    val jsonBody = Json.stringify(json)
    val request = url(host + queueName)
    val req = request.PUT.setBody(jsonBody).setHeader("Content-Type","application/json").setHeader("Authorization",token)
    Http(req OK as.String).either
  }

  def getQueueInfo(queueName: String): Future[Either[Throwable,String]] = {
    val request = url(host + queueName)
    val req = request.GET.setHeader("Authorization",token)
    val result = Http(req OK as.String).either
    result
  }

  def deleteQueue(queueName: String): Future[Either[Throwable,String]] = {
    val request = url(host + queueName)
    val req = request.DELETE.setHeader("Authorization",token)
    val result = Http(req OK as.String).either
    result
  }

  def listQueues(per_page:Int = 30,previous: String = "",prefix: String = ""): Future[Either[Throwable,String]] = {
    val request = url(host.stripSuffix("/"))
    val req = request.GET.setHeader("Authorization",token).addQueryParameter("per_page","30")
    val result = Http(req OK as.String).either
    result
  }

  def updateQueue(queueName: String,json: JsValue): Future[Either[Throwable,String]] = {
    val jsonBody = Json.stringify(json)
    val request = url(host + queueName)
    val req = request.PATCH.setBody(jsonBody).setHeader("Content-Type","application/json").setHeader("Authorization",token)
    Http(req OK as.String).either
  }


  def postMessages(queueName: String,json: JsValue): Future[Either[Throwable,String]] = {
    val jsonBody = Json.stringify(json)
    val request = url(host + queueName + "/messages")
    val req = request.POST.setBody(jsonBody).setHeader("Content-Type","application/json").setHeader("Authorization",token)
    Http(req OK as.String).either
  }

  def getPushStatus(queueName: String,messageId: String): Future[Either[Throwable,String]] = {
    val request = url(host + queueName + s"/messages/$messageId/subscribers")
    val req = request.GET.setHeader("Authorization",token)
    val result = Http(req OK as.String).either
    result
  }

  def peekMessages(queueName: String): Future[Either[Throwable,String]] = {
    val request = url(host + queueName + s"/messages")
    val req = request.GET.setHeader("Authorization",token)
    val result = Http(req OK as.String).either
    result
  }

  def deleteMessageById(queueName: String,messageId: String): Future[Either[Throwable,String]] = {
    val request = url(host + queueName + s"/messages/$messageId")
    val req = request.DELETE.setHeader("Authorization",token)
    val result = Http(req OK as.String).either
    result
  }

  def deleteAllMessages(queueName: String): Future[Either[Throwable,String]] = {
    val request = url(host + queueName + s"/messages")
    val req = request.DELETE.setHeader("Authorization",token)
    val result = Http(req OK as.String).either
    result
  }

  def addOrUpdateSubscribers(queueName: String,json: JsValue): Future[Either[Throwable,String]] = {
    val jsonBody = Json.stringify(json)
    val request = url(host + queueName + "/subscribers")
    val req = request.POST.setBody(jsonBody).setHeader("Content-Type","application/json").setHeader("Authorization",token)
    Http(req OK as.String).either
  }

  def replaceSubscribers(queueName: String,json: JsValue): Future[Either[Throwable,String]] = {
    val jsonBody = Json.stringify(json)
    val request = url(host + queueName + "/subscribers")
    val req = request.PUT.setBody(jsonBody).setHeader("Content-Type","application/json").setHeader("Authorization",token)
    Http(req OK as.String).either
  }

  def deleteSubscriber(queueName: String,json: JsValue): Future[Either[Throwable,String]] = {
    val jsonBody = Json.stringify(json)
    val request = url(host + queueName + "/subscribers")
    val req = request.DELETE.setBody(jsonBody).setHeader("Content-Type","application/json").setHeader("Authorization",token)
    Http(req OK as.String).either
  }
}
