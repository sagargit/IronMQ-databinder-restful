package model

import model.IronQueueType.IronQueueType
import org.joda.time.DateTime
import play.api.libs.json.{Json, JsObject}

/**
 * Created by sagarhp on 3/25/16.
 */
case class Subscriber(
                       name: Option[String],
                       url: Option[String],
                       headers: Option[JsObject] = None
                       )

case class PushQueue(
                      subscribers: Option[Seq[Subscriber]],
                      retries: Option[Int] = None,
                      retries_delay: Option[Int] = None,
                      error_queue: Option[String] = None
                      )

case class AddIronQueue(
                      message_timeout: Option[Int] = None,
                      message_expiration: Option[Int] = None,
                      `type`:Option[IronQueueType],
                      push: Option[PushQueue] = None
                      )

case class ReadIronQueue(
                      project_id: Option[Int],
                      name: Option[String],
                      size: Option[Int],
                      total_messages: Option[Int],
                      message_timeout: Option[Int] = None,
                      message_expiration: Option[Int] = None,
                      `type`:Option[IronQueueType],
                      push: Option[PushQueue] = None
                         )



case class CreateQueue(
                      queue: Option[AddIronQueue]
                        )

case class GetQueueInfo(
                      queue: Option[ReadIronQueue]
                         )

case class UpdateQueue(
                        queue: Option[AddIronQueue] // queue type is static
                        )

case class QueueName(
                    name: Option[String]
                      )

case class ListQueues(
                       queues: Seq[QueueName]
                       )

case class AddOrUpdateSubscriber(
                                subscribers: Option[Seq[Subscriber]]
                                  )

case class ReplaceSubscriber(
                              subscribers: Option[Seq[Subscriber]]
                              )

case class Message(
                  body: Option[String],
                  delay: Option[Int],
                  push_headers: Option[JsObject]
                    )

case class PostMessages(
                       messages: Option[Seq[Message]]
                         )

case class PostMessageResult(
                            ids: Option[Seq[String]],
                            msg: Option[String]
                              )

case class MessagePushStatus(
                  name: Option[String],
                  retries_remaining: Option[Int],
                  tries: Option[Int],
                  status_code: Option[Int],
                  url: Option[String],
                  last_try_at: Option[DateTime]
                              )

case class GetMessagePushStatusResult(
                                    subscribers: Option[MessagePushStatus]
                              )

case class MessageById(
                  id: Option[Int],
                  body: Option[String],
                  reserved_count: Option[Int]
)

case class GetMessageByIdResult(
                  message: Option[MessageById]
                           )

case class PeekMessages(
                       messages: Option[MessageById]
                         )

case class UpdateOrDeleteResult(
                               msg: Option[String]
                                 )

object IronQueue{
  implicit val subscriberFormat = Json.format[Subscriber]
  implicit val pushQueueFormat = Json.format[PushQueue]
  implicit val addQueueFormat = Json.format[AddIronQueue]
  implicit val readQueueFormat = Json.format[ReadIronQueue]
  implicit val createQueueFormat = Json.format[CreateQueue]
  implicit val getQueueFormat = Json.format[GetQueueInfo]
  implicit val updateQueue = Json.format[UpdateQueue]
  implicit val queueNameFormat = Json.format[QueueName]
  implicit val listQueueFormat = Json.format[ListQueues]
  implicit val addOrUpdateSubscriber = Json.format[AddOrUpdateSubscriber]
  implicit val replaceSubscriber = Json.format[ReplaceSubscriber]
  implicit val message = Json.format[Message]
  implicit val messagePushStatusFormat = Json.format[MessagePushStatus]
  implicit val messagePushStatusResultFormat = Json.format[GetMessagePushStatusResult]
  implicit val postMessageFormat = Json.format[PostMessages]
  implicit val postMessageResultFormat = Json.format[PostMessageResult]
  implicit val messageByIdFormat = Json.format[MessageById]
  implicit val getMessageByIdResult = Json.format[GetMessageByIdResult]
  implicit val peekMessagesFormat = Json.format[PeekMessages]
  implicit val updateOrDeleteFormat = Json.format[UpdateOrDeleteResult]
}