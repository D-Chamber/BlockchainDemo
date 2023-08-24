import java.util.*;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int i;

        //generate Genesis block here
        blockchain.add(generateGenesisBlock());

        //this loop is for creating the menu
        while(true) {
            System.out.println("""
                _________________Menu____________________
                1. Add a transaction
                2. Display the blockchain by block number
                3. Edit the block by block number
                4. Display the blockchain
                5. Exit
                """);

            i  = scanner.nextInt();
            scanner.nextLine();

            //Logic for the menu to know which function to run.
            switch (i) {
                case 1 -> {
                    //Enter transaction to the block
                    ArrayList<String> list = new ArrayList<>();
                    String[] tmp = new String[3];
                    do {
                        System.out.println("""
                                __________Transaction Submenu__________
                                1. Add Entry
                                2. Submit
                                3. Refresh
                                """);
                        i = scanner.nextInt();
                        scanner.nextLine();

                        switch (i) {
                            case 1 -> {
                                System.out.println("\nPlease enter sender name: ");
                                tmp[0] = scanner.nextLine();
                                System.out.println("\nPlease enter amount to send: ");
                                tmp[1] = scanner.nextLine();
                                System.out.println("\nPlease enter receiver name: ");
                                tmp[2] = scanner.nextLine();
                                String transaction = tmp[0] + " pays " + tmp[1] + " BTC to " + tmp[2];

                                list.add(transaction);
                            }

                            case 2 -> {
                                String[] transactionToBlock = new String[list.size()];
                                for (int j = 0; j < list.size(); j++) {
                                    transactionToBlock[j] = list.get(j);
                                }
                                blockchain.add(new Block(transactionToBlock, blockchain.get(blockchain.size() - 1).getBlockHash()));
                                System.out.println("\nBlock added to the blockchain.\n");
                            }

                            case 3 -> {
                                System.out.println("Recalculating hash...");
                                recalculatingHash();
                                System.out.println("Hash recalculated.");
                            }

                            default -> System.out.println("Invalid input.");
                        }
                    } while (i != 2);
                }

                case 2 -> {
                    //Display the blockchain by block number
                    System.out.println("\nEnter the block number to display.");
                    i = scanner.nextInt();
                    scanner.nextLine();
                    if (i < blockchain.size()) {
                        System.out.println("\nBlock number " + i + ":");
                        System.out.println(blockchain.get(i));
                    } else {
                        System.out.println("Block does not exist in the blockchain.");
                    }
                    System.out.println("\n");
                }

                case 3 -> {
                    //Edit the block by block number when the block is edited, the hash of the block should be changed.
                    System.out.println("\nEnter the block number to edit. (This option only allows you to edit the last transactions entry to the block.)");
                    i = scanner.nextInt();
                    scanner.nextLine();


                    System.out.println("Editing Block number " + i + ": \n");
                    System.out.println(blockchain.get(i).toEdit());
                    String[] transactionToEdit = blockchain.get(i).getTransaction();


                    if (i < blockchain.size()) {
                        System.out.println("Enter the transaction to store into the block to finish.");
                        input = scanner.nextLine();
                        transactionToEdit[transactionToEdit.length - 1] = input;
                        blockchain.set(i, new Block(transactionToEdit, blockchain.get(i).getPreviousHash()));

                        // for clarity if the transaction has been edited at all the changes are propagated to the rest of the blockchain
                        // in actuality this would not be done so that if the transaction is edited it would cause a discrepancy with the hash
                        // in the blockchain. (disable this line to see the difference).
                        recalculatingHash();

                        //Time to check if the chain is valid
                        if (isChainValid()) {
                            System.out.println("The chain is valid.");
                        } else {
                            System.out.println("The chain is not valid.");
                        }
                    } else {
                        System.out.println("Block does not exist in the blockchain.");
                    }

                    System.out.println("\n");
                }

                case 4 -> {
                    //Display the blockchain
                    System.out.println("\nComplete Blockchain: \n");
                    for (Block block : blockchain) {
                        System.out.println("Block " + blockchain.indexOf(block) + ":");
                        System.out.println(block);
                    }
                    System.out.println("\n");
                }

                case 5 -> {
                    //Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid input.");
            }
        }
    }

    private static void recalculatingHash() {
        Block cb;
        Block pb;

        //loop through blockchain to check hashes:
        for (int l = 1; l < blockchain.size(); l++) {
            cb = blockchain.get(l);
            pb = blockchain.get(l-1);

            if (!Objects.equals(cb.getPreviousHash(), pb.getBlockHash())) {
                cb.setPreviousHash(pb.getBlockHash());
            }
        }
    }

    public static Boolean isChainValid() {
        Block cb;
        Block pb;

        //loop through blockchain to check hashes:
        for (int l = 1; l < blockchain.size(); l++) {
            cb = blockchain.get(l);
            pb = blockchain.get(l-1);

            if (!cb.getPreviousHash().equals(pb.getBlockHash())) {
                return false;
            }
        }

        return true;
    }

    private static Block generateGenesisBlock() {
        String[] genesis = {"Genesis"};

        return new Block(genesis, null);
    }
}
