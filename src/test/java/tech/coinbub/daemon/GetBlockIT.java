package tech.coinbub.daemon;

import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.coinbub.daemon.nrc.Block;
import tech.coinbub.daemon.nrc.ScriptPublicKey;
import tech.coinbub.daemon.nrc.Transaction;
import tech.coinbub.daemon.nrc.TxInput;
import tech.coinbub.daemon.nrc.TxOutput;
import static tech.coinbub.daemon.testutils.BeanMatcher.hasOnly;
import static tech.coinbub.daemon.testutils.BeanPropertyMatcher.property;
import tech.coinbub.daemon.testutils.Dockerized;

@ExtendWith(Dockerized.class)
public class GetBlockIT {
    private static final String HASH = "00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889";
    @Test
    public void supportsShortTransactionList(final Nrc nrc) {
        final Block block = nrc.getblock(HASH);
        assertThat(block, hasOnly(
                property("hash", is(equalTo(HASH))),
                property("confirmations", is(equalTo(3L))),
                property("size", is(equalTo(174L))),
                property("height", is(equalTo(11L))),
                property("version", is(equalTo(7L))),
                property("merkleroot", is(equalTo("091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5"))),
                property("mint", is(equalTo(new BigDecimal("0.0")))),
                property("time", is(equalTo(1527710579L))),
                property("nonce", is(equalTo(252995L))),
                property("bits", is(equalTo("1e06e9b6"))),
                property("difficulty", is(equalTo(new BigDecimal("0.00056506")))),
                property("blocktrust", is(equalTo("250833"))),
                property("chaintrust", is(equalTo("700117"))),
                property("previousblockhash", is(equalTo("0000086ff119684c38f28f825bb29d1fb5fce007b4b572f9c92deb5de7a83521"))),
                property("nextblockhash", is(equalTo("00000014a4e3ab7057a7094331022ec4524c095f13313ab976bfc6b3cea8a264"))),
                property("flags", is(equalTo("proof-of-work"))),
                property("proofhash", is(equalTo("00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889"))),
                property("entropybit", is(equalTo(1L))),
                property("modifier", is(equalTo("0000000000000000"))),
                property("modifierv2", is(equalTo("cd607a9208d44759f3b26cd0759dc22bcb7366c8133a8f34fa83027f53713cf7"))),
                property("tx", hasSize(1))
        ));

        final Transaction tx = block.tx.get(0);
        assertThat(tx, hasOnly(
                property("txid", is(equalTo("091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5"))),
                property("version", is(nullValue())),
                property("time", is(nullValue())),
                property("locktime", is(nullValue())),
                property("vin", is(nullValue())),
                property("vout", is(nullValue()))
        ));
    }

    @Test
    public void supportsLongTransactionList(final Nrc nrc) {
        final Block block = nrc.getblock(HASH, true);
        // Block details verified in `supportsShortTransactionList()`
        final Transaction tx = block.tx.get(0);
        assertThat(tx, hasOnly(
                property("txid", is(equalTo("091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5"))),
                property("version", is(equalTo(1L))),
                property("time", is(equalTo(1527710577L))),
                property("locktime", is(equalTo(0L))),
                property("vin", hasSize(1)),
                property("vout", hasSize(1))
        ));

        final TxInput in = tx.vin.get(0);
        assertThat(in, hasOnly(
                property("coinbase", is(equalTo("5b0103"))),
                property("sequence", is(equalTo(4294967295L)))
        ));

        final TxOutput out = tx.vout.get(0);
        assertThat(out, hasOnly(
                property("value", is(equalTo(new BigDecimal("0.0")))),
                property("n", is(equalTo(0L))),
                property("scriptPubKey", is(not(nullValue())))
        ));

        final ScriptPublicKey key = out.scriptPubKey;
        assertThat(key, hasOnly(
                property("asm", is(equalTo("OP_DUP OP_HASH160 3fb5cf7b72a3830a1fe7c5a09fd7d48b590d4737 OP_EQUALVERIFY OP_CHECKSIG"))),
                property("hex", is(equalTo("76a9143fb5cf7b72a3830a1fe7c5a09fd7d48b590d473788ac"))),
                property("type", is(equalTo(ScriptPublicKey.Type.pubkeyhash))),
                property("reqSigs", is(equalTo(1L))),
                property("addresses", hasSize(1))
        ));
    }
    
}
