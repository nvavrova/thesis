from source import SrcClass as sc, SrcExtendedClass as sec

class UserCls():
    def method_one(self):
        inst = sc()
        inst.parent_var

    def method_two(self):
        inst = sec()
        for key in inst.parent_var:
            if key in inst.child_var:
                print(inst.child_var[key])

    def method_three(self):
        self.child_var = {
            'a': 10,
            'b': 20,
            'c': 30,
        }