package controllers

import model.IronMQ
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller {

  def createQueue(name : String) = Action.async(parse.json) { request =>
    val json = request.body
    IronMQ.createQueue(name, json).map {
      k => k.fold(
        left => {
          BadRequest(left.getMessage)
        },
        right => {
          Ok(Json.parse(right))
        }
      )
    }
  }

  def updateQueue(name : String) = Action.async(parse.json) { request =>
    val json = request.body
    IronMQ.updateQueue(name, json).map {
      k => k.fold(
        left => {
          BadRequest(left.getMessage)
        },
        right => {
          Ok(Json.parse(right))
        }
      )
    }
  }

  def getQueueInfo(name: String) = Action.async {
    request =>
      IronMQ.getQueueInfo(name).map {
        k => k.fold(
          left => {
            BadRequest(left.getMessage)
          },
          right => {
            Ok(Json.parse(right))
          }
        )
      }
  }

  def deleteQueue(name: String) = Action.async {
    request =>
      IronMQ.deleteQueue(name).map {
        k => k.fold(
          left => {
            BadRequest(left.getMessage)
          },
          right => {
            Ok(Json.parse(right))
          }
        )
      }
  }

  def listAllQueues = Action.async{
    request =>
      IronMQ.listQueues().map{
      k => k.fold(
      left => {
        BadRequest(left.getMessage)
      },
      right => {
        Ok(Json.parse(right))
      }
      )
      }
  }

  def postMessageToQueue(name : String) = Action.async(parse.json) { request =>
    val json = request.body
    IronMQ.postMessages(name, json).map {
      k => k.fold(
        left => {
          BadRequest(left.getMessage)
        },
        right => {
          Ok(Json.parse(right))
        }
      )
    }
  }

  /**
   *  In the case subscriber with given name exists, it will be updated.
   * @param name name of the queue
   * @return Result
   */

  def addOrUpdateSubscribers(name : String) = Action.async(parse.json) { request =>
    val json = request.body
    IronMQ.addOrUpdateSubscribers(name, json).map {
      k => k.fold(
        left => {
          BadRequest(left.getMessage)
        },
        right => {
          Ok(Json.parse(right))
        }
      )
    }
  }

  def replaceSubscribers(name : String) = Action.async(parse.json) { request =>
    val json = request.body
    IronMQ.replaceSubscribers(name, json).map {
      k => k.fold(
        left => {
          BadRequest(left.getMessage)
        },
        right => {
          Ok(Json.parse(right))
        }
      )
    }
  }

  //TODO the method is not working correctly:
  //TODO scenario: there is a subscriber in the queue named: subscriber_name
  // TODO and the method cannot give desired response when a valid name is given, instead, it gives correct name when invalid name is given
  def deleteSubscribers(name : String) = Action.async(parse.json) { request =>
    val json = request.body
    IronMQ.deleteSubscriber(name, json).map {
      k => k.fold(
        left => {
          BadRequest(left.getMessage)
        },
        right => {
          Ok(Json.parse(right))
        }
      )
    }
  }

  def getPushStatus(name: String, id: String) = Action.async {
    request =>
      IronMQ.getPushStatus(name,id).map {
        k => k.fold(
          left => {
            BadRequest(left.getMessage)
          },
          right => {
            Ok(Json.parse(right))
          }
        )
      }
  }

  def peekMessages(name: String) = Action.async {
    request =>
      IronMQ.peekMessages(name).map {
        k => k.fold(
          left => {
            BadRequest(left.getMessage)
          },
          right => {
            Ok(Json.parse(right))
          }
        )
      }
  }

  def deleteMessageById(name: String, id: String) = Action.async {
    request =>
      IronMQ.deleteMessageById(name,id).map {
        k => k.fold(
          left => {
            BadRequest(left.getMessage)
          },
          right => {
            Ok(Json.parse(right))
          }
        )
      }
  }

  def deleteAllMessages(name: String) = Action.async {
    request =>
      IronMQ.deleteAllMessages(name).map {
        k => k.fold(
          left => {
            BadRequest(left.getMessage)
          },
          right => {
            Ok(Json.parse(right))
          }
        )
      }
  }

}