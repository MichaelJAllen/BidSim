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

/** Class representing a bidder.
  *
  * @constructor Construct a bidder. Use the factory method in the companion to create bidders.
  *
  * @param name Name of this bidder. (Note that names are not checked for uniqueness.)
  *
  * @param prob Vector containing probability of a bid value equal to the index value.
  */
class Bidder(val name: String, prob: Vector[Double]) {

  /** Vector of cumulative probabilities for a bid value less than or equal to the index value.
    */
  private val cumProb = prob.tail.scanLeft(prob.head)(_ + _)

  /** Next bid for this bidder.
    *
    * @return A randomly-determined bid, honoring the probabilities of each of this bidders bids.
    */
  def bid() = Bid.bid(cumProb)
}
