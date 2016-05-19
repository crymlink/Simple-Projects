from sys import exit
from random import randint
class Scene(object):
    def enter(self):
        print("This scene is not yet configured. Subclass it and implement enter().")
        exit(1)

class Engine(object):
    def __init__(self, scene_map):
        self.scene_map = scene_map

    def play(self):
        current_scene = self.scene_map.opening_scene()
        last_scene = self.scene_map.next_scene("finished")

        while current_scene != last_scene:
            next_scene_name = current_scene.enter()
            current_scene = self.scene_map.next_scene(next_scene_name)
        current_scene.enter()
class Death(Scene):
    lines = [ "You died. For real dude ?",
                "Rekt, try again",
                "SKULLS FOR THE SKULLTHRONE"
                ]

    def enter(self):
        print (Death.lines[randint(0, len(self.lines)-1)])
        exit(1)


class Startingroom(Scene):
    def enter(self):
        health = 3
        health2 = 2
        stamina = 2
        print("You are on a Spaceshulk, a destroyed Spacevessel flying through space")
        print("You got word that a Summoning is happening on this ship")
        print("As the mighty SpaceSardine you are, the Order is to destroy the Heretic....ehm the bad Hering brood")
        print("This game has nothing to do with the famous Spacehulk game")
        print("You penetrated the Spaceshulks hull and you find yourselfe in a Darkroom, but your gene enhanced senses can make out one lifesign")
        print("Suddenly a Hering shaped Lifeform is rushing you down with his sword")
        print("You draw your sword and are ready for the fight")
        print("He swings the sword from above his head at you")

        action = input("<<block/dodge>> ")

        if action =="block":
            print("Where do you want to block the blow?")
            action2 = input("<<up/left/right/down>>")
            if action2 in ["left", "right", "down"]:
                health -= 1
                print("You get hit with a swing from above")
                print("Your Health is now:", health)
                print("You stagger bag but are ready to fight again")
                print("The hering swings at you again this time from your left")
                print("You see it coming and can spend a Stamina to make a special move")
                print("Or you can block the blow")
                action3 = input("<<block,specialmove>>")
                if action3 == "specialmove":
                    stamina -= 1
                    print("Your stamina is now:", stamina)
                    health2 -= 2
                    print("You parry the swing, turn in a 360 and chopp the herings head off")
                    return "Corridor"
                elif action3 == "block":
                    print("Where do you want to block the blow?")
                    action2 = input("<<up/left/right/down>>")
                    if action2 in ["left", "right", "down"]:
                        health -= 1
                        print("You get hit with a swing from the left")
                        print("Your Health is now:", health)
                        print("After the hit you feel dizzy but luckily the foe vanishes")
                        return "Corridor"
            elif action2 == "up":
                print("You parry the blow and attack the Herring with a vicious sideslash")
                health2 -= 2
                print("The Herring screams and dies on your blade")
                return "Corridor"

        elif action =="dodge":
            print("With your Sardinelike reflexes you dodge out of the way")
            print("And after that stylish move you swing at the herings head dealing 2dmg")
            print("The hering sinks to the ground and is dead")
            health2 -= 2
            return "Corridor"

        else:
            print ("DOES NOT COMPUTE!")
            return "Startingroom"


class Corridor(Scene):
    def enter (self):
        print("You scane the corridor for more Herring")
        print("Sadly there is none and you have to starve")
        print("But while scanning you can hear the wierd summing noices for the command room")
        action = input("<<rest,letsgo>>")
        if action == "rest":
            Startingroom.health = 3
            print(" You regain your Health and start moving to the command room")
            return "Summoningroom"
        elif action == "letsgo":
            print(" In good old SpaceSardine fashion you start Screaming and running towards the Heretic")
            return "Summoningroom"

class Summoningroom(Scene):
    def enter(self):
        print("You enter the room and 4 Heringman are looking at you with drawn weapons")
        print("You think to yourselfe \"Screaming was a stupid idea\" ")

        action = input("<<do nothing and die/sardine special move>> ")
        if action == "do nothing and die":
            print(" You did nothing and got shot for 4 dmg")
            return "Death"

        if action == "sardine special move":
            print("YOUR NEW NAME IS NEO YOU ARE IN THE MATRIX")
            print(" Dodging every bullet you shoot every single one of them dead with style")
            return "finished"

class Finished(Scene):
    def enter(self):
        print("You did it ! Good Yob.")
        return "finished"

class Map(object):
    scenes = {
        "Startingroom": Startingroom(),
        "Corridor": Corridor(),
        "Summoningroom": Summoningroom(),
        "Death": Death(),
        "finished": Finished(),
        }
    def __init__(self, start_scene):
        self.start_scene = start_scene

    def next_scene(self, scene_name):
        val = Map.scenes.get(scene_name)
        return val

    def opening_scene(self):
        return self.next_scene(self.start_scene)

a_map = Map("Startingroom")
a_game = Engine(a_map)
a_game.play()
