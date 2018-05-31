package tech.coinbub.daemon.nrc;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a single block in the blockchain.
 * 
 * Received when calling `getblock`.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Block {
    public String hash;
    public Long confirmations;
    public Long size;
    public Long height;
    public Long version;
    public String merkleroot;
    public BigDecimal mint;
    public Long time;
    public Long nonce;
    public String bits;
    public BigDecimal difficulty;
    public String blocktrust;
    public String chaintrust;
    public String previousblockhash;
    public String nextblockhash;
    public String flags;
    public String proofhash;
    public Long entropybit;
    public String modifier;
    public String modifierv2;
    public List<Transaction> tx;
}

//nrc@test:~$ nrcd getblock 00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889
//{
//    "hash" : "00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889",
//    "confirmations" : 3,
//    "size" : 174,
//    "height" : 11,
//    "version" : 7,
//    "merkleroot" : "091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5",
//    "mint" : 0.00000000,
//    "time" : 1527710579,
//    "nonce" : 252995,
//    "bits" : "1e06e9b6",
//    "difficulty" : 0.00056506,
//    "blocktrust" : "250833",
//    "chaintrust" : "700117",
//    "previousblockhash" : "0000086ff119684c38f28f825bb29d1fb5fce007b4b572f9c92deb5de7a83521",
//    "nextblockhash" : "00000014a4e3ab7057a7094331022ec4524c095f13313ab976bfc6b3cea8a264",
//    "flags" : "proof-of-work",
//    "proofhash" : "00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889",
//    "entropybit" : 1,
//    "modifier" : "0000000000000000",
//    "modifierv2" : "cd607a9208d44759f3b26cd0759dc22bcb7366c8133a8f34fa83027f53713cf7",
//    "tx" : [
//        "091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5"
//    ]
//}

//nrc@test:~$ nrcd getblock 00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889 true
//{
//    "hash" : "00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889",
//    "confirmations" : 3,
//    "size" : 174,
//    "height" : 11,
//    "version" : 7,
//    "merkleroot" : "091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5",
//    "mint" : 0.00000000,
//    "time" : 1527710579,
//    "nonce" : 252995,
//    "bits" : "1e06e9b6",
//    "difficulty" : 0.00056506,
//    "blocktrust" : "250833",
//    "chaintrust" : "700117",
//    "previousblockhash" : "0000086ff119684c38f28f825bb29d1fb5fce007b4b572f9c92deb5de7a83521",
//    "nextblockhash" : "00000014a4e3ab7057a7094331022ec4524c095f13313ab976bfc6b3cea8a264",
//    "flags" : "proof-of-work",
//    "proofhash" : "00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889",
//    "entropybit" : 1,
//    "modifier" : "0000000000000000",
//    "modifierv2" : "cd607a9208d44759f3b26cd0759dc22bcb7366c8133a8f34fa83027f53713cf7",
//    "tx" : [
//        {
//            "txid" : "091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5",
//            "txid" : "091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5",
//            "version" : 1,
//            "time" : 1527710577,
//            "locktime" : 0,
//            "vin" : [
//                {
//                    "coinbase" : "5b0103",
//                    "sequence" : 4294967295
//                }
//            ],
//            "vout" : [
//                {
//                    "value" : 0.00000000,
//                    "n" : 0,
//                    "scriptPubKey" : {
//                        "asm" : "OP_DUP OP_HASH160 3fb5cf7b72a3830a1fe7c5a09fd7d48b590d4737 OP_EQUALVERIFY OP_CHECKSIG",
//                        "hex" : "76a9143fb5cf7b72a3830a1fe7c5a09fd7d48b590d473788ac",
//                        "reqSigs" : 1,
//                        "type" : "pubkeyhash",
//                        "addresses" : [
//                            "mmKpfZ1mabUapgamdaZkiyf8EQmZqZz3yY"
//                        ]
//                    }
//                }
//            ]
//        }
//    ]
//}
