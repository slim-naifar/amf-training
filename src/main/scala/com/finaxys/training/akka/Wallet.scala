package com.finaxys.training.akka

case class Wallet(var balance : Double = 0){
  def deposit(amount : Double) : Wallet= {
    balance = balance + amount
    this
  }
  def withdraw(amount : Double) : Wallet= {
    balance = balance - amount
    this
  }
}