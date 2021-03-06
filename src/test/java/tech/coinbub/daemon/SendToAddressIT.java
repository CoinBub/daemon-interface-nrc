package tech.coinbub.daemon;

import com.googlecode.jsonrpc4j.JsonRpcClientException;
import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.coinbub.daemon.nrc.Transaction;
import tech.coinbub.daemon.nrc.TransactionDetail;
import static tech.coinbub.daemon.testutils.BeanMatcher.hasOnly;
import static tech.coinbub.daemon.testutils.BeanPropertyMatcher.property;
import tech.coinbub.daemon.testutils.Dockerized;

@ExtendWith(Dockerized.class)
public class SendToAddressIT {
    public static final String VALID_ADDRESS = "mm1PNXvwUo9PB7eScmoNyTjz93Wwt6qwDM";

    @Test
    public void throwsErrorOnInvalidAddress(final Nrc nrc) {
        final JsonRpcClientException ex = Assertions.assertThrows(JsonRpcClientException.class, () -> {
            nrc.sendtoaddress("abc", BigDecimal.ONE);
        });
        assertThat(ex.getMessage(), is(equalTo("Invalid Noorcoin address")));
    }

    @Test
    public void supportsNoComments(final Nrc nrc) {
        final String txid = nrc.sendtoaddress(VALID_ADDRESS, BigDecimal.ONE);
        final Transaction tx = nrc.gettransaction(txid);
        assertThat(tx.amount, is(equalTo(new BigDecimal("-1.0"))));
    }

    @Test
    public void supportsSourceComment(final Nrc nrc) {
        final String txid = nrc.sendtoaddress(VALID_ADDRESS, BigDecimal.ONE, "test transaction!");
        final Transaction tx = nrc.gettransaction(txid);
        assertThat(tx, hasOnly(
                property("txid", is(not(nullValue()))),
                property("version", is(equalTo(1L))),
                property("time", is(not(nullValue()))),
                property("locktime", is(equalTo(3L))),
                property("vin", hasSize(1)),
                property("vout", hasSize(2)),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(equalTo(new BigDecimal("-0.00010")))),
                property("confirmations", is(equalTo(0L))),
                property("timereceived", is(not(nullValue()))),
                property("details", hasSize(1)),
                property("comment", is(equalTo("test transaction!")))
        ));

        final TransactionDetail detail = tx.details.get(0);
        assertThat(detail, hasOnly(
                property("account", isEmptyString()),
                property("address", is(equalTo(VALID_ADDRESS))),
                property("category", is(equalTo(TransactionDetail.Category.send))),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(equalTo(new BigDecimal("-0.00010"))))
        ));
    }

    @Test
    public void supportsDestinationComment(final Nrc nrc) {
        final String txid = nrc.sendtoaddress(VALID_ADDRESS, BigDecimal.ONE, "test transaction!", "receiving test!");
        final Transaction tx = nrc.gettransaction(txid);
        assertThat(tx, hasOnly(
                property("txid", is(not(nullValue()))),
                property("version", is(equalTo(1L))),
                property("time", is(not(nullValue()))),
                property("locktime", is(equalTo(3L))),
                property("vin", hasSize(1)),
                property("vout", hasSize(2)),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(equalTo(new BigDecimal("-0.00010")))),
                property("confirmations", is(equalTo(0L))),
                property("timereceived", is(not(nullValue()))),
                property("details", hasSize(1)),
                property("comment", is(equalTo("test transaction!"))),
                property("to", is(equalTo("receiving test!")))
        ));
        
        final TransactionDetail detail = tx.details.get(0);
        assertThat(detail, hasOnly(
                property("account", isEmptyString()),
                property("address", is(equalTo(VALID_ADDRESS))),
                property("category", is(equalTo(TransactionDetail.Category.send))),
                property("amount", is(equalTo(new BigDecimal("-1.0")))),
                property("fee", is(equalTo(new BigDecimal("-0.00010"))))
        ));
    }
}
