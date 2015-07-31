class ClsOne():
    var = 2

    def print_stuff(self):  # comment
        print (self.var)
        self.multiply(3)
        # comment 2
        print (ClsOne.var)

    def multiply(self, number):
        return number * 2


# comment 3

class ClsTwo():
    def met(self):
        print(self.met2(self.varr))

    def met2(self, str):
        return str.upper() if not str is None else ""


co = ClsOne()
co.talk()
