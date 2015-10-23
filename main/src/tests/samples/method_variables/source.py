out_of_cls_var = 10

class SrcClass():
    def __init__(self):
        self.parent_var = ['a', 'b', 'c']


class SrcExtendedClass(SrcClass):
    def __init__(self):
        self.child_var = {
            'a': 10,
            'b': 20,
            'c': 30,
        }