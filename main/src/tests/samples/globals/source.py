var = {10, 20, 30}

global non_cls_glob


class SrcCls():
    first = range(10)
    global cls_glob

    def __init__(self):
        self.second = {"one": 1, "two": 2}
        self.third = "lalala"

    def met(self):
        return self.first.get(0)

    def met2(self):
        self.x = var

    def met3(self):
        self.y = non_cls_glob