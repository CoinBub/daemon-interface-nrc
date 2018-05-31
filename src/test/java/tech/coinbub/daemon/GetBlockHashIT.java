package tech.coinbub.daemon;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.coinbub.daemon.testutils.Dockerized;

@ExtendWith(Dockerized.class)
public class GetBlockHashIT {
    public static final Long HEIGHT = 9L;

    @Test
    public void canGetBlockHash(final Nrc nrc) {
        final String best = nrc.getblockhash(HEIGHT);
        assertThat(best, is(equalTo("00000ac1af9c8375bd1f60a0a86dfb5e3ba322e0b15d326ef7e0eb4e1be2c4ef")));
    }
}
