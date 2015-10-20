import source as s


class UsrCls():
    def __init__(self):
        self.var = s.var

    def met(self):
        x = [i * s.non_cls_glob for i in range(10)]
