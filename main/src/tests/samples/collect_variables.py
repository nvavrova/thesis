class ClsOne():
    var = 2

    def print_stuff(self):
        print (self.var)
        self.multiply(3)
        print (ClsOne.var)

    def multiply(self, number):
        return number * 2


class ClsTwo():
    def met(self):
        print(self.met2(self.varr))

    def met2(self, str):
        return str.upper() if not str is None else ""


co = ClsOne()
co.talk()
