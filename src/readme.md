Blockchain Programming Project - Tim Nguyen

## Introduction
This project is a simple blockchain implementation in java. The blocks are added to the chain via an ArrayList.
The Block class contains the information such as the previous blocks hash, the current blocks hash, and the transaction
data.

## How to run
To run the program, simply run the main method in the Blockchain class. The program will then prompt you to enter
the transaction data. Once you have entered the data and then input done, the program will then queue the transaction
data to an Array of Strings that will be used to create the blocks. The program will then create the blocks and add it
to the chain. The program can do a couple different things via the menu. The menu will prompt you to enter a number.
The options allow you to just display the whole chain, a single block, or to edit the last inputted transaction data for
a specific block.

# Operations
1. Allows you to add multiple transaction to the chain via a block.
    - The program will prompt you to choose an option
    - The options are Add Entry, Submit, and Refresh
        - Add Entry will prompt you enter the name of the sender, the amount of BTC to send, and the name of the receiver.
        - Submit allows you to submit the transaction data to the chain and take you back to the main menu.
        - Refresh allows you to recalculate hashes.
    - The program will then create the blocks and add it to the chain.

2. Allows you to view the specific block within the chain.
    - The program will prompt you to enter the block number.
    - The program will then display the block.
    - If the block number is out of bounds, the program will display an error message.

3. Allows you to edit a specific block.
    - The program will prompt you to enter the block number.
    - The program will then display the block.
    - If the block number is out of bounds, the program will display an error message.
    - The program will then prompt you to enter the transaction data for the specified block.
    - It will then update the block with the new transaction data.
    - Hashes are recalculated and propagated throughout the whole chain.
        - The hash are recalculated just so we can get a valid check of the hash values for the blocks. In reality, you do not want
        to propagate the hash change to the whole chain because it would allow us to know where changes are made in the chain.
        (You can see the difference and try it for yourself by commenting out the hashRecalculation() method in the Blockchain class.)

4. Allows you to display the whole blockchain.

5. Allows you to exit the program.
