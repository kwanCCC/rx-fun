package org.dora.rx;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class AdaptersTest {
    static final String MESSAGE = "Adapters";

    @Test
    public void can_accept_completableStage() {
        Adapters.completableStage(CompletableFuture.completedFuture(MESSAGE)).test().assertComplete().assertValue(MESSAGE);
    }

}
