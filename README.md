BidSim
======

Simple bidding simulation model, inspired by
[this question on _StackOverflow_](http://stackoverflow.com/questions/43968718/creating-a-simulation-of-a-bidding-game-on-geogebra).

# Running the model.

To run it, you will require a
_[Java SE Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)_ (_JDK_) version 8 or
later, _[SBT](https://scala-sbt.org)_ and _[git](https://git-scm.com/downloads)_.

Typically, you will then open a _Terminal_ window (on _Linux_) or a _Command Prompt_ window (on _Windows_) and enter the following commands:

```
git clone https://github.com/MichaelJAllen/BidSim.git
cd bidsim
sbt "run 1000"
```

(that latter command compiles and runs the program, performing 1,000 bid rounds. Change the value to perform a different number of rounds.)

__NOTE: This simulation is NOT built using _Facsimile___
