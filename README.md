# The-Goose-Game
[![Build Status](https://travis-ci.com/DadeKuma/The-Goose-Game.svg?branch=master)](https://travis-ci.com/DadeKuma/The-Goose-Game)
[![codecov](https://codecov.io/gh/DadeKuma/The-Goose-Game/branch/master/graph/badge.svg)](https://codecov.io/gh/DadeKuma/The-Goose-Game)

A simple text game inspired by the classic board game 'The Goose'.

## How to install
#### 1. Clone this repository
```
git clone https://github.com/DadeKuma/The-Goose-Game
```

#### 2. Build with IDE
##### Intellij
```
Open > .../The-Goose-Game/pom.xml
```

##### Eclipse
```
File > Open Projects from File System... > Directory > ../The-Goose-Game
```

#### 3. Compile and run the project!

## How to play

### Goal
The aim of the game is to reach square number 63 before any of the other players and avoid obstacles.

### Commands
The Goose Game is a text-based game. You have to enter a command to the console and press "Enter".

Currently implemented commands are:

```
add player <player_name>                        #Add a player to the game
move <player_name>                              #Move a player: system will throw dices for him
move <player_name> <die_value>(<, die_value>)   #Move a player telling directly dices results
```