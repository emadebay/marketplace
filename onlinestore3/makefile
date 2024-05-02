all: clean
	javac clientview/Client.java

	javac model/Account.java
	javac model/AdminAccount.java
	javac model/ProductInterface.java
	javac model/Product.java

	javac model/ProductsContainer.java
	javac model/AccountsInterface.java
	javac model/AccountsContainer.java
	javac model/ProductsInterface.java
	
	javac controller/Observer.java
	javac controller/AccountsObserver.java

	javac controller/AccountsFactoryInterface.java
	javac controller/AccountsFactoryImplementation.java
	
	
	javac controller/Command.java
	javac controller/CreateAdminCommand.java
	javac controller/LoginCommand.java
	javac controller/AddNewAccountCommand.java
	javac controller/RemoveAccountCommand.java
	javac controller/AddProductCommand.java
	javac controller/RemoveProductCommand.java
	javac controller/UpdateProductCommand.java
	javac controller/CreateCustomerCommand.java
	javac controller/PurchaseCommand.java
	javac controller/AddItemCommand.java
	javac controller/ViewProductsCommad.java

	javac controller/Dispatcher.java

	javac controller/Authorization.java


	javac controller/SimilarAccFactoryImpl.java
	javac controller/SimilarAccFactoryInfc.java

	javac controller/ServerFrontController.java
	javac controller/ClientDispatcher.java
	javac controller/ClientFrontController.java

	javac serverview/StoreServer.java
	


run-client:
	java clientview/Client
run-server:
	java serverview/StoreServer
clean:
	rm -rf */*.class

