package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class EnumClasses:Worker {
    override fun run() {
        super.run()
        println("Direction is defined as ${Direction.values().contentToString()}")
        val currentDirection = Direction.TOP
        currentDirection.move()
        currentDirection.movee()
        currentDirection.moveee()

        val dog = Animal.DOG
        dog.move()
        dog.stop()
        dog.movee().stopp()

        println("enumValues? ${enumValues<Animal>().joinToString{ "${it.ordinal}: ${it.name }"}}")
        println("enumValues? ${enumValueOf<Animal>("DOG")}")
    }

    // The most basic usage of enum classes is implementing type-safe enums:
    enum class Direction(val velocity: Int) {
        LEFT(10) {
            override fun move() {
                println("move to $name as ${velocity}m/m")
            }
        },
        TOP(20) {
            override fun move() {
                println("move to $name as ${velocity}m/m")
            }
        },
        RIGHT(30) {
            override fun move() {
                println("move to $name as ${velocity}m/m")
            }
        },
        BOTTOM(40) {
            override fun move() {
                println("move to $name as ${velocity}m/m")
            }
        };

        // Enum constants can also declare their own anonymous classes with their corresponding methods, as well as overriding base methods.
        abstract fun move()

        fun movee() {
            println("move to $name as ${velocity}m/s")
        }
    }

    fun Direction.moveee() {
        println("move to ${this.name} as ${this.velocity}m/h")
    }

    interface Movable {
        fun move()
        fun stop()
    }

//    An enum class can implement an interface (but not derive from a class)
    enum class Animal: Movable {
        DOG() {
            override fun move() {
                println ("$name is moving...")
            }
            override fun stop() {
                println ("$name is stopped.")
            }
        },
        CAT() {
            override fun move() {
                println ("$name is moving...")
            }
            override fun stop() {
                println ("$name is stopped.")
            }
        };
    }

    fun Animal.movee(): Animal {
        println ("$name is moving...")
        return this
    }

    fun Animal.stopp(): Animal {
        println ("$name is stopped.")
        return this
    }
}