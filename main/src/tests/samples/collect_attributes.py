class TestCls():
    var = 2

    def print_stuff(self):
        print (self.var)
        self.multiply(3)
        print (TestCls.var)

    def multiply(self, number):
        return number * 2


tc = TestCls()
tc.talk()
