from source import SrcCls as sc


class FirstTestCls():
    sc.first.append(100)


class SecondTestCls():
    def __init__(self):
        self.var = sc.first = []
        self.var2 = sc.met()
        if sc.third['one'] == 1:
            print ("whoa")
