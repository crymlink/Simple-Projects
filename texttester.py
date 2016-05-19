def test_email(zahl):
    flag_words = ["destroy", "strike", "bomb", "kill"]
    path = input("Type the path of the .txt")

    with open(path) as txt:
        str_txt = txt.read().replace('\n', '')
        str_txt = str_txt.lower()
    found_flag = False
    for flag_words in flag_words:
        if flag_words in str_txt:
            found_flag = True
    if found_flag == True:
        print("!!!!Flag Found, Further Investigation requiert!!!!")
        print("Email is as follows:")
        print("-------------------------------------------------")
        print(str_txt)
        print("Do you want to test more Mails?")
        print("yes, no  ")
        action = input().lower()
        if action in ["yes", "y", "ye"]:
            test_email(1)
        elif action in ["no", "n", "nop", "nope"]:
            exit()
    if found_flag == False:
        print("Email is Clean, do you want to test more Mails ?")
        print("yes, no  ")
        action = input().lower()
        if action in ["yes", "y", "ye"]:
            test_email(1)
        elif action in ["no", "n", "nop", "nope"]:
            exit()

test_email(1)
