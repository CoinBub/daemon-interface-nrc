package tech.coinbub.daemon.nrc;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
    public String txid;
    public Long version;
    public Long time;
    public Long locktime;
    public List<TxInput> vin;
    public List<TxOutput> vout;
    
    // Long-form transaction data
    public BigDecimal amount;
    public BigDecimal fee;
    public Long confirmations;
    public Boolean generated;
    public String blockhash;
    public Long blockindex;
    public Long blocktime;
    public Long timereceived;
    public List<TransactionDetail> details;
    public String comment;
    public String to;
    
    public Transaction() {}
    public Transaction(final String txid) {
        this.txid = txid;
    }
}

//nrc@test:~$ nrcd gettransaction 091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5
//{
//    "txid" : "091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5",
//    "version" : 1,
//    "time" : 1527710577,
//    "locktime" : 0,
//    "vin" : [
//        {
//            "coinbase" : "5b0103",
//            "sequence" : 4294967295
//        }
//    ],
//    "vout" : [
//        {
//            "value" : 0.00000000,
//            "n" : 0,
//            "scriptPubKey" : {
//                "asm" : "OP_DUP OP_HASH160 3fb5cf7b72a3830a1fe7c5a09fd7d48b590d4737 OP_EQUALVERIFY OP_CHECKSIG",
//                "hex" : "76a9143fb5cf7b72a3830a1fe7c5a09fd7d48b590d473788ac",
//                "reqSigs" : 1,
//                "type" : "pubkeyhash",
//                "addresses" : [
//                    "mmKpfZ1mabUapgamdaZkiyf8EQmZqZz3yY"
//                ]
//            }
//        }
//    ],
//    "amount" : 0.00000000,
//    "confirmations" : 3,
//    "generated" : true,
//    "blockhash" : "00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889",
//    "blockindex" : 0,
//    "blocktime" : 1527710579,
//    "txid" : "091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5",
//    "time" : 1527710578,
//    "timereceived" : 1527710578,
//    "details" : [
//        {
//            "account" : "",
//            "address" : "mmKpfZ1mabUapgamdaZkiyf8EQmZqZz3yY",
//            "category" : "immature",
//            "amount" : 0.00000000
//        }
//    ]
//}
