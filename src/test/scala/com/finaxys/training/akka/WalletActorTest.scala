package com.finaxys.training.akka

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class WalletActorTest extends TestKit(ActorSystem("MySpec")) with ImplicitSender
with WordSpecLike  with Matchers with BeforeAndAfterAll {

 /*
 "wallet actor basic test" must {
    val system = ActorSystem.create("bank-system")
    val walletActorRef = system.actorOf(Props(classOf[WalletActor]), "test-wallet-actor")

    walletActorRef ! GetBalance()
    walletActorRef ! Deposit(100)
    walletActorRef ! GetBalance()
    walletActorRef ! Withdraw(50)
    walletActorRef ! GetBalance()
  }
*/
  "wallet actor basic test 2" in {

    val walletActorRef = TestActorRef(Props(classOf[WalletActor]), "test-wallet-actor")

    walletActorRef ! GetBalance()
    walletActorRef ! Deposit(200)
    walletActorRef ! GetBalance()
    walletActorRef ! Withdraw(100)
    walletActorRef ! GetBalance()
    expectMsg("Wallet Balance 0.0")
    expectMsg("Wallet Balance 200.0")
    expectMsg("Wallet Balance 100.0")
  }
}
