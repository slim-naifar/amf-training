package com.finaxys.training.akka

import akka.actor.Actor

case class Deposit(amount : Double)
case class Withdraw(amount : Double)
case class GetBalance()

class WalletActor extends Actor{

  var wallet = Wallet(0)

  override def receive = {
    case Deposit(amount) =>{
      println(s"Received deposit msg, sender = ${sender().path}, amount = ${amount}")
      wallet = wallet.deposit(amount)
    }
    case Withdraw(amount) => {
      println(s"Received withdraw msg, sender = ${sender().path}, amount = ${amount}")
      wallet = wallet.withdraw(amount)
    }
    case GetBalance() => {
      val response = s"Wallet Balance ${wallet.balance}"
      println(response)
      sender() ! response
    }
  }
}
