🐍 Snake Game (Java)

A classic Snake game built with Java Swing — no external libraries, no dependencies, just pure java.awt and javax.swing. Built and tested in NetBeans, but runs anywhere with a JDK.

🎮 Features


Smooth grid-based movement with a Timer-driven game loop
Growing snake with self-collision and wall-collision detection
Randomly spawning food that never overlaps the snake
Live score tracking
Game-over screen with instant restart (SPACE)
Single-file implementation — easy to read, easy to extend


📸 Preview

┌────────────────────────────┐
│ Score: 4                   │
│                             │
│        ■■■■                │
│           ■                │
│                     ●       │
│                             │
└────────────────────────────┘

🚀 Getting Started

Prerequisites


Java Development Kit (JDK) 8 or later
NetBeans IDE (recommended) or any Java-compatible editor


Run in NetBeans


Clone this repo:


bash   git clone https://github.com/your-username/snake-game-java.git


Open NetBeans → File → Open Project → select the cloned folder
(or create a new Java Application project and drop SnakeGame.java into Source Packages)
Right-click SnakeGame.java → Run File


Run from the command line

bashjavac SnakeGame.java
java SnakeGame

🕹️ Controls

KeyAction↑ ↓ ← →Move the snakeSPACERestart after game over

🧠 How It Works

The game logic lives in a single JPanel subclass:


Game loop — a javax.swing.Timer ticks every 120ms, calling move() and repaint()
Snake body — stored as a LinkedList<Point>, with the head at index 0
Growth — eating food adds a new head without removing the tail; otherwise the tail is trimmed each tick
Collision detection — checks wall boundaries and whether the new head position overlaps the existing body
Rendering — all drawing happens in paintComponent(), kept fully separate from game logic


🛠️ Customize It

Want to...Change thisSpeed up the gameLower the Timer delay (120 → 80)Make the board biggerIncrease GRID_WIDTH / GRID_HEIGHTChange colorsEdit the Color values in paintComponent()Add obstaclesExtend collision checks in move()

📋 Roadmap


 Increasing speed as score rises
 Persistent high score (saved to file)
 Multiple food types with different point values
 Sound effects
 Split into multiple classes (Snake, Food, GameBoard)
