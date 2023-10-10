class petStore:
    def _init_(self):
        self.pets={"dog":[],"cat":[],"rabbit":[],"parrot":[]}

    def petdetails(self,name,age,price,breed):
        details={"age":age,"price":price,"breed":breed,"status":"available"}
        self.pets[name].append(details)
        
    def search(self,name):
        for i in self.pets.keys():
           if i==name:
                print (self.pets[name])
                break
        breed=input("Enter the breed :").lower()
        for i in self.pets[name]:
            if i["breed"]==breed:
                print(i)
    def sellpet(self,name,itemno):
        self.pets[name][itemno-1]["status"]="sold"
        print("item sold")
    def petlist(self):
        print("available dog are :")
        print(self.pets["dog"])
        print("available cat :")
        print(self.pets["cat"]) 
        print("available rabbit :")
        print(self.pets["rabbit"]) 
        print("available parrot :")
        print(self.pets["parrot"])