package com.major;

import java.util.Date;

/**
 * 包含 String 类型的 hash 属性，用来表示数字签名。变量 previousHash
 * 表示前一区块的哈希，变量 data 表示区块数据
 */
public class Block {

    public String hash;
    public String previousHash;

    private String data;
    private long timestamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash(); // Making sure we do this after we set the other values.
    }

    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash + Long.toString(timestamp) + Integer.toString(nonce) + data);
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        System.out.println("Trying to Mine block ...");
        long start = System.currentTimeMillis();
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!!, used time " + (System.currentTimeMillis() - start) + "ms, hash " + hash);
    }
}
