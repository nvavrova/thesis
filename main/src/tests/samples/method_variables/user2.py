import source as s

class UserCls2():
    def __init__(self):
        pass

    def method_one(self):
        inst = s.SrcExtendedClass()
        return inst.parent_var

    def method_two(self):
        inst = s.SrcExtendedClass()
        x = inst.child_var
        y = inst.parent_var
        inst = s.SrcClass()
        x = inst.parent_var

    def method_three(self):
        return s.out_of_cls_var