var = {10, 20, 30}


class SrcCls():
    first = range(10)

    def __init__(self):
        self.second = {"one": 1, "two": 2}
        self.third = "lalala"

    def met(self):
        return self.first.get(0)
