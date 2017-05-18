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

import java.util.Random

/** Object for performing bid rounds.
  */
object Bid {

  /** ''Pseudo-random number'' generator (''PRNG'')
    */
  private val prng = new Random()

  /** Randomly sample a bid from the provided cumulative array.
    *
    * @param cumProb Vector of cumulative probabilities. The vector must contain at least one value, may have any
    * number of values (in increasing order) and have a final value of 1.0.
    *
    * @return A bid value in the range 0 to `cumProb.length - 1`. (Note: the original problem called for bid values of
    * 1, 2 or 3. This function, when passed a vector of 3 cumulative probabilities, will return bids of 0, 1 or 2 -
    * because those are the vector indices. If this is unacceptable, simply add 1 to the final value.)
    *
    * @throws IllegalArgumentException if `cumProb` does not meet our requirements.
    */
  def bid(cumProb: Vector[Double]): Int = {

    // A couple of sanity checks on the cumulative array.
    require(cumProb.nonEmpty)
    require(cumProb(cumProb.length - 1) == 1.0)

    // Sample a pseudo-random value, in the range [0, 1), which we'll compare to the cumulative probabilities.
    val u = prng.nextDouble()

    // Now find the first cumulative probability that is greater then u - the corresponding array index is our bid.
    cumProb.indexWhere(cp => cp > u)
  }
}
