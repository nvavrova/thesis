import base
from lib import LibCls as lc

class Derived(base.BaseCls, lc):
    def do_stuff(self):
        print("Derived do stuff")