# Shopping-App-Android
Shopping cart application using android


When the Shopping application starts, the user will see a list of available items
displayed in a dropdown menu. The user should pick an item name from the drop-down 
menu and enter a number for priority and quantity. The price of any item will be printed
automatically as they click on its name. The application displays an alert box with a 
specific error message depending on what went wrong (negative number orany special
character, alphabets instead of numbers or vice versa, and if any the text fields are
left empty). Also, if the user enters duplicate item the program will update the item 
data with the recent inputs. For priority numbers, it can be duplicated like (1, 2, 2, 3). 
The highest priority would be the lowest number).  Once the user adds all the items that 
he wants to purchase, the user will be asked to provide a budget and a username. There is
input validation for the budget, it can only be an integer or double he should click on 
the checkout button, there should be at least one on item added to the list otherwise an
alert box. The app will automatically calculate the total starting from the highest priority
(lowest number) maximizing the amount based on the entered budget. In the second window 
(Activity), the user can see all the shopped and not shopped items printed out sorted with 
the total price of the transaction, budget, and current balance. The items that were not 
shopped are saved in the database and displayed as a reminder for the user when starting 
the app or trying to place another order.


