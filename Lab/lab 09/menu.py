import Lab_09 
shop = Lab_09.petStore()
while True:
    print("1. Add pets")
    print("2. Search")
    print("3. Sell")
    print("4. Seelist")
    print("5. Exit")
    choice = int(input("Enter your choice :"))
    if choice==1:
        name=input("Enter Name:").lower()
        age=input("Enter the age:")
        price=input("Enter the price:")
        breed=input("Enter the breed:")  
        shop.petdetails(name,age,price,breed)
    elif choice==2:
        print("Select the Animal")
        print("Dog,Cat,Rabbit,Parrot")   
        name=input("Enter the name:").lower()
        shop.search(name)
    elif choice==3:
        name=input("Enter the name:")
        shop.search(name)
        itemno=int(input("Enter the item number:"))
        shop.sellpet(name,itemno)
    elif choice==4:
        shop.petlist()
    elif choice==5:
        exit()
    else:
        print("enter the correct option")




#shop.petdetails(1,1300,"abc","dogs")
#shop.petlist()