//======================================================================================================================
// BidSim -- A simple bid simulator
// Copyright © 2017, Michael J Allen.
//
// This file is part of BidSim.
//
// BidSim is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
//
// BidSim is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
// of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along with BidSim. If not, see:
//
//   http://www.gnu.org/licenses/lgpl.
//======================================================================================================================
// Scala source file belonging to the consulting.hindsight.bid package.
//======================================================================================================================
package consulting.hindsight.bid

import scala.annotation.tailrec

/** Simple bid simulation command line app.
  *
  * A group of bidders make random bids in a ''bid round'', according to a probability array (relating the probability
  * of bidding the index value of that probability). The winner is the bidder with the lowest unique bid; if two or more
  * bidders make the same bid, then they cannot win; if all bidders make the same bid, then no-one wins.
  *
  * This simulation was created as part of the solution to
  * [[http://stackoverflow.com/questions/43968718/creating-a-simulation-of-a-bidding-game-on-geogebra this question on
  * _StackOverflow_]].
  *
  * The easiest way to run the simulation is via _sbt_ using the command: `sbt "run 1000"`.
  *
  * The above example will perform 1,000 bid rounds.
  */
object BidSim
extends App {

  /** Bidders.
    */
  private val bidders = List(
    new Bidder("Alice", Vector(1.0/ 3.0, 1.0 / 3.0, 1.0 / 3.0)),
    new Bidder("Bob", Vector(0.25, 0.25, 0.5)),
    new Bidder("Charlie", Vector(0.5, 0.25, 0.25)),
  )

  // The number of rounds is the first argument to the program.
  private val rounds = args(0).toInt

  /** Perform all rounds.
    *
    * Report the winner of each round, keep score.
    *
    * @param remRounds Number of rounds remaining.
    *
    * @param score Current score going into the current round.
    *
    * @return Final score after all rounds completed.
    */
  @tailrec
  private def performRound(remRounds: Int, score: Map[Bidder, Int]): Map[Bidder, Int] = {

    // If we've performed all of our rounds, then return the final score.
    if(remRounds == 0) score

    // Otherwise, perform another round. Note the winner and update the score accordingly.
    else {
      val updatedScore = BidRound(bidders) match {
        case None => {
          println("There was no winner this round")
          score
        }
        case Some(winner) => {
          println(s"Winner of this round was: ${winner.name}")
          score + (winner -> (score(winner) + 1))
        }
      }
      performRound(remRounds - 1, updatedScore)
    }
  }

  // Notify user what is happening.
  println(s"Starting first of $rounds rounds)")

  /** Final score.
    */
  val finalScore = performRound(rounds, Map.empty[Bidder, Int].withDefaultValue(0))

  // Output the final score.
  finalScore.foreach {
    case (bidder, wins) => {
      println(s"${bidder.name} had $wins wins (${wins * 100.0 / rounds}%)")
    }
  }
}
