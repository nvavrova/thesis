class DummyClass:
    staticVar = 5

    def __init__(self, name):
        self.name = name

    def print_name(self):
        print self.name


class CallingClass:
    def __init__(self):
        self.dummy = DummyClass('test')

    def do_stuff(self):
        # another_dummy = DummyClass('test21')
        self.dummy.print_name()
        self.print_name()
        test_array = [1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 1, 2, 3,
                      4, 5, 6, 6, 7, 8, 9, ]
        # print self.dummy.name
        # print DummyClass.staticVar

    def print_name(self):
        print "no name"
