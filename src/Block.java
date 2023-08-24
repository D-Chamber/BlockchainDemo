import java.math.BigInteger;
import java.security.MessageDigest;

public class Block {
    private final String[] trans;
    private String previousHash;
    private String blockHash;

    public Block(String[] trans, String previousHash) {
        this.trans = trans;
        StringBuilder str = new StringBuilder();

        for (String tran : trans) {
            str.append(tran);
        }

        String transaction = str.toString();

        // hashing algorithm is used to hash the string
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashValue = digest.digest(transaction.getBytes());
            BigInteger bigInt = new BigInteger(1, hashValue);
            this.blockHash = bigInt.toString(16);
        } catch (Exception e) {
            System.out.println("No such algorithm exists.");
        }

        this.previousHash = previousHash;
    }

    // Getter Methods
    public String getBlockHash() {
        return this.blockHash;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public String[] getTransaction() {
        return trans;
    }

    // toString method to display the block information.
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String tran : trans) {
            sb.append(tran).append("; ");
        }

        return String.format("""
           Block Hash: %s
           Previous Hash: %s
           Transactions: %s
           """, blockHash, previousHash, sb);
    }

    public String toEdit() {
        StringBuilder sb = new StringBuilder();

        for (String tran : trans) {
            sb.append(tran).append("; ");
        }

        return String.format("""
           Block Hash: %s (cannot be edited)
           Previous Hash: %s (cannot be edited)
           Transactions: %s (for editing)
           """, blockHash, previousHash, sb);
    }

    public void setPreviousHash(String blockHash) {
        this.previousHash = blockHash;
    }
}
