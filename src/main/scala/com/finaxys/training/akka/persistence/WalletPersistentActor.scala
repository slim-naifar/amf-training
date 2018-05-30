package com.finaxys.training.akka.persistence

import akka.persistence.{PersistentActor, SnapshotOffer}
import com.finaxys.training.akka.Wallet

trait Cmd

case class Deposit(amount: Double) extends Cmd

case class Withdraw(amount: Double) extends Cmd

case class GetBalance() extends Cmd

trait Evt

case class Deposited(amount: Double) extends Evt

case class Withdrawn(amount: Double) extends Evt


class WalletPersistentActor extends PersistentActor {

  override def persistenceId = "wallet-actor"

  var state = Wallet(0)

  def updateState(event: Evt): Unit =
    event match {
      case Deposited(amount) => state = state.deposit(amount)
      case Withdrawn(amount) => state = state.withdraw(amount)
    }

  val receiveRecover: Receive = {
    case evt: Evt => updateState(evt)
    case SnapshotOffer(_, snapshot: Wallet) => state = snapshot
  }

  val receiveCommand: Receive = {

    case Deposit(amount) => {
      persist(Deposited(amount)) { evt =>
        updateState(evt)
      }
    }
    case Withdraw(amount) => {
      persist(Withdrawn(amount)) { evt =>
        updateState(evt)
      }
    }
    case GetBalance() => {
      val response = s"Wallet Balance ${state.balance}"
      println(response)
      sender() ! response
    }
  }
}