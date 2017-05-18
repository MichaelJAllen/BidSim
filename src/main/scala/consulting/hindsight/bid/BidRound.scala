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

import scala.collection.immutable.SortedMap

/** Perform a round of bidding.
  */
object BidRound {

  /** Perform another round of bidding.
    *
    * @return `Some` bidder if there was a winner, `None` if all bidders made the same bid.
    */
  def apply(bidders: List[Bidder]) = {

    // Create a map of bids to to bidders for this round. Each bidder gets to make a random bid.
    val bids = bidders.foldLeft(SortedMap.empty[Int, List[Bidder]].withDefaultValue(Nil)) {(map, bidder) =>
      val bid = bidder.bid()
      map + (bid -> (bidder :: map(bid)))
    }

    // Filter out duplicates.
    val uniqueBids = bids.filter {
      case (_, listBidders) => listBidders.tail.isEmpty
    }

    // Return the object at the head of the unique bids map.
    uniqueBids.headOption.map {
      case(_, listBidders) => {
        assert(listBidders.nonEmpty)
        assert(listBidders.tail.isEmpty)
        listBidders.head
      }
    }
  }
}
