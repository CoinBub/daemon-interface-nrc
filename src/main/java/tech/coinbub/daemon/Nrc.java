package tech.coinbub.daemon;

import java.math.BigDecimal;
import tech.coinbub.daemon.nrc.Block;
import tech.coinbub.daemon.nrc.Transaction;

/*
 * nrc@test:~$ Noorcoind help
 * addmultisigaddress <nrequired> <'["key","key"]'> [account]
 * addnode <node> <add|remove|onetry>
 * addredeemscript <redeemScript> [account]
 * backupwallet <destination>
 * checkkernel [{"txid":txid,"vout":n},...] [createblocktemplate=false]
 * checkwallet
 * createrawtransaction [{"txid":txid,"vout":n},...] {address:amount,...}
 * decoderawtransaction <hex string>
 * decodescript <hex string>
 * dumpprivkey <Noorcoinaddress>
 * dumpwallet <filename>
 * encryptwallet <passphrase>
 * getaccount <Noorcoinaddress>
 * getaccountaddress <account>
 * getaddednodeinfo <dns> [node]
 * getaddressesbyaccount <account>
 * getbalance [account] [minconf=1]
 * getbestblockhash
 * getblock <hash> [txinfo]
 * getblockbynumber <number> [txinfo]
 * getblockcount
 * getblockhash <index>
 * getblocktemplate [params]
 * getcheckpoint
 * getconnectioncount
 * getdifficulty
 * getgenerate
 * getinfo
 * getmininginfo
 * getnettotals
 * getnewaddress [account]
 * getnewpubkey [account]
 * getpeerinfo
 * getrawmempool
 * getrawtransaction <txid> [verbose=0]
 * getreceivedbyaccount <account> [minconf=1]
 * getreceivedbyaddress <Noorcoinaddress> [minconf=1]
 * getstakesubsidy <hex string>
 * getstakinginfo
 * getsubsidy [nTarget]
 * gettransaction <txid>
 * getwork [data]
 * getworkex [data, coinbase]
 * help [command]
 * importprivkey <Noorcoinprivkey> [label] [rescan=true]
 * importwallet <filename>
 * keypoolrefill [new-size]
 * listaccounts [minconf=1]
 * listaddressgroupings
 * listreceivedbyaccount [minconf=1] [includeempty=false]
 * listreceivedbyaddress [minconf=1] [includeempty=false]
 * listsinceblock [blockhash] [target-confirmations]
 * listtransactions [account] [count=10] [from=0]
 * listunspent [minconf=1] [maxconf=9999999]  ["address",...]
 * makekeypair [prefix]
 * move <fromaccount> <toaccount> <amount> [minconf=1] [comment]
 * ping
 * repairwallet
 * resendtx
 * reservebalance [<reserve> [amount]]
 * sendfrom <fromaccount> <toNoorcoinaddress> <amount> [minconf=1] [comment] [comment-to]
 * sendmany <fromaccount> {address:amount,...} [minconf=1] [comment]
 * sendrawtransaction <hex string>
 * sendtoaddress <Noorcoinaddress> <amount> [comment] [comment-to]
 * setaccount <Noorcoinaddress> <account>
 * setgenerate <generate> [genproclimit]
 * settxfee <amount>
 * signmessage <Noorcoinaddress> <message>
 * signrawtransaction <hex string> [{"txid":txid,"vout":n,"scriptPubKey":hex,"redeemScript":hex},...] [<privatekey1>,...] [sighashtype="ALL"]
 * stop
 * submitblock <hex data> [optional-params-obj]
 * validateaddress <Noorcoinaddress>
 * validatepubkey <Noorcoinpubkey>
 * verifymessage <Noorcoinaddress> <signature> <message>
 */
public interface Nrc {
    /**
     * `getnewaddress [account]`
     * 
     * Returns a new Noorcoin address for receiving payments.  If [account] is specified, it is added to the address
     * book so payments received with the address will be credited to [account].
     */
    String getnewaddress();
    String getnewaddress(String account);

    /**
     * `getbestblockhash`
     * 
     * Returns the hash of the best block in the longest block chain.
     */
    String getbestblockhash();

    /**
     * `getblockhash <index>`
     * 
     * Returns hash of block in best-block-chain at <index>.
     */
    String getblockhash(Long index);

    /**
     * `getblock <hash> [txinfo]`
     * 
     * txinfo optional to print more detailed tx info
     * Returns details of a block with given block-hash.
     */
    Block getblock(String hash);
    Block getblock(String hash, boolean txinfo);
    
    /**
     * `gettransaction <txid>`
     * 
     * Get detailed information about <txid>
     */
    Transaction gettransaction(String txid);
    /**
     * `sendtoaddress <Noorcoinaddress> <amount> [comment] [comment-to]`
     * 
     * <amount> is a real and is rounded to the nearest 0.000001
     */
    String sendtoaddress(String address, BigDecimal amount);
    String sendtoaddress(String address, BigDecimal amount, String comment);
    String sendtoaddress(String address, BigDecimal amount, String comment, String commentTo);

    public enum NodeAction {
        add,
        remove,
        onetry
    }
}
