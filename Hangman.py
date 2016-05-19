def my_game_code(gamecounter):

    word = input("Type the word the other player has to guess: ")
    list_word = [f for f in word]
    hidden = []
    for i in list_word:
        hidden.append(' _ ')
    print(hidden)
    counter = 10

    while counter >0:
        letter = input('Type in the guess(single letter)  ')
        if len(letter) > 1:
            print("One letter!")
        elif letter in ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w','x', 'y', 'z']:
            for idx, value in enumerate(list_word, 0):
                if letter in value:
                    hidden.pop(idx)
                    hidden.insert(idx, letter)

                    if hidden == list_word:
                        print("You did it, Good Job")
                        action =input("press x to close and y to play again")
                        if action == "x":
                            exit()
                        elif action == "y":
                            my_game_code(3)
                    else: continue
            print(hidden)
            if letter not in list_word:
                counter -= 1
                print("Letter not in the word, try again!")
                print("You have %d health left" %counter)
                print(hidden)
my_game_code(3)
