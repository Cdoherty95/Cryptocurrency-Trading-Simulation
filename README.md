# ITMD510_CryptoTrader

This program is a cryptocurrency trading simulator. It allows users to simulate the buying and selling of Bitcoin and Ethereum. Users can simulate depositing and withdrawing USD from/to their bank. 

To begin the application, launch the .jar file in the zip folder. There are no users by default because I chose to use SQLite as the database. You must use the register option to create users. There is a button to create an admin user which would not be there on production version of the software. I have included a copy of a database of users named ‘CryptoTrader-Included-DB.db’ if you wish not to create your own. You must change the name of the .db file to just the word ‘CryptoTrader’ to use the database. Usernames: admin, user. Passwords admin, user respectively. Passwords are salted and hashed using a randomly generated salt, then stored in the database.

Link to video of app: https://youtu.be/ylgXbfocGkU
