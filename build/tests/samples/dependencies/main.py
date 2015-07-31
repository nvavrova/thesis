from base import Base1
import base2 as b
from base import TotalBase as tb

class Main():
    def __init__(self):
        self.b1 = Base1()
        self.b2 = b.Base2()
        self.tb = tb()


class Main2():
    b1 = Base1()