package tech.coinbub.daemon;

import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.coinbub.daemon.nrc.ScriptPublicKey;
import tech.coinbub.daemon.nrc.ScriptSignature;
import tech.coinbub.daemon.nrc.Transaction;
import tech.coinbub.daemon.nrc.TransactionDetail;
import tech.coinbub.daemon.nrc.TxInput;
import tech.coinbub.daemon.nrc.TxOutput;
import static tech.coinbub.daemon.testutils.BeanMatcher.hasOnly;
import static tech.coinbub.daemon.testutils.BeanPropertyMatcher.property;
import tech.coinbub.daemon.testutils.Dockerized;

@ExtendWith(Dockerized.class)
public class GetTransactionIT {
    @Test
    public void canGetTransaction(final Nrc nrc) {
        final Transaction tx = nrc.gettransaction("091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5");
        assertThat(tx, hasOnly(
                property("txid", is(equalTo("091d9a30192ad7f4ef1cdff69d1e945920ef3185758f932d80fd9ae7d9d5a6d5"))),
                property("version", is(equalTo(1L))),
                property("time", is(equalTo(1527710578L))),
                property("locktime", is(equalTo(0L))),
                property("vin", hasSize(1)),
                property("vout", hasSize(1)),
                property("amount", is(equalTo(new BigDecimal("0.0")))),
                property("confirmations", is(equalTo(3L))),
                property("generated", is(equalTo(true))),
                property("blockhash", is(equalTo("00000452faee6fa3eb81a94a26d43ee73877ce308bb368b7f1fcdcf278dd3889"))),
                property("blockindex", is(equalTo(0L))),
                property("blocktime", is(equalTo(1527710579L))),
                property("timereceived", is(equalTo(1527710578L))),
                property("details", hasSize(1))
        ));
        
        final TransactionDetail detail = tx.details.get(0);
        assertThat(detail, hasOnly(
                property("account", isEmptyString()),
                property("address", is(equalTo("mmKpfZ1mabUapgamdaZkiyf8EQmZqZz3yY"))),
                property("category", is(equalTo(TransactionDetail.Category.immature))),
                property("amount", is(equalTo(new BigDecimal("0.0"))))
        ));
        
        // Identical to that found in `TestGetBlock.supportsShortTransactionList`
        final TxInput in = tx.vin.get(0);
        assertThat(in, hasOnly(
                property("coinbase", is(equalTo("5b0103"))),
                property("sequence", is(equalTo(4294967295L)))
        ));

        // Identical to that found in `TestGetBlock.supportsShortTransactionList`
        final TxOutput out = tx.vout.get(0);
        assertThat(out, hasOnly(
                property("value", is(equalTo(new BigDecimal("0.0")))),
                property("n", is(equalTo(0L))),
                property("scriptPubKey", is(not(nullValue())))
        ));

        // Identical to that found in `TestGetBlock.supportsShortTransactionList`
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
