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
// Primary SBT build file.
//======================================================================================================================
organization := "consulting.hindsight"

version := "1.0.0"

lazy val bid = project.in(file(".")).
settings(
  name := "bid",
  scalaVersion := "2.12.2"
)


