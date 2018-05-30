package com.finaxys.training.akka.persistence

import akka.actor.{ActorSystem, Props}
import akka.stream.{ActorMaterializer, Materializer}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.ExecutionContext

class WalletPersistentActorTest extends TestKit(ActorSystem("MySpec")) with ImplicitSender
  with WordSpecLike  with Matchers with BeforeAndAfterAll {


  implicit val ec: ExecutionContext = system.dispatcher
  implicit val mat: Materializer = ActorMaterializer()


  override def afterAll(): Unit ={
    TestKit.shutdownActorSystem(system)
  }
  "wallet persistent actor basic test" in {

    val walletActorRef = system.actorOf(Props(classOf[WalletPersistentActor]), "test-wallet-persistent-actor")

    walletActorRef ! GetBalance()
    walletActorRef ! Deposit(200)
    walletActorRef ! GetBalance()
    walletActorRef ! Withdraw(100)
    walletActorRef ! GetBalance()

    expectMsg("Wallet Balance 0.0")
    expectMsg("Wallet Balance 200.0")
    expectMsg("Wallet Balance 100.0")

    system.stop(walletActorRef)

    val walletActorRef2 = system.child("test-wallet-persistent-actor")

    walletActorRef ! GetBalance()
    expectMsg("Wallet Balance 100.0")
  }
}
